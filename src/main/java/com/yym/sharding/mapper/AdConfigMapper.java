package com.yym.sharding.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yym.sharding.model.AdConfigDO;


public interface AdConfigMapper extends BaseMapper<AdConfigDO> {


    @Select("select * from ad_config")
    List<Object> listAdConfig();

    @Update("update ad_config set config_value = 'test_update' where config_key = 'config_key_0'")
    boolean updateAdConfig();

}
