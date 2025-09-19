package com.example.copywritingdemo.controller;

import com.example.copywritingdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    // 注册页面
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // 注册逻辑
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "两次输入的密码不一致");
            return "register";
        }

        if (userService.exists(username)) {
            model.addAttribute("error", "用户名已存在");
            return "register";
        }

        userService.register(username, password);
        return "redirect:/login?registered=true";
    }
}
