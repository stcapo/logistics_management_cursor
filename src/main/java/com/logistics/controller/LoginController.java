package com.logistics.controller;

import com.logistics.dto.LoginDTO;
import com.logistics.dto.ResultDTO;
import com.logistics.entity.User;
import com.logistics.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录控制器
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param loginDTO 登录信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResultDTO<User> login(@RequestBody LoginDTO loginDTO) {
        try {
            User user = userService.login(loginDTO);
            return ResultDTO.success("登录成功", user);
        } catch (Exception e) {
            log.error("登录失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }
    
    /**
     * 初始化管理员账号
     * @return 初始化结果
     */
    @GetMapping("/initAdmin")
    public ResultDTO<User> initAdmin() {
        try {
            // 检查是否已存在admin账号
            try {
                User existAdmin = userService.getUserByUsername("admin");
                return ResultDTO.success("管理员账号已存在", existAdmin);
            } catch (Exception e) {
                // 不存在则创建
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("123456");
                admin.setRealName("系统管理员");
                admin.setPhone("13800138000");
                admin.setEmail("admin@logistics.com");
                admin.setStatus(1);
                
                User savedAdmin = userService.addUser(admin);
                return ResultDTO.success("管理员账号初始化成功", savedAdmin);
            }
        } catch (Exception e) {
            log.error("初始化管理员账号失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }
} 