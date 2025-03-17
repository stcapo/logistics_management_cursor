package com.logistics.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 物流订单实体类
 */
@Data
@Entity
@Table(name = "logistics_order")
public class LogisticsOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 订单编号
    @Column(nullable = false, unique = true, length = 32)
    private String orderNo;

    // 客户名称
    @Column(nullable = false, length = 100)
    private String customerName;

    // 客户电话
    @Column(nullable = false, length = 20)
    private String customerPhone;

    // 发货地址
    @Column(nullable = false, length = 255)
    private String fromAddress;

    // 收货地址
    @Column(nullable = false, length = 255)
    private String toAddress;

    // 货物名称
    @Column(nullable = false, length = 100)
    private String goodsName;

    // 货物重量(kg)
    @Column(precision = 10, scale = 2)
    private BigDecimal weight;

    // 货物体积(m³)
    @Column(precision = 10, scale = 2)
    private BigDecimal volume;

    // 运费
    @Column(precision = 10, scale = 2)
    private BigDecimal freight;

    // 订单状态：0-待分配，1-已分配，2-运输中，3-已完成，4-已取消
    @Column(length = 1)
    private Integer status = 0;

    // 备注
    @Column(length = 500)
    private String remark;

    // 创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    // 更新时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime = new Date();
} 