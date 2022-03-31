package com.hbacakk.workmanager.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hbacakk.workmanager.models.Islem;
import com.hbacakk.workmanager.R;

import java.util.ArrayList;

public class IslemAdapter extends RecyclerView.Adapter<IslemAdapter.ViewHolder>{
    Activity activity;
    ArrayList<Islem> islemArrayList;

    public IslemAdapter(Activity activity, ArrayList<Islem> islemArrayList) {
        this.activity = activity;
        this.islemArrayList = islemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(islemArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return islemArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardViewWork;
        TextView textWork,textDifficultyLevel,textTime,textWorkName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textWork=itemView.findViewById(R.id.textWorkName);
            textDifficultyLevel=itemView.findViewById(R.id.textDifficultyLevel);
            textTime=itemView.findViewById(R.id.textTime);
            cardViewWork=itemView.findViewById(R.id.cardViewWork);
        }
        void setData(Islem islem){
            textWork.setText(""+islem.getGorev());
            textDifficultyLevel.setText("Zorluk Seviyesi :"+islem.getZorlukSeviyesi());
            textTime.setText("Zaman :"+islem.getZaman());
        }
    }
}
