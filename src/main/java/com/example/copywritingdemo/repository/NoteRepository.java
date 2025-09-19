package com.example.copywritingdemo.repository;

import com.example.copywritingdemo.entity.Note;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends BaseRepository<Note, Integer> {
}
