package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.data.DatabaseHelper;
import com.example.notesapp.model.Note;

public class NewNoteActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText noteDescEditText, noteTitleEditText;
    Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        db = new DatabaseHelper(this);

        noteTitleEditText = findViewById(R.id.changeNoteTitleEditText);
        noteDescEditText = findViewById(R.id.changeNoteDescEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get text from Title and Description edit texts.
                String noteTitle = noteTitleEditText.getText().toString();
                String noteDesc = noteDescEditText.getText().toString();

                Note newNote = new Note(noteTitle ,noteDesc);

                //Inserting new note into DB
                long result = db.createNote(newNote);

                //Toast declaring the success of the db insertion
                if(result != -1){
                    Toast.makeText(NewNoteActivity.this, "Note saved!", Toast.LENGTH_LONG).show();

                    //Go back to the main screen
                    Intent intent = new Intent(NewNoteActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else Toast.makeText(NewNoteActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}