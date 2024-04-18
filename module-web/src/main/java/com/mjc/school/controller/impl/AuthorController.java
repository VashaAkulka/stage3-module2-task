package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDTO;
import com.mjc.school.service.error.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class AuthorController implements BaseController<AuthorModel, AuthorDTO, Long> {
    BaseService<AuthorModel, AuthorDTO, Long> service;

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
    public AuthorDTO create(AuthorModel createRequest) {
        try {
            return service.create(createRequest);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public AuthorDTO update(AuthorModel updateRequest) {
        try {
            return service.update(updateRequest);
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
