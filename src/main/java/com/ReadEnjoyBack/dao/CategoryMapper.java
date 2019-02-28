package com.ReadEnjoyBack.dao;

import com.ReadEnjoyBack.pojo.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /*获取当前分类的子节点(平级)*/
    List<Category> selectCategoryChildrenByParentId(Integer categoryId);

    /*得到当前分类Id的分类名字*/
    String getCategoryName(Integer categoryId);

}