package com.unique.app.adssan;

public  class  DataEditorial{
   String _drName;
   int _drImage;

    public DataEditorial(String _drName, int _drImage) {
        this._drName = _drName;
        this._drImage = _drImage;
    }

    public String get_drName() {
        return _drName;
    }

    public void set_drName(String _drName) {
        this._drName = _drName;
    }

    public int get_drImage() {
        return _drImage;
    }

    public void set_drImage(int _drImage) {
        this._drImage = _drImage;
    }
}