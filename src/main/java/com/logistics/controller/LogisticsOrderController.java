package com.logistics.controller;

import com.logistics.dto.ResultDTO;
import com.logistics.entity.LogisticsOrder;
import com.logistics.service.LogisticsOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物流订单控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/order")
public class LogisticsOrderController {

    @Autowired
    private LogisticsOrderService orderService;

    /**
     * 添加订单
     * @param order 订单信息
     * @return 添加结果
     */
    @PostMapping
    public ResultDTO<LogisticsOrder> addOrder(@RequestBody LogisticsOrder order) {
        try {
            LogisticsOrder savedOrder = orderService.addOrder(order);
            return ResultDTO.success("添加订单成功", savedOrder);
        } catch (Exception e) {
            log.error("添加订单失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }

    /**
     * 更新订单
     * @param order 订单信息
     * @return 更新结果
     */
    @PutMapping
    public ResultDTO<LogisticsOrder> updateOrder(@RequestBody LogisticsOrder order) {
        try {
            LogisticsOrder updatedOrder = orderService.updateOrder(order);
            return ResultDTO.success("更新订单成功", updatedOrder);
        } catch (Exception e) {
            log.error("更新订单失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }

    /**
     * 删除订单
     * @param id 订单ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResultDTO<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResultDTO.success("删除订单成功", null);
        } catch (Exception e) {
            log.error("删除订单失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }

    /**
     * 根据ID查询订单
     * @param id 订单ID
     * @return 订单信息
     */
    @GetMapping("/{id}")
    public ResultDTO<LogisticsOrder> getOrderById(@PathVariable Long id) {
        try {
            LogisticsOrder order = orderService.getOrderById(id);
            return ResultDTO.success(order);
        } catch (Exception e) {
            log.error("查询订单失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }

    /**
     * 查询所有订单
     * @return 订单列表
     */
    @GetMapping
    public ResultDTO<List<LogisticsOrder>> getAllOrders() {
        try {
            List<LogisticsOrder> orders = orderService.getAllOrders();
            return ResultDTO.success(orders);
        } catch (Exception e) {
            log.error("查询订单列表失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }
    
    /**
     * 根据订单编号查询订单
     * @param orderNo 订单编号
     * @return 订单信息
     */
    @GetMapping("/no/{orderNo}")
    public ResultDTO<LogisticsOrder> getOrderByOrderNo(@PathVariable String orderNo) {
        try {
            LogisticsOrder order = orderService.getOrderByOrderNo(orderNo);
            return ResultDTO.success(order);
        } catch (Exception e) {
            log.error("查询订单失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }
    
    /**
     * 根据客户名称查询订单
     * @param customerName 客户名称
     * @return 订单列表
     */
    @GetMapping("/customer")
    public ResultDTO<List<LogisticsOrder>> getOrdersByCustomerName(@RequestParam String customerName) {
        try {
            List<LogisticsOrder> orders = orderService.getOrdersByCustomerName(customerName);
            return ResultDTO.success(orders);
        } catch (Exception e) {
            log.error("查询订单列表失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }
    
    /**
     * 根据客户电话查询订单
     * @param customerPhone 客户电话
     * @return 订单列表
     */
    @GetMapping("/phone")
    public ResultDTO<List<LogisticsOrder>> getOrdersByCustomerPhone(@RequestParam String customerPhone) {
        try {
            List<LogisticsOrder> orders = orderService.getOrdersByCustomerPhone(customerPhone);
            return ResultDTO.success(orders);
        } catch (Exception e) {
            log.error("查询订单列表失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }
    
    /**
     * 根据订单状态查询订单
     * @param status 订单状态
     * @return 订单列表
     */
    @GetMapping("/status/{status}")
    public ResultDTO<List<LogisticsOrder>> getOrdersByStatus(@PathVariable Integer status) {
        try {
            List<LogisticsOrder> orders = orderService.getOrdersByStatus(status);
            return ResultDTO.success(orders);
        } catch (Exception e) {
            log.error("查询订单列表失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }
    
    /**
     * 根据货物名称查询订单
     * @param goodsName 货物名称
     * @return 订单列表
     */
    @GetMapping("/goods")
    public ResultDTO<List<LogisticsOrder>> getOrdersByGoodsName(@RequestParam String goodsName) {
        try {
            List<LogisticsOrder> orders = orderService.getOrdersByGoodsName(goodsName);
            return ResultDTO.success(orders);
        } catch (Exception e) {
            log.error("查询订单列表失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }
} 