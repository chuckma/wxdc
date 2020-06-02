package com.wechatorder.demo.service;

import com.wechatorder.demo.dto.OrderDTO;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 订单服务接口
 * Created by Chris on 2020/3/5.
 */
public interface OrderService {
    //创建订单
    OrderDTO create(OrderDTO orderDTO);
    //查询某个订单：根据某个订单ID
    OrderDTO findOne(String orderId);
    //查询所有买家的订单列表
    Page<OrderDTO> findList(Pageable pageable);
    //根据买家id查询订单列表
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);
    //取消订单
    OrderDTO cancel(OrderDTO orderDTO);
    //完成订单
    OrderDTO finish(OrderDTO orderDTO);
    //支付订单
    OrderDTO paid(OrderDTO orderDTO);

}
