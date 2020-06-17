package com.example.storage;

import com.example.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, currentSize, searchKey);
    }


    @Override
    protected void insertResume(Resume r, int index) {
        index =  - index - 1;
        System.arraycopy(storage, index , storage, index + 1, currentSize - index);
        storage[index] = r;
    }

    @Override
    protected void fillDeleted(int index) {
        System.arraycopy(storage, index + 1, storage, index, currentSize - 1 - index);
    }

}
