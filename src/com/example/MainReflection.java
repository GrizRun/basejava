package com.example;

import com.example.model.Resume;
import com.example.storage.ArrayStorage;
import com.example.storage.Storage;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessError, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Storage storage = new ArrayStorage();
        Field storageLimit = storage.getClass().getSuperclass().getDeclaredField("STORAGE_LIMIT");
        storageLimit.setAccessible(true);
        System.out.println((int) storageLimit.get(storage));



        Resume r = new Resume();
        System.out.println(r);
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        field.set(r, "test reflection");
        Method method = r.getClass().getDeclaredMethod("toString");
        System.out.println(method.invoke(r));
    }
}
