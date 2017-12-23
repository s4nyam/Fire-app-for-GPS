package com.example.sanyam.myapp;

/**
 * Created by Sanyam on 23-12-2017.
 */

public class coordinates {
    String latti;
    String longi;
    public coordinates(){

    }

    public coordinates(String latti, String longi) {
        this.latti = latti;
        this.longi = longi;
    }

    public String getLatti() {
        return latti;
    }

    public String getLongi() {
        return longi;
    }
}

