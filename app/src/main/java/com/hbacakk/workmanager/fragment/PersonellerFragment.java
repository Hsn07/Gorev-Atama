package com.hbacakk.workmanager.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hbacakk.workmanager.Database;
import com.hbacakk.workmanager.YeniPersonelActivity;
import com.hbacakk.workmanager.adapter.PersonelAdapter;
import com.hbacakk.workmanager.models.Personel;
import com.hbacakk.workmanager.R;

import java.util.ArrayList;

public class PersonellerFragment extends Fragment {
    
    ArrayList<Personel> personelArrayList;
    RecyclerView recyclerViewPersoneller;
    PersonelAdapter personelAdapter;
    Database database;
    LinearLayout layoutEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_personeller, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        //Database
        database=new Database(getActivity());
        //initialize
        recyclerViewPersoneller=view.findViewById(R.id.recylerViewPersoneller);
        layoutEmpty=view.findViewById(R.id.layoutEmpty);

        //Personel Listelenmesi
        personelArrayList =new ArrayList<>();
        personelAdapter=new PersonelAdapter(getActivity(),personelArrayList);
        recyclerViewPersoneller.setAdapter(personelAdapter);
        getPersonelData();
        //
        view.findViewById(R.id.textYeniPersonel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), YeniPersonelActivity.class));
            }
        });
    }

    private void getPersonelData() {
        personelArrayList.clear();
        personelArrayList.addAll(database.getPersonelListesi());
        personelAdapter.notifyDataSetChanged();

        if (personelArrayList.size()>0){
            recyclerViewPersoneller.setVisibility(View.VISIBLE);
            layoutEmpty.setVisibility(View.GONE);
        }else {
            recyclerViewPersoneller.setVisibility(View.GONE);
            layoutEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPersonelData();
    }
}