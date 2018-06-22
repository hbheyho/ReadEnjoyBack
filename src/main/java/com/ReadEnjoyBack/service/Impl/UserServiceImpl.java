package com.ReadEnjoyBack.service.Impl;

import com.ReadEnjoyBack.common.Const;
import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.common.TokenCache;
import com.ReadEnjoyBack.dao.UserMapper;
import com.ReadEnjoyBack.pojo.User;
import com.ReadEnjoyBack.pojo.UserCollection;
import com.ReadEnjoyBack.service.IUserService;
import com.ReadEnjoyBack.util.DateTimeUtil;
import com.ReadEnjoyBack.util.MD5Util;
import com.ReadEnjoyBack.util.PropertiesUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Author:HB
 * @Description: 用户模块实现
 * @Createdata:Created in  15:44  2018/5/17.
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {
    /*注入usermapper*/
    @Autowired
    private UserMapper userMapper;

    /*------------------------------------- 前台 ---------------------------------------*/
    /*
     * @Author:HB
     * @Description:  用户登录
     * @Data:17:33 2018/5/17
     * @param username
     * @param password
     returns:com.ReadEnjoyBack.common.ServerResponse<com.ReadEnjoyBack.pojo.User>
     */
    @Override
    public ServerResponse<User> login(String email, String password) {
        ServerResponse validResponse = checkValid(email,Const.EMAIL);
        if (validResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("当前邮箱不存在");
        }
        String MD5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(email,MD5Password);
        /*已经判断了邮箱 判断密码就行*/
        if (user == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }
        if (user.getStatus() == 0){
            return ServerResponse.createByErrorMessage("该账户无效");
        }
        /*用户密码置空 不返回*/
        user.setPassword(StringUtils.EMPTY);
        user.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","http://image.readenjoy.com/"));
        return ServerResponse.createBySuccesse("登录成功",user);
    }
    /*
     * @Author:HB
     * @Description: 用户注册
     * @Data:19:42 2018/5/17
     * @param user
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
     */
    @Override
    public ServerResponse<String> register(User user) {
        /*检查用户名是否被注册了*/
        ServerResponse validResponse = checkValid(user.getUsername(), Const.USERNAME);
        if (!validResponse.isSuccess()){
            return validResponse;
        }
        validResponse = checkValid(user.getEmail(), Const.EMAIL);
        if (!validResponse.isSuccess()){
            return validResponse;
        }
        /*设置用户权限*/
        user.setRole(Const.Role.ROLE_CUSTOMER);
        user.setStatus(Const.USERSTATUS);
        user.setHeadpic("default.jpg");
        /*md5加密*/
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        /*插入用户*/
        int resultCount = userMapper.insert(user);
        if (resultCount == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }
    /*
    * @Author:HB
    * @Description: 用户名和email的实时验证
    * @Data:22:46 2018/5/17
    * @param str
   * @param type
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
    */
    public  ServerResponse<String>  checkValid(String str,String type){
        if (StringUtils.isNotBlank(type)){  // 类型不为空是才进行相应检验
            if (Const.USERNAME.equals(type)){  //用户检验
                int resultCount = userMapper.checkUsername(str);
                if (resultCount > 0){
                    return ServerResponse.createByErrorMessage("该用户名已存在");
                }
            }
            if (Const.EMAIL.equals(type)){  //用户检验
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0){
                    return ServerResponse.createByErrorMessage("该邮箱已存在");
                }
            }
        }else{
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("检验成功");
    }
    /*
     * @Author:HB
     * @Description: 通过email得到用户密保问题
     * @Data:8:54 2018/5/21
     * @param username
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
    */
    public ServerResponse<String> getQuestionByEmail(String email){
        /*检验用户名是否存在*/
        ServerResponse validResponse = this.checkValid(email,Const.EMAIL);
        if (validResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("当前邮箱不存在");
        }
        /*查找对应用户名答案*/
        String question = userMapper.getQuestionByEmail(email);
        if (StringUtils.isNotBlank(question)){
            return  ServerResponse.createBySuccesse(question);
        }
        return ServerResponse.createByErrorMessage("您还未设置相应的密保问题");
    }
     /*
      * @Author:HB
      * @Description: 根据密保问题获取密保答案
      * @Data:9:36 2018/5/21
      * @param username
     * @param question
     * @param answer
      returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
      */
    @Override
    public ServerResponse<String> checkAnswerByQuestion(String email, String question, String answer) {
        int resultConunt = userMapper.checkAnswer(email,question,answer);
        if (resultConunt > 0){  //验证正确
            String  forgetToken = UUID.randomUUID().toString();  // 生成一串token值
            // 把token存在缓存中
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + email,forgetToken);
            return ServerResponse.createBySuccesse(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题答案错误");
    }
    /*
     * @Author:HB
     * @Description: 根据缓存中的forgetToken修改用户名密码
     * @Data:10:06 2018/5/21
     * @param username
    * @param passwordNew
    * @param forgetToken
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
     */
    @Override
    public ServerResponse<String> resetPasswordByForgetToken(String email, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken)) {
            return ServerResponse.createByErrorMessage("参数错误，Token需要传递");
        }
        ServerResponse validResponse = this.checkValid(email, Const.EMAIL);
        if (validResponse.isSuccess()) {
            //用户名不存在
            return ServerResponse.createByErrorMessage("邮箱不存在");
        }
       /*获取用户缓存的Token值*/
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + email);
        if (StringUtils.isBlank(token)) {
            return ServerResponse.createByErrorMessage("Token无效或过期");
        }
        /*用forgetToken 与缓存中的toke比较*/
        if (StringUtils.equals(forgetToken,token)){
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            int rowCount = userMapper.updatePasswordByEmail(email, md5Password);
            if (rowCount > 0) {
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }
        }else{
                return ServerResponse.createByErrorMessage("Token无效，请重新获取");
        }
                return ServerResponse.createByErrorMessage("修改密码失败");
    }
    /*
     * @Author:HB
     * @Description: 修改用户密码（用户登录之后）
     * @Data:10:32 2018/5/21
     * @param passwordOld
    * @param passwordNew
    * @param user
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
     */
    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
        int resultCount = userMapper.checkUserOlaPassword(MD5Util.MD5EncodeUtf8(passwordOld),user.getId());
        if (resultCount == 0){
            return  ServerResponse.createByErrorMessage("旧密码错误");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0){
            return  ServerResponse.createBySuccessMessage("密码更新成功");
        }
        return ServerResponse.createByErrorMessage("密码更新失败");
    }
    /*
     * @Author:HB
     * @Description: 登录之间进行用户更新
     * @Data:19:33 2018/5/23
     * @param user
     returns:com.ReadEnjoyBack.common.ServerResponse<com.ReadEnjoyBack.pojo.User>
     */
    @Override
    public ServerResponse<User> updateInformation(User user) {
        // username不能被更新
        //email 也要被校验,校验是否已经存在，并且存在的email相同的话，不能是当前用户的
        int resultCount = userMapper.checkUsernameByUserId(user.getUsername(),user.getId());
        if (resultCount > 0){
            return  ServerResponse.createByErrorMessage("用户名已经存在");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        /*需要更新的信息*/
        updateUser.setUsername(user.getUsername());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());
        updateUser.setGender(user.getGender());
        updateUser.setSigns(user.getSigns());

        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount > 0){
            return ServerResponse.createBySuccesse("更新用户信息成功",updateUser);
        }
        return ServerResponse.createByErrorMessage("更新用户信息失败");
    }
    /*
      * @Author:HB
      * @Description: 获取登录用户信息
      * @Data:16:08 2018/5/21
      * @param session
      returns:com.ReadEnjoyBack.common.ServerResponse<com.ReadEnjoyBack.pojo.User>
     */
    public ServerResponse<User> getInformation(Integer userId){
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null){
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }
        //密码置空
        user.setPassword(StringUtils.EMPTY);
        user.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","http://image.readenjoy.com/"));
        return ServerResponse.createBySuccesse(user);
    }
    /*------------------------------------- 后台 ---------------------------------------*/
    /*
     * @Author:HB
     * @Description: 检查是否有管理员权限
     * @Data:11:08 2018/5/24
     * @param user
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
     */
    @Override
    public ServerResponse<String> checkAdminRole(User user) {
        if (user != null && user.getRole().intValue() == Const.Role.ROLE_ADMIN){
            return ServerResponse.createBySuccess();
        }else{
           return ServerResponse.createByError();
        }
    }
    /*
     * @Author:HB
     * @Description: 得到用户列表
     * @Data:21:03 2018/6/14
     * @param pageNum pageSize
     returns:
    */
    @Override
    public ServerResponse<PageInfo> getUserList(int pageNum, int pageSize) {
        // 开始分页
        PageHelper.startPage(pageNum,pageSize);
        // 填充自己的sal逻辑
        List<User> userList = userMapper.selectUserList();
        for(User  userItem: userList ){
            userItem.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","http://image.readenjoy.com/"));
            userItem.setCTime(DateTimeUtil.dateToStr(userItem.getCreateTime()));
            userItem.setUTime(DateTimeUtil.dateToStr(userItem.getUpdateTime()));
            userItem.setPassword(StringUtils.EMPTY);
        }
        // 分页收尾
        PageInfo pageInfo = new PageInfo(userList);
        return ServerResponse.createBySuccesse(pageInfo);
    }
    /*
     * @Author:HB
     * @Description:增加用户
     * @Data:21:18 2018/6/17
     * @param User
     returns:
    */
    @Override
    public ServerResponse<String> addUser(User user) {
               /*检查用户名是否被注册了*/
        ServerResponse validResponse = checkValid(user.getUsername(), Const.USERNAME);
        if (!validResponse.isSuccess()){
            return validResponse;
        }
        validResponse = checkValid(user.getEmail(), Const.EMAIL);
        if (!validResponse.isSuccess()){
            return validResponse;
        }
        /*设置用户权限*/
        user.setRole(Const.Role.ROLE_CUSTOMER);
        /*设置用户状态*/
        user.setStatus(Const.USERSTATUS);
        /*md5加密*/
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        /*插入用户*/
        int resultCount = userMapper.insert(user);
        if (resultCount == 0){
            return ServerResponse.createByErrorMessage("增加失败");
        }
        return ServerResponse.createBySuccessMessage("增加成功");
    }
    /*
     * @Author:HB
     * @Description: 删除用户
     * @Data:21:24 2018/6/17
     * @param userId
     returns: string
    */
    @Override
    public ServerResponse<String> deleteUser(Integer userId) {
        if (userId == null){
            return ServerResponse.createByErrorMessage("你还没选择你要删除的用户哦");
        }
        int resultCount = userMapper.deleteByPrimaryKey(userId);
        if (resultCount == 0){
            return ServerResponse.createByErrorMessage("删除用户失败");
        }
        return ServerResponse.createBySuccessMessage("删除用户成功");
    }
    /*
     * @Author:HB
     * @Description: 修改用户状态
     * @Data:21:34 2018/6/17
     * @param userId
     returns:
    */
    @Override
    public ServerResponse<String> modifyUserStatus(Integer userId) {
        if (userId == null){
            return ServerResponse.createByErrorMessage("你还没选择你要删除的用户哦");
        }
        Integer userStatus = userMapper.selectUserStatus(userId);
        if (userStatus == null) {
            return ServerResponse.createByErrorMessage("没找到相应的用户哦");
        }
        if (Const.USERSTATUS == userStatus ){  // 用户有效 1
            User user = new User();
            user.setId(userId);
            user.setStatus(0);
            int resultCount = userMapper.updateByPrimaryKeySelective(user);
            if (resultCount == 0){
                return ServerResponse.createByErrorMessage("修改用户状态失败");
            }
            return ServerResponse.createBySuccessMessage("修改用户状态成功");
        }else {  // 用户无效
            User user = new User();
            user.setId(userId);
            user.setStatus(1);
            int resultCount = userMapper.updateByPrimaryKeySelective(user);
            if (resultCount == 0){
                return ServerResponse.createByErrorMessage("修改用户状态失败");
            }
            return  ServerResponse.createBySuccessMessage("修改用户状态成功");
        }
    }
}
