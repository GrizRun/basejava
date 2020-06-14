package com.example.storage;

import com.example.exception.NotExistStorageException;
import com.example.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    private static ArrayList<Resume> storage = new ArrayList<>();

    @Override
    protected void saveSecured(Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume getSecured(String uuid) {
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)){
                return r;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    protected void deleteSecured(String uuid) {
        storage.removeIf(r -> r.getUuid().equals(uuid));
    }

    @Override
    protected void updateSecured(Resume resume) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).equals(resume)){
                storage.set(i, resume);
                return;
            }
        }
        throw new NotExistStorageException(resume.getUuid());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean contains(String uuid) {
        for (Resume resume : storage) {
            if (resume.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

}
