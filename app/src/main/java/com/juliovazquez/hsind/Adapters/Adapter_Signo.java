package com.juliovazquez.hsind.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juliovazquez.hsind.Pojos.Pojo_Signos_Vitales;
import com.juliovazquez.hsind.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Signo extends RecyclerView.Adapter<Adapter_Signo.ViewHolder> {

    List<Pojo_Signos_Vitales> signosVitalesList = new ArrayList<>();
    Context context;

    public Adapter_Signo(Context context, List<Pojo_Signos_Vitales> pojoSignos_vitales) {
        this.context = context;
        this.signosVitalesList = pojoSignos_vitales;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_signos, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtPresion.setText(signosVitalesList.get(position).getPresionSistolica() + "/" + signosVitalesList.get(position).getPresionDiastolica());
        holder.txtPulso.setText(signosVitalesList.get(position).getPulso() + " BPM");
        holder.txtFr.setText(signosVitalesList.get(position).getFrecuenciaRespiratoria() + " RPM");
        holder.txtGlucosa.setText(signosVitalesList.get(position).getGlucosa() + "% O2");
        holder.txtTemperatura.setText(signosVitalesList.get(position).getTemperatura() + "°C");
        holder.txtFecha.setText(signosVitalesList.get(position).getCreated_at().substring(0,10));
    }

    @Override
    public int getItemCount() {
        return signosVitalesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private ItemClickListener clickListener;
        TextView txtFecha;
        TextView txtFr;
        TextView txtGlucosa;
        TextView txtPulso;
        TextView txtTemperatura;
        TextView txtPresion;
        LinearLayout layoutSignos;

        public ViewHolder(@NonNull View item) {
            super(item);
            txtFecha = item.findViewById(R.id.txtFecha);
            txtPulso = item.findViewById(R.id.txtPulso);
            txtFr = item.findViewById(R.id.txtFr);
            txtGlucosa = item.findViewById(R.id.txtGlucosa);
            txtTemperatura = item.findViewById(R.id.txtTemperatura);
            txtPresion = item.findViewById(R.id.txtPresion);
            layoutSignos = item.findViewById(R.id.layoutSignos);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
