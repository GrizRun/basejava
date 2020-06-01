package com.example.storage;

import static org.junit.Assert.*;

public class ArrayStorageTest extends AbstractStorageTest {
    private static ArrayStorage arrayStorage = new ArrayStorage();

    public ArrayStorageTest() {
        super(arrayStorage);
    }
}