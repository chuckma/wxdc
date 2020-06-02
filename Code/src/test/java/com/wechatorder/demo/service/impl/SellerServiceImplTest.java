package com.wechatorder.demo.service.impl;

import com.wechatorder.demo.dataobject.SellerInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Chris on 2020/4/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class SellerServiceImplTest {
    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    void findSellerInfoByOpenid() {
        SellerInfo res = sellerService.findSellerInfoByOpenid("asdfghjkl");
        Assert.assertEquals("asdfghjkl", res.getOpenid());
    }
}