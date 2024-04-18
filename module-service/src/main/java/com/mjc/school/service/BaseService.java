package com.mjc.school.service;

import com.mjc.school.service.error.ValidationException;

import java.util.List;

public interface BaseService<T, R, K> {
    List<R> readAll();

    R readById(K id) throws ValidationException;

    R create(T createRequest) throws ValidationException;

    R update(T updateRequest) throws ValidationException;

    boolean deleteById(K id) throws ValidationException;
}
