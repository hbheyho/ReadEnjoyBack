package com.ReadEnjoyBack.service.Impl;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.dao.*;
import com.ReadEnjoyBack.pojo.*;
import com.ReadEnjoyBack.service.IFileService;
import com.ReadEnjoyBack.util.FTPUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.UUID;

/**
 * @Author:HB
 * @Description: 书籍上传Service实现
 * @Createdata:Created in  10:57  2018/6/5.
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService {
    @Autowired
    private BookVersionMapper bookVersionMapper;
    @Autowired
    private UserDownLoadMapper userDownLoadMapper;
    @Autowired
    private UserUploadMapper userUploadMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookMapper bookMapper;
    // 文件上传日志
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    /*
     * @Author:HB
     * @Description: 文件上传操作
     * @Data:11:04 2018/6/5
     * @param MultipartFile path
     returns:
    */
    @Override
    public ServerResponse upload(MultipartFile file, String path,String userName,String bookISBN) {
        // 得到文件上传的原始文件名
        String  fileName = file.getOriginalFilename();
        // 得到上传文件的大小/ 进行大小控制（进行转换存储）
        String fileSize = "";
        long fileSizeByte = file.getSize();
        float fileSizeKB = fileSizeByte / 1024;
        float fileSizeMB = fileSizeKB / 1024;
        if (fileSizeMB>1 && fileSizeMB<10){
            DecimalFormat fNum = new DecimalFormat("##0.00");
            fileSize = fNum.format(fileSizeMB) + "M";
        }else if (fileSizeMB > 10){
             return ServerResponse.createByErrorMessage("你上传的文件有点大哦");
        }else {
             fileSize = fileSizeKB + "KB";
        }
        // 得到文件扩展名
        String  fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 进行文件的类型控制
        if (!StringUtils.equals(fileExtensionName,"txt") && !StringUtils.equals(fileExtensionName,"pdf") &&
        !StringUtils.equals(fileExtensionName,"mobi") && !StringUtils.equals(fileExtensionName,"epub") &&
        !StringUtils.equals(fileExtensionName,"docx") ){
            return ServerResponse.createByErrorMessage("你上传的好像不是文档哦！");
        }
        // 上传上传文件名
        String  uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("开始上传文件,上传文件名是：{}，上传路径是：{}，新文件名：{}",fileName,path,uploadFileName);
         // 新建一个文件夹
        File fileDir = new File(path);
        if (!fileDir.exists()){
            // 不存在则创建
            // 赋予文件夹可写权限
            fileDir.setWritable(true);
            // 创建文件夹
            fileDir.mkdirs();
        }
        // 创建文件
        File targetFile  = new File(path,uploadFileName);
        // 文件上传
        try {
            // 文件上传成功
            file.transferTo(targetFile);
            // 进行版本信息存入
            BookVersion bookVersion = new BookVersion();
            bookVersion.setBookIsbn(bookISBN);
            bookVersion.setBookOriginname(fileName);
            bookVersion.setUploadUser(userName);
            bookVersion.setBookSize(fileSize);
            bookVersion.setBookUploadname(uploadFileName);
            bookVersion.setDownNumber(0);
            bookVersion.setCollectNumber(0);
            bookVersionMapper.insert(bookVersion);
            // 得到上传上传版本的版本ID
            int bookVersionId = bookVersionMapper.getBookVersionId(uploadFileName);
            // 进行用户上传信息录入
            UserUpload userUpload = new UserUpload();
            userUpload.setUserName(userName);
            userUpload.setBookVersionId(bookVersionId);
            userUploadMapper.insert(userUpload);
            // 上传文件到FTP服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile),"book");
        } catch (IOException e) {
            logger.error("文件上传失败！",e);
            return null;
        }
        // 删除upload中的上传文件
        targetFile.delete();
        return ServerResponse.createBySuccessMessage("上传成功！");
    }
    /*
     * @Author:HB
     * @Description: 书籍下载操作
     * @Data:17:19 2018/6/5
     * @param bookName
     returns:
    */
    @Override
    public byte[] download(String bookName,String userName , int versionId) throws IOException {
        // 对下载结果进行存储
        UserDownLoad userDownLoad = new UserDownLoad();
        userDownLoad.setBookVersionId(versionId);
        userDownLoad.setUserName(userName);
        int resultCount = userDownLoadMapper.insert(userDownLoad);
        if (resultCount == 0){
           logger.error("存储用户的下载信息出错！");
        }
        // 书籍版本的下载次数+1
        resultCount = bookVersionMapper.updateDownNumber(versionId);
        if (resultCount == 0){
            logger.error("更新版本的下载量信息出错！");
        }
        // 返回下载的结果
        byte[] downResult = FTPUtil.downloadFile(bookName);
        if (downResult.length == 0){
           logger.error("下载错误！");
        }
        return downResult;
    }
    /*
     * @Author:HB
     * @Description: 用户头像上传
     * @Data:16:11 2018/6/9
     * @param file path userName
     returns:
    */
    @Override
    public ServerResponse uploadImg(MultipartFile file, String path, String userName) {
        // 得到文件上传的原始文件名
        String  fileName = file.getOriginalFilename();
        // 得到上传文件的大小/ 进行大小控制（进行转换存储）
        String fileSize = "";
        long fileSizeByte = file.getSize();
        float fileSizeKB = fileSizeByte / 1024;
        float fileSizeMB = fileSizeKB / 1024;
        System.out.println("文件大小为：" + fileSizeMB);
        if (fileSizeMB>1 && fileSizeMB<5){
            DecimalFormat fNum = new DecimalFormat("##0.00");
            fileSize = fNum.format(fileSizeMB) + "M";
        }else if (fileSizeMB > 5){
            return ServerResponse.createByErrorMessage("你上传的图片有点大哦");
        }else {
            fileSize = fileSizeKB + "KB";
        }
        // 得到文件扩展名
        String  fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 进行文件的类型控制
        if (!StringUtils.equals(fileExtensionName,"jpg") && !StringUtils.equals(fileExtensionName,"jpeg") &&
                !StringUtils.equals(fileExtensionName,"png") && !StringUtils.equals(fileExtensionName,"bmp") ){
            return ServerResponse.createByErrorMessage("你上传的好像不是图片哦");
        }
        // 上传上传文件名
        String  uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("开始上传文件,上传文件名是：{}，上传路径是：{}，新文件名：{}",fileName,path,uploadFileName);
        // 新建一个文件夹
        File fileDir = new File(path);
        if (!fileDir.exists()){
            // 不存在则创建
            // 赋予文件夹可写权限
            fileDir.setWritable(true);
            // 创建文件夹
            fileDir.mkdirs();
        }
        // 创建文件
        File targetFile  = new File(path,uploadFileName);
        // 文件上传
        try {
            // 文件上传成功
            file.transferTo(targetFile);
            // 更新当前用户头像信息
            int resultCount = userMapper.updateUserHeadPicByUserName(uploadFileName, userName);
            if (resultCount == 0){
                return ServerResponse.createByErrorMessage("更新用户头像信息失败");
            }
            // 上传文件到FTP服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile),"img");
            // 删除upload中的文件
            targetFile.delete();
        } catch (IOException e) {
            logger.error("图片上传失败！",e);
            return ServerResponse.createByErrorMessage("图片上传失败");
        }
        return ServerResponse.createBySuccessMessage("上传成功");
    }
    /*
     * @Author:HB
     * @Description: 书籍封面上传
     * @Data:21:48 2018/6/18
     * @param file path book
     returns:
    */
    @Override
    public ServerResponse uploadBookImg(MultipartFile file, String path, Book book) {
        // 得到文件上传的原始文件名
        String  fileName = file.getOriginalFilename();
        // 得到上传文件的大小/ 进行大小控制（进行转换存储）
        String fileSize = "";
        long fileSizeByte = file.getSize();
        float fileSizeKB = fileSizeByte / 1024;
        float fileSizeMB = fileSizeKB / 1024;
        System.out.println("文件大小为：" + fileSizeMB);
        if (fileSizeMB>1 && fileSizeMB< 5){
            DecimalFormat fNum = new DecimalFormat("##0.00");
            fileSize = fNum.format(fileSizeMB) + "M";
        }else if (fileSizeMB > 5){
            return ServerResponse.createByErrorMessage("你上传的图片有点大哦");
        }else {
            fileSize = fileSizeKB + "KB";
        }
        // 得到文件扩展名
        String  fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 进行文件的类型控制
        if (!StringUtils.equals(fileExtensionName,"jpg") && !StringUtils.equals(fileExtensionName,"jpeg") &&
                !StringUtils.equals(fileExtensionName,"png") && !StringUtils.equals(fileExtensionName,"bmp") ){
            return ServerResponse.createByErrorMessage("你上传的好像不是图片哦");
        }
        // 上传上传文件名
        String  uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("开始上传文件,上传文件名是：{}，上传路径是：{}，新文件名：{}",fileName,path,uploadFileName);
        // 新建一个文件夹
        File fileDir = new File(path);
        if (!fileDir.exists()){
            // 不存在则创建
            // 赋予文件夹可写权限
            fileDir.setWritable(true);
            // 创建文件夹
            fileDir.mkdirs();
        }
        // 创建文件
        File targetFile  = new File(path,uploadFileName);
        // 文件上传
        try {
            // 文件上传成功
            file.transferTo(targetFile);
            // 更新书籍信息
            book.setBookImage(uploadFileName);
            int resultCount = bookMapper.insert(book);
            if (resultCount == 0){
                return ServerResponse.createByErrorMessage("上传书籍封面信息失败");
            }
            // 上传文件到FTP服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile),"img");
            // 删除upload中的文件
            targetFile.delete();
        } catch (IOException e) {
            logger.error("图片上传失败",e);
            return ServerResponse.createByErrorMessage("图片上传失败");
        }
        return ServerResponse.createBySuccessMessage("上传成功");
    }
}
