package com.logistics.service.impl;

import com.logistics.dto.LoginDTO;
import com.logistics.entity.User;
import com.logistics.repository.UserRepository;
import com.logistics.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 用户服务实现类
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(LoginDTO loginDTO) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(
                loginDTO.getUsername(), loginDTO.getPassword());
        if (!userOptional.isPresent()) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        User user = userOptional.get();
        if (user.getStatus() != 1) {
            throw new RuntimeException("用户已被禁用");
        }
        
        return user;
    }

    @Override
    public User addUser(User user) {
        // 检查用户名是否已存在
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("用户名已存在");
        }
        
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        // 检查用户是否存在
        User existingUser = getUserById(user.getId());
        
        // 如果修改了用户名，检查新用户名是否已存在
        if (!existingUser.getUsername().equals(user.getUsername())) {
            Optional<User> userWithSameUsername = userRepository.findByUsername(user.getUsername());
            if (userWithSameUsername.isPresent()) {
                throw new RuntimeException("用户名已存在");
            }
        }
        
        user.setUpdateTime(new Date());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        // 检查用户是否存在
        User existingUser = getUserById(id);
        
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("用户不存在");
        }
        return userOptional.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("用户不存在");
        }
        return userOptional.get();
    }
} 