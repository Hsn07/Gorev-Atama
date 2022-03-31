package com.hbacakk.workmanager.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hbacakk.workmanager.Database;
import com.hbacakk.workmanager.R;
import com.hbacakk.workmanager.adapter.GunlukIslemAdapter;
import com.hbacakk.workmanager.adapter.TarihAdapter;
import com.hbacakk.workmanager.models.GunlukIslem;
import com.hbacakk.workmanager.models.Islem;
import com.hbacakk.workmanager.models.Personel;
import com.hbacakk.workmanager.models.Tarih;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class GorevDagilimiFragment extends Fragment {

    TextView textPersonelSayisi,textIslemSayisi;
    Database database;

    ArrayList<Personel> personelArrayList=new ArrayList<>();
    ArrayList<Islem> islemArrayList=new ArrayList<>();
    ArrayList<GunlukIslem> gunlukIslemArrayList=new ArrayList<>();

    RecyclerView recylerViewGorevDagilimi;
    GunlukIslemAdapter gunlukIslemAdapter;
    LinearLayout layoutEmpty;


    ArrayList<Tarih> tarihArrayList;
    RecyclerView recylerViewTarihler;
    TarihAdapter tarihAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gorev_dagilimi, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        //
        database=new Database(getContext());
        //
        recylerViewGorevDagilimi=view.findViewById(R.id.recylerViewGorevDagilimi);
        recylerViewTarihler=view.findViewById(R.id.recylerViewTarihler);
        textPersonelSayisi=view.findViewById(R.id.textPersonalCount);
        textIslemSayisi=view.findViewById(R.id.textJobCount);
        layoutEmpty=view.findViewById(R.id.layoutEmpty);

        //
        gunlukIslemAdapter=new GunlukIslemAdapter(getActivity(),gunlukIslemArrayList);
        recylerViewGorevDagilimi.setAdapter(gunlukIslemAdapter);
        //


        //

        tarihArrayList=new ArrayList<>();
        tarihAdapter=new TarihAdapter(getActivity(),tarihArrayList);
        recylerViewTarihler.setAdapter(tarihAdapter);

        getDatabaseInfo();

        //
        view.findViewById(R.id.cardViewGorevDagilimi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (personelArrayList.size()==6 && islemArrayList.size()==6){

                }else {

                }*/
                Calendar calendar=Calendar.getInstance();
                Log.d("TAG", "onClick:------------- Date: "+new SimpleDateFormat("yyyy-MM-dd hh:mm").format(calendar.getTime()));
                if (personelArrayList.size()==islemArrayList.size()){
                    for (int i=0;i<personelArrayList.size();i++){
                        Tarih tarih=new Tarih(calendar.getTime());
                        Log.d("TAG", "onClick: "+islemArrayList.get(i).getId()+
                                " "+personelArrayList.get(i).getId()+" "+
                                tarih.getTarihZaman()
                                );
                        if (database.yeniGunlukIslem(
                            islemArrayList.get(i).getId(),
                            personelArrayList.get(i).getId(), tarih.getTarihZaman()
                        )){
                            getGunlukGorevDagilimi(tarih.getTarihZaman());
                            Toast.makeText(getContext(), "Görev Dağılımı Yapıldı...", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Görev Dağılımı Başarısız...", Toast.LENGTH_SHORT).show();
                        }

                    }

                }else {
                    Toast.makeText(getContext(), "Lütfen personel ve işlem sayısı eşit olmalıdır.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getTarihler() {
        tarihArrayList.clear();
        tarihArrayList.addAll(database.getTarihler());
        tarihAdapter.notifyDataSetChanged();
        if (tarihArrayList.size()!=0){
            getGunlukGorevDagilimi(tarihArrayList.get(tarihArrayList.size()-1).getTarihZaman());
            recylerViewTarihler.smoothScrollToPosition(recylerViewTarihler.getAdapter().getItemCount());
        }else {
            getGunlukGorevDagilimi(new Tarih(Calendar.getInstance().getTime()).getTarihZaman());
        }
    }

    private void getGunlukGorevDagilimi(String tarih) {
        Log.d("TAG", "getGunlukGorevDagilimi: tarih:"+tarih);
        gunlukIslemArrayList.clear();
        gunlukIslemArrayList.addAll(database.getGunlukIslemler(tarih));
        gunlukIslemAdapter.notifyDataSetChanged();
        if (gunlukIslemArrayList.size()>0){
            recylerViewGorevDagilimi.setVisibility(View.VISIBLE);
            layoutEmpty.setVisibility(View.GONE);
        }else {
            recylerViewGorevDagilimi.setVisibility(View.GONE);
            layoutEmpty.setVisibility(View.VISIBLE);
        }
    }

    private void getDatabaseInfo() {
        personelArrayList.clear();
        islemArrayList.clear();

        personelArrayList.addAll(database.getPersonelListesi());
        islemArrayList.addAll(database.getIslemListesi());

        textPersonelSayisi.setText(personelArrayList.size()+"");
        textIslemSayisi.setText(islemArrayList.size()+"");

        //
        getTarihler();
    }

    @Override
    public void onResume() {
        super.onResume();
        getDatabaseInfo();
    }
}