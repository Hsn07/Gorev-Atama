package com.hbacakk.workmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hbacakk.workmanager.models.Personel;

public class YeniPersonelActivity extends AppCompatActivity {

    EditText inputAd,inputSoyad;
    Button buttonKaydet;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_personel);
        initialize();
    }

    private void initialize() {
        //
        database=new Database(this);
        //
        inputAd=findViewById(R.id.inputName);
        inputSoyad=findViewById(R.id.inputSurname);
        //

        findViewById(R.id.imageViewBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //
        findViewById(R.id.cardViewAddPersonal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputAd.getText().toString().trim().length()<0 || inputAd.getText().toString().trim().isEmpty()){
                    inputAd.setError("Lütfen personel adını giriniz !!!");
                }else if (inputSoyad.getText().toString().trim().length()<0 || inputSoyad.getText().toString().trim().isEmpty()){
                    inputSoyad.setError("Lütfen personel Soyadını giriniz !!!");
                }else {
                    if (database.yeniPersonel(new Personel(
                            inputAd.getText().toString().trim(),
                            inputSoyad.getText().toString().trim(),
                            0,
                            0,
                            "Henüz bir iş yapılmadı"

                    ))){
                        Toast.makeText(YeniPersonelActivity.this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(YeniPersonelActivity.this, "Kayıt Başarısız", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }
}