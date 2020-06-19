package com.example.storage;

import com.example.exception.ExistStorageException;
import com.example.exception.NotExistStorageException;
import com.example.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {


    protected static final Comparator<Resume> resumeFullNameComparator = (o1, o2) -> {
        int fullNameCompare = o1.getFullName().compareTo(o2.getFullName());
        if (fullNameCompare != 0) {
            return fullNameCompare;
        } else {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    };

    @Override
    public void save(Resume r) {
        Object key = getNotExistedSearchKey(r.getUuid());
        doSave(r, key);
    }

    @Override
    public Resume get(String uuid) {
        Object key = getExistedSearchKey(uuid);
        return doGet(uuid, key);
    }

    @Override
    public void delete(String uuid) {
        Object key = getExistedSearchKey(uuid);
        doDelete(uuid, key);
    }

    @Override
    public void update(Resume r) {
        Object key = getExistedSearchKey(r.getUuid());
        doUpdate(r, key);
    }

    @Override
    public List<Resume> getAllSorted() {
        Resume[] resumes = getAllArray();
        Arrays.sort(resumes, resumeFullNameComparator);
        return Arrays.asList(resumes);
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doSave(Resume r, Object key);

    protected abstract Resume doGet(String uuid, Object key);

    protected abstract void doDelete(String uuid, Object key);

    protected abstract void doUpdate(Resume resume, Object key);

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExist(Object searchKey);
}
