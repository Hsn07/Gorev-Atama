package com.hbacakk.workmanager.models;

public class GunlukIslem {
    int id;
    Islem islem;
    Personel personel;

    public GunlukIslem() {
    }

    public GunlukIslem(int id, Islem islem, Personel personel) {
        this.id = id;
        this.islem = islem;
        this.personel = personel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Islem getIslem() {
        return islem;
    }

    public void setIslem(Islem islem) {
        this.islem = islem;
    }

    public Personel getPersonel() {
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }
}
