package com.example.tasklist.util;

import com.example.tasklist.entity.Task;

import java.lang.reflect.Field;

public class MergeObject<T> {

    private T local;
    private T remote;

    public MergeObject(T local, T remote) {
        this.local = local;
        this.remote = remote;
    }

    public <T> T merge() {
        Class<?> clazz = local.getClass();
        Object merged = null;
        try {
            merged = clazz.newInstance();

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                field.set(merged, (field.get(local) != null) ? field.get(local) : field.get(remote));
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return (T) merged;
    }
}
