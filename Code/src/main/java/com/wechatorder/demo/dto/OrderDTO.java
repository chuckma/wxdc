package com.wechatorder.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wechatorder.demo.dataobject.OrderDetail;
import com.wechatorder.demo.enums.OrderStatusEnum;
import com.wechatorder.demo.enums.PayStatusEnum;
import com.wechatorder.demo.utils.EnumUtil;
import com.wechatorder.demo.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 一个订单的信息：区别于dataobject中的数据库表的示实例
 * （增加了一些数据库表中不存在得属性，用于区别数据库表的实例，为了不混淆使用数据库表的实例）
 * Created by Chris on 2020/3/5.
 */
//自动生成：构造器（含：无参、有参）、set、get方法
@Data
public class OrderDTO {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;
    //不知道为什么在创建对象时，该属性 = null
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    //订单详情列表（数据库表中不存在的属性）:包含订单中所有商品的信息
    List<OrderDetail> orderDetailList;

    /**
     * 根据orderStatus，获取对应枚举类的元素信息
     * JsonIgnore：当信息为null时，不显示该信息（不用该注释，则会显示null）
     * @return
     */
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
