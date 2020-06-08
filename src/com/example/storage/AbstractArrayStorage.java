package com.example.storage;

import com.example.exception.ExistStorageException;
import com.example.exception.NotExistStorageException;
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
    public void save(Resume r) {
        if (currentSize == storage.length) {
            throw new StorageException("Storage is overflow.", r.getUuid());
        }
        int index = getIndex(r.getUuid());
        if (index > -1) {
            throw new ExistStorageException(r.getUuid());
        }
        insertResume(r, index);
        currentSize++;
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        storage[index] = resume;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        fillDeleted(index);
        storage[currentSize - 1] = null;
        currentSize--;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume r, int index);

    protected abstract void fillDeleted(int index);

}
