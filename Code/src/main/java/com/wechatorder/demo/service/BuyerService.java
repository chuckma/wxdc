package com.wechatorder.demo.service;

import com.wechatorder.demo.dto.OrderDTO;

/**
 * 买家的服务函数
 * Created by Chris on 2020/3/6.
 */
public interface BuyerService {
    //查询自己的订单
    OrderDTO findOrderOne(String openid, String orderId);
    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
