package com.yym.sharding.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yym.sharding.model.ProductOrderDO;


public interface ProductOrderMapper extends BaseMapper<ProductOrderDO> {

    @Select("select * from product_order o left join product_order_item i on o.id=i.product_order_id")
    List<Object> listProductOrderDetail();


    @Select("select * from product_order")
    List<Object> listProductOrder();

    @Update("update product_order set state = 'testUpdate' where user_id in(2, 16, 5) ")
    boolean updateProductOrder();

    @Update("update product_order po inner join product_order_item poi on po.id = poi.product_id" +
            " set poi.product_name = 'testUpdate' where po.id = 1996197419843002370 ")
    boolean updateProductOrderDetailUsePartitionKey();

    @Update("update product_order po inner join product_order_item poi on po.id = poi.product_id" +
            " set poi.product_name = 'testUpdate' where poi.id = 1996197419843002370 ")
    boolean updateProductOrderDetail();
}
