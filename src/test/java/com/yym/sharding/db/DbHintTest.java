package com.yym.sharding.db;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.yym.sharding.TestApplication;
import com.yym.sharding.mapper.ProductOrderMapper;
import com.yym.sharding.model.ProductOrderDO;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2025-12-02 14:54
 * <p>
 * 精准策略 分库分表
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@TestPropertySource(locations = "classpath:application-hint.yml")
@Slf4j
public class DbHintTest {

    @Autowired
    private ProductOrderMapper productOrderMapper;

    // ============================  新增 ============================

    /**
     * 插入 订单 + 订单项（绑定表）
     */
    @Test
    public void testSaveProductOrder() {

        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            // 1️⃣ 先插入订单主表
            Long userId = (long) random.nextInt(50);

            ProductOrderDO order = new ProductOrderDO();
            order.setCreateTime(new Date());
            order.setNickname("隐式 分库分表 i=" + i);
            order.setOutTradeNo(UUID.randomUUID().toString().replace("-", ""));
            order.setPayAmount(100.00);
            order.setState("PAY");
            order.setUserId(userId);

            // 这里不要自己 setId，让 ShardingSphere 生成
            productOrderMapper.insert(order);

            // 拿到 ShardingSphere 生成的订单ID
            Long orderId = order.getId();
            System.out.println("生成订单ID = " + orderId + "，用户ID = " + userId);


        }
    }


    // ============================  查询 ============================


    /**
     * 查询绑定表单张 product_order
     */
    @Test
    public void testListProductOrder() {

        System.out.println(productOrderMapper.listProductOrder());
    }
}
