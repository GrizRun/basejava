package com.example.storage;

import com.example.model.Resume;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int storageSize = 0;

    private int findUuid(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void save(Resume r) {
        if (storageSize == Array.getLength(storage)) {
            System.out.println("Невозможно сохранить резюме. Хранилище резюме переполнено.");
            return;
        }
        if (findUuid(r.getUuid()) > -1) {
            System.out.format("Попытка сохранить резюме %s, которое уже есть в хранилище.\n", r.getUuid());
            return;
        }
        storage[storageSize] = r;
        storageSize++;
    }

    public Resume get(String uuid) {
        int index = findUuid(uuid);
        if (index == -1) {
            System.out.format("Резюме %s не найдено.\n", uuid);
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = findUuid(uuid);
        if (index == -1 ) {
            System.out.format("Резюме %s не найдено.\n", uuid);
            return;
        }
        storage[index] = storage[storageSize - 1];
        storage[storageSize - 1] = null;
        storageSize--;
    }

    public void update(Resume resume) {
        int index = findUuid(resume.getUuid());
        if (index == -1) {
            System.out.format("Резюме %s не найдено.\n", resume.getUuid());
            return;
        }
        storage[index] = resume;
    }

    public void clear() {
        if (storageSize == 0) {
            return;
        }
        Arrays.fill(storage, 0, storageSize - 1, null);
        storageSize = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
    }

    public int size() {
        return storageSize;
    }
}
