package com.unique.app.adssan;

public class DataQuestions1 {


//    FeedReaderDbHelper.COLUMN_QUESTIONS1_YEARS + " TEXT," +
//    FeedReaderDbHelper.COLUMN_QUESTIONS1_SUBJECT + " TEXT," +
//    FeedReaderDbHelper.COLUMN_QUESTIONS1_PART + " TEXT," +
//    FeedReaderDbHelper.COLUMN_QUESTIONS1_CHAPTER + " TEXT," +
//    FeedReaderDbHelper.COLUMN_QUESTIONS1_QUE + " TEXT," +

    String id;
    String idvalue;
    String tid;
    String ques;
    String cno;
    String rno;
    String years;
    String subject;
    String part;
    String chapter;
    String que;

    DataQuestions1(String idvalue, String id, String tid, String years, String subject, String part, String chapter, String que, String ques, String cno, String rno) {
        this.cno = cno;
        this.idvalue = idvalue;
        this.id = id;
        this.ques = ques;
        this.tid = tid;
        this.rno = rno;
        this.years = years;
        this.subject = subject;
        this.part = part;
        this.chapter = chapter;
        this.que = que;
//
//        Log.v("valuevalue", jsonObject1.getString("id"));
//        Log.v("valuevalue", jsonObject1.getString("tid"));
//        Log.v("valuevalue", jsonObject1.getString("years"));
//        Log.v("valuevalue", jsonObject1.getString("subject"));
//        Log.v("valuevalue", jsonObject1.getString("part"));
//        Log.v("valuevalue", jsonObject1.getString("chapter"));
//        Log.v("valuevalue", jsonObject1.getString("que"));
//        Log.v("valuevalue", jsonObject1.getString("ques"));
//        Log.v("valuevalue", jsonObject1.getString("cno"));
//        Log.v("valuevalue", jsonObject1.getString("rno"));
    }

    public String getIdvalue() {
        return idvalue;
    }

    public String getTid() {
        return tid;
    }

    public String getChapter() {
        return chapter;
    }

    public String getPart() {
        return part;
    }

    public String getQue() {
        return que;
    }

    public String getSubject() {
        return subject;
    }

    public String getYears() {
        return years;
    }

    public String getId() {
        return id;
    }

    public String getCno() {
        return cno;
    }

    public String getQues() {
        return ques;
    }

    public String getRno() {
        return rno;
    }
}

