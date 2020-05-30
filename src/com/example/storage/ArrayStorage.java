package com.example.storage;

import com.example.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int storageSize = 0;

    private Integer findUuid(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    public void save(Resume r) {
        if (storageSize == 10000) {
            System.out.println("Невозможно сохранить резюме. Хранилище резюме переполнено.");
            return;
        }
        if (findUuid(r.getUuid()) != null) {
            System.out.format("Попытка сохранить резюме %s, которое уже есть в хранилище.\n", r.getUuid());
            return;
        }
        storage[storageSize] = r;
        storageSize++;
    }

    public Resume get(String uuid) {
        Integer f = findUuid(uuid);
        if (f != null) {
            return storage[f];
        } else {
            System.out.format("Резюме %s не найдено.\n", uuid);
            return null;
        }
    }

    public void delete(String uuid) {
        Integer f = findUuid(uuid);
        if (f != null) {
            storage[f] = storage[storageSize - 1];
            storage[storageSize - 1] = null;
            storageSize--;
        } else {
            System.out.format("Резюме %s не найдено.\n", uuid);
        }
    }

    public void update(Resume resume) {
        Integer f = findUuid(resume.getUuid());
        if (f != null) {
            storage[f] = resume;
        } else {
            System.out.format("Резюме %s не найдено.\n", resume.getUuid());
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, storageSize - 1, null);
        storageSize--;
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
