package com.wechatorder.demo.service;

/**
 * ======================  Redis缓存、分布式锁测试案例 =========================
 * 秒杀活动的服务接口
 * Created by Chris on 2020/4/17.
 */
public interface SecKillService {
    String querySecKillProductInfo(String productId);

    void orderProductMockDiffUser(String productId);
}
