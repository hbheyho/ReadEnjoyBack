package com.ReadEnjoyBack.service;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.Navigation;

import java.util.List;

/**
 * @Author:HB
 * @Description: 导航管理接口
 * @Createdata:Created in  10:24  2018/5/24.
 */
public interface INavigationService {
    /*增加导航结点*/
   ServerResponse<String> addNavigation(String navName,String url,Integer parentId);
    /*修改导航名字*/
   ServerResponse<String> updateNavUsername(Integer navId,String navName,String url);
   /*获取当前导航的子节点(平级)*/
   ServerResponse<List<Navigation>>  getChildrenParallelNavigation(Integer navId);
    /*获取当前导航的子节点(递归)*/
   ServerResponse<List<Navigation>> getDeepChildrenParallelNavigation(Integer navId);
}
