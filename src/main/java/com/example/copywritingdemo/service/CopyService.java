package com.example.copywritingdemo.service;

import com.example.copywritingdemo.entity.Copy;
import com.example.copywritingdemo.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CopyService {

    @Autowired
    private CopyRepository copyRepository;

    /**
     * 分页查询全部（不带关键字）
     * @param page 0-based 页码
     * @param size 每页条数
     * @return Page<Copy>
     */
    public Page<Copy> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return copyRepository.findAll(pageable);
    }

    /**
     * 带关键字的分页搜索（在 title 或 content 字段中模糊匹配）
     * @param keyword 搜索关键字
     * @param page 0-based 页码
     * @param size 每页条数
     * @return Page<Copy>
     */
    public Page<Copy> search(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return copyRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);
    }

    /**
     * 保存（新增/更新）
     */
    public Copy save(Copy copy) {
        return copyRepository.save(copy);
    }

    /**
     * 根据 id 查询
     */
    public Copy findById(Integer id) {
        Optional<Copy> opt = copyRepository.findById(id);
        return opt.orElse(null);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        copyRepository.deleteById(id);
    }
}
