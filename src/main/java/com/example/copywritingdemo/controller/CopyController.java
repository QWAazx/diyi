package com.example.copywritingdemo.controller;

import com.example.copywritingdemo.entity.Copy;
import com.example.copywritingdemo.entity.User;
import com.example.copywritingdemo.service.CopyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/copy")
public class CopyController {

    @Autowired
    private CopyService copyService;

    // 列表 + 搜索 + 分页
    @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword,
            Model model) {

        Page<Copy> copyPage;
        if (keyword != null && !keyword.isEmpty()) {
            copyPage = copyService.search(keyword, page, size);
        } else {
            copyPage = copyService.findAll(page, size);
        }

        model.addAttribute("copyPage", copyPage);
        model.addAttribute("keyword", keyword);

        return "copy-list";
    }

    // 添加表单
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("copy", new Copy());
        return "copy-form";
    }

    // 保存
    @PostMapping("/save")
    public String save(@ModelAttribute Copy copy, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null) {
            copy.setCreator(currentUser.getUsername()); // 自动写入创建者
        }
        copyService.save(copy);
        return "redirect:/copy/list";
    }

    // 编辑
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("copy", copyService.findById(id));
        return "copy-form";
    }

    // 删除
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        copyService.deleteById(id);
        return "redirect:/copy/list";
    }
}
