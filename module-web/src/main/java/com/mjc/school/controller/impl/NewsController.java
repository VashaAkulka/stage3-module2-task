package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.error.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class NewsController implements BaseController<NewsModel, NewsDTO, Long> {

    BaseService<NewsModel, NewsDTO, Long> service;

    @Override
    public List<NewsDTO> readAll() {
        System.out.println(service.readAll());
        return service.readAll();
    }

    @Override
    public NewsDTO readById(Long id) {
        try {
            System.out.println(service.readById(id));
            return service.readById(id);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public NewsDTO create(NewsModel createRequest) {
        try {
            NewsDTO newsDTO = service.create(createRequest);
            System.out.println("Create success");
            return newsDTO;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public NewsDTO update(NewsModel updateRequest) {
        try {
            NewsDTO newsDTO = service.update(updateRequest);
            System.out.println("Update success");
            return newsDTO;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            boolean result = service.deleteById(id);
            System.out.println("Delete success");
            return result;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
