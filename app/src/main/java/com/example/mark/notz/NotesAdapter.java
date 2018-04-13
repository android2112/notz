package com.example.mark.notz;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private String[] mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }

    }
        @NonNull
        @Override
        public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            TextView v = (TextView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_note, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;


        }

        @Override
        public void onBindViewHolder(NotesAdapter.ViewHolder holder, int position) {
            NotesAdapter.ViewHolder  noteVH = (NotesAdapter.ViewHolder)holder;
            noteVH.mTextView.setText(mDataset[position]);

        }


        @Override
        public int getItemCount() {

                return mDataset.length;
        }

    public NotesAdapter(String [] nome) {

        mDataset=nome;

    }
}

