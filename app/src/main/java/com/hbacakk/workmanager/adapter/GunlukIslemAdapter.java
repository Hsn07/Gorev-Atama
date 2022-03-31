package com.hbacakk.workmanager.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hbacakk.workmanager.R;
import com.hbacakk.workmanager.models.GunlukIslem;

import java.util.ArrayList;

public class GunlukIslemAdapter extends RecyclerView.Adapter<GunlukIslemAdapter.ViewHolder>{
    Activity activity;
    ArrayList<GunlukIslem> gunlukIslemArrayList;

    public GunlukIslemAdapter(Activity activity, ArrayList<GunlukIslem> gunlukIslemArrayList) {
        this.activity = activity;
        this.gunlukIslemArrayList = gunlukIslemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(gunlukIslemArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return gunlukIslemArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textJobPersonalName,textJobPersonalLastJob,textJobDifficultyLevel,textJobTime,textJobWork;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textJobPersonalName=itemView.findViewById(R.id.textJobPersonalName);
            textJobPersonalLastJob=itemView.findViewById(R.id.textJobPersonalLastJob);
            textJobDifficultyLevel=itemView.findViewById(R.id.textJobDifficultyLevel);
            textJobTime=itemView.findViewById(R.id.textJobTime);
            textJobWork=itemView.findViewById(R.id.textJobWork);
        }
        public void setData(GunlukIslem gunlukIslem){
            textJobPersonalName.setText(gunlukIslem.getPersonel().getAd()+" "+gunlukIslem.getPersonel().getSoyAd());
            textJobPersonalLastJob.setText(gunlukIslem.getPersonel().getEnSonYapilanIs());
            textJobDifficultyLevel.setText(gunlukIslem.getIslem().getZorlukSeviyesi()+" Zorluk Seviyesi");
            textJobTime.setText(gunlukIslem.getIslem().getZaman()+" Gün");
            textJobWork.setText(gunlukIslem.getIslem().getGorev());
        }
    }
}
