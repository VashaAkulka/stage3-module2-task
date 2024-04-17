package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.data.BaseDataSource;
import com.mjc.school.repository.model.NewsModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    BaseDataSource<NewsModel> source;

    @Override
    public List<NewsModel> readAll() {
        return source.read();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return source.read().stream()
                .filter(news -> news.getId().equals(id))
                .findFirst();
    }

    @Override
    public NewsModel create(NewsModel entity) {
        List<NewsModel> newsModelList = source.read();
        newsModelList.add(entity);
        source.write(newsModelList);
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        List<NewsModel> newsModelList = source.read();

        for (int i = 0; i < newsModelList.size(); i++) {
            if (newsModelList.get(i).getId().equals(entity.getId())) {
                newsModelList.set(i, entity);
                source.write(newsModelList);
                return entity;
            }
        }

        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean result;
        List<NewsModel> newsModelList = source.read();
        result = newsModelList.removeIf(news -> news.getId().equals(id));
        source.write(newsModelList);
        return result;
    }

    @Override
    public boolean existById(Long id) {
        return source.read().stream()
                .anyMatch(news -> news.getId().equals(id));
    }
}
