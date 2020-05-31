package com.example.storage;

import com.example.model.Resume;

import java.util.Arrays;

public abstract class AbstractStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int curSize = 0;

    public void clear() {
        Arrays.fill(storage, 0, curSize, null);
        curSize = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, curSize);
    }

    public int size() { return curSize; }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.format("Резюме %s не найдено.\n", uuid);
            return null;
        }
        return storage[index];
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.format("Резюме %s не найдено.\n", resume.getUuid());
            return;
        }
        storage[index] = resume;
    }

    protected abstract int getIndex(String uuid);
}
