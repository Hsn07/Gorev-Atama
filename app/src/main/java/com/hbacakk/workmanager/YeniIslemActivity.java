package com.hbacakk.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hbacakk.workmanager.models.Islem;
import com.hbacakk.workmanager.models.Personel;

public class YeniIslemActivity extends AppCompatActivity {

    Database database;
    CardView cardViewAddIslem;
    EditText inputGorevAdi,inputZorlukSeviyesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_islem);
        initialize();
    }

    private void initialize() {
        //
        database=new Database(this);
        //
        inputGorevAdi=findViewById(R.id.inputWorkName);
        inputZorlukSeviyesi=findViewById(R.id.inputDifficultyLevel);

        //
        findViewById(R.id.cardViewAddIslem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputGorevAdi.getText().toString().trim().length()<0 || inputGorevAdi.getText().toString().trim().isEmpty()){
                    inputGorevAdi.setError("Lütfen personel adını giriniz !!!");
                }else if (inputZorlukSeviyesi.getText().toString().trim().length()<0 || inputZorlukSeviyesi.getText().toString().trim().isEmpty()){
                    inputZorlukSeviyesi.setError("Lütfen personel Soyadını giriniz !!!");
                }else {
                    if (database.yeniIslem(new Islem(
                            inputGorevAdi.getText().toString().trim(),
                            Integer.parseInt(inputZorlukSeviyesi.getText().toString().trim()),
                            1

                    ))){
                        Toast.makeText(YeniIslemActivity.this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(YeniIslemActivity.this, "Kayıt Başarısız", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
        findViewById(R.id.imageViewBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}