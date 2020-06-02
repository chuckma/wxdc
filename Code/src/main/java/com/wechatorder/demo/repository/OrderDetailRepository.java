package com.wechatorder.demo.repository;

import com.wechatorder.demo.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 操作数据库：order_detail表格
 * Created by Chris on 2020/3/4.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId);
}
