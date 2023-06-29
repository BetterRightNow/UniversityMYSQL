package com.solvd.laba.service;

public class JsonWrapper {
    private String className;
    private Object object;

    public JsonWrapper() {
    }

    public JsonWrapper(String className, Object object) {
        this.className = className;
        this.object = object;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
