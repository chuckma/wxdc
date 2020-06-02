package com.wechatorder.demo.service;

import com.wechatorder.demo.dataobject.ProductInfo;
import com.wechatorder.demo.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 商品服务端的接口
 * Created by Chris on 2020/3/2.
 */
public interface ProductService {
    Optional<ProductInfo> findOne(String productId);
    /**
     * 查询所有已经上架的商品
     * @return
     */
    List<ProductInfo> findUpAll();
    Page<ProductInfo> findAll(Pageable pageable);
    ProductInfo save(ProductInfo productInfo);

    /**
     *增加库存
     * @param cartDTOList
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减少库存
     * @param cartDTOList
     */
    void decreaseStock(List<CartDTO> cartDTOList);

    /**
     * 上架商品
     * @param productId
     * @return
     */
    ProductInfo onSale(String productId);

    /**
     * 下架商品
     * @param productId
     * @return
     */
    ProductInfo offSale(String productId);

}
