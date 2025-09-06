package com.sky.controller.user;

import com.sky.context.BaseContext;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userOrderController")
@RequestMapping("/user/order")
@Api(tags = "用户订单接口")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/submit")
    @ApiOperation("用户下单")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        log.info("用户下单：{}", ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO = orderService.submit(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }
    /**
     * 订单支付
     *
     * @param ordersPaymentDTO
     * @return
     */
    @PutMapping("/payment")
    @ApiOperation("订单支付")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        log.info("订单支付：{}", ordersPaymentDTO);
        OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
        log.info("生成预支付交易单：{}", orderPaymentVO);
        return Result.success(orderPaymentVO);
    }
    /**
     * 订单列表
     *
     * @param ordersPageQueryDTO
     * @return
     */
    @GetMapping("/historyOrders")
    @ApiOperation("订单列表")
    public Result<PageResult> list(OrdersPageQueryDTO ordersPageQueryDTO) {
        log.info("分页查询订单列表：{}", ordersPageQueryDTO);
        ordersPageQueryDTO.setUserId(BaseContext.getCurrentId());
        PageResult pageResult = orderService.pageQuery(ordersPageQueryDTO);
        log.info("查询结果：{}", pageResult);
        return Result.success(pageResult);
    }
    /**
     * 订单支付成功
     *
     * @param outTradeNo
     */
    @PutMapping("/paySuccess")
    @ApiOperation("订单支付成功")
    public Result paySuccess(String outTradeNo) {
        log.info("订单支付成功，订单号：{}", outTradeNo);
        orderService.paySuccess(outTradeNo);
        return Result.success();
    }
    /**
     * 订单详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/details")
    @ApiOperation("订单详情")
    public Result<OrderVO> details(Long id) {
        log.info("查询订单详情，订单id：{}", id);
        OrderVO orderVO = orderService.details(id);
        return Result.success(orderVO);
    }
    /**
     * 再来一单
     *
     * @param id
     * @return
     */
    @PostMapping("/repetition/{id}")
    @ApiOperation("再来一单")
    public Result repetition(@PathVariable Long id) {
        log.info("再来一单，订单id：{}", id);
        orderService.repetition(id);
        return Result.success();
    }
    /**
     * 订单取消
     *
     * @param id
     * @return
     */
    @PutMapping("/cancel/{id}")
    @ApiOperation("订单取消")
    public Result cancel(@PathVariable Long id) {
        log.info("订单取消，订单id：{}", id);
        orderService.cancel(id);
        return Result.success();
    }
    /**
     * 订单细节查询
     *
     * @param id
     * @return
     */
    @GetMapping("/orderDetail/{id}")
    @ApiOperation("订单细节查询")
    public Result<OrderVO> orderDetail(@PathVariable Long id) {
        log.info("订单细节查询，订单id：{}", id);
        OrderVO orderVO = orderService.details(id);
        log.info("查询结果：{}", orderVO);
        return Result.success(orderVO);
    }



}