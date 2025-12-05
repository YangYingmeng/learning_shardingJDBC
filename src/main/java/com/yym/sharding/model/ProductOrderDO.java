package com.yym.sharding.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@TableName("product_order") // 业务上使用逻辑表明
@EqualsAndHashCode(callSuper = false)
public class ProductOrderDO {

    @TableId(value = "id", type = IdType.NONE) // 使用shardingJDBC的雪花算法生成主键
    private Long id;

    private String outTradeNo;

    private String state;

    private Date createTime;

    private Double payAmount;

    private String nickname;

    private Long userId;

}
