package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.notesapp.data.DatabaseHelper;

public class ShowNotesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);

        //RecyclerView and DB setup
        recyclerView = findViewById(R.id.notesRecyclerView);
        db = new DatabaseHelper(this);
        setRecyclerView();
    }

    public void setRecyclerView(){
        recyclerViewAdapter = new RecyclerViewAdapter(db.getAllNotes(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}