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
import com.hbacakk.workmanager.R;
import com.hbacakk.workmanager.YeniIslemActivity;
import com.hbacakk.workmanager.adapter.IslemAdapter;
import com.hbacakk.workmanager.models.Islem;

import java.util.ArrayList;

public class IslemlerFragment extends Fragment {

    RecyclerView recyclerViewIslemler;
    ArrayList<Islem> islemArrayList;
    IslemAdapter islemAdapter;
    Database database;
    LinearLayout layoutEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gorevler, container, false);
        // Inflate the layout for this fragment

        initialize(view);

        return view;
    }

    private void initialize(View view) {
        //
        database=new Database(getActivity());
        //
        recyclerViewIslemler=view.findViewById(R.id.recylerViewIslemler);
        layoutEmpty=view.findViewById(R.id.layoutEmpty);
        //
        islemArrayList=new ArrayList<>();
        islemAdapter=new IslemAdapter(getActivity(),islemArrayList);
        recyclerViewIslemler.setAdapter(islemAdapter);
        getData();
        //
        view.findViewById(R.id.textYeniIslemler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), YeniIslemActivity.class));
            }
        });
    }

    private void getData() {
        islemArrayList.clear();
        islemArrayList.addAll(database.getIslemListesi());
        islemAdapter.notifyDataSetChanged();
        if (islemArrayList.size()>0){
            recyclerViewIslemler.setVisibility(View.VISIBLE);
            layoutEmpty.setVisibility(View.GONE);
        }else {
            recyclerViewIslemler.setVisibility(View.GONE);
            layoutEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
}