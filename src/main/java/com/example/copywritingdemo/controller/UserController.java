package com.example.copywritingdemo.controller;

import com.example.copywritingdemo.entity.User;
import com.example.copywritingdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 用户列表
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }

    // 添加用户表单
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    // 保存用户
    @PostMapping("/save")
    public String save(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/user/list";
    }

    // 编辑用户
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user-form";
    }

    // 删除用户
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return "redirect:/user/list";
    }
}
