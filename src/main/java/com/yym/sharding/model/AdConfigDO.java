package com.yym.sharding.model;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ad_config")
public class AdConfigDO {

    private Long id;

    private String configKey;

    private String configValue;

    private String type;

}
