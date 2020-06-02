package com.wechatorder.demo.service;

import com.wechatorder.demo.dto.OrderDTO;

/**
 * 微信推送消息接口
 * Created by Chris on 2020/4/16.
 */
public interface PushMessageService {
    void orderStatus(OrderDTO orderDTO);
}
