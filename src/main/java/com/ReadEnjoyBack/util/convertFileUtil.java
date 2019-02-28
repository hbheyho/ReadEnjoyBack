package com.ReadEnjoyBack.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author:HB
 * @Description:
 * @Createdata:Created in  22:24  2018/10/20.
 */
public class convertFileUtil {
    private static final int environment = 1;// 环境 1：Windows 2：Linux
    private String fileString;// (只涉及PDF2swf路径问题)
    private String outputPath = "";// 输出路径 ，如果不设置就输出在默认的位置
    private String fileName;  // 文件名
    private File pdfFile; // pdf名字
    private File swfFile; // swf文件名
    private File docFile; // office 文件名

    // 文件上传日志
    private Logger logger = LoggerFactory.getLogger(convertFileUtil.class);
    public convertFileUtil(String fileString) {
        // 对传过来的文件路径初始化
        ini(fileString);
    }
    /*
    * @Author:HB
    * @Description: 初始化文件路径
    * @Data:22:41 2018/10/20
    * @param null
    returns:
    */
    private void ini(String fileString) {
        this.fileString = fileString;
        fileName = fileString.substring(0, fileString.lastIndexOf(".")); // 得到文件名
        docFile = new File(fileString);
        pdfFile = new File(fileName+ ".pdf");
        swfFile = new File(fileName+ ".swf");
    }
    /*
     * @Author:HB
     * @Description: 重新设置filename
     * @Data:22:43 2018/10/20
     * @param null
     returns:
    */
    public void setFile(String fileString) {
        ini(fileString);
    }

    /*
     * @Author:HB
     * @Description: 转化为pdf
     * @Data:22:43 2018/10/20
     * @param null
     returns:
    */
    private void doc2pdf() throws Exception {
        if (docFile.exists()) {
            // 还不是paf文件 转换为pdf
            if (!pdfFile.exists()) {
                // openoffice 连接 端口为 8100
                OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
                try {
                    connection.connect();
                    DocumentConverter converter = new OpenOfficeDocumentConverter(
                            connection);
                    converter.convert(docFile, pdfFile); 	// docfile 转换为 pdffile
                    // 关闭连接
                    connection.disconnect();
                    System.out.println("****pdf转换成功，PDF输出"+ pdfFile.getPath() + "****");
                } catch (java.net.ConnectException e) {
                    e.printStackTrace();
                    System.out.println("****swf转换器异常，openoffice 服务未启动！****");
                    throw e;
                } catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
                    e.printStackTrace();
                    System.out.println("****swf转换器异常，读取转换文件 失败****");
                    throw e;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            } else {
                System.out.println("****已经转换为pdf，不需要再进行转化 ****");
            }
        } else {
            System.out.println("****swf转换器异常，需要转换的文档不存在， 无法转换****");
        }
    }
   /*
    * @Author:HB
    * @Description: 转化成swf
    * @Data:23:03 2018/10/20
    * @param null
    returns:
   */
    private void pdf2swf() throws Exception {
        Runtime r = Runtime.getRuntime(); 	// 得到当前的运行环境
        // swf格式不存在 进行转化
        if (!swfFile.exists()) {
            if (pdfFile.exists()) {
                if (environment == 1) {// windows环境处理
                    try {
                        System.out.println("我开始调用swf了");
                        Process p = r.exec("E:/SWFTools/pdf2swf.exe "+ pdfFile.getPath() + " -o "+ swfFile.getPath() + " -T 9");
                        System.out.println("我调用成功了哦");
                        System.out.print(loadStream(p.getInputStream()));
                        System.err.print(loadStream(p.getErrorStream()));
                        System.out.print(loadStream(p.getInputStream()));
                        System.err.println("****swf转换成功，文件输出： "+swfFile.getPath() + "****");
                        if (pdfFile.exists()){
                            pdfFile.delete();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw e;
                    }
                } else if (environment == 2) {// linux环境处理
                    try {
                        Process p = r.exec("pdf2swf" + pdfFile.getPath()+ " -o " + swfFile.getPath() + " -T 9");
                        System.out.print(loadStream(p.getInputStream()));
                        System.err.print(loadStream(p.getErrorStream()));
                        System.err.println("****swf转换成功，文件输出： "+ swfFile.getPath() + "****");
                        if (pdfFile.exists()) {
                            pdfFile.delete();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw e;
                    }
                }
            } else {
                System.out.println("****pdf不存在,无法转换****");
            }
        } else {
            System.out.println("****swf已经存在不需要转换****");
        }
    }
    static String loadStream(InputStream in) throws IOException {
        int ptr = 0;
        in = new BufferedInputStream(in);
        StringBuffer buffer = new StringBuffer();
        while ((ptr = in.read()) != -1) {
            buffer.append((char) ptr);
        }
        return buffer.toString();
    }

   /*
    * @Author:HB
    * @Description: 转化主方法
    * @Data:23:04 2018/10/20
    * @param null
    returns:
   */
    public boolean conver() {
        if (swfFile.exists()) {
            System.out.println("****swf转换器开始工作，该文件已经转换为 swf****");
            return true;
        }
        if (environment == 1) {
            System.out.println("****swf转换器开始工作，当前设置运行环境 windows****");
        } else {
            System.out.println("****swf转换器开始工作，当前设置运行环境 linux****");
        }
        // 还不是swf格式 进行转换
        try {
            doc2pdf();
            pdf2swf();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("文件存在吗？"+swfFile);
        System.out.println("把文件上传到ftp服务器:" );
        try {
            FTPUtil.uploadFile(Lists.<File>newArrayList(swfFile),"swf");
            // 上传成功之后删除
            swfFile.delete();
        }catch (IOException e){
            logger.error("文件上传失败！",e);
        }
        if (swfFile.exists()) {
            System.out.println("存在");
            return true;
        } else {
            System.out.println("不存在");
            return false;
        }
    }
    /*
     * @Author:HB
     * @Description: 返回文件路径
     * @Data:23:05 2018/10/20
     * @param null
     returns:
    */
    /*public String getswfPath(){
        if (this.swfFile.exists()){
            String tempString = swfFile.getPath();
            tempString = tempString.replaceAll("\\\\", "/");
            System.out.println("最后文件路径为"+tempString);
            return tempString;
        } else {
            return "文件不存在";
        }
    }*/

   /*
    * @Author:HB
    * @Description: 设置输出路径
    * @Data:23:05 2018/10/20
    * @param null
    returns:
   */
   /* public void setOutputPath(String outputPath){
        this.outputPath = outputPath;
        if (!outputPath.equals("")) {
            String realName = fileName.substring(fileName.lastIndexOf("/"),
                    fileName.lastIndexOf("."));
            if (outputPath.charAt(outputPath.length()) == '/') {
                swfFile = new File(outputPath + realName + ".swf");
            } else {
                swfFile = new File(outputPath + realName + ".swf");
            }
        }
    }*/
}
