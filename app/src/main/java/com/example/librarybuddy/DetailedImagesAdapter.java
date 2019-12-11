package com.example.librarybuddy;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class DetailedImagesAdapter extends RecyclerView.Adapter<DetailedImagesAdapter.ViewHolder> {

    private String[] titles;
    private String[] authors;
    private int[] stock;
    private int[] resourceIds;
    private Listener listener;

    public void setListener(Listener listener){
        this.listener = listener;
    }

    public DetailedImagesAdapter(String[] titles, String[] authors, int[] stock, int[] resourceIds){
        this.titles = titles;
        this.authors = authors;
        this.stock = stock;
        this.resourceIds = resourceIds;
    }

    interface Listener{
        void onClick(int position);
    }

    @Override
    public DetailedImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);

        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){

        final CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.card_image);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), resourceIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(titles[position]);
        TextView textView = (TextView) cardView.findViewById(R.id.book_title);
        textView.setText("Title: " + titles[position]);

        textView = (TextView) cardView.findViewById(R.id.author_text);
        textView.setText("Author: " + authors[position]);

        textView = (TextView) cardView.findViewById(R.id.availability_text);
        textView.setText("Available Copies: " + stock[position]);
        if(stock[position] > 0){
            textView.setTextColor(Color.GREEN);
        }else{
            textView.setTextColor(Color.RED);
        }
        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(listener != null){
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount(){
        return titles.length;
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        private CardView cardView;

        public ViewHolder(CardView v){
            super(v);
            cardView = v;
        }


    }
}
