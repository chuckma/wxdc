package com.wechatorder.demo.controller;

import com.wechatorder.demo.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ======================  Redis缓存、分布式锁测试案例 =========================
 * 秒杀活动
 *
 * 测试url：（1）http://localhost:8080/sell/skill/order/123456789    (本地)
 *          （2）http://chrissell.natapp1.cc/sell/skill/order/123456789（外网）
 *
 * 使用Apach AP压力测试工具：
 * 100个请求，10个线程处理
 * ab -n 100 -c 10 http://chrissell.natapp1.cc/sell/skill/order/123456789
 *
 * Created by Chris on 2020/4/17.
 */
@RestController
@RequestMapping("/skill")
@Slf4j
public class SecKillController {
    @Autowired
    private SecKillService secKillService;

    /**
     * 获取商品信息
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId) throws Exception{
        return secKillService.querySecKillProductInfo(productId);
    }

    /**
     * ==============测试时使用的方法===============
     * 秒杀商品：失败时 会自动提示， 抢到商品时，会返回库存数量
     *
     * @return
     */
    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId) throws Exception{
        log.info("@skill request, productId" + productId );
        secKillService.orderProductMockDiffUser(productId);
        return secKillService.querySecKillProductInfo(productId);
    }
}
