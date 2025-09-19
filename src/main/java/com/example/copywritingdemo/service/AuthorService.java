package com.example.copywritingdemo.service;

import com.example.copywritingdemo.entity.Author;
import com.example.copywritingdemo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Page<Author> findAll(int page, int size) {
        return authorRepository.findAll(PageRequest.of(page, size));
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public void delete(Integer id) {
        authorRepository.deleteById(id);
    }

    public Author findById(Integer id) {
        return authorRepository.findById(id).orElse(null);
    }
}
