package com.example.storage;

import com.example.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractStorage{

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < currentSize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertResume(Resume r) {
        storage[currentSize] = r;
    }

    @Override
    protected void fillVoid(int index) {
        storage[index] = storage[currentSize - 1];
    }

}
