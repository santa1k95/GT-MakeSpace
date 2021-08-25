package com.gt.utils;

import com.gt.helpers.Constants;

public class DTower extends Room {
    String name = Constants.D_TOWER_NAME;
    Integer capacity = 7;

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
        return "DTower{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", ts=" + ts +
                '}';
    }


}
