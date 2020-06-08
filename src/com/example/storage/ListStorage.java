package com.example.storage;

import com.example.model.Resume;

public class ListStorage extends AbstractStorage {
    @Override
    public void save(Resume r) {
        
    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
