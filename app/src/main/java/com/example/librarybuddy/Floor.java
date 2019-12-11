package com.example.librarybuddy;



public class Floor {
    private String level;
    private int imageResourceId;
    public static final Floor[] floors = {
            new Floor("First floor", R.drawable.first_floor_map),
            new Floor("Second floor", R.drawable.second_floor_map),
            new Floor("Third floor", R.drawable.third_floor_map),
            new Floor("Fourth floor", R.drawable.fourth_floor_map)

    };



    public Floor(String level, int imageResourceId) {
        this.level = level;
        this.imageResourceId = imageResourceId;
    }


    public int getImageResourceId() {
        return imageResourceId;
    }

    public String toString(){
        return level;
    }
}
