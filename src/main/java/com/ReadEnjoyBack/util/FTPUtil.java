package com.ReadEnjoyBack.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 * @Author:HB
 * @Description: FTP服务器工具类
 * @Createdata:Created in  14:51  2018/5/27.
 */
public class FTPUtil {

    private static Logger logger = LoggerFactory.getLogger(FTPUtil.class);
    // 读取配置FTP文件
    private static String ftpIp = PropertiesUtil.getProperty("ftp.server.ip");
    private static String ftpUser = PropertiesUtil.getProperty("ftp.user");
    private static String ftpPass = PropertiesUtil.getProperty("ftp.pass");

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    // 文件上传（返回Boolean 类型 true为成功 false 为失败 对外暴露接口）
    public static boolean uploadFile(List<File> fileList,String remotePath) throws IOException {
        // FTP服务器数据初始化
        FTPUtil ftpUtil = new FTPUtil(ftpIp,21,ftpUser,ftpPass);
        logger.info("开始连接FTP服务器！{},{},{},{}",ftpUtil.user,ftpUtil.port,ftpUtil.pwd,ftpUtil.ip);
        boolean result = ftpUtil.uploadFile(remotePath,fileList);
        logger.info("结束上传，上传结果时：{}",result);

        // 进行文件的格式转换(若是mobi类型和equb类型就不转化)
        String extentName = fileList.get(0).toString().
                substring(fileList.get(0).toString().lastIndexOf(".") + 1);
        if (!StringUtils.equals(extentName,"mobi") && !StringUtils.equals(extentName,"epub")){
            convertFileUtil convertFile = new convertFileUtil(fileList.get(0).toString());
            //调用conver方法开始转换，先执行doc2pdf()将office文件转换为pdf;再执行pdf2swf()将pdf转换为swf;
            convertFile.conver();
        }
        return result;
    }

    // 书籍下载(返回Boolean类型 true为成功 false 为失败 对外暴露接口)
    public static byte[] downloadFile(String fileName) throws IOException {
        logger.info("下载的文件是：{}",fileName );
        FTPUtil ftpUtil = new FTPUtil(ftpIp,21,ftpUser,ftpPass);
        logger.info("开始连接FTP服务器！{},{},{},{}",ftpUtil.user,ftpUtil.port,ftpUtil.pwd,ftpUtil.ip);
        byte[] result = ftpUtil.downloadFile("book",fileName);
        return result;
    }

    // 文件上传（具体实现 不对外暴露）  remotePath 上传的远程文件夹
    private boolean uploadFile(String remotePath,List<File> fileList) throws IOException {
        boolean uploaded = true;
        FileInputStream fis = null;
        //连接Ftp服务器
        if (connectServer(this.ip,this.port,this.user,this.pwd)){
            try {
                //更改工作目录
                ftpClient.changeWorkingDirectory(remotePath);
                // 设置缓存区
                ftpClient.setBufferSize(1024);
                // 设置编码
                ftpClient.setControlEncoding("UTF-8");
                // 设置文件类型为二进制文件类型
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                // 打开本地被动模式
                ftpClient.enterLocalPassiveMode();
                // 进行文件上传
                for (File fileItem: fileList){
                    fis = new FileInputStream(fileItem);
                    // ftp存储文件
                    ftpClient.storeFile(fileItem.getName(),fis);
                }
            } catch (IOException e) {
               logger.error("上传文件异常",e);
                uploaded = false;
            }finally {
                // 关闭输入流
                fis.close();
                // 关闭连接
                ftpClient.disconnect();
            }
        }
        return uploaded;
    }
    // 文件下载（具体实现 不对外暴露）  remotePath 下载的远程文件夹
    private byte[] downloadFile(String remotePath,String fileName) throws IOException {
        BufferedInputStream in = null; // 设置缓存 提升读取速度
        ByteArrayOutputStream out = null;  // 字节数组输出流
        byte[] fileByte = new byte[1024];
        InputStream fis = null;
        // 下载文件的远程地址
        String remoteFilePath = remotePath+"/"+fileName;
        // 连接FTP服务器
        if (connectServer(this.ip,this.port,this.user,this.pwd)){
            try {
                // 更改工作目录
                ftpClient.changeWorkingDirectory(remoteFilePath);
                // 设置缓存区
                 ftpClient.setBufferSize(1024);
                // 设置编码
                 ftpClient.setControlEncoding("UTF-8");
                // 设置文件类型为二进制文件类型
                 ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                // 打开本地被动模式
                ftpClient.enterLocalPassiveMode();
                // 文件下载(得到文件的输入流)
                fis =  ftpClient.retrieveFileStream(remoteFilePath);
                // 存到缓存区
                in = new BufferedInputStream(fis);
                out = new ByteArrayOutputStream(1024);  //创建一个大小为1024字节的缓冲区。
                int size = 0;
                while ((size = in.read(fileByte)) != -1){
                    out.write(fileByte,0,size);
                }
                 fileByte = out.toByteArray();  // 输出流转换为字节数组
            } catch (IOException e) {
                logger.error("下载文件异常",e);
            }finally {
                // 关闭各种流
                 fis.close();
                 out.close();
                 in.close();
                // 关闭连接
                ftpClient.disconnect();
            }

        }
        return fileByte;
    }
    // 连接FTP服务器
    private boolean connectServer(String ip,int port,String user,String pwd){
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user,pwd);
            logger.info("登录是否成功:{}",isSuccess);
        } catch (IOException e) {
            logger.error("连接FTP服务器异常",e);
        }
        return isSuccess;
    }

    public static String getFtpIp() {
        return ftpIp;
    }

    public static void setFtpIp(String ftpIp) {
        FTPUtil.ftpIp = ftpIp;
    }

    public static String getFtpUser() {
        return ftpUser;
    }

    public static void setFtpUser(String ftpUser) {
        FTPUtil.ftpUser = ftpUser;
    }

    public static String getFtpPass() {
        return ftpPass;
    }

    public static void setFtpPass(String ftpPass) {
        FTPUtil.ftpPass = ftpPass;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
