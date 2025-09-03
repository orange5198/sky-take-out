package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.aspect.AutoFillAspect;
import com.sky.entity.OrderDetail;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    /**
     * 批量插入订单明细数据
     * @param orderDetailList 订单明细数据
     */
    void insertBatch(List<OrderDetail> orderDetailList);
    /**
     * 根据订单id查询订单明细
     * @param id 订单id
     * @return
     */
    List<OrderDetail> listByOrderId(Long id);
}
