package com.example.librarybuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "homework";
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
        db.execSQL("CREATE TABLE BOOKS ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "TITLE TEXT, "
                + "AUTHOR TEXT, "
                + "STOCK INTEGER, "
                + "RESOURCE INTEGER);");
        insertBook(db, "A Clockwork Orange", "Anthony Burgess", 6, R.drawable.clockwork);

    }

    public static void insertEvent(SQLiteDatabase db, String date, String event){
        ContentValues personValues = new ContentValues();
        personValues.put("DATE", date);
        personValues.put("EVENT_DESCRIPTION", event);
        db.insert("EVENTS", null, personValues);
    }
    public static void insertBook(SQLiteDatabase db, String title, String author, int stock, int resource){
        ContentValues personValues = new ContentValues();
        personValues.put("TITLE", title);
        personValues.put("AUTHOR", author);
        personValues.put("STOCK", stock);
        personValues.put("RESOURCE", resource);
        db.insert("BOOKS", null, personValues);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}