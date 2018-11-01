package com.unique.app.adssan;

public class CardScoreData {
    private String read;
    private String unread;
    private String subject;


    CardScoreData(String read, String unread, String subject)


    {
        this.read = read;
        this.unread = unread;
        this.subject = subject;

    }

    public String getSubject() {
        return subject;
    }

    public String getRead() {
        return read;
    }

    public String getUnread() {
        return unread;
    }
}
