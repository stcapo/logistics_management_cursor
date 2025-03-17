package com.logistics.service;

import com.logistics.entity.LogisticsOrder;

import java.util.List;

/**
 * 物流订单服务接口
 */
public interface LogisticsOrderService {
    
    /**
     * 添加订单
     * @param order 订单信息
     * @return 订单信息
     */
    LogisticsOrder addOrder(LogisticsOrder order);
    
    /**
     * 更新订单
     * @param order 订单信息
     * @return 订单信息
     */
    LogisticsOrder updateOrder(LogisticsOrder order);
    
    /**
     * 删除订单
     * @param id 订单ID
     */
    void deleteOrder(Long id);
    
    /**
     * 根据ID查询订单
     * @param id 订单ID
     * @return 订单信息
     */
    LogisticsOrder getOrderById(Long id);
    
    /**
     * 查询所有订单
     * @return 订单列表
     */
    List<LogisticsOrder> getAllOrders();
    
    /**
     * 根据订单编号查询订单
     * @param orderNo 订单编号
     * @return 订单信息
     */
    LogisticsOrder getOrderByOrderNo(String orderNo);
    
    /**
     * 根据客户名称模糊查询订单
     * @param customerName 客户名称
     * @return 订单列表
     */
    List<LogisticsOrder> getOrdersByCustomerName(String customerName);
    
    /**
     * 根据客户电话查询订单
     * @param customerPhone 客户电话
     * @return 订单列表
     */
    List<LogisticsOrder> getOrdersByCustomerPhone(String customerPhone);
    
    /**
     * 根据订单状态查询订单
     * @param status 订单状态
     * @return 订单列表
     */
    List<LogisticsOrder> getOrdersByStatus(Integer status);
    
    /**
     * 根据货物名称模糊查询订单
     * @param goodsName 货物名称
     * @return 订单列表
     */
    List<LogisticsOrder> getOrdersByGoodsName(String goodsName);
} 