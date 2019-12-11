package com.example.librarybuddy;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;


public class FragmentFour extends Fragment implements AdapterView.OnItemSelectedListener{

    public FragmentFour() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_four, container, false);
        Spinner spinner = (Spinner) v.findViewById(R.id.spinnerMap);
        ArrayAdapter<Floor> adapter = new ArrayAdapter<>(inflater.getContext(),
                android.R.layout.simple_spinner_item,
                Floor.floors);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        return v;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ImageView iv = getActivity().findViewById(R.id.imageView_map);
        iv.setImageResource(Floor.floors[position].getImageResourceId());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
