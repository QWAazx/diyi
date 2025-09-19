package com.example.copywritingdemo.service;

import com.example.copywritingdemo.entity.User;
import com.example.copywritingdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 查询所有用户
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // 根据 ID 查询用户
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    // 保存用户
    public User save(User user) {
        return userRepository.save(user);
    }

    // 删除用户
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    // 根据用户名查询用户（登录/注册时用）
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
