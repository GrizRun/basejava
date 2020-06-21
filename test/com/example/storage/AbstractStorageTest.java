package com.example.storage;

import com.example.exception.ExistStorageException;
import com.example.exception.NotExistStorageException;
import com.example.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class AbstractStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_1 = new Resume("Ivanov Ivan", UUID_1);
    private static final Resume RESUME_2 = new Resume("Ivanov Ivan", UUID_2);
    private static final Resume RESUME_3 = new Resume("DefaultName", UUID_3);
    protected Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);

    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void getAllSorted() {
        List<Resume> actualResume = storage.getAllSorted();
        List<Resume> expectedResume = Arrays.asList(RESUME_3, RESUME_1, RESUME_2);
        Assert.assertEquals(expectedResume, actualResume);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
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
        Resume resume = new Resume("EmptyName","uuid1");
        storage.save(resume);
    }

    @Test
    public void saveNewResume() {
        Resume resume = new Resume(UUID.randomUUID().toString());
        storage.save(resume);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }


    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws NotExistStorageException {
        Resume dummy = new Resume("dummy");
        storage.update(dummy);
    }

    @Test
    public void updateWell() throws NotExistStorageException {
        Resume r1 = new Resume("EmptyName2", UUID_1);
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

}