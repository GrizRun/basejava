package com.example;

import com.example.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class mainReflection {
    public static void main(String[] args) throws IllegalAccessError, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Resume r = new Resume();
        System.out.println(r);
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        field.set(r, "test reflection");
        Method method = r.getClass().getDeclaredMethod("toString", null);
        System.out.println(method.invoke(r, null));
    }
}
