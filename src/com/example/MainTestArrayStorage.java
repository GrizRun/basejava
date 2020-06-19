package com.example;

import com.example.model.Resume;
import com.example.storage.ListStorage;
import com.example.storage.Storage;

/**
 * Test for your com.example.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new ListStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid3");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid1");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        //System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        //printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

        for (Integer i = 0; i < 10000; i++) {
            Resume r = new Resume(i.toString());
            ARRAY_STORAGE.save(r);
        }
        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllArray()) {
            System.out.println(r);
        }
    }
}
