package com.example.storage;

import com.example.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, curSize, searchKey);
    }

    @Override
    public void save(Resume r) {
        if (curSize == storage.length) {
            System.out.println("Невозможно сохранить резюме. Хранилище резюме переполнено.");
            return;
        }
        if (getIndex(r.getUuid()) >= 0) {
            System.out.format("Попытка сохранить резюме %s, которое уже есть в хранилище.\n", r.getUuid());
            return;
        }
        int i;
        for (i = curSize - 1; (i >= 0 && storage[i].getUuid().compareTo(r.getUuid()) > 0); i--)
            storage[i + 1] = storage[i];
        storage[i + 1] = r;
        curSize++;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0 ) {
            System.out.format("Резюме %s не найдено.\n", uuid);
            return;
        }
        System.arraycopy(storage, index + 1, storage, index, curSize - 1 - index);
        curSize--;
    }
}
