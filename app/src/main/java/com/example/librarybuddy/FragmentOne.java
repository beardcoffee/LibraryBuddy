package com.example.librarybuddy;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class FragmentOne extends Fragment {
     Cursor cursor;
    SQLiteDatabase db;

     public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_one, container, false);
        ListView events = (ListView) v.findViewById(R.id.event_list);
        SQLiteOpenHelper databaseHelper = new DatabaseHelper(getContext());


        db = databaseHelper.getWritableDatabase();

        cursor = db.query("EVENTS",
          new String[]{"_id", "DATE", "EVENT_DESCRIPTION"},
          null, null, null, null, null);


        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this.getContext(),
        android.R.layout.simple_list_item_2,
        cursor,
        new String[] {"DATE", "EVENT_DESCRIPTION"},
        new int[]{android.R.id.text1,android.R.id.text2},
        0);

        events.setAdapter(listAdapter);

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

}

}
