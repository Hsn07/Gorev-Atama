package com.hbacakk.workmanager.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarih {
    Date date;
    boolean selected=false;

    public Tarih(Date date) {
        this.date = date;
    }
    public Tarih(String stringTarih) {

        try {
            date = new SimpleDateFormat("dd-MM-yyyy hh:mm").parse(stringTarih);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Tarih() {
    }

    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public String getTarih() {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    public String getSaat() {
        return new SimpleDateFormat("HH:mm").format(date);
    }

    public String getTarihZaman(){
        return new SimpleDateFormat("dd-MM-yyyy hh:mm").format(date);
    }

    public String getGun() {
        return new SimpleDateFormat("dd").format(date);
    }

    public String getAyGun() {
        return new SimpleDateFormat("dd LLLL EEEE").format(date);
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Tarih{" +
                "date=" + date +
                ", selected=" + selected +
                '}';
    }
}
