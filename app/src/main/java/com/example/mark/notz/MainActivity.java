package com.example.mark.notz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // private String[] myDataset = {"nota 1"," nota 2", "fai la spesa", "paga bolletta luca", "dadsadasa", "dsasdasd", "dassad"};
    private ArrayList<Note> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView =findViewById(R.id.rv);


        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setLayoutManager(mLayoutManager);


        myDataset = new ArrayList<>();

        Note pinPalazzo = new Note("PIN","12345");
        myDataset.add(pinPalazzo);

        Note spesa = new Note("Spesa","comprare il lattejkladkljfkldjfkljsdkfljsdlakfjkldsjflksjdfksdjfkljsdklfjlkjdjfjlkjlfdj");
        myDataset.add(spesa);

        Note spesanuova = new Note("Spesa","comprare il pane");
        myDataset.add(spesanuova);

        Note spesadir = new Note("Spesa","comprare la frutta ecceccecececececececececcecece");
        myDataset.add(spesadir);

        Note nspesa = new Note("spesa","ghhgdfkhjdslfhjsdklfhjsdfhshfjksdhfjlsdhjfldhsfjhdsjflhdsjfhljsdhfsdhf");
        myDataset.add(nspesa);

        Note mspesa = new Note("spesa","ghhgdfkhjdslfhjsdklfhjsdfhshfjksdhfjlsdhjfldhsfjhdsjflhdsjfhljsdhfsdhf");
        myDataset.add(mspesa);

        mAdapter = new NotesAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);


    }
}