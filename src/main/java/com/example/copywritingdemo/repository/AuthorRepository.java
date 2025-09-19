package com.example.copywritingdemo.repository;

import com.example.copywritingdemo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
