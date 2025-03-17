package com.logistics.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 */
@Data
@Entity
@Table(name = "sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 用户名
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    // 密码
    @Column(nullable = false)
    private String password;

    // 真实姓名
    @Column(length = 50)
    private String realName;

    // 手机号
    @Column(length = 20)
    private String phone;

    // 邮箱
    @Column(length = 100)
    private String email;

    // 状态：0-禁用，1-启用
    @Column(length = 1)
    private Integer status = 1;

    // 创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    // 更新时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime = new Date();
} 