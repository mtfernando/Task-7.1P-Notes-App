package com.example.notesapp.model;

public class Note {
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

    String header, desc;
    public void Note(String header, String desc){
        this.header = header;
        this.desc = desc;
    }
}
