package com.ReadEnjoyBack.controller.portal;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.Category;
import com.ReadEnjoyBack.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author:HB
 * @Description: 分类信息的获取（前台）
 * @Createdata:Created in  20:09  2018/6/2.
 */
@Controller
@RequestMapping("/category/")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    /*
    * @Author:HB
    * @Description: 获取parentId=0的分类
    * @Data:20:05 2018/6/2
    * @param null
    returns:
   */
    @RequestMapping(value = "get_parent_categoty.do")
    @ResponseBody
    public ServerResponse<List<Category>> getParentCategory(@RequestParam(defaultValue = "0") Integer categoryId,
                                                            HttpServletResponse response, HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iCategoryService.getChildrenParallelCategory(categoryId);
    }
}
