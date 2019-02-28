package com.ReadEnjoyBack.dao;

import com.ReadEnjoyBack.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer bookId);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bookId);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
    /*--------------------------------------------前台------------------------------------------------------------*/
    /*得到书籍列表通过书籍下载量*/
    List<Book> getBookListByDownNumber();
    /*根据书名/作者/出版社/对书籍进行查询(前台)*/
    List<Book> selectBookByBookNameOrBookWriterOrPublicNameFront(@Param("conditionName") String conditionName);
    /*通过书籍ISBN获取书籍详细信息*/
    Book getBookDetail(@Param("bookISBN") String BookISBN);
    /*检查书籍ISBN是否存在*/
    int checkBookIsbn(@Param("bookIsbn") String bookIsbn);
    /*通过分类得到相应书籍列表*/
    List<Book> selectBookListByCategoryId(@Param("parentId") int parentId,@Param("categoryId") int categoryId );
/*--------------------------------------------后台------------------------------------------------------------*/
    /*对书籍进行列表查询*/
    List<Book> selectList();
    /*根据书名/作者/出版社/对书籍进行查询（后台）*/
    List<Book> selectBookByBookNameOrBookWriterOrPublicNameBack(@Param(value = "bookName") String bookName,
                                                            @Param(value = "bookWriter") String bookWriter,
                                                            @Param(value = "publishName")String publishName,
                                                            @Param(value = "categoryName")String categoryName );
   /*检查书籍状态*/
    Integer selectBookStatus(@Param("bookId") Integer bookId);

}