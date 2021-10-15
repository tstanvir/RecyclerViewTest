package com.tstanvir.recyclerviewtest.model;

public class UserInfo {
    private int id;
    private String name;
    private String handle;

    public UserInfo() {

    }

    public UserInfo(String name, String handle) {
        this.name = name;
        this.handle = handle;
    }

    public UserInfo(int id, String name, String handle) {
        this.id = id;
        this.name = name;
        this.handle = handle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHandle() {
        return handle;
    }
}
