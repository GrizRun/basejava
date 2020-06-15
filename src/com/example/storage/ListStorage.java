package com.example.storage;

import com.example.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    private static ArrayList<Resume> storage = new ArrayList<>();

    @Override
    protected void saveSecured(Resume r, int i) {
        storage.add(r);
    }

    @Override
    protected Resume getSecured(String uuid, int i) {
        return storage.get(i);
    }

    @Override
    protected void deleteSecured(String uuid, int i) {
        storage.remove(i);
    }

    @Override
    protected void updateSecured(Resume resume, int i) {
        storage.set(i, resume);
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)){
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
