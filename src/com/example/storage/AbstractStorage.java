package com.example.storage;

import com.example.exception.ExistStorageException;
import com.example.exception.NotExistStorageException;
import com.example.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume r) {
        int i = getIndex(r.getUuid());
        if (i > -1) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveSecured(r, i);
        }
    }

    @Override
    public Resume get(String uuid) {
        int i = getIndex(uuid);
        if (i > -1) {
            return getSecured(uuid, i);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        int i = getIndex(uuid);
        if (i > -1) {
            deleteSecured(uuid, i);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void update(Resume r) {
        int i = getIndex(r.getUuid());
        if (i > -1) {
            updateSecured(r, i);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }


    protected abstract int getIndex (String uuid);

    protected abstract void saveSecured(Resume r, int i);

    protected abstract Resume getSecured(String uuid, int i);

    protected abstract void deleteSecured(String uuid, int i);

    protected abstract void updateSecured(Resume resume, int i);
}
