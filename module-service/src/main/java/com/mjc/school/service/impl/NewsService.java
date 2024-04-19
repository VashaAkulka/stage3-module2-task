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
public class NewsService implements BaseService<NewsDTO, Long> {
    private BaseValidation<NewsDTO> validation;
    private BaseRepository<NewsModel, Long> repository;

    @Override
    public List<NewsDTO> readAll() {
        return NewsMapper.INSTANCE.newsListToNewsDtoList(repository.readAll());
    }

    @Override
    public NewsDTO readById(Long id) throws ValidationException {
        return NewsMapper.INSTANCE.newsToNewsDto(repository.readById(id).orElseThrow(() -> new ValidationException(ErrorCode.NO_SUCH_NEWS.getErrorData())));
    }

    @Override
    public NewsDTO create(NewsDTO createRequest) throws ValidationException {
        validation.validate(createRequest);

        OptionalLong maxId = repository.readAll()
                .stream()
                .mapToLong(NewsModel::getId)
                .max();

        NewsModel newsModel = new NewsModel();
        long nextId = maxId.orElse(0) + 1;
        newsModel.setId(nextId);

        newsModel.setCreateDate(LocalDateTime.now());
        newsModel.setLastUpdateDate(LocalDateTime.now());

        newsModel.setTitle(createRequest.getTitle());
        newsModel.setContent(createRequest.getContent());
        newsModel.setAuthorId(createRequest.getAuthorId());

        return NewsMapper.INSTANCE.newsToNewsDto(repository.create(newsModel));
    }

    @Override
    public NewsDTO update(NewsDTO updateRequest, Long id) throws ValidationException {
        validation.validate(updateRequest);

        NewsModel newsModel = new NewsModel();
        newsModel.setLastUpdateDate(LocalDateTime.now());
        newsModel.setAuthorId(updateRequest.getAuthorId());
        newsModel.setTitle(updateRequest.getTitle());
        newsModel.setContent(updateRequest.getContent());

        if (!repository.existById(id)) throw new ValidationException(ErrorCode.NO_SUCH_NEWS.getErrorData());
        return NewsMapper.INSTANCE.newsToNewsDto(repository.update(newsModel, id));
    }

    @Override
    public boolean deleteById(Long id) throws ValidationException {
        if (!repository.existById(id)) throw new ValidationException(ErrorCode.NO_SUCH_NEWS.getErrorData());
        return repository.deleteById(id);
    }
}
