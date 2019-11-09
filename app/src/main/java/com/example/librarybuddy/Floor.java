package com.example.librarybuddy;

import java.util.ArrayList;

public class Floor {
    private String level;
    private int imageResourceId;
    public static final Floor[] floors = {
            new Floor("First floor", R.drawable.first_floor_map)

    };



    public Floor(String level, int imageResourceId) {
        this.level = level;
        this.imageResourceId = imageResourceId;
    }


    public String getLevel() {
        return level;
    }



    public int getImageResourceId() {
        return imageResourceId;
    }

    public String toString(){
        return level;
    }
}
