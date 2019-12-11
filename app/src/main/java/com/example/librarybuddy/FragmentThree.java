package com.example.librarybuddy;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;


public class FragmentThree extends Fragment implements AdapterView.OnItemSelectedListener {

    private LinearLayout linearLayout;
    private Spinner spinner;
    private ArrayList<Room> rooms;


    public FragmentThree() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_three, container, false);
        linearLayout = (LinearLayout) getActivity().findViewById(R.id.room_reserve);
        spinner = (Spinner) v.findViewById(R.id.spinnerDates);
        SQLiteOpenHelper databaseHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        final Cursor cursor = db.query("RESERVATIONS",
                new String[]{"DATE"},
                null, null, "DATE", null, null
        );
        ArrayList<String> dates = new ArrayList<String>(){{
            for(int i = 0; i < cursor.getCount(); i++){
                cursor.moveToPosition(i);
                add(cursor.getString(0));
            }
        }};
        db.close();
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(),
                android.R.layout.simple_spinner_item,
                dates);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        return v;
    }

    public long getRoomUID(int position){
        if(rooms.size() > 0) {
            Room tempRoom = rooms.get(position);
            rooms.remove(position);
            return tempRoom.getUniqueId();
        }
        return -1;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String date = spinner.getItemAtPosition(position).toString();;

        SQLiteOpenHelper databaseHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query("RESERVATIONS",
                new String[]{"ROOM", "DATE", "TIME", "STUDENT_ID", "_ID"},
                "DATE=?", new String[]{date}, null, null, null
        );

        RadioGroup rg = getActivity().findViewById(R.id.room_list);
        if(rg.getChildCount() > 0){
            rg.removeAllViews();
        }

        rooms = new ArrayList<Room>();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int uid = (int) cursor.getLong(4);
            RadioButton radioButton = new RadioButton(getActivity());
            radioButton.setText("Rm: " + cursor.getString(0) + " " + cursor.getString(2));
            rooms.add(new Room(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), uid));
            rg.addView(radioButton, i);
        }
        cursor.close();
        db.close();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    class Room{

        private String room;
        private String date;
        private String time;
        private int studentId;
        private long uniqueId;

        public Room(String room, String date, String time, int studentId, long uniqueId){
            this.room = room;
            this.date = date;
            this.time = time;
            this.studentId = studentId;
            this.uniqueId = uniqueId;
        }

        public long getUniqueId() {
            return uniqueId;
        }

        public int getStudentId() {
            return studentId;
        }

        public String getDate() {
            return date;
        }

        public String getRoom() {
            return room;
        }

        public String getTime() {
            return time;
        }
    }

}
