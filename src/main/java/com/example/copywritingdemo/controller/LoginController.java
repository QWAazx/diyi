package com.example.copywritingdemo.controller;

import com.example.copywritingdemo.entity.User;
import com.example.copywritingdemo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // 显示登录页面
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // 登录逻辑
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = userService.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("currentUser", user);
            return "redirect:/copy/list"; // 登录成功跳转到文案列表
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }

    // 注册页面
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // 注册逻辑
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        User existing = userService.findByUsername(username);
        if (existing != null) {
            model.addAttribute("error", "用户名已存在");
            return "register";
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userService.save(newUser);

        // 注册成功后跳回登录页并提示
        redirectAttributes.addFlashAttribute("msg", "注册成功，请登录！");
        return "redirect:/login";
    }

    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
