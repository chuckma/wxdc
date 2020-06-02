package com.wechatorder.demo.repository;

import com.wechatorder.demo.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 操作表格product_info里面的数据
 * JpaRepository<T, S>：  T:要操作的类、表 ;  S：主键类型（一般是ID）
 * Created by Chris on 2020/3/2.
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    //根据商品状态查找：获得已经上架商品的信息
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
