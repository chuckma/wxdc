package com.wechatorder.demo.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 用于浏览器显示的必填字段信息提示
 * Created by Chris on 2020/3/6.
 */
@Data
public class OrderForm {
    //买家姓名
    @NotEmpty(message = "姓名必填")
    private String name;
    //买家手机号
    @NotEmpty(message = "手机必填")
    private String phone;
    //买家地址
    @NotEmpty(message = "地址必填")
    private String address;
    //买家微信的openid，用于查询订单
    @NotEmpty(message = "openid必填")
    private String openid;
    //购物车信息：即订单
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
