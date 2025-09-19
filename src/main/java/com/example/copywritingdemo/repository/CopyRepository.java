package com.example.copywritingdemo.repository;

import com.example.copywritingdemo.entity.Copy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyRepository extends JpaRepository<Copy, Integer> {

    // 标题或内容模糊搜索（与上面的 CopyService.search 配合）
    Page<Copy> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
