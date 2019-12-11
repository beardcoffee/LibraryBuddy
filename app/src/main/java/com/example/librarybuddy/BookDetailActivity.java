package com.example.librarybuddy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BookDetailActivity extends AppCompatActivity {

    public static String BOOK_TITLE;
    public static String BOOK_AUTHOR;
    public static String BOOK_STOCK;
    public static String BOOK_RESOURCE;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);
        BookFragment fragment = (BookFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
        Intent intent = getIntent();

        System.out.println("BOOK TITLE:" + intent.getStringExtra("book_title"));
        String bookTitle = intent.getStringExtra("book_title");
        Toolbar toolbar = (Toolbar) findViewById(R.id.up_toolbar);
        toolbar.setTitle(bookTitle);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView textView = (TextView) findViewById(R.id.fragment_title);
        textView.setText(bookTitle);

        int bookImage = intent.getIntExtra("book_resource", 0);
        ImageView imageView = (ImageView) findViewById(R.id.book_image);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, bookImage));
      //  imageView.setContentDescription(bookTitle);

        String bookAuthor = intent.getStringExtra("book_author");
        textView = (TextView) findViewById(R.id.fragment_author);
        textView.setText(bookAuthor);

        String bookSynopsis = intent.getStringExtra("book_synopsis");
        textView = (TextView) findViewById(R.id.fragment_synopsis);
        textView.setText(bookSynopsis);

        int stock = intent.getIntExtra("book_stock", 0);
        textView = (TextView) findViewById(R.id.fragment_availability);
        textView.setText("Available Copies: " + Integer.toString(stock));
        if(stock > 0){
            textView.setTextColor(Color.GREEN);
        }else{
            textView.setTextColor(Color.RED);
        }

    }
}
