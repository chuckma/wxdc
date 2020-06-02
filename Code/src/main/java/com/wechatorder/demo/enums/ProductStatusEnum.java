package com.wechatorder.demo.enums;


/**
 * 枚举类：商品状态
 * 0：上架，  1：下架
 * Created by Chris on 2020/3/2.
 */
public enum ProductStatusEnum implements CodeEnum{
    UP(0, "Added"),
    DOWN(1, "Sold Out")
    ;

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取code参数的数值:0, 1
     * @return
     */
    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
