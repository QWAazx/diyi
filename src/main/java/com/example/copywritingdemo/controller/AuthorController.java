package com.example.copywritingdemo.controller;

import com.example.copywritingdemo.entity.Author;
import com.example.copywritingdemo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Author> authorPage = authorService.findAll(page, 10);
        model.addAttribute("authorPage", authorPage);
        return "author-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("author", new Author());
        return "author-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Author author) {
        authorService.save(author);
        return "redirect:/author/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        authorService.delete(id);
        return "redirect:/author/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "author-form";
    }
}
