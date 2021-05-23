package com.example.notesapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.notesapp.model.Note;
import com.example.notesapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Util.CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS";
        db.execSQL(DROP_USER_TABLE, new String[] {Util.NOTES_TABLE_NAME});

        onCreate(db);
    }

    public long createNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.NOTE_HEADER, note.getHeader());
        values.put(Util.NOTE_DESC, note.getDesc());

        //Inserting row into Notes table
        long result = db.insert(Util.NOTES_TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public List<Note> getAllNotes(){
        SQLiteDatabase db = this.getReadableDatabase()
        List<Note> notesList = new ArrayList<>();

        Cursor c = db.rawQuery(Util.FETCH_ALL_NOTES, null);

        //Attribute indices
        final int idIndex = c.getColumnIndex(Util.NOTE_ID);
        final int headerIndex = c.getColumnIndex(Util.NOTE_HEADER);
        final int descIndex = c.getColumnIndex(Util.NOTE_DESC);

        try{
            //If cursor is empty
            if(!c.moveToFirst()){
                return notesList;
            }
            do{
                final int noteID = c.getInt(idIndex);
                final String noteHeader = c.getString(headerIndex);
                final String noteDesc = c.getString(descIndex);

                notesList.add(new Note(noteID, noteHeader, noteDesc));
            } while(c.moveToNext());
        }

        finally {
            c.close();
            db.close();
        }

        return notesList;
    }

    //This method deletes a row based on a given int note ID.
    public void deleteNote(int noteID){
        SQLiteDatabase db = this.getWritableDatabase();
        final String DELETE_ROW = "DELETE FROM " + Util.NOTES_TABLE_NAME + " WHERE " + Util.NOTE_ID + " = " + noteID ;

        db.execSQL(DELETE_ROW);
        db.close();
    }

}
