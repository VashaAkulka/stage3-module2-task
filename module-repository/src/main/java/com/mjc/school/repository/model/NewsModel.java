package com.mjc.school.repository.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class NewsModel implements BaseEntity<Long>, Serializable {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
