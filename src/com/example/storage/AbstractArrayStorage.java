package com.example.storage;

import com.example.exception.StorageException;
import com.example.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int currentSize = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, currentSize, null);
        currentSize = 0;
    }

    @Override
    public Resume[] getAllArray() {
        return Arrays.copyOf(storage, currentSize);
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    protected void doSave(Resume r, Object i) {
        if (currentSize == storage.length) {
            throw new StorageException("Storage is overflow.", r.getUuid());
        }
        insertResume(r, (Integer) i);
        currentSize++;
    }

    @Override
    protected Resume doGet(String uuid, Object i) {
        return storage[(int) i];
    }

    @Override
    protected void doDelete(String uuid, Object i) {
        fillDeleted((Integer) i);
        storage[currentSize - 1] = null;
        currentSize--;
    }

    @Override
    protected void doUpdate(Resume resume, Object i) {
        storage[(int) i] = resume;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey > -1;
    }


    protected abstract Object getSearchKey(String uuid);

    protected abstract void insertResume(Resume r, int index);

    protected abstract void fillDeleted(int index);

}
