package com.example.lab9.zad3;

public class Journal {
    private int avgmark;
    private int id;
    private String name;

    public int getAvgmark() {
        return avgmark;
    }

    public void setAvgmark(int avgmark) {
        this.avgmark = avgmark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSkips() {
        return skips;
    }

    public void setSkips(int skips) {
        this.skips = skips;
    }

    private int skips;

    public Journal() {
    }

    public Journal(int avgmark, int id, String name, int skips) {
        this.avgmark = avgmark;
        this.id = id;
        this.name = name;
        this.skips = skips;
    }
}
