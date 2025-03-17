package com.logistics.repository;

import com.logistics.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 用户数据访问接口
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户对象
     */
    Optional<User> findByUsername(String username);
    
    /**
     * 根据用户名和密码查找用户
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    Optional<User> findByUsernameAndPassword(String username, String password);
    
    /**
     * 根据用户名和状态查找用户
     * @param username 用户名
     * @param status 状态
     * @return 用户对象
     */
    Optional<User> findByUsernameAndStatus(String username, Integer status);
} 