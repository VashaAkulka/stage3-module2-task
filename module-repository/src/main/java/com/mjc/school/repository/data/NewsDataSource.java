package com.mjc.school.repository.data;

import com.mjc.school.repository.model.NewsModel;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsDataSource implements BaseDataSource<NewsModel> {
    private final String pathNews = "module-repository/src/main/resources/news.bin";
    private final List<NewsModel> newsList = new ArrayList<>();
    static private NewsDataSource instance = null;

    private NewsDataSource() {

    }

    static public NewsDataSource getInstance() {
        if (instance == null) {
            instance = new NewsDataSource();
        }
        return instance;
    }

    @Override
    public List<NewsModel> read() {

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathNews))) {
            while(true) {
                try {
                    newsList.add((NewsModel) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (EOFException e) {
            return newsList;
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return newsList;
    }

    @Override
    public void write(List<NewsModel> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathNews))) {
            for (NewsModel news : newsList) oos.writeObject(news);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
