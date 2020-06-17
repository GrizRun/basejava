package com.example.storage;

import com.example.exception.StorageException;
import com.example.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import static com.example.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void saveUnderLimit()  {
        storage.clear();
        for (int i = 0; i < STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
        Assert.assertEquals(STORAGE_LIMIT, storage.size());
    }

    @Test(expected = StorageException.class)
    public void saveOverLimit() throws StorageException {
        storage.clear();
        int limit;
        limit = STORAGE_LIMIT + 1;
        for (int i = 0; i < limit; i++) {
            storage.save(new Resume());
        }
    }

}