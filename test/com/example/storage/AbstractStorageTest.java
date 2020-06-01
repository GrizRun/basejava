package com.example.storage;

import com.example.exception.ExistStorageException;
import com.example.exception.NotExistStorageException;
import com.example.exception.StorageException;
import com.example.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

public abstract class AbstractStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume[] RESUME_ARRAY = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
    private Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));

    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] returnGetAll;
        returnGetAll = storage.getAll();
        Assert.assertEquals(3, returnGetAll.length);
        Assert.assertArrayEquals(RESUME_ARRAY, returnGetAll);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
        Resume[] emptyStorage = new Resume[getStorageLimit()];
        Assert.assertArrayEquals(emptyStorage, getStorage());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws NotExistStorageException {
        storage.get("dummy");
    }

    @Test
    public void getExist() {
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
        Assert.assertEquals(RESUME_3, storage.get(UUID_3));
        Assert.assertEquals(RESUME_2, storage.get(UUID_2));
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() throws ExistStorageException {
        Resume uuid1 = new Resume("uuid1");
        storage.save(uuid1);
    }

    @Test
    public void saveWell() {
        Resume r = new Resume();
        storage.save(r);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }

    @Test
    public void saveUnderLimit() throws NoSuchFieldException, IllegalAccessException {
        storage.clear();
        int limit = getStorageLimit();
        for (int i = 0; i < limit; i++) {
            storage.save(new Resume());
        }
        Assert.assertEquals(limit, storage.size());
    }

    @Test(expected = StorageException.class)
    public void saveOverLimit() throws NoSuchFieldException, IllegalAccessException, StorageException {
        storage.clear();
        int limit;
        limit = getStorageLimit() + 1;
        for (int i = 0; i < limit; i++) {
            storage.save(new Resume());
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws NotExistStorageException {
        Resume dummy = new Resume("dummy");
        storage.update(dummy);
    }

    @Test
    public void updateWell() throws NotExistStorageException {
        Resume r1 = new Resume(UUID_1);
        storage.update(r1);
        Assert.assertEquals(3, storage.size());
        Assert.assertEquals(r1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws NotExistStorageException {
        storage.delete("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteWell() throws NotExistStorageException {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    private int getStorageLimit() throws NoSuchFieldException, IllegalAccessException {
        Field storageLimit = storage.getClass().getSuperclass().getDeclaredField("STORAGE_LIMIT");
        storageLimit.setAccessible(true);
        return (int) storageLimit.get(storage);
    }

    private Resume[] getStorage() throws NoSuchFieldException, IllegalAccessException {
        Field storageLimit = storage.getClass().getSuperclass().getDeclaredField("storage");
        storageLimit.setAccessible(true);
        return (Resume[]) storageLimit.get(storage);
    }
}