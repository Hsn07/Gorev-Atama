package com.hbacakk.workmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.hbacakk.workmanager.fragment.GorevDagilimiFragment;
import com.hbacakk.workmanager.fragment.IslemlerFragment;
import com.hbacakk.workmanager.fragment.PersonellerFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        Calendar calendar=Calendar.getInstance();
        Log.d("TAG", "onCreate------------------:Time  "+calendar.getTime());
        Log.d("TAG", "onCreate------------------:Date "+new SimpleDateFormat("yyyy-MM-dd hh:mm").format(calendar.getTime()));


    }



    private void initialize() {
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        /*NavController navController= Navigation.findNavController(this,R.id.fragmentContainerView);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);
*/

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.gunluk:
                        loadFragment(new GorevDagilimiFragment());
                        return true;
                    case R.id.gorevler:
                        loadFragment(new IslemlerFragment());
                        return true;
                    case R.id.personeller:
                        loadFragment(new PersonellerFragment());
                        return true;
                }
                return false;
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragment, null)
                .commit();
    }


}