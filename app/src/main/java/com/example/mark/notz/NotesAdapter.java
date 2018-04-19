package com.example.mark.notz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private ArrayList<Note> mDataset;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public TextView mTextViewContent;
        public CardView cardView;

        public Context context;


        public ViewHolder(View v) {
            super(v);

            cardView = (CardView)v.findViewById(R.id.cv);
            mTextView = (TextView)v.findViewById(R.id.title);
            mTextViewContent = (TextView)v.findViewById((R.id.text));

        }
    }

    public NotesAdapter(ArrayList<Note> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_note, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.mTextView.setText(mDataset.get(position).getTitle());
        holder.mTextViewContent.setText(mDataset.get(position).getDescription());




    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}