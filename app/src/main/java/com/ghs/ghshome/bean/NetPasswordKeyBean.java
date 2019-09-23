package com.ghs.ghshome.bean;

public class NetPasswordKeyBean {
   private String name;
   private int id;

    public NetPasswordKeyBean(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public NetPasswordKeyBean() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
