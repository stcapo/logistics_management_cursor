package com.logistics.controller;

import com.logistics.dto.ResultDTO;
import com.logistics.entity.User;
import com.logistics.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户
     * @param user 用户信息
     * @return 添加结果
     */
    @PostMapping
    public ResultDTO<User> addUser(@RequestBody User user) {
        try {
            User savedUser = userService.addUser(user);
            return ResultDTO.success("添加用户成功", savedUser);
        } catch (Exception e) {
            log.error("添加用户失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }

    /**
     * 更新用户
     * @param user 用户信息
     * @return 更新结果
     */
    @PutMapping
    public ResultDTO<User> updateUser(@RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(user);
            return ResultDTO.success("更新用户成功", updatedUser);
        } catch (Exception e) {
            log.error("更新用户失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }

    /**
     * 删除用户
     * @param id 用户ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResultDTO<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResultDTO.success("删除用户成功", null);
        } catch (Exception e) {
            log.error("删除用户失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public ResultDTO<User> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return ResultDTO.success(user);
        } catch (Exception e) {
            log.error("查询用户失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }

    /**
     * 查询所有用户
     * @return 用户列表
     */
    @GetMapping
    public ResultDTO<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResultDTO.success(users);
        } catch (Exception e) {
            log.error("查询用户列表失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }
} 