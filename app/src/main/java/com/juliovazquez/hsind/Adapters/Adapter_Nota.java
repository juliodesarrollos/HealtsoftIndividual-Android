package com.juliovazquez.hsind.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juliovazquez.hsind.Pojos.Pojo_Nota;
import com.juliovazquez.hsind.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Nota extends RecyclerView.Adapter<Adapter_Nota.ViewHolder> {

    List<Pojo_Nota> pojoNotas = new ArrayList<>();
    Context context;

    public Adapter_Nota(Context context, List<Pojo_Nota> pojoNotas) {
        this.context = context;
        this.pojoNotas = pojoNotas;
    }

    @NonNull
    @Override
    public Adapter_Nota.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nota, parent, false);
        Adapter_Nota.ViewHolder viewHolder = new Adapter_Nota.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Nota.ViewHolder holder, int position) {
        holder.txtNota.setText(pojoNotas.get(position).getNota());
        holder.txtFecha.setText(pojoNotas.get(position).getCreated_at().substring(0, 10));
    }

    @Override
    public int getItemCount() {
        return pojoNotas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView txtNota;
        TextView txtFecha;

        public ViewHolder(@NonNull View item) {
            super(item);
            txtFecha = item.findViewById(R.id.txtFecha);
            txtNota = item.findViewById(R.id.txtNota);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            //this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            //clickListener.onClick(v, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
