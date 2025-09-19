package com.example.copywritingdemo.service;

import com.example.copywritingdemo.entity.Source;
import com.example.copywritingdemo.repository.SourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceService {
    private final SourceRepository sourceRepository;

    public SourceService(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    public List<Source> findAll() {
        return sourceRepository.findAll();
    }

    public Source save(Source source) {
        return sourceRepository.save(source);
    }

    public Source findById(Integer id) {
        return sourceRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        sourceRepository.deleteById(id);
    }
}
