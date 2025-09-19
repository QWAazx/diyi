package com.example.copywritingdemo.service;

import org.springframework.data.domain.Page;

public interface BaseService<T> {

    Page<T> findAll(int page, int size);

    Page<T> search(String keyword, int page, int size);

    T save(T entity);

    T findById(Integer id);

    void deleteById(Integer id);
}
