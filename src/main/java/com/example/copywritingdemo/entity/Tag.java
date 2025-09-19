package com.example.copywritingdemo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String creator;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String description;

    private String remark;
}
