package com.example.librarybuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "librarybuddy";
    private static final int DB_VERSION = 1;

    DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE EVENTS ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "DATE TEXT, "
                + "EVENT_DESCRIPTION TEXT);");
        insertEvent(db, "11/26", "Interview Workshop");
        insertEvent(db, "11/27", "Free Coffee");
        insertEvent(db, "12/01", "Quiet Time");
        insertEvent(db, "12/03", "Reading Day");
        insertEvent(db, "12/04", "24 hour library hour extension begins");
        insertEvent(db, "12/11", "Academic Advising");
        insertEvent(db, "12/13", "CSCI 4661 App demos");
        db.execSQL("CREATE TABLE BOOKS ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "TITLE TEXT, "
                + "AUTHOR TEXT, "
                + "STOCK INTEGER, "
                + "RESOURCE INTEGER, "
                + "SYNOPSIS TEXT);");
        insertBook(db, "A Clockwork Orange", "Anthony Burgess", 6, R.drawable.clockwork, "Crazy book of stuff, cool read.");
        insertBook(db, "Red Clocks", "Leni Zumas", 0, R.drawable.red_clocks, "Cool book about red clocks.");
        db.execSQL("CREATE TABLE RESERVATIONS ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "ROOM TEXT, "
                + "DATE TEXT, "
                + "TIME TEXT, "
                + "STUDENT_ID INTEGER);");
        insertReservation(db, "102", "December 12", "10:00AM-11:00AM", 0);
        insertReservation(db, "102", "December 12", "11:00AM-12:00PM", 0);
        insertReservation(db, "102", "December 13", "10:00AM-11:00AM", 0);
        insertReservation(db, "101", "December 13", "10:00AM-11:00AM", 0);
        insertReservation(db, "105", "December 13", "12:00PM-2:00PM", 0);
    }

    public static void insertEvent(SQLiteDatabase db, String date, String event){
        ContentValues personValues = new ContentValues();
        personValues.put("DATE", date);
        personValues.put("EVENT_DESCRIPTION", event);
        db.insert("EVENTS", null, personValues);
    }
    public static void insertBook(SQLiteDatabase db, String title, String author, int stock, int resource, String synopsis){
        ContentValues personValues = new ContentValues();
        personValues.put("TITLE", title);
        personValues.put("AUTHOR", author);
        personValues.put("STOCK", stock);
        personValues.put("RESOURCE", resource);
        personValues.put("SYNOPSIS", synopsis);
        db.insert("BOOKS", null, personValues);
    }
    public static void insertReservation(SQLiteDatabase db, String room, String date, String time, int studentId){
        ContentValues personValues = new ContentValues();
        personValues.put("ROOM", room);
        personValues.put("DATE", date);
        personValues.put("TIME", time);
        personValues.put("STUDENT_ID", studentId);
        db.insert("RESERVATIONS", null, personValues);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}