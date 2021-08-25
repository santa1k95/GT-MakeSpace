package com.gt.utils;

import com.gt.helpers.Constants;

public class GMansion extends Room {
    String name = Constants.G_MANSION_NAME;
    Integer capacity = 20;

    @Override
    public Integer getCapacity() {
        return capacity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GMansion{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", ts=" + ts +
                '}';
    }


}
