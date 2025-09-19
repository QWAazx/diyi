package com.example.copywritingdemo.repository;

import com.example.copywritingdemo.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 公共 Repository 基类
 * 注意：必须加 @NoRepositoryBean，避免 Spring 去实例化它
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {
}
