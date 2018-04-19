package com.example.mark.notz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button button;
    private TextView prova;


    // private String[] myDataset = {"nota 1"," nota 2", "fai la spesa", "paga bolletta luca", "dadsadasa", "dsasdasd", "dassad"};
    private ArrayList<Note> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView =findViewById(R.id.rv);
        button=findViewById(R.id.add_button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(myIntent);
            }
        });


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

        mAdapter = new NotesAdapter(myDataset,MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);


        Intent intent = getIntent();

        String s=intent.getStringExtra("titolo");
        String v=intent.getStringExtra("testo");

        Note d= new Note(s,v);
        myDataset.add(d);
















    }
}