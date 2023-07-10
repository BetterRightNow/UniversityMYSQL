package com.solvd.laba.service;

public class SolidFactory {
    public <T> T createSolid(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T instance = clazz.newInstance();
        return instance;
    }
}
