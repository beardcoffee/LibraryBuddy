package com.example.librarybuddy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BookFragment extends Fragment {
    private int pirateId;

    public BookFragment() {
        // Required empty public constructor
    }

    public void setPirateId(int id){
        this.pirateId = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View bookView = inflater.inflate(R.layout.book_fragment, container, false);

        return  bookView;
    }

}
