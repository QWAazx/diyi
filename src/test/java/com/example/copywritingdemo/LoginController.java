package com.example.copywritingdemo.controller;

import com.example.copywritingdemo.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    // 用一个 Map 模拟数据库
    private Map<String, User> userStore = new HashMap<>();

    // 登录页
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html
    }

    // 登录逻辑
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = userStore.get(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("currentUser", user);
            System.out.println("✅ 登录成功 -> " + username);
            return "redirect:/copy/list";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }

    // 注册页
    @GetMapping("/register")
    public String registerPage() {
        return "register"; // register.html
    }

    // 注册逻辑
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model) {
        if (userStore.containsKey(username)) {
            model.addAttribute("error", "用户名已存在");
            return "register";
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userStore.put(username, newUser);
        System.out.println("✅ 注册成功 -> " + username);
        return "redirect:/login";
    }

    // 退出
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
