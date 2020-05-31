package com.example.storage;

import com.example.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractStorage{

    @Override
    public void save(Resume r) {
        if (curSize == storage.length) {
            System.out.println("Невозможно сохранить резюме. Хранилище резюме переполнено.");
            return;
        }
        if (getIndex(r.getUuid()) > -1) {
            System.out.format("Попытка сохранить резюме %s, которое уже есть в хранилище.\n", r.getUuid());
            return;
        }
        storage[curSize] = r;
        curSize++;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1 ) {
            System.out.format("Резюме %s не найдено.\n", uuid);
            return;
        }
        storage[index] = storage[curSize - 1];
        storage[curSize - 1] = null;
        curSize--;
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < curSize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
