package com.ReadEnjoyBack.service.Impl;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.dao.NavigationMapper;
import com.ReadEnjoyBack.pojo.Navigation;
import com.ReadEnjoyBack.service.INavigationService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author:HB
 * @Description: 导航管理实现
 * @Createdata:Created in  10:25  2018/5/24.
 */
@Service("iNavigationService")
public class NavigationServiceImpl implements INavigationService {
    /*对类CategoryServiceImpl进行日志打印*/
    private Logger logger = LoggerFactory.getLogger(NavigationServiceImpl.class);
    // 注入navigationMapper
    @Autowired
    private NavigationMapper navigationMapper;

    /*
     * @Author:HB
     * @Description: 增加导航项
     * @Data:10:50 2018/5/24
     * @param parentId
    * @param navName
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
     */
    @Override
    public ServerResponse<String> addNavigation(String navName,String url, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(navName)){
            return ServerResponse.createByErrorMessage("添加导航错误");
        }
        Navigation navigation = new Navigation();
        navigation.setName(navName);
        navigation.setUrl(url);
        navigation.setParentId(parentId);
        navigation.setStatus(true);  // 状态为可用
        int resultCount = navigationMapper.insert(navigation);
        if (resultCount > 0){
            return  ServerResponse.createBySuccessMessage("添加导航成功！");
        }
        return ServerResponse.createByErrorMessage("添加导航失败！");
    }
      /*
       * @Author:HB
       * @Description:修改导航名字
       * @Data:14:17 2018/5/24
      * @param navId
      * @param navName
       returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
       */
    @Override
    public ServerResponse<String> updateNavUsername(Integer navId, String navName,String url) {
        if (navId == null || StringUtils.isBlank(navName) || StringUtils.isBlank(url)){
            return ServerResponse.createByErrorMessage("修改导航出现错误!");
        }
        Navigation navigation = new Navigation();
        navigation.setId(navId);
        navigation.setUrl(url);
        navigation.setName(navName);
        int resultCount = navigationMapper.updateByPrimaryKeySelective(navigation);
        if (resultCount > 0){
            return ServerResponse.createBySuccessMessage("修改导航成功！");
        }
        return ServerResponse.createByErrorMessage("修改导航失败！");
    }
     /*
      * @Author:HB
      * @Description: 获取当前导航的子节点(平级)
      * @Data:14:41 2018/5/24
      * @param navId
      returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
      */
    @Override
    public ServerResponse<List<Navigation>> getChildrenParallelNavigation(Integer navId) {
        List<Navigation> navigationList = navigationMapper.selectNavigationChildrenByParentId(navId);
        if (CollectionUtils.isEmpty(navigationList)){
            return ServerResponse.createByErrorMessage("未找到当前导航的子分类");
        }
        return ServerResponse.createBySuccesse(navigationList);

    }
     /*
      * @Author:HB
      * @Description: 获取当前导航的子节点(深度查询)
      * @Data:15:13 2018/5/24
     * @param navId
      returns:com.ReadEnjoyBack.common.ServerResponse
      */
    @Override
    public ServerResponse<List<Navigation>> getDeepChildrenParallelNavigation(Integer navId) {
        // 对categorySet 进行初始化
        Set<Navigation> navigationSet = Sets.newHashSet();
        findChildDeep(navigationSet,navId);
        // 对categoryIdList 进行初始化
        List<Navigation> navigationList = Lists.newArrayList();
        if (navId != null){
           for (Navigation navigationItem: navigationSet){
               navigationList.add(navigationItem);
           }
        }
        return ServerResponse.createBySuccesse(navigationList);
    }

    //递归算法,算出子节点
    private Set<Navigation> findChildDeep(Set<Navigation> navigationSet ,Integer navId){
        Navigation navigation = navigationMapper.selectByPrimaryKey(navId);
        if(navigation != null){
            navigationSet.add(navigation);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<Navigation> navigationList = navigationMapper.selectNavigationChildrenByParentId(navId);
        for(Navigation navigationItem : navigationList){
            findChildDeep(navigationSet,navigationItem.getId());
        }
        return navigationSet;
    }
}
