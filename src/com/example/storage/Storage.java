package com.example.storage;

import com.example.model.Resume;

public interface Storage {
    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    void update(Resume resume);

    void clear();

    Resume[] getAll();

    int size();
}
