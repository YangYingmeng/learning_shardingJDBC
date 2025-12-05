package com.yym.sharding.model;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@TableName("product_order_item")
@EqualsAndHashCode(callSuper = false)
public class ProductOrderItemDO {

    private Long id;

    private Long productOrderId;

    private Long productId;

    private String productName;

    private Integer buyNum;

    private Long userId;

}
