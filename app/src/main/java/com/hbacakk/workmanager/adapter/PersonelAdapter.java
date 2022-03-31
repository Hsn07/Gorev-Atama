package com.hbacakk.workmanager.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hbacakk.workmanager.models.Personel;
import com.hbacakk.workmanager.R;

import java.util.ArrayList;

public class PersonelAdapter extends RecyclerView.Adapter<PersonelAdapter.ViewHolder>{
    Activity activity;
    ArrayList<Personel> personelArrayList;

    public PersonelAdapter(Activity activity, ArrayList<Personel> personelArrayList) {
        this.activity = activity;
        this.personelArrayList = personelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personal,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(personelArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return personelArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textName,textSum,textLastWork;
        CardView cardViewPersonel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName=itemView.findViewById(R.id.textPernonalName);
            textSum=itemView.findViewById(R.id.textSum);
            textLastWork=itemView.findViewById(R.id.textLastWork);
            cardViewPersonel=itemView.findViewById(R.id.cardViewPersonel);
        }
        public void setData(Personel personel){
            textName.setText(personel.getAd()+" "+personel.getSoyAd());
            textSum.setText(personel.getToplamIslemZorlugu()+" / "+personel.getYapilanIsSayisi());
            textLastWork.setText(personel.getEnSonYapilanIs());

        }
    }
}
