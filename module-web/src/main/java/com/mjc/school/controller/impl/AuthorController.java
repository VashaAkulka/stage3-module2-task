package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDTO;
import com.mjc.school.service.error.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class AuthorController implements BaseController<AuthorDTO, Long> {
    BaseService<AuthorDTO, Long> service;

    @Override
    public List<AuthorDTO> readAll() {
        System.out.println(service.readAll());
        return service.readAll();
    }

    @Override
    public AuthorDTO readById(Long id) {
        try {
            System.out.println(service.readById(id));
            return service.readById(id);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public AuthorDTO create(AuthorDTO createRequest) {
        try {
            return service.create(createRequest);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public AuthorDTO update(AuthorDTO updateRequest, Long id) {
        try {
            return service.update(updateRequest, id);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            return service.deleteById(id);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
