package com.wechatorder.demo.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wechatorder.demo.dataobject.ProductInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 商品（大类，包含类目）
 * Created by Chris on 2020/3/2.
 */
public class ProductVO implements Serializable {
    private static final long serialVersionUID = -7030232296691149514L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

    public ProductVO(){}

    public ProductVO(String categoruName, Integer categotyType, List<ProductInfoVO> productInfoVOList) {
        this.categoryName = categoruName;
        this.categoryType = categotyType;
        this.productInfoVOList = productInfoVOList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public List<ProductInfoVO> getProductInfoVOList() {
        return productInfoVOList;
    }

    public void setProductInfoVOList(List<ProductInfoVO> productInfoVOList) {
        this.productInfoVOList = productInfoVOList;
    }

    @Override
    public String toString() {
        return "ProductVO{" +
                "categoryName='" + categoryName + '\'' +
                ", categoryType=" + categoryType +
                ", productInfoVOList=" + productInfoVOList +
                '}';
    }
}
