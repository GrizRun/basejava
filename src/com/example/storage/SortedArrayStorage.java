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
    protected void insertResume(Resume r) {
        int i;
        for (i = curSize - 1; (i >= 0 && storage[i].getUuid().compareTo(r.getUuid()) > 0); i--)
            storage[i + 1] = storage[i];
        storage[i + 1] = r;
        curSize++;
    }

    @Override
    protected void fillVoid(int index) {
        System.arraycopy(storage, index + 1, storage, index, curSize - 1 - index);
        curSize--;
    }

}
