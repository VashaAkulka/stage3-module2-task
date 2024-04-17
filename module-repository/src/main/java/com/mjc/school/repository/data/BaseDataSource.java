package com.mjc.school.repository.data;

import java.util.List;

public interface BaseDataSource<K> {
    List<K> read();
    void write(List<K> list);
}
