package com.ReadEnjoyBack.dao;

import com.ReadEnjoyBack.pojo.Navigation;

import java.util.List;

public interface NavigationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Navigation record);

    int insertSelective(Navigation record);

    Navigation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Navigation record);

    int updateByPrimaryKey(Navigation record);

    /*获取当前导航的子节点(平级)*/
    List<Navigation> selectNavigationChildrenByParentId(Integer navId);
}