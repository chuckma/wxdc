package com.wechatorder.demo.service;

import com.wechatorder.demo.dataobject.SellerInfo;

/**
 * 卖家端：查询卖家信息
 * Created by Chris on 2020/4/16.
 */
public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openid);
}
