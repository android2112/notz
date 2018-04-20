package com.example.mark.notz;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private NotesAdapter mAdapter;
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
        //button= findViewById(R.id.fab);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
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
    private void showdialog(){

        AlertDialog.Builder ab = new AlertDialog.Builder(this);

        ab.setView(R.layout.note_dialog);
        ab.setTitle(R.string.dialog);//mettere stringhe nella cartella
        ab.setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Note note = new Note("titolo","contenuto");
                mAdapter.addNote(note);


            }
        });
        ab.setNegativeButton(R.string.dialog_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        ab.show();





    }
}