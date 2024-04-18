package com.mjc.school.service.validate;

import com.mjc.school.service.error.ValidationException;

public interface BaseValidation<T> {
    void validate(T t) throws ValidationException;
}
