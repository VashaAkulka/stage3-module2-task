package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.error.ErrorCode;
import com.mjc.school.service.error.ValidationException;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validate.BaseValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalLong;

@Service
@AllArgsConstructor
public class NewsService implements BaseService<NewsModel, NewsDTO, Long> {
    private BaseValidation<NewsModel> validation;
    private BaseRepository<NewsModel, Long> repository;

    @Override
    public List<NewsDTO> readAll() {
        return NewsMapper.INSTANCE.newsListToNewsDtoList(repository.readAll());
    }

    @Override
    public NewsDTO readById(Long id) {
        try {
            return NewsMapper.INSTANCE.newsToNewsDto(repository.readById(id).orElseThrow(() -> new ValidationException(ErrorCode.NO_SUCH_NEWS.getErrorData())));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public NewsDTO create(NewsModel createRequest) {
        try {
            validation.validate(createRequest);

            OptionalLong maxId = repository.readAll()
                    .stream()
                    .mapToLong(NewsModel::getId)
                    .max();

            long nextId = maxId.orElse(0) + 1;
            createRequest.setId(nextId);

            createRequest.setCreateDate(LocalDateTime.now());
            createRequest.setLastUpdateDate(LocalDateTime.now());

            return NewsMapper.INSTANCE.newsToNewsDto(repository.create(createRequest));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public NewsDTO update(NewsModel updateRequest) {
        try {
            validation.validate(updateRequest);

            updateRequest.setLastUpdateDate(LocalDateTime.now());
            NewsModel news = repository.update(updateRequest);
            if (news == null) throw new ValidationException(ErrorCode.NO_SUCH_NEWS.getErrorData());
            return NewsMapper.INSTANCE.newsToNewsDto(news);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            if (!repository.deleteById(id)) throw new ValidationException(ErrorCode.NO_SUCH_NEWS.getErrorData());
            return true;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
