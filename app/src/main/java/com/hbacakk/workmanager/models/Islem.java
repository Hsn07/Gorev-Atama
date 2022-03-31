package com.hbacakk.workmanager.models;

public class Islem {
    int id;
    String gorev;
    int zorlukSeviyesi;
    int zaman;

    public Islem() {
    }

    public Islem(String gorev, int zorlukSeviyesi, int zaman) {
        this.gorev = gorev;
        this.zorlukSeviyesi = zorlukSeviyesi;
        this.zaman = zaman;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGorev() {
        return gorev;
    }

    public void setGorev(String gorev) {
        this.gorev = gorev;
    }

    public int getZorlukSeviyesi() {
        return zorlukSeviyesi;
    }

    public void setZorlukSeviyesi(int zorlukSeviyesi) {
        this.zorlukSeviyesi = zorlukSeviyesi;
    }

    public int getZaman() {
        return zaman;
    }

    public void setZaman(int zaman) {
        this.zaman = zaman;
    }
}
