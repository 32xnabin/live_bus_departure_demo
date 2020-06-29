package com.truiton.busdeparturelive;

public class Departure {
    String name;
    String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return Long.parseLong(time);
    }

    public void setTime(String time) {
        this.time = time;
    }
}
