package com.example.storage;

import com.example.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < currentSize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    protected void insertResume(Resume r, int index) {
        storage[currentSize] = r;
    }

    @Override
    protected void fillDeleted(int index) {
        storage[index] = storage[currentSize - 1];
    }

}
