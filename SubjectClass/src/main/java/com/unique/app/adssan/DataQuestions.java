package com.unique.app.adssan;

public class DataQuestions {

    String id;
    String tid;
    String years;
    String subject;
    String chapter_name;
    String part;
    String que;

    DataQuestions(String id, String tid, String years, String subject, String part, String chapter_name, String que) {
        this.id = id;
        this.part = part;
        this.tid = tid;
        this.years = years;
        this.subject = subject;
        this.chapter_name = chapter_name;
        this.que = que;


//        Log.v("valuevalue", jsonObject1.getString("tid"));
//        Log.v("valuevalue", jsonObject1.getString("years"));
//        Log.v("valuevalue", jsonObject1.getString("subject"));
//        Log.v("valuevalue", jsonObject1.getString("part"));
//        Log.v("valuevalue", jsonObject1.getString("chapter_name"));
//        Log.v("valuevalue", jsonObject1.getString("que"));

    }

    public String getPart() {
        return part;
    }

    public String getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public String getQue() {
        return que;
    }

    public String getTid() {
        return tid;
    }

    public String getYears() {
        return years;
    }
}

