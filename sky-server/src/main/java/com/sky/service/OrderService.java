package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {

    /**
     * 用户下单
     * @param ordersSubmitDTO   下单数据
     * @return 订单
     */
    OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO);
    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    OrderVO details(Long id);

    void repetition(Long id);

    void cancel(OrdersCancelDTO ordersCancelDTO);

    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQuery);

    void delivery(Long id);


    PageResult pageQuery(OrdersPageQueryDTO ordersPageQuery);

    void confirm(Long id);

    void complete(Long id);

    Object statistics();

    void rejection(OrdersRejectionDTO ordersRejectionDTO);

    @Transactional(rollbackFor = Exception.class)
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    void Usercancel(Long id);
}
