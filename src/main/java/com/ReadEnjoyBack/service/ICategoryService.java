package com.ReadEnjoyBack.service;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.Category;
import com.ReadEnjoyBack.pojo.Navigation;

import java.util.List;

/**
 * @Author:HB
 * @Description: 书籍分类操作Service
 * @Createdata:Created in  9:30  2018/5/29.
 */
public interface ICategoryService {
    /*增加分类节点*/
    ServerResponse<String> addCategory(String categoryName, Integer parentId);
    /*修改分类名字*/
    ServerResponse<String> updateCategoryname(Integer categoryId,String categoryName);
    /*获取当前导分类的子节点(平级)*/
    ServerResponse<List<Category>>  getChildrenParallelCategory(Integer categoryId);
    /*获取当前分类的子节点(递归)*/
    ServerResponse<List<Category>> getDeepChildrenParallelCategory(Integer categoryId);
}
