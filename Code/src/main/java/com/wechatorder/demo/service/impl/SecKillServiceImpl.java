package com.wechatorder.demo.service.impl;

import com.wechatorder.demo.exception.SellException;
import com.wechatorder.demo.service.RedisLock;
import com.wechatorder.demo.service.SecKillService;
import com.wechatorder.demo.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * ======================  Redis缓存、分布式锁测试案例 =========================
 * 产品秒杀业务
 * Created by Chris on 2020/4/17.
 */
@Service
public class SecKillServiceImpl implements SecKillService {
    private static final int TIME_OUT = 10 * 1000;
    @Autowired
    private RedisLock redisLock;

    //商品特价，10000份
    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;
    static{
        //模拟多个表：商品信息表、库存表、秒杀成功表
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456789", 10000);
        stock.put("123456789", 10000);
    }

    private String queryMap(String productId){
        return "国庆活动，皮蛋粥特价，限量份:" + products.get(productId) +
                "还剩余：" + stock.get(productId) + "份" +
                "该商品成功下单用户数目：" + orders.size() + "人";
    }

    /**
     * 获取商品信息
     * @param productId
     * @return
     */
    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    /**
     * 开始秒杀商品
     * @param productId
     */
    @Override
    public void orderProductMockDiffUser(String productId) {
        //1、加锁
        //时间 = 系统时间 + 过期时间
        long time = System.currentTimeMillis() + TIME_OUT;
        if(! redisLock.lock(productId, String.valueOf(time))){
            throw new SellException(101, "哎哟喂，人也太多了，换个姿势再试试~~~");
        }

        //2、查询商品库存
        int stockNum = stock.get(productId);
        if(stockNum == 0){
            throw new SellException(100, "活动结束");
        }
        else{
            //下单
            orders.put(KeyUtil.getUniqueKey(), productId);
            //减库存
            stockNum -= 1;
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException  e){
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }

        //3、解锁
        redisLock.unlock(productId, String .valueOf(time));
    }
}
