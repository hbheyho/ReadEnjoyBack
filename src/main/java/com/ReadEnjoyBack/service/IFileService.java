package com.ReadEnjoyBack.service;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @Author:HB
 * @Description: 书籍上传Service
 * @Createdata:Created in  10:57  2018/6/5.
 */
public interface IFileService {
    // 书籍上传操作
    ServerResponse upload(MultipartFile file, String path, String userName, String bookISBN);
    // 书籍下载操作
    byte[] download(String bookName,String username,int versionId) throws IOException;
    // 用户头像上传
    ServerResponse uploadImg(MultipartFile file, String path, String userName);
    // 书籍的封面上传
    ServerResponse uploadBookImg(MultipartFile file, String path, Book book);
}
