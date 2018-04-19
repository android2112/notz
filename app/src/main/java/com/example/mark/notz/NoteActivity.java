package com.example.mark.notz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {
    public EditText title;
    public EditText description;
    public Button bottone;
    Intent intent=getIntent();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        title =(EditText)findViewById(R.id.title);
        description = (EditText)findViewById(R.id.text);
        bottone = findViewById(R.id.bottone);

        bottone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(NoteActivity.this,MainActivity.class);

                intent.putExtra("titolo",title.getText().toString());
                intent.putExtra("testo",description.getText().toString());

                startActivity(intent);
            }
        });











    }
}

