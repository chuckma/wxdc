package com.wechatorder.demo.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品表单：用于/product/index网页提交商品信息
 * Created by Chris on 2020/4/14.
 */
@Data
public class ProductForm {
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    /** 类目编号. */
    private Integer categoryType;
}
