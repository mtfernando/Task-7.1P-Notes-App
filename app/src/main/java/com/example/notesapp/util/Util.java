package com.example.notesapp.util;

public class Util {
    //Utility functions stored here
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "notes_db";
    public static final String NOTES_TABLE_NAME = "notes";

    public static final String NOTE_ID = "note_id";
    public static final String NOTE_HEADER = "note_header";
    public static final String NOTE_DESC = "note_desc";

    //SQL Table Creation
    public static final String CREATE_NOTES_TABLE = "CREATE TABLE " + NOTES_TABLE_NAME
            + "(" + NOTE_ID + " TEXT PRIMARY KEY AUTOINCREMENT," + NOTE_HEADER + " TEXT," +
            NOTE_DESC + " TEXT)";

    public static final String FETCH_ALL_NOTES = "SELECT * FROM " + Util.NOTES_TABLE_NAME;
}
