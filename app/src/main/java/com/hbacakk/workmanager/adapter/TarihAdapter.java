package com.hbacakk.workmanager.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hbacakk.workmanager.R;
import com.hbacakk.workmanager.listener.TarihListener;
import com.hbacakk.workmanager.models.Tarih;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TarihAdapter extends RecyclerView.Adapter<TarihAdapter.ViewHolder>{
    Activity activity;
    ArrayList<Tarih> tarihArrayList;

    public TarihAdapter(Activity activity, ArrayList<Tarih> tarihArrayList) {
        this.activity = activity;
        this.tarihArrayList = tarihArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(tarihArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return tarihArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textTarih,textSaat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTarih=itemView.findViewById(R.id.textDate);
            textSaat=itemView.findViewById(R.id.textTime);

        }
        void setData(Tarih tarih){
            textTarih.setText(tarih.getAyGun());
            textSaat.setText(tarih.getSaat());
        }
    }
}
