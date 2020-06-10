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
    public Resume[] getAll() {
        return Arrays.copyOf(storage, currentSize);
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    protected void saveSecured(Resume r) {
        if (currentSize == storage.length) {
            throw new StorageException("Storage is overflow.", r.getUuid());
        }
        insertResume(r, getIndex(r.getUuid()));
        currentSize++;
    }

    @Override
    protected Resume getSecured(String uuid) {
        return storage[getIndex(uuid)];
    }

    @Override
    protected void deleteSecured(String uuid) {
        fillDeleted(getIndex(uuid));
        storage[currentSize - 1] = null;
        currentSize--;
    }

    @Override
    protected void updateSecured(Resume resume) {
        storage[getIndex(resume.getUuid())] = resume;
    }


    @Override
    protected boolean contains(String uuid) {
        return getIndex(uuid) >= 0;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume r, int index);

    protected abstract void fillDeleted(int index);

}
