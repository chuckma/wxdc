package com.wechatorder.demo.dataobject.dao;

import com.wechatorder.demo.dataobject.ProductCategory;
import com.wechatorder.demo.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * ================== Mybatis测试 ====================
 *
 * Created by Chris on 2020/4/17.
 */
public class ProductCategoryDao {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    public int intsertByMap(Map<String, Object> map){
        return productCategoryMapper.insertByMap(map);
    }
}
