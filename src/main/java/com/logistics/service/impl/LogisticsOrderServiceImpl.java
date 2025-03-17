package com.logistics.service.impl;

import com.logistics.entity.LogisticsOrder;
import com.logistics.repository.LogisticsOrderRepository;
import com.logistics.service.LogisticsOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 物流订单服务实现类
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class LogisticsOrderServiceImpl implements LogisticsOrderService {

    @Autowired
    private LogisticsOrderRepository orderRepository;

    @Override
    public LogisticsOrder addOrder(LogisticsOrder order) {
        // 生成订单编号
        if (order.getOrderNo() == null || order.getOrderNo().trim().isEmpty()) {
            order.setOrderNo(generateOrderNo());
        } else {
            // 检查订单编号是否已存在
            Optional<LogisticsOrder> existingOrder = orderRepository.findByOrderNo(order.getOrderNo());
            if (existingOrder.isPresent()) {
                throw new RuntimeException("订单编号已存在");
            }
        }
        
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        return orderRepository.save(order);
    }

    @Override
    public LogisticsOrder updateOrder(LogisticsOrder order) {
        // 检查订单是否存在
        LogisticsOrder existingOrder = getOrderById(order.getId());
        
        // 如果修改了订单编号，检查新订单编号是否已存在
        if (!existingOrder.getOrderNo().equals(order.getOrderNo())) {
            Optional<LogisticsOrder> orderWithSameNo = orderRepository.findByOrderNo(order.getOrderNo());
            if (orderWithSameNo.isPresent()) {
                throw new RuntimeException("订单编号已存在");
            }
        }
        
        order.setUpdateTime(new Date());
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        // 检查订单是否存在
        LogisticsOrder existingOrder = getOrderById(id);
        
        orderRepository.deleteById(id);
    }

    @Override
    public LogisticsOrder getOrderById(Long id) {
        Optional<LogisticsOrder> orderOptional = orderRepository.findById(id);
        if (!orderOptional.isPresent()) {
            throw new RuntimeException("订单不存在");
        }
        return orderOptional.get();
    }

    @Override
    public List<LogisticsOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public LogisticsOrder getOrderByOrderNo(String orderNo) {
        Optional<LogisticsOrder> orderOptional = orderRepository.findByOrderNo(orderNo);
        if (!orderOptional.isPresent()) {
            throw new RuntimeException("订单不存在");
        }
        return orderOptional.get();
    }

    @Override
    public List<LogisticsOrder> getOrdersByCustomerName(String customerName) {
        return orderRepository.findByCustomerNameLike("%" + customerName + "%");
    }

    @Override
    public List<LogisticsOrder> getOrdersByCustomerPhone(String customerPhone) {
        return orderRepository.findByCustomerPhone(customerPhone);
    }

    @Override
    public List<LogisticsOrder> getOrdersByStatus(Integer status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public List<LogisticsOrder> getOrdersByGoodsName(String goodsName) {
        return orderRepository.findByGoodsNameLike("%" + goodsName + "%");
    }
    
    /**
     * 生成订单编号
     * @return 订单编号
     */
    private String generateOrderNo() {
        // 生成基于时间和UUID的订单编号
        String timeStr = String.valueOf(System.currentTimeMillis()).substring(0, 10);
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        return "LG" + timeStr + uuid;
    }
} 