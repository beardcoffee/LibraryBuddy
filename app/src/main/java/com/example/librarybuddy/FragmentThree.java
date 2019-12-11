package com.example.librarybuddy;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;


public class FragmentThree extends Fragment implements AdapterView.OnItemSelectedListener {

    private LinearLayout linearLayout;

    public FragmentThree() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private ArrayList<String> dates = new ArrayList<String>(){{
      add("October 28");
      add("October 29");
    }};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_three, container, false);
        linearLayout = (LinearLayout) getActivity().findViewById(R.id.room_reserve);
        Spinner spinner = (Spinner) v.findViewById(R.id.spinnerDates);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(),
                android.R.layout.simple_spinner_item,
                dates);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        return v;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
