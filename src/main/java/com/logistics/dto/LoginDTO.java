package com.logistics.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录数据传输对象
 */
@Data
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
} 