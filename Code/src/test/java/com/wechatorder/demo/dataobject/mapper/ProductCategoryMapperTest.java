package com.wechatorder.demo.dataobject.mapper;

import com.wechatorder.demo.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ================== Mybatis测试 ====================
 * 使用MyBatis，对数据库里面的数据进行增、删、改、查操作
 * 使用了两种方式来使用mybatirs：
 * （1）注解
 * （1）xml配置
 *
 * Created by Chris on 2020/4/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper categoryMapper;

    /*************************************** 注解方式 ********************************************/

    @Test
    public void insertByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "Electric");
        map.put("category_type", 1);
        int result = categoryMapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("Object");
        productCategory.setCategoryType(520);
        int res = categoryMapper.insertByObject(productCategory);
        Assert.assertEquals(1, res);

    }

    @Test
    public void findByCategoryType() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory = categoryMapper.findByCategoryType(520);
        Assert.assertEquals(520,productCategory.getCategoryType().longValue());
    }

    @Test
    public void findByCategoryName() throws Exception {
        List<ProductCategory> result = categoryMapper.findByCategoryName("Object");
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void updateByCategoryType() throws Exception {
        int result = categoryMapper.updateByCategoryType("傻越最爱的呀", 521);
        Assert.assertEquals(1,result);
    }

    @Test
    public void updateByObject() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("傻越最爱");
        productCategory.setCategoryType(521);
        int result = categoryMapper.updateByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void deleteByCategoryType() throws Exception {
        int result = categoryMapper.deleteByCategoryType(521);
        Assert.assertEquals(1,result);
    }


    /*************************************** XML方式 ********************************************/
    @Test
    public void selectByCategoryType() {
        ProductCategory productCategory = categoryMapper.selectByCategoryType(520);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void selectByCategoryId() {
        ProductCategory productCategory = categoryMapper.selectByCategoryId(7);
        Assert.assertNotNull(productCategory);
    }
}