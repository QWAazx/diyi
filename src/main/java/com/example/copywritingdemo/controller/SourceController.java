package com.example.copywritingdemo.controller;

import com.example.copywritingdemo.entity.Source;
import com.example.copywritingdemo.service.SourceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/source")
public class SourceController {

    private final SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    // 列表
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("sources", sourceService.findAll());
        return "source-list";
    }

    // 添加表单
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("source", new Source());
        return "source-form";
    }

    // 保存
    @PostMapping("/save")
    public String save(@ModelAttribute Source source) {
        sourceService.save(source);
        return "redirect:/source/list";
    }

    // 编辑
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("source", sourceService.findById(id));
        return "source-form";
    }

    // 删除
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        sourceService.deleteById(id);
        return "redirect:/source/list";
    }
}
