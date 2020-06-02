package com.wechatorder.demo.service.impl;

import com.wechatorder.demo.dataobject.SellerInfo;
import com.wechatorder.demo.repository.SellerInfoRepository;
import com.wechatorder.demo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Chris on 2020/4/16.
 */
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
