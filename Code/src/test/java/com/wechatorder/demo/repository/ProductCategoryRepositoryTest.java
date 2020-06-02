package com.wechatorder.demo.repository;

import com.wechatorder.demo.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试类：注意测试使用的数据，必须和MySQL表格里面的约束条件、数据类型一样
 * （特别是 非null）
 * Created by Chris on 2020/3/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest(){
        Optional<ProductCategory> productCategory = repository.findById(7);
        System.out.println(productCategory.toString());
    }

    @Test
    @Transactional
    public void saveTest(){
        Date date = new Date();
        System.out.println("********************" + date + "********************");
        //这里的数据类型、非空约束等，必须和MySQL数据库中对应表格设置一样，否则会出现数据无法save
        ProductCategory productCategory = new ProductCategory("Telephone", 3 , date, date);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void update(){
        Date date = new Date();
        ProductCategory productCategory = new ProductCategory("Travel", 4, date, date);
        ProductCategory result = repository.save(productCategory);
        Assert.assertEquals(productCategory, result);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(7,8,9);

        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }
}