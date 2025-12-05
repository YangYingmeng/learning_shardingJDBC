package com.yym.sharding.db;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yym.sharding.TestApplication;
import com.yym.sharding.mapper.AdConfigMapper;
import com.yym.sharding.mapper.ProductOrderItemMapper;
import com.yym.sharding.mapper.ProductOrderMapper;
import com.yym.sharding.model.AdConfigDO;
import com.yym.sharding.model.ProductOrderDO;
import com.yym.sharding.model.ProductOrderItemDO;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2025-12-02 14:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@Slf4j
public class DbTest {

    @Autowired
    private ProductOrderMapper productOrderMapper;
    @Autowired
    private ProductOrderItemMapper productOrderItemMapper;

    @Autowired
    private AdConfigMapper adConfigMapper;

    // ============================  新增 ============================

    /**
     * 插入 订单 + 订单项（绑定表）
     */
    @Test
    public void testSaveProductOrderAndItem() {

        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            // 1️⃣ 先插入订单主表
            Long userId = (long) random.nextInt(50);

            ProductOrderDO order = new ProductOrderDO();
            order.setCreateTime(new Date());
            order.setNickname("自定义水平分库分表 i=" + i);
            order.setOutTradeNo(UUID.randomUUID().toString().replace("-", ""));
            order.setPayAmount(100.00);
            order.setState("PAY");
            order.setUserId(userId);

            // 这里不要自己 setId，让 ShardingSphere 生成
            productOrderMapper.insert(order);

            // 拿到 ShardingSphere 生成的订单ID
            Long orderId = order.getId();
            System.out.println("生成订单ID = " + orderId + "，用户ID = " + userId);

            // 2️⃣ 再插入订单项（绑定表）
            for (int j = 0; j < 3; j++) {
                ProductOrderItemDO item = new ProductOrderItemDO();
                item.setProductOrderId(orderId);     // 关键：绑定用这个字段分片
                item.setProductId(1000L + j);
                item.setProductName("测试商品-" + j);
                item.setBuyNum(1 + j);
                item.setUserId(userId);              // 必须和订单 userId 一致（用于分库）

                productOrderItemMapper.insert(item);
            }
        }
    }

    /**
     * 插入广播表 ad_config
     */
    @Test
    public void testSaveAdConfig() {

        for (int i = 0; i < 5; i++) {
            AdConfigDO config = new AdConfigDO();
            config.setConfigKey("config_key_" + i);
            config.setConfigValue("config_value_" + i);
            config.setType("SYSTEM");

            adConfigMapper.insert(config);

            System.out.println("生成 ad_config ID = " + config.getId());
        }
    }


    // ============================  查询 ============================


    /**
     * 查询广播表 ad_config
     */
    @Test
    public void testListAdConfig() {

        System.out.println(adConfigMapper.listAdConfig());
    }

    /**
     * 查询绑定表单张 product_order
     */
    @Test
    public void testListProductOrder() {

        System.out.println(productOrderMapper.listProductOrder());
    }

    /**
     * 查询绑定表 product_order product_order_item
     */
    @Test
    public void testListProductOrderDetail() {

        System.out.println(productOrderMapper.listProductOrderDetail());
    }



    @Test
    public void testBingding() {

        List<Object> list = productOrderMapper.listProductOrderDetail();
        System.out.println(list);
    }


    /**
     * 有分片键
     */
    @Test
    public void testPartitionKeySelect() {
        productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().eq("id", 1464129579089227778L));
        //productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().in("id",Arrays.asList(1464129579089227778L,1464129582369173506L,1464129583140925441L)));
    }


    /**
     * 无有分片键
     */
    @Test
    public void testNoPartitionKeySelect() {
        productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().eq("out_trade_no", "2cc08fb8-7e77-4973-b408-7c68925b"));
        //productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().in("out_trade_no",Arrays.asList("2cc08fb8-7e77-4973-b408-7c68925b")));
    }


    /**
     * 有分片键
     */
    @Test
    public void testPartitionKeyDel() {
        //productOrderMapper.delete(new QueryWrapper<ProductOrderDO>().eq("id",1464129579089227778L));
        //productOrderMapper.delete(new QueryWrapper<ProductOrderDO>().in("id",Arrays.asList(1464129579089227778L,1464129582369173506L,1464129583140925441L)));
    }


    /**
     * 无有分片键
     */
    @Test
    public void testNoPartitionKeyDel() {
        //productOrderMapper.delete(new QueryWrapper<ProductOrderDO>().eq("out_trade_no","2cc08fb8-7e77-4973-b408-7c68925b"));
        productOrderMapper.delete(
                new QueryWrapper<ProductOrderDO>().in("out_trade_no", Arrays.asList("2cc08fb8-7e77-4973-b408-7c68925b")));
    }


    @Test
    public void testBetween() {

        productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().between("id", 1L, 1L));
    }


    @Test
    public void testMultiPartitionKeySelect() {

        productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().eq("id", 66L).eq("user_id", 99L));
    }


    /**
     * 正常可以用AOP进行实现
     */
    @Test
    public void testHit() {

        //清除历史规则
        HintManager.clear();

        //获取对应的实例
        HintManager hintManager = HintManager.getInstance();

        //设置库的分片键值，value是用于库分片取模
        hintManager.addDatabaseShardingValue("product_order", 3L);

        //设置表的分片键值，value是用于表分片取模
        hintManager.addTableShardingValue("product_order", 8L);

        //如果在读写分离数据库中，Hint 可以强制读主库（主从复制存在一定延时，但在业务场景中，可能更需要保证数据的实时性）
        //hintManager.setMasterRouteOnly();

        //对应的value,只做查询，不做sql解析
        productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().eq("id", 66L));

    }

}
