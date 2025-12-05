-- 创建数据库
CREATE DATABASE IF NOT EXISTS sharding_order_0
DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

CREATE DATABASE IF NOT EXISTS sharding_order_1
DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;


USE sharding_order_0;

-- ========================
-- 订单主表 product_order_0
-- ========================
CREATE TABLE IF NOT EXISTS `product_order_0` (
                                                 `id` BIGINT NOT NULL COMMENT '主键id',
                                                 `out_trade_no` VARCHAR(64) DEFAULT NULL COMMENT '订单唯一标识',
    `state` VARCHAR(11) DEFAULT NULL COMMENT 'NEW未支付,PAY已支付,CANCEL超时取消',
    `create_time` DATETIME DEFAULT NULL COMMENT '订单生成时间',
    `pay_amount` DECIMAL(16,2) DEFAULT NULL COMMENT '订单实际支付价格',
    `nickname` VARCHAR(64) DEFAULT NULL COMMENT '昵称',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ========================
-- 订单主表 product_order_1
-- ========================
CREATE TABLE IF NOT EXISTS `product_order_1` (
                                                 `id` BIGINT NOT NULL COMMENT '主键id',
                                                 `out_trade_no` VARCHAR(64) DEFAULT NULL COMMENT '订单唯一标识',
    `state` VARCHAR(11) DEFAULT NULL COMMENT 'NEW未支付,PAY已支付,CANCEL超时取消',
    `create_time` DATETIME DEFAULT NULL COMMENT '订单生成时间',
    `pay_amount` DECIMAL(16,2) DEFAULT NULL COMMENT '订单实际支付价格',
    `nickname` VARCHAR(64) DEFAULT NULL COMMENT '昵称',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ========================
-- 广播表 ad_config
-- ========================
CREATE TABLE IF NOT EXISTS `ad_config` (
                                           `id` BIGINT NOT NULL COMMENT '主键id',
                                           `config_key` VARCHAR(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配置key',
    `config_value` VARCHAR(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配置value',
    `type` VARCHAR(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类型',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ========================
-- 订单项表 product_order_item_0
-- ========================
CREATE TABLE IF NOT EXISTS `product_order_item_0` (
                                                      `id` BIGINT NOT NULL COMMENT '主键id',
                                                      `product_order_id` BIGINT DEFAULT NULL COMMENT '订单号',
                                                      `product_id` BIGINT DEFAULT NULL COMMENT '产品id',
                                                      `product_name` VARCHAR(128) DEFAULT NULL COMMENT '商品名称',
    `buy_num` INT DEFAULT NULL COMMENT '购买数量',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ========================
-- 订单项表 product_order_item_1
-- ========================
CREATE TABLE IF NOT EXISTS `product_order_item_1` (
                                                      `id` BIGINT NOT NULL COMMENT '主键id',
                                                      `product_order_id` BIGINT DEFAULT NULL COMMENT '订单号',
                                                      `product_id` BIGINT DEFAULT NULL COMMENT '产品id',
                                                      `product_name` VARCHAR(128) DEFAULT NULL COMMENT '商品名称',
    `buy_num` INT DEFAULT NULL COMMENT '购买数量',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;



USE sharding_order_1;

-- ========================
-- 订单主表 product_order_0
-- ========================
CREATE TABLE IF NOT EXISTS `product_order_0` (
                                                 `id` BIGINT NOT NULL COMMENT '主键id',
                                                 `out_trade_no` VARCHAR(64) DEFAULT NULL COMMENT '订单唯一标识',
    `state` VARCHAR(11) DEFAULT NULL COMMENT 'NEW未支付,PAY已支付,CANCEL超时取消',
    `create_time` DATETIME DEFAULT NULL COMMENT '订单生成时间',
    `pay_amount` DECIMAL(16,2) DEFAULT NULL COMMENT '订单实际支付价格',
    `nickname` VARCHAR(64) DEFAULT NULL COMMENT '昵称',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ========================
-- 订单主表 product_order_1
-- ========================
CREATE TABLE IF NOT EXISTS `product_order_1` (
                                                 `id` BIGINT NOT NULL COMMENT '主键id',
                                                 `out_trade_no` VARCHAR(64) DEFAULT NULL COMMENT '订单唯一标识',
    `state` VARCHAR(11) DEFAULT NULL COMMENT 'NEW未支付,PAY已支付,CANCEL超时取消',
    `create_time` DATETIME DEFAULT NULL COMMENT '订单生成时间',
    `pay_amount` DECIMAL(16,2) DEFAULT NULL COMMENT '订单实际支付价格',
    `nickname` VARCHAR(64) DEFAULT NULL COMMENT '昵称',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ========================
-- 广播表 ad_config
-- ========================
CREATE TABLE IF NOT EXISTS `ad_config` (
                                           `id` BIGINT NOT NULL COMMENT '主键id',
                                           `config_key` VARCHAR(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配置key',
    `config_value` VARCHAR(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配置value',
    `type` VARCHAR(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类型',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ========================
-- 订单项表 product_order_item_0
-- ========================
CREATE TABLE IF NOT EXISTS `product_order_item_0` (
                                                      `id` BIGINT NOT NULL COMMENT '主键id',
                                                      `product_order_id` BIGINT DEFAULT NULL COMMENT '订单号',
                                                      `product_id` BIGINT DEFAULT NULL COMMENT '产品id',
                                                      `product_name` VARCHAR(128) DEFAULT NULL COMMENT '商品名称',
    `buy_num` INT DEFAULT NULL COMMENT '购买数量',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ========================
-- 订单项表 product_order_item_1
-- ========================
CREATE TABLE IF NOT EXISTS `product_order_item_1` (
                                                      `id` BIGINT NOT NULL COMMENT '主键id',
                                                      `product_order_id` BIGINT DEFAULT NULL COMMENT '订单号',
                                                      `product_id` BIGINT DEFAULT NULL COMMENT '产品id',
                                                      `product_name` VARCHAR(128) DEFAULT NULL COMMENT '商品名称',
    `buy_num` INT DEFAULT NULL COMMENT '购买数量',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户id',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
