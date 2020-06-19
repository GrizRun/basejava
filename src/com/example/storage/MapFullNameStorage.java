package com.example.storage;

import com.example.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapFullNameStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getKey().equals(uuid)) {
                return entry.getValue().getFullName();
            }
        }
        return null;
    }

    @Override
    protected void doSave(Resume r, Object key) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(String uuid, Object key) {
        return storage.get(uuid);
    }

    @Override
    protected void doDelete(String uuid, Object key) {
        storage.remove(uuid);
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAllArray() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
