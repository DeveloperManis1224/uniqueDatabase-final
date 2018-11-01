package com.unique.app.adssan;

public class DataChapter {

    String id;
    String chapter_name;
    String subject;
    String year;
    String columnid;


    DataChapter(String id, String columnid, String chapter_name, String subject, String year) {


        this.columnid = columnid;
        this.id = id;
        this.chapter_name = chapter_name;
        this.subject = subject;
        this.year = year;


    }

    public String getColumnid() {
        return columnid;
    }

    public String getYear() {
        return year;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public String getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }
}
