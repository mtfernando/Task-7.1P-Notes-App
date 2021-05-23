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

public class changeNoteActivity extends AppCompatActivity {

    EditText noteTitleEditText, noteDescEditText;
    Button updateButton, deleteButton;
    DatabaseHelper db;
    Note oldNote; //Note being received from intent
    Note newNote; //New Note once updated
    int noteID;
    String noteTitle, noteDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_note);

        noteTitleEditText = findViewById(R.id.changeNoteTitleEditText);
        noteDescEditText = findViewById(R.id.changeNoteDescEditText);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID", -1);
        noteTitle = intent.getStringExtra("noteTitle");
        noteDesc = intent.getStringExtra("noteDesc");

        //Set EditTexts based on data received
        noteTitleEditText.setText(noteTitle);
        noteDescEditText.setText(noteDesc);

        //Note object created from existing version of the note.
        //This is not used in the current code, but created in case a user arises.
        oldNote = new Note(noteID, noteTitle, noteDesc);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteNote(noteID); //Delete old note

                //Create new note with EditText from changeNoteActivity
                newNote = new Note(noteTitleEditText.getText().toString(),
                        noteDescEditText.getText().toString());

                //Add new note to DB
                db.createNote(newNote);

                Toast.makeText(changeNoteActivity.this, "Note updated!", Toast.LENGTH_SHORT).show();

                showAllNotes();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteNote(noteID);
                Toast.makeText(changeNoteActivity.this, "Note deleted!", Toast.LENGTH_SHORT).show();
                showAllNotes();
            }
        });
    }

    //Returns to Activity which shows all notes
    public void showAllNotes(){
        Intent intent = new Intent(changeNoteActivity.this, ShowNotesActivity.class);
        startActivity(intent);
    }
}