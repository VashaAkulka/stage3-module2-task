package com.mjc.school.repository.data;

import com.mjc.school.repository.model.AuthorModel;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorDataSource implements BaseDataSource<AuthorModel> {
    private final String pathAuthor = "module-repository/src/main/resources/author.bin";
    private final List<AuthorModel> authorList = new ArrayList<>();
    static private AuthorDataSource instance = null;

    private AuthorDataSource() {

    }

    static public AuthorDataSource getInstance() {
        if (instance == null) {
            instance = new AuthorDataSource();
        }
        return instance;
    }

    @Override
    public List<AuthorModel> read() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathAuthor))) {
            while(true) {
                try {
                    authorList.add((AuthorModel) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (EOFException e) {
            return authorList;
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return authorList;
    }

    @Override
    public void write(List<AuthorModel> k) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathAuthor))) {
            for (AuthorModel author  : authorList) oos.writeObject(author);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
