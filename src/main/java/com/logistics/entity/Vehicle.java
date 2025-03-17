package com.logistics.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 车辆实体类
 */
@Data
@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 车牌号
    @Column(nullable = false, unique = true, length = 20)
    private String plateNumber;

    // 车型
    @Column(length = 50)
    private String vehicleType;

    // 载重(吨)
    @Column(precision = 10, scale = 2)
    private BigDecimal loadCapacity;

    // 车辆状态：0-维修中，1-闲置中，2-运输中
    @Column(length = 1)
    private Integer status = 1;

    // 司机姓名
    @Column(length = 50)
    private String driverName;

    // 司机电话
    @Column(length = 20)
    private String driverPhone;

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