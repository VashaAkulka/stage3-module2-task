package com.mjc.school.repository.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AuthorModel implements BaseEntity<Long>, Serializable {
    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
