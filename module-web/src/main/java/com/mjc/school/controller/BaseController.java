package com.mjc.school.controller;

import java.util.List;

public interface BaseController<R, K> {

    List<R> readAll();

    R readById(K id);

    R create(R createRequest);

    R update(R updateRequest, Long id);

    boolean deleteById(K id);
}
