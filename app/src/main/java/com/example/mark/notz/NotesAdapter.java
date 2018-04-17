package com.example.mark.notz;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{
    private ArrayList<Note> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public TextView mTextViewContent;
        public CardView cardView;

        public ViewHolder(View v) {
            super(v);
            cardView = (CardView)itemView.findViewById(R.id.cv);
            mTextView = (TextView)itemView.findViewById(R.id.title);
            mTextViewContent = (TextView)itemView.findViewById((R.id.text));
        }
    }

    public NotesAdapter(ArrayList<Note> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_note, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextView.setText(mDataset.get(position).getTitle());
        holder.mTextViewContent.setText(mDataset.get(position).getDescription());

    }

    public int getItemCount() {
        return mDataset.size();
    }
}