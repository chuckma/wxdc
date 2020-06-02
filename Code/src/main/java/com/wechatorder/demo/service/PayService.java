package com.wechatorder.demo.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.wechatorder.demo.dto.OrderDTO;

/**
 * Created by Chris on 2020/4/7.
 */
public interface PayService {
    //创建支付订单
    PayResponse create(OrderDTO orderDTO);
    //异步通知
    PayResponse notify(String notifyData);
    //退款
    RefundResponse refund(OrderDTO orderDTO);
}
