package com.mjc.school.service;

import com.mjc.school.service.error.ValidationException;

import java.util.List;

public interface BaseService<R, K> {
    List<R> readAll();

    R readById(K id) throws ValidationException;

    R create(R createRequest) throws ValidationException;

    R update(R updateRequest, K id) throws ValidationException;

    boolean deleteById(K id) throws ValidationException;
}
