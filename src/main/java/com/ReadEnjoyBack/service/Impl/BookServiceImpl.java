package com.ReadEnjoyBack.service.Impl;

import com.ReadEnjoyBack.common.ResponseCode;
import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.dao.BookMapper;
import com.ReadEnjoyBack.dao.CategoryMapper;
import com.ReadEnjoyBack.pojo.Book;
import com.ReadEnjoyBack.pojo.Category;
import com.ReadEnjoyBack.service.IBookService;
import com.ReadEnjoyBack.util.DateTimeUtil;
import com.ReadEnjoyBack.util.PropertiesUtil;
import com.ReadEnjoyBack.vo.BookDetailVo;
import com.ReadEnjoyBack.vo.BookListVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:HB
 * @Description:  书籍操作接口实现
 * @Createdata:Created in  8:58  2018/5/29.
 */
@Service("iBookService")
public class BookServiceImpl implements IBookService {
    private Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    // 注入bookMapper和categoryMapper
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CategoryMapper categoryMapper;


/*------------------------后台接口-------------------------------------*/
    /*
     * @Author:HB
     * @Description: 新增或者更新书籍信息
     * @Data:10:54 2018/5/29
     * @param book
     returns:
    */
    public ServerResponse<String> saveOrUpdateBook(Book book){
        if (book.getBookIsbn() != null && book.getCategoryId() !=null && book.getBookName() != null){
            if (book.getBookId() != null){  //书籍更新操作
                int resultCount = bookMapper.updateByPrimaryKey(book);
                if (resultCount > 0){
                    return ServerResponse.createBySuccessMessage("书籍更新成功！");
                }
                return ServerResponse.createByErrorMessage("书籍更新失败！");
            }else {  // 进行书籍增加操作
                int resulCount = bookMapper.insert(book);
                if (resulCount > 0) {
                    return ServerResponse.createBySuccessMessage("书籍新增成功！");
                }
                return ServerResponse.createByErrorMessage("书籍新增失败！");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新书籍的参数错误!");
    }
     /*
      * @Author:HB
      * @Description: 获取书籍详情信息
      * @Data:20:34 2018/5/29
      * @param bookId
      returns: BookDetailVo
     */
    @Override
    public ServerResponse<BookDetailVo> getBookDetail(String bookISBN) {
        if (bookISBN == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Book bookDetail = bookMapper.getBookDetail(bookISBN);
        if (bookDetail == null){
            return ServerResponse.createByErrorMessage("书籍被删除或者不存在！");
        }
        // 生成bookDetailVo
        BookDetailVo  bookDetailVo = assembleBookDetailVo(bookDetail);
        return ServerResponse.createBySuccesse(bookDetailVo);
    }
    /*
     * @Author:HB
     * @Description: 书籍列表开发
     * @Data:9:20 2018/5/30
     * @param pageNum pageSize
     returns:
    */
    @Override
    public ServerResponse<PageInfo> getBookList(int pageNum, int pageSize) {
        // 开始分页
        PageHelper.startPage(pageNum,pageSize);
        // 填充自己sql逻辑
        List<Book> bookList = bookMapper.selectList();
        List<BookListVo> bookListVoList = new ArrayList<BookListVo>();
        for (Book bookItem: bookList){
            BookListVo bookListVo = assembleBookListVo(bookItem);
            bookListVoList.add(bookListVo);
        }
        // pageHelper收尾
        PageInfo pageInfo = new PageInfo(bookList); // 对集合进行自动的分页处理
        pageInfo.setList(bookListVoList); // 控制前台展示的内容 对list进行重置
        return ServerResponse.createBySuccesse(pageInfo);
    }
    /*
     * @Author:HB
     * @Description: 书籍搜索操作
     * @Data:10:07 2018/5/30
     * @param pageNum pageSize bookName bookWriter publishName
     returns:
    */
    @Override
    public ServerResponse<PageInfo> searchBook(int pageNum, int pageSize, String bookName, String bookWriter, String publishName
    ,String categoryName) {
        PageHelper.startPage(pageNum,pageSize);
        if (StringUtils.isNotBlank(bookName)){
            bookName = new StringBuilder().append("%").append(bookName).append("%").toString();
        }
        if (StringUtils.isNotBlank(bookWriter)){
            bookWriter = new StringBuilder().append("%").append(bookWriter).append("%").toString();
        }
        if (StringUtils.isNotBlank(publishName)){
            publishName = new StringBuilder().append("%").append(publishName).append("%").toString();
        }
        if (StringUtils.isNotBlank(categoryName)){
            categoryName = new StringBuilder().append("%").append(categoryName).append("%").toString();
        }
        List<Book> bookList = bookMapper.selectBookByBookNameOrBookWriterOrPublicNameBack(bookName,bookWriter,publishName,categoryName);
        List<BookListVo> bookListVoList = new ArrayList<BookListVo>();
        for (Book bookItem: bookList){
            BookListVo bookListVo = assembleBookListVo(bookItem);
            bookListVoList.add(bookListVo);
        }
        PageInfo pageInfo = new PageInfo(bookList);
        pageInfo.setList(bookListVoList);
        return ServerResponse.createBySuccesse(pageInfo);
    }



/*------------------------前台接口-------------------------------------*/
    /*
     * @Author:HB
     * @Description:获取书籍信息 根据下载的书籍次数
     * @Data:20:40 2018/6/1
     * @param 
     returns:com.ReadEnjoyBack.common.ServerResponse<java.util.List<com.ReadEnjoyBack.pojo.Book>>
    */
    @Override
    public ServerResponse<List<BookListVo>> getBookByDownNumber() {
        List<Book> bookList = new ArrayList<Book>();
        bookList = bookMapper.getBookListByDownNumber();
        List<BookListVo> bookListVoList = new ArrayList<BookListVo>();
        for (Book bookItem:bookList){
            BookListVo bookListVo = assembleBookListVo(bookItem);
            bookListVoList.add(bookListVo);
        }
        return ServerResponse.createBySuccesse(bookListVoList);
    }
    /*
      * @Author:HB
      * @Description: 数据搜索 （根据书名/ 书籍作者/ 书籍出版社）
      * @Data:21:34 2018/6/1
      * @param bookName
     * @param bookWriter
     * @param publishName
     * @param categoryName
     returns:com.ReadEnjoyBack.common.ServerResponse<java.util.List<com.ReadEnjoyBack.vo.BookListVo>>
    */
    @Override
    public ServerResponse<List<BookListVo>> searchBookByBookNameAndBookWriterAndPublish(String conditionName) {
        if (StringUtils.isNotBlank(conditionName)){
            conditionName = new StringBuilder().append("%").append(conditionName).append("%").toString();
        }
        List<Book> bookList = bookMapper.selectBookByBookNameOrBookWriterOrPublicNameFront(conditionName);
        List<BookListVo> bookListVoList = new ArrayList<BookListVo>();
        for (Book bookItem: bookList){
            BookListVo bookListVo = assembleBookListVo(bookItem);
            bookListVoList.add(bookListVo);
        }
        return ServerResponse.createBySuccesse(bookListVoList);
    }


/*------------------------生成Vo 业务对象-------------------------------------*/

    // 根据book生成生成一个bookListVo对象
    private BookListVo assembleBookListVo(Book book){
        BookListVo bookListVo = new BookListVo();
        bookListVo.setBookId(book.getBookId());
        bookListVo.setBookIsbn(book.getBookIsbn());
        bookListVo.setCategoryId(book.getCategoryId());
        bookListVo.setBookName(book.getBookName());
        bookListVo.setBookWriter(book.getBookWriter());
        bookListVo.setBookInfo(book.getBookInfo());
        bookListVo.setBookImage(book.getBookImage());
        bookListVo.setBookStatus(book.getBookStatus());

        // 得到所属分类的分类名
        String categoryName = categoryMapper.getCategoryName(book.getCategoryId());
        bookListVo.setBookCategoryName(categoryName);

        bookListVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","XXXXXX"));
        return bookListVo;
    }

    // 根据book生成一个bookDetailVo对象
    private BookDetailVo assembleBookDetailVo(Book book){
        BookDetailVo bookDetailVo = new BookDetailVo();
        bookDetailVo.setBookId(book.getBookId());
        bookDetailVo.setBookIsbn(book.getBookIsbn());
        bookDetailVo.setCategoryId(book.getCategoryId());
        bookDetailVo.setBookName(book.getBookName());
        bookDetailVo.setBookWriter(book.getBookWriter());
        bookDetailVo.setBookTranster(book.getBookTranster());
        bookDetailVo.setBookPublish(book.getBookPublish());
        bookDetailVo.setBookInfo(book.getBookInfo());
        bookDetailVo.setBookDirectory(book.getBookDirectory());
        bookDetailVo.setBookWriterInformation(book.getBookWriterInformation());
        bookDetailVo.setBookScore(book.getBookScore());
        bookDetailVo.setBookStatus(book.getBookStatus());

        // imagehost
        bookDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","XXXX"));
        // patrentCategoryId;
        Category category = categoryMapper.selectByPrimaryKey(book.getCategoryId());
        if (category == null){
           bookDetailVo.setParentCategoryId(0);
        }else {
           bookDetailVo.setParentCategoryId(category.getParentId());
        }
        //createTime
        bookDetailVo.setCreateTime(DateTimeUtil.dateToStr(book.getCreateTime()));
        // updateTime
        bookDetailVo.setUpdateTime(DateTimeUtil.dateToStr(book.getUpdateTime()));
        return bookDetailVo;
    }
}
