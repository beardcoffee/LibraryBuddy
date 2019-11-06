package com.example.librarybuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter adapter = new 	SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager myPager = findViewById(R.id.pager);
        myPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(myPager);
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm){
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return new FragmentOne();
                case 1:
                    return new FragmentTwo();
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
