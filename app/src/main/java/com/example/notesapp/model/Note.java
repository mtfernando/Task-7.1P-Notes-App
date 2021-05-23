package com.example.notesapp.model;

public class Note {
    String header, desc;
    int id;

    public Note(String header, String desc){
        this.header = header;
        this.desc = desc;
    }

    public Note(int id, String header, String desc){
        this.id = id;
        this.header = header;
        this.desc = desc;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
