package com.example.librarybuddy;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class FragmentTwo extends Fragment {

    private LinearLayout linearLayout;
    private boolean search;
    private String query;
    public FragmentTwo() {
        // Required empty public constructor
    }

    public FragmentTwo(boolean search, String query) {
        // Required empty public constructor
        this.search = search;
        this.query = query;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_two, container, false);
        linearLayout = (LinearLayout) v.findViewById(R.id.booksearch);

        if(search && query.length() > 0) {
            RecyclerView rv = (RecyclerView) linearLayout.findViewById(R.id.books);
            SQLiteOpenHelper databaseHelper = new DatabaseHelper(getContext());

            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            Cursor cursor = db.query("BOOKS",
                    new String[]{"TITLE", "AUTHOR", "STOCK", "RESOURCE", "SYNOPSIS"},
                    "TITLE LIKE ?", new String[] {"%" + query + "%"}, null, null, null
            );
            final String[] titles = new String[cursor.getCount()];
            final String[] authors = new String[cursor.getCount()];
            final int[] stock = new int[cursor.getCount()];
            final int[] cardImages = new int[cursor.getCount()];
            final String[] descriptions = new String[cursor.getCount()];
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                titles[i] = cursor.getString(0);
                authors[i] = cursor.getString(1);
                stock[i] = cursor.getInt(2);
                cardImages[i] = cursor.getInt(3);
                descriptions[i] = cursor.getString(4);

            }
            DetailedImagesAdapter adapter = new DetailedImagesAdapter(titles, authors, stock, cardImages);
            rv.setAdapter(adapter);
            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter.setListener(new DetailedImagesAdapter.Listener() {
                @Override
                public void onClick(int position) {
                    Intent intent = new Intent(getActivity(), BookDetailActivity.class);
                    intent.putExtra("book_title", titles[position]);
                    intent.putExtra("book_author", authors[position]);
                    intent.putExtra("book_stock", stock[position]);
                    intent.putExtra("book_resource", cardImages[position]);
                    intent.putExtra("book_synopsis", descriptions[position]);
                    getActivity().startActivity(intent);
                }
            });
            cursor.close();
            db.close();
        }
        return v;
    }

}
