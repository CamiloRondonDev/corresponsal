package com.example.corresponsal.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.corresponsal.corresponsal.Deposito;
import com.example.corresponsal.corresponsal.HistoriaTransacciones;
import com.example.corresponsal.corresponsal.ConsultarSaldoCorresponsalParaCliente;
import com.example.corresponsal.corresponsal.PagoConTarjeta;
import com.example.corresponsal.R;
import com.example.corresponsal.corresponsal.RetirosCorresponsal;
import com.example.corresponsal.corresponsal.Transferencias;
import com.example.corresponsal.entidades.MenuCorresponsal;

import java.util.ArrayList;

public class AdaptadorMenuCorresponsal extends RecyclerView.Adapter<AdaptadorMenuCorresponsal.CorresponsalViewHolder> {
    ArrayList<MenuCorresponsal> menuCorresponsal;
    Context context;

    public AdaptadorMenuCorresponsal(Context context , ArrayList<MenuCorresponsal> menuCorresponsal) {
        this.context = context;
        this.menuCorresponsal = menuCorresponsal;
    }

    @NonNull
    @Override
    public AdaptadorMenuCorresponsal.CorresponsalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_corresponsal, null, false);
        return new CorresponsalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorMenuCorresponsal.CorresponsalViewHolder holder, int position) {
        int position02 = position;

        Glide.with(context)
                .load(menuCorresponsal.get(position02).getUrlImagenMenuCorresponsal())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imagenMenuCorresponsal);
        holder.nombreMenuCorresponsal.setText(menuCorresponsal.get(position02).getNombreMenuCorresponsal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (menuCorresponsal.get(position02).getNombreMenuCorresponsal()) {
                    case "Pago con tarjeta" :
                        Intent intent01 = new Intent(context , PagoConTarjeta.class);
                        context.startActivity(intent01);
                        break;

                    case "Retiros" :
                        Intent intent02 = new Intent(context , RetirosCorresponsal.class);
                        context.startActivity(intent02);
                        break;

                    case "Depositos" :
                        Intent intent03 = new Intent(context , Deposito.class);
                        context.startActivity(intent03);
                        break;

                    case "Transferencias" :
                        Intent intent04 = new Intent(context , Transferencias.class);
                        context.startActivity(intent04);
                        break;

                    case "Historial Transferencias" :
                        Intent intent05 = new Intent(context , HistoriaTransacciones.class);
                        context.startActivity(intent05);
                        break;
                    case "Consulta Saldo" :
                        Intent intent06 = new Intent(context , ConsultarSaldoCorresponsalParaCliente.class);
                        context.startActivity(intent06);
                        break;



                }
            }
        });




    }






    @Override
    public int getItemCount() {
        return menuCorresponsal.size();
    }

    public class CorresponsalViewHolder extends RecyclerView.ViewHolder {

        TextView nombreMenuCorresponsal;
        ImageView imagenMenuCorresponsal;

        public CorresponsalViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreMenuCorresponsal = itemView.findViewById(R.id.nombre_menu_corresponsal);
            imagenMenuCorresponsal = itemView.findViewById(R.id.imagen_corresponsal_menu);
        }

    }
}
