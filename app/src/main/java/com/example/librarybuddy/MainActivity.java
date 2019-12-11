package com.example.librarybuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private FragmentTwo bookSearchFragment;
    private SectionsPagerAdapter adapter;
    private boolean search = false;
    private String query = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new 	SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager myPager = findViewById(R.id.pager);
        myPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(myPager);
    }
    public void searchBook(View view) {
        TextView queryTextView = (TextView) findViewById(R.id.search_query);
        query = queryTextView.getText().toString();
        if(query.replaceAll("\\s","").length() > 0){
            if(query.length() >= 3){
                search = true;
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Query too short! 3 characters minimum", Toast.LENGTH_SHORT);
                toast.show();
                search = false;
            }
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "Invalid query!", Toast.LENGTH_SHORT);
            toast.show();
            search = false;
        }
        queryTextView.setText("");
        adapter.notifyDataSetChanged();
    }
    private class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm){
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return new FragmentOne();
                case 1:
                    return bookSearchFragment = new FragmentTwo(search, query);

                case 2:
                    return new FragmentThree();
                case 3:
                    return new FragmentFour();
                case 4:
                    return new FragmentFive();
            }
            return null;
        }
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
        @Override
        public int getCount() {
            return 5;
        }

        public CharSequence getPageTitle(int position){
            switch(position){
                case 0:
                    return getResources().getText(R.string.first_tab);
                case 1:
                    return getResources().getText(R.string.second_tab);
                case 2:
                    return getResources().getText(R.string.third_tab);
                case 3:
                    return getResources().getText(R.string.fourth_tab);
                case 4:
                    return getResources().getText(R.string.five_tab);
            }
            return null;
        }
    }
}
