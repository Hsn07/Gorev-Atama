package com.hbacakk.workmanager.models;

public class Personel {

    int id;
    String ad;
    String soyAd;
    int toplamIslemZorlugu;
    int yapilanIsSayisi;
    String enSonYapilanIs;


    public Personel() {
    }

    public Personel( String ad, String soyAd, int toplamIslemZorlugu, int yapilanIsSayisi, String enSonYapilanIs) {
        this.ad = ad;
        this.soyAd = soyAd;
        this.toplamIslemZorlugu = toplamIslemZorlugu;
        this.yapilanIsSayisi = yapilanIsSayisi;
        this.enSonYapilanIs = enSonYapilanIs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyAd() {
        return soyAd;
    }

    public void setSoyAd(String soyAd) {
        this.soyAd = soyAd;
    }

    public int getToplamIslemZorlugu() {
        return toplamIslemZorlugu;
    }

    public void setToplamIslemZorlugu(int toplamIslemZorlugu) {
        this.toplamIslemZorlugu = toplamIslemZorlugu;
    }

    public int getYapilanIsSayisi() {
        return yapilanIsSayisi;
    }

    public void setYapilanIsSayisi(int yapilanIsSayisi) {
        this.yapilanIsSayisi = yapilanIsSayisi;
    }

    public String getEnSonYapilanIs() {
        return enSonYapilanIs;
    }

    public void setEnSonYapilanIs(String enSonYapilanIs) {
        this.enSonYapilanIs = enSonYapilanIs;
    }
}
