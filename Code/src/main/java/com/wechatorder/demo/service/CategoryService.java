package com.wechatorder.demo.service;

import com.wechatorder.demo.dataobject.ProductCategory;

import java.util.List;
import java.util.Optional;

/**
 * 类目服务端的接口
 * Created by Chris on 2020/3/2.
 */
public interface CategoryService {
    Optional<ProductCategory> findOne(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    ProductCategory save(ProductCategory productCategory);
}
