package com.example.copywritingdemo.controller;

import com.example.copywritingdemo.entity.Note;
import com.example.copywritingdemo.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // 列表
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("notes", noteService.findAll());
        return "note-list";
    }

    // 添加表单
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("note", new Note());
        return "note-form";
    }

    // 保存
    @PostMapping("/save")
    public String save(@ModelAttribute Note note) {
        noteService.save(note);
        return "redirect:/note/list";
    }

    // 编辑
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("note", noteService.findById(id));
        return "note-form";
    }

    // 删除
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }
}
