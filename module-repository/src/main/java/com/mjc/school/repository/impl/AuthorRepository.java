package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.data.BaseDataSource;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    BaseDataSource<AuthorModel> source;

    @Override
    public List<AuthorModel> readAll() {
        return source.read();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return source.read().stream()
                .filter(news -> news.getId().equals(id))
                .findFirst();
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        List<AuthorModel> authorModelList = source.read();
        authorModelList.add(entity);
        source.write(authorModelList);
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        List<AuthorModel> authorModelList = source.read();

        for (int i = 0; i < authorModelList.size(); i++) {
            if (authorModelList.get(i).getId().equals(entity.getId())) {
                authorModelList.set(i, entity);
                source.write(authorModelList);
                return entity;
            }
        }

        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean result;
        List<AuthorModel> authorModelList = source.read();
        result = authorModelList.removeIf(news -> news.getId().equals(id));
        source.write(authorModelList);
        return result;
    }

    @Override
    public boolean existById(Long id) {
        return source.read().stream()
                .anyMatch(news -> news.getId().equals(id));
    }
}
