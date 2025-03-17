package com.logistics.repository;

import com.logistics.entity.LogisticsOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 物流订单数据访问接口
 */
@Repository
public interface LogisticsOrderRepository extends JpaRepository<LogisticsOrder, Long> {
    
    /**
     * 根据订单编号查找订单
     * @param orderNo 订单编号
     * @return 订单对象
     */
    Optional<LogisticsOrder> findByOrderNo(String orderNo);
    
    /**
     * 根据客户名称查找订单列表
     * @param customerName 客户名称
     * @return 订单列表
     */
    List<LogisticsOrder> findByCustomerNameLike(String customerName);
    
    /**
     * 根据客户电话查找订单列表
     * @param customerPhone 客户电话
     * @return 订单列表
     */
    List<LogisticsOrder> findByCustomerPhone(String customerPhone);
    
    /**
     * 根据订单状态查找订单列表
     * @param status 订单状态
     * @return 订单列表
     */
    List<LogisticsOrder> findByStatus(Integer status);
    
    /**
     * 根据货物名称模糊查询订单列表
     * @param goodsName 货物名称
     * @return 订单列表
     */
    List<LogisticsOrder> findByGoodsNameLike(String goodsName);
} 