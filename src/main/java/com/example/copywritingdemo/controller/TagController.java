package com.example.copywritingdemo.controller;

import com.example.copywritingdemo.entity.Tag;
import com.example.copywritingdemo.entity.User;
import com.example.copywritingdemo.service.TagService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       @RequestParam(required = false) String keyword) {
        Page<Tag> tagPage = (keyword != null && !keyword.isEmpty())
                ? tagService.search(keyword, page, size)
                : tagService.findAll(page, size);
        model.addAttribute("tagPage", tagPage);
        model.addAttribute("keyword", keyword);
        return "tag-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("tag", new Tag());
        return "tag-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Tag tag, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null) {
            tag.setCreator(currentUser.getUsername());
        }
        tagService.save(tag);
        return "redirect:/tag/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("tag", tagService.findById(id));
        return "tag-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        tagService.deleteById(id);
        return "redirect:/tag/list";
    }
}
