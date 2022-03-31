package com.hbacakk.workmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.hbacakk.workmanager.models.GunlukIslem;
import com.hbacakk.workmanager.models.Islem;
import com.hbacakk.workmanager.models.Personel;
import com.hbacakk.workmanager.models.Tarih;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static String DATABASE_NAME="WorkManager";
    private static int DATABASE_VERSION=1;
    static String TAG="DataBase";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PERSONEL="CREATE TABLE Personel("+"personelID INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "ad VARCHAR , " +
                "soyad VARCHAR , " +
                "toplamIslemZorlugu INTEGER , " +
                "yapilanIsSayisi INTEGER , " +
                "enSonYapilanIs VARCHAR , " +
                "guncellemeTarihi DATE , " +
                "olusturulmaTarihi DATE)";
        db.execSQL(CREATE_PERSONEL);
        String CREATE_ISLEM="CREATE TABLE Islem("+"islemID INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "gorev VARCHAR , " +
                "zorlukSeviyesi INTEGER , " +
                "zaman INTEGER , " +
                "olusturulmaTarihi DATE)";
        db.execSQL(CREATE_ISLEM);
        String CREATE_GUNLUK_ISLEM="CREATE TABLE GunlukIslem("+"id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "islemID INTEGER , " +
                "personelID INTEGER , " +
                "olusturulmaTarihi DATE)";
        db.execSQL(CREATE_GUNLUK_ISLEM);

        String CREATE_TRIGGER_PERSONEL_INFO="CREATE TRIGGER UPDATE_PERSONEL_INFO " +
                "AFTER INSERT " +
                "ON GunlukIslem " +
                "BEGIN " +
                "UPDATE Personel SET " +
                "enSonYapilanIs=(SELECT gorev FROM ISLEM WHERE islemID=NEW.islemID) ," +
                "toplamIslemZorlugu=toplamIslemZorlugu+(SELECT zorlukSeviyesi FROM ISLEM WHERE islemID=NEW.islemID) ," +
                "yapilanIsSayisi=yapilanIsSayisi + 1 ," +
                "guncellemeTarihi=datetime() where personelID=NEW.personelID; " +
                "END;";
        db.execSQL(CREATE_TRIGGER_PERSONEL_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Personel İşlemleri
    public boolean yeniPersonel(Personel personel){
        try {
            SQLiteDatabase db=this.getWritableDatabase();
            String INSERT_PERSONEL="INSERT INTO Personel (ad,soyad,toplamIslemZorlugu,yapilanIsSayisi,enSonYapilanIs,guncellemeTarihi,olusturulmaTarihi) VALUES "+
                    " ('"+personel.getAd()+"','"+personel.getSoyAd()+"','0','0','Henüz iş yapılmadı',datetime(),datetime())";
            db.execSQL(INSERT_PERSONEL);
            db.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }


    }

    public ArrayList<Personel> getPersonelListesi(){
        SQLiteDatabase db=this.getWritableDatabase();
        String SELECT_PERSONELLER="SELECT * FROM Personel ORDER BY toplamIslemZorlugu ASC";
        Cursor cursor=db.rawQuery(SELECT_PERSONELLER,null);
        ArrayList<Personel> personelArrayList=new ArrayList<>();
        try {
            if (cursor!=null){
                cursor.moveToFirst();
                do {
                    Personel personel=new Personel();
                    personel.setId(cursor.getInt(0));
                    personel.setAd(cursor.getString(1));
                    personel.setSoyAd(cursor.getString(2));
                    personel.setToplamIslemZorlugu(cursor.getInt(3));
                    personel.setYapilanIsSayisi(cursor.getInt(4));
                    personel.setEnSonYapilanIs(cursor.getString(5));

                    personelArrayList.add(personel);
                }while (cursor.moveToNext());
            }
            db.close();
        }catch (Exception e){}
        return personelArrayList;
    }
    //Islem İşlemleri
    public boolean yeniIslem(Islem islem){
        try {
            SQLiteDatabase db=this.getWritableDatabase();
            String INSERT_ISLEM="INSERT INTO Islem (gorev,zorlukSeviyesi,zaman,olusturulmaTarihi) VALUES "+
                    "('"+islem.getGorev()+"','"+islem.getZorlukSeviyesi()+"','"+islem.getZaman()+"',datetime())";
            db.execSQL(INSERT_ISLEM);
            db.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }
    public ArrayList<Islem> getIslemListesi(){
        SQLiteDatabase db=this.getWritableDatabase();
        String SELECT_ISLEMLER="SELECT * FROM Islem ORDER BY zorlukSeviyesi DESC";

        Cursor cursor=db.rawQuery(SELECT_ISLEMLER,null);
        ArrayList<Islem> islemArrayList=new ArrayList<>();
        try {
            if (cursor!=null){
                cursor.moveToFirst();
                do {
                    Islem islem=new Islem();
                    islem.setId(cursor.getInt(0));
                    islem.setGorev(cursor.getString(1));
                    islem.setZorlukSeviyesi(cursor.getInt(2));
                    islem.setZaman(cursor.getInt(3));
                    islemArrayList.add(islem);
                }while (cursor.moveToNext());
            }
            db.close();
        }catch (Exception e){}
        return islemArrayList;
    }
    //GunlukIslem Islemler
    public boolean yeniGunlukIslem(int islemID,int personelID,String date){
        try {
            SQLiteDatabase db=this.getWritableDatabase();
            String INSERT_GUNLUK_ISLEM="INSERT INTO GunlukIslem (islemID,personelID,olusturulmaTarihi) VALUES "+
                    "('"+islemID+"','"+personelID+"','"+date+"')";
            db.execSQL(INSERT_GUNLUK_ISLEM);
            db.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }
    public ArrayList<GunlukIslem> getGunlukIslemler(String tarih){
        SQLiteDatabase db=this.getWritableDatabase();
        String SELECT_GUNLUK_ISLEMLER="SELECT gi.id, i.islemID,i.gorev,i.zorlukSeviyesi,i.zaman,p.personelID,p.ad,p.soyad,p.toplamIslemZorlugu,p.yapilanIsSayisi,p.enSonYapilanIs from Islem AS i, Personel AS p, GunlukIslem gi WHERE i.islemID=gi.islemID AND p.personelID=gi.personelID AND gi.olusturulmaTarihi='"+tarih+"' ORDER BY id DESC";
        Cursor cursor=db.rawQuery(SELECT_GUNLUK_ISLEMLER,null);
        ArrayList<GunlukIslem> gunlukIslemArrayList=new ArrayList<>();
        try {
            if (cursor!=null){
                cursor.moveToFirst();
                do {

                    Islem islem=new Islem();
                    Personel personel=new Personel();

                    islem.setId(cursor.getInt(1));
                    islem.setGorev(cursor.getString(2));
                    islem.setZorlukSeviyesi(cursor.getInt(3));
                    islem.setZaman(cursor.getInt(4));

                    personel.setId(cursor.getInt(5));
                    personel.setAd(cursor.getString(6));
                    personel.setSoyAd(cursor.getString(7));
                    personel.setToplamIslemZorlugu(cursor.getInt(8));
                    personel.setYapilanIsSayisi(cursor.getInt(9));
                    personel.setEnSonYapilanIs(cursor.getString(10));

                    gunlukIslemArrayList.add(new GunlukIslem(cursor.getInt(0),islem,personel));

                }while (cursor.moveToNext());
            }
            db.close();
        }catch (Exception e){}
        return gunlukIslemArrayList;
    }
    public ArrayList<Tarih> getTarihler(){
        SQLiteDatabase db=this.getWritableDatabase();
        String SELECT_TARIHLER="SELECT olusturulmaTarihi from GunlukIslem group by olusturulmaTarihi";
        Cursor cursor=db.rawQuery(SELECT_TARIHLER,null);
        ArrayList<Tarih> tarihler=new ArrayList<>();
        try {
            if (cursor!=null){
                cursor.moveToFirst();
                do {

                    Tarih tarih=new Tarih(cursor.getString(0));


                    tarihler.add(tarih);

                }while (cursor.moveToNext());
            }
            db.close();
        }catch (Exception e){}
        return tarihler;
    }

}
