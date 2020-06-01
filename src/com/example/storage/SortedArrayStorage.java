package com.example.storage;

import com.example.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, currentSize, searchKey);
    }

    @Override
    protected void insertResume(Resume r) {
        int i;
        //ищем место, куда вставить новый элемент, чтобы сохранить сортировку
        for (i = currentSize - 1; (i >= 0 && storage[i].getUuid().compareTo(r.getUuid()) > 0); i--);
        System.arraycopy(storage, i + 1, storage, i + 2, currentSize - 1 - i);
        storage[i + 1] = r;
    }

    @Override
    protected void fillVoid(int index) {
        System.arraycopy(storage, index + 1, storage, index, currentSize - 1 - index);
    }

}
