package com.gt.utils;

import com.gt.helpers.Constants;

public class CCave extends Room {
    String name = Constants.C_CAVE_NAME;
    Integer capacity = 3;


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
        return "CCave{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", ts=" + ts +
                '}';
    }
}
