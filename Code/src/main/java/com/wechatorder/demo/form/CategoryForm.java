package com.wechatorder.demo.form;

import lombok.Data;

/**
 * 商品类目信息
 * Created by Chris on 2020/4/15.
 */
@Data
public class CategoryForm {
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
}
