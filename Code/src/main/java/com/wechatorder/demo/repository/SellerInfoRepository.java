package com.wechatorder.demo.repository;

import com.wechatorder.demo.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 卖家信息
 * Created by Chris on 2020/4/16.
 */
//@Repository
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenid(String openid);
}
