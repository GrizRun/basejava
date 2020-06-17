package com.example.storage;

import com.example.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    private static ArrayList<Resume> storage = new ArrayList<>();

    @Override
    protected void doSave(Resume r, Object key) {
        storage.add(r);
    }

    @Override
    protected Resume doGet(String uuid, Object key) {
        return storage.get((int) key);
    }

    @Override
    protected void doDelete(String uuid, Object key) {
        storage.remove((int)key);
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {
        storage.set((int)key, resume);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey > -1;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
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

}
