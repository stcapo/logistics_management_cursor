package com.logistics.service;

import com.logistics.dto.LoginDTO;
import com.logistics.entity.User;

import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService {
    
    /**
     * 用户登录
     * @param loginDTO 登录信息
     * @return 用户信息
     */
    User login(LoginDTO loginDTO);
    
    /**
     * 添加用户
     * @param user 用户信息
     * @return 用户信息
     */
    User addUser(User user);
    
    /**
     * 更新用户
     * @param user 用户信息
     * @return 用户信息
     */
    User updateUser(User user);
    
    /**
     * 删除用户
     * @param id 用户ID
     */
    void deleteUser(Long id);
    
    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     */
    User getUserById(Long id);
    
    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> getAllUsers();
    
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    User getUserByUsername(String username);
} 