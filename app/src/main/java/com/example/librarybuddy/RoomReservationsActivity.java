package com.example.librarybuddy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class RoomReservationsActivity extends AppCompatActivity {



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_list);
        int studentId = getIntent().getIntExtra("student_id", 0);
        ReservationFragment fragment = (ReservationFragment) getSupportFragmentManager().findFragmentById(R.id.reservation_fragment);
        Intent intent = getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.reservation_toolbar);
        toolbar.setTitle("Reservations");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.query("RESERVATIONS",
                new String[]{"ROOM", "DATE", "TIME","_id"},
                "STUDENT_ID=?", new String[] {Integer.toString(studentId)}, null, null, null
        );
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[] {"ROOM", "DATE"},
                new int[]{android.R.id.text1,android.R.id.text2},
                0);
        listAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (view.getId() == android.R.id.text1) {
                    TextView dateTextView = (TextView) view;
                    dateTextView.setText("Room " + cursor.getString(0));
                    return true;
                }
                if (view.getId() == android.R.id.text2) {
                    TextView dateTextView = (TextView) view;
                    dateTextView.setText(cursor.getString(1) + ", " + cursor.getString(2));
                    return true;
                }
                return false;
            }
        });

        ListView listView = (ListView) findViewById(R.id.reservation_list);
        listView.setAdapter(listAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
