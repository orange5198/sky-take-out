package com.sky.controller.admin;

import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminOrderController")
@RequestMapping("/admin/order")
@Slf4j
@Api("订单管理接口")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 订单搜索
     *
     * @param ordersPageQuery 查询参数
     * @return 订单列表
     */
    @GetMapping("/conditionSearch")
    @ApiOperation("订单搜索")
    public Result<PageResult> conditionSearch(OrdersPageQueryDTO ordersPageQuery) {
        log.info("订单搜索：{}", ordersPageQuery);
        PageResult pageResult = orderService.conditionSearch(ordersPageQuery);
        return Result.success(pageResult);

    }
    /**
     * 订单详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/details/{id}")
    @ApiOperation("订单详情")
    public Result<OrderVO> details(@PathVariable Long id) {
        log.info("查询订单详情，订单id为{}", id);
        OrderVO orderVO = orderService.details(id);
        return Result.success(orderVO);
    }
    /**
     * 订单派送
     *
     * @param id
     * @return
     */
    @PutMapping("/delivery/{id}")
    @ApiOperation("订单派送")
    public Result delivery(@PathVariable Long id) {
        log.info("订单派送，订单id为{}", id);
        orderService.delivery(id);
        return Result.success();
    }
    /**
     * 订单取消
     *
     * @param ordersCancelDTO
     * @return
     */
    @PutMapping("/cancel")
    @ApiOperation("订单取消")
    public Result cancel(@RequestBody OrdersCancelDTO ordersCancelDTO) {
        log.info("订单取消，订单id为{}", ordersCancelDTO.getId());
        orderService.cancel(ordersCancelDTO);
        return Result.success();
    }
    /**
     * 接单
     *
     * @param ordersConfirmDTO
     * @return
     */
    @PutMapping("/confirm")
    @ApiOperation("接单")
    public Result confirm(@RequestBody OrdersConfirmDTO ordersConfirmDTO) {
        log.info("接单，订单id为{}", ordersConfirmDTO.getId());
        orderService.confirm(ordersConfirmDTO.getId());
        return Result.success();
    }
    /**
     * 确认完成
     *
     * @param id
     * @return
     */
    @PutMapping("/complete/{id}")
    @ApiOperation("确认完成")
    public Result complete(@PathVariable Long id) {
        log.info("确认完成，订单id为{}", id);
        orderService.complete(id);
        return Result.success();
    }
    /**
     * 各个状态的订单数量统计
     *
     */
    @GetMapping("/statistics")
    @ApiOperation("各个状态的订单数量统计")
    public Result statistics() {
        log.info("各个状态的订单数量统计");
        return Result.success(orderService.statistics());
    }
    /**
     * 拒单
     * @return
     */
    @PutMapping("/rejection")
    @ApiOperation("拒单")
    public Result rejection(@RequestBody OrdersRejectionDTO ordersRejectionDTO) throws Exception {
        log.info("拒单：{}", ordersRejectionDTO);
        orderService.rejection(ordersRejectionDTO);
        return Result.success();
    }

}
