package com.example.storage;

import com.example.model.Resume;

import java.util.List;

public interface Storage {
    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    void update(Resume resume);

    void clear();

    Resume[] getAllArray();

    List<Resume> getAllSorted();

    int size();
}
