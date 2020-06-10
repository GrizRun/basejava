package com.example.storage;

import com.example.exception.ExistStorageException;
import com.example.exception.NotExistStorageException;
import com.example.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume r) {
        if (contains(r.getUuid())) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveSecured(r);
        }
    }

    @Override
    public Resume get(String uuid) {
        if (contains(uuid)) {
            return getSecured(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        if (contains(uuid)) {
            deleteSecured(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void update(Resume r) {
        if (contains(r.getUuid())) {
            updateSecured(r);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    protected abstract boolean contains(String uuid);

    protected abstract void saveSecured(Resume r);

    protected abstract Resume getSecured(String uuid);

    protected abstract void deleteSecured(String uuid);

    protected abstract void updateSecured(Resume resume);
}
