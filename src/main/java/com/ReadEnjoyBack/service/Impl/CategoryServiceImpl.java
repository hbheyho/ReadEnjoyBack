package com.ReadEnjoyBack.service.Impl;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.dao.CategoryMapper;
import com.ReadEnjoyBack.pojo.Category;
import com.ReadEnjoyBack.pojo.Navigation;
import com.ReadEnjoyBack.service.ICategoryService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author:HB
 * @Description: 书籍分类Service实现
 * @Createdata:Created in  9:31  2018/5/29.
 */
@Service("iCategoryService")
public class CategoryServiceImpl  implements ICategoryService{
    private  Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    // 注入mapper
    @Autowired
    private CategoryMapper categoryMapper;
    /*
    * @Author:HB
    * @Description: 增加分类节点
    * @Data:10:50 2018/5/29
    * @param parentId
    * @param categoryName
    returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
    */
    @Override
    public ServerResponse<String> addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("添加分类错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);  // 状态为可用
        int resultCount = categoryMapper.insert(category);
        if (resultCount > 0){
            return  ServerResponse.createBySuccessMessage("添加分类成功！");
        }
        return ServerResponse.createByErrorMessage("添加分类失败！");
    }
    /*
     * @Author:HB
     * @Description:修改分类名字
     * @Data:14:17 2018/5/24
    * @param categoryId
    * @param categoryName
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
     */
    @Override
    public ServerResponse<String> updateCategoryname(Integer categoryId, String categoryName) {
        if (categoryId == null || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("修改分类名出现错误!");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        int resultCount = categoryMapper.updateByPrimaryKeySelective(category);
        if (resultCount > 0){
            return ServerResponse.createBySuccessMessage("修改分类名字成功！");
        }
        return ServerResponse.createByErrorMessage("修改分类名字失败！");
    }
    /*
     * @Author:HB
     * @Description: 获取当前分类的子节点(平级)
     * @Data:14:41 2018/5/29
     * @param categoryId
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
     */
    @Override
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)){
            return ServerResponse.createByErrorMessage("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccesse(categoryList);
    }
    /*
     * @Author:HB
     * @Description: 获取当前分类的子节点(深度查询)
     * @Data:15:13 2018/5/29
    * @param categoryId
     returns:com.ReadEnjoyBack.common.ServerResponse
     */
    @Override
    public ServerResponse<List<Category>> getDeepChildrenParallelCategory(Integer categoryId){
        Set<Category> categorySet = Sets.newHashSet();
        findChildDeep(categorySet,categoryId);


        List<Category> categoryIdList = Lists.newArrayList();
        if(categoryId != null){
            for(Category categoryItem : categorySet){
                categoryIdList.add(categoryItem);
            }
        }
        return ServerResponse.createBySuccesse(categoryIdList);
    }

    /*递归算法计算子节点*/
    private Set<Category> findChildDeep(Set<Category> categorySet,Integer categoryId){
        Category category = categoryMapper.selectByPrimaryKey(categoryId);  // 获取当前id的分类信息
        if (category != null){
            categorySet.add(category);
        }
        // 查找子节点 如果子节点为空 退出 (根据parentId查找子节点)
        List<Category>  categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for (Category categoryItem: categoryList){
            findChildDeep(categorySet,categoryItem.getId());
        }
        logger.info("当前1111为：{}",categorySet);
        return categorySet;
    }
}
