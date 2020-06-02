package com.wechatorder.demo.repository;

import com.wechatorder.demo.dataobject.SellerInfo;
import com.wechatorder.demo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Chris on 2020/4/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class SellerInfoRepositoryTest {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.getUniqueKey());
        sellerInfo.setUsername("chris_seller");
        sellerInfo.setPassword("123456789");
        sellerInfo.setOpenid("asdfghjkl");
        sellerInfo.setCreateTime(new Date());
        sellerInfo.setUpdateTime(new Date());
        SellerInfo res = sellerInfoRepository.save(sellerInfo);
        Assert.assertNotNull(res);
    }

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("asdfghjkl");
        Assert.assertEquals("asdfghjkl", sellerInfo.getOpenid());
    }
}