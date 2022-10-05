package com.example.corresponsal.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corresponsal.R;
import com.example.corresponsal.administrador.VerDatosCorresponsal;
import com.example.corresponsal.entidades.Corresponsales;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdaptadorMostrarCorresponsales extends RecyclerView.Adapter<AdaptadorMostrarCorresponsales.CorresponsalesViewHolder> {
    ArrayList<Corresponsales>corresponsales;
    ArrayList<Corresponsales>corresponsalesOriginal;
    Context context;

    public AdaptadorMostrarCorresponsales(Context context,  ArrayList<Corresponsales> corresponsales ) {
        this.context = context;
        this.corresponsales = corresponsales;
        corresponsalesOriginal = new ArrayList<>();
        corresponsalesOriginal.addAll(corresponsales);
    }

    @NonNull
    @Override
    public CorresponsalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_corresponsales, null, false);
        return new CorresponsalesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CorresponsalesViewHolder holder, int position) {
        int position02 = position;

        holder.nombreCorresponsal.setText(corresponsales.get(position02).getNombreCorresponsal());
        holder.numeroCuentaCorresponsal.setText(corresponsales.get(position02).getNitCorresponsal());
        holder.nitCorresponsal.setText(corresponsales.get(position02).getNitCorresponsal());
        holder.habilitado.setText("habilitado");
        holder.saldo.setText(corresponsales.get(position02).getSaldoCorresponsal());

    }

    public void filtradoCorresponsales (String txtbuscar2) {
        int longitud2 =txtbuscar2.length();
        if (longitud2 == 0){
            corresponsales.clear();
            corresponsales.addAll(corresponsalesOriginal);

        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Corresponsales> collecion = corresponsales.stream().filter(i -> i.getNombreCorresponsal().toLowerCase().contains(txtbuscar2.toLowerCase())).collect(Collectors.toList());
                corresponsales.clear();
                corresponsales.addAll(collecion);

            }else {
                for ( Corresponsales c:corresponsalesOriginal) {
                    if (c.getNombreCorresponsal().toLowerCase().contains(txtbuscar2.toLowerCase()));
                        corresponsales.add(c);
                }
            }
        }

        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return corresponsales.size();
    }

    public class CorresponsalesViewHolder extends RecyclerView.ViewHolder {

        TextView nombreCorresponsal;
        TextView numeroCuentaCorresponsal;
        TextView nitCorresponsal;
        TextView habilitado;
        TextView saldo;



        public CorresponsalesViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreCorresponsal = itemView.findViewById(R.id.nombreCorresItem);
            numeroCuentaCorresponsal = itemView.findViewById(R.id.numCuentacorresItem);
            nitCorresponsal = itemView.findViewById(R.id.nitCorresItem);
            habilitado = itemView.findViewById(R.id.estadoHoDItem);
            saldo = itemView.findViewById(R.id.saldoCorresItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, VerDatosCorresponsal.class);
                    intent.putExtra("NITCORRESP" , corresponsales.get(getAdapterPosition()).getNitCorresponsal());
                    intent.putExtra("NOMBRE_CORRES" , corresponsales.get(getAdapterPosition()).getNombreCorresponsal());
                    intent.putExtra("SALDO_CORRES" , corresponsales.get(getAdapterPosition()).getSaldoCorresponsal());
                    intent.putExtra("CORREO_CORRES" , corresponsales.get(getAdapterPosition()).getCorreoCorresponsal());
                    context.startActivity(intent);
                }
            });



        }
    }
}
