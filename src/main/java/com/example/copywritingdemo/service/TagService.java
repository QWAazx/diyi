package com.example.copywritingdemo.service;

import com.example.copywritingdemo.entity.Tag;
import com.example.copywritingdemo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TagService implements BaseService<Tag> {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Page<Tag> findAll(int page, int size) {
        return tagRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Tag> search(String keyword, int page, int size) {
        return tagRepository.findByTitleContainingOrContentContaining(
                keyword, keyword, PageRequest.of(page, size));
    }

    @Override
    public Tag save(Tag entity) {
        return tagRepository.save(entity);
    }

    @Override
    public Tag findById(Integer id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        tagRepository.deleteById(id);
    }
}
