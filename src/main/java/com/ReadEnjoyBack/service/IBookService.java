package com.ReadEnjoyBack.service;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.Book;
import com.ReadEnjoyBack.vo.BookDetailVo;
import com.ReadEnjoyBack.vo.BookListVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author:HB
 * @Description: 书籍操作接口
 * @Createdata:Created in  8:57  2018/5/29.
 */
public interface IBookService {
    /*---------------前台--------------------*/
    /*获取书籍信息 根据下载的书籍次数*/
    ServerResponse<List<BookListVo>> getBookByDownNumber();
    /*数据搜索 （根据书名/ 书籍作者/ 书籍出版社*/
    ServerResponse<List<BookListVo>> searchBookByBookNameAndBookWriterAndPublish(String conditionName);
    /*书籍ISBN号检查*/
    ServerResponse<String> checkBookIsbn(String bookIsbn);
    /*分类信息搜索通过categoryId*/
    ServerResponse<PageInfo> getBookListByCategoryId(int categoryId,int pageNum,int pageSize);
    /*---------------后台--------------------*/
    /*新增或者更新书籍信息*/
    ServerResponse<String> saveOrUpdateBook(Book book);
    /*获取书籍详情信息*/
    ServerResponse<BookDetailVo> getBookDetail(String bookISBN);
    /*书籍列表开发*/
    ServerResponse<PageInfo> getBookList(int pageNum, int pageSize);
    /*书籍搜索*/
    ServerResponse<PageInfo> searchBook(int pageNum, int pageSize ,String bookName, String bookWriter,String publishName,String categoryName);
    /*删除书籍*/
    ServerResponse<String> deleteBook(Integer bookId);
    /*修改书籍状态*/
    ServerResponse<String> modifyBookStatus(Integer bookId);


}
