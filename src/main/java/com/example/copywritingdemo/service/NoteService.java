package com.example.copywritingdemo.service;

import com.example.copywritingdemo.entity.Note;
import com.example.copywritingdemo.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public Note findById(Integer id) {
        return noteRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        noteRepository.deleteById(id);
    }
}
