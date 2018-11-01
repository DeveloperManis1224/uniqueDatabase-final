package com.unique.app.adssan;

public class CardQuestionsData {
    String chaptername;
    String cno;
    String _id;
    String sts;

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public CardQuestionsData(String sts) {
        this.sts = sts;
    }

    CardQuestionsData(String chaptername, String cno, String id,String sts1)
    {
this.chaptername = chaptername;

this.cno = cno;
        this._id = id;
        this.sts = sts1;

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getChaptername() {
        return chaptername;
    }

    public String getCno() {
        return cno;
    }
}
