package com.example.copywritingdemo.repository;

import com.example.copywritingdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    // 根据用户名查找用户
    User findByUsername(String username);
}
