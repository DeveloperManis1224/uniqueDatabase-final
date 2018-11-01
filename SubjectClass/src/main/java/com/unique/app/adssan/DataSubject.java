package com.unique.app.adssan;

public class DataSubject {


    String cid;
    String category_name;
    String year;
    String Id;

    DataSubject(String id, String cid, String category_name, String year) {

        this.Id = id;

        this.cid = cid;
        this.category_name = category_name;
        this.year = year;


    }

    public String getId() {
        return Id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getCid() {
        return cid;
    }

    public String getYear() {
        return year;
    }


}
