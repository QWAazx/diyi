package com.example.copywritingdemo.repository;

import com.example.copywritingdemo.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    // 根据标题或内容模糊搜索
    Page<Tag> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
