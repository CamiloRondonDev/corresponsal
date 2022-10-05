package com.example.corresponsal.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corresponsal.R;
import com.example.corresponsal.entidades.Clientes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AdaptadorMostrarClientes extends RecyclerView.Adapter<AdaptadorMostrarClientes.ClienteViewHolder> {
   ArrayList<Clientes>clientes;
   ArrayList<Clientes>listaOriginal;
   Context context;

    public AdaptadorMostrarClientes(Context context, ArrayList<Clientes> clientes) {
        this.context = context;
        this.clientes = clientes;

        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(clientes);
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_clientes, null, false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        int position01 = position;

        holder.nombreClienteItem.setText(clientes.get(position01).getNombreCliente());
        holder.cedulaClienteItem.setText(clientes.get(position01).getNumerotarjeta());
        holder.numerocuentaItem.setText(clientes.get(position01).getNumeroCedula());
        holder.saldoClienteItem.setText(clientes.get(position01).getSaldoInicial());

    }
    //metodo pase seach
    public void filtrado (String textobuscar){
        int longitud = textobuscar.length();
        if(longitud == 0){
            clientes.clear();
            clientes.addAll(listaOriginal);
        }else {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Clientes> collecion = clientes.stream().filter(i -> i.getNombreCliente().toLowerCase().contains(textobuscar.toLowerCase())).collect(Collectors.toList());
                clientes.clear();
                clientes.addAll(collecion);
            } else{
                for (Clientes c: listaOriginal){
                    if (c.getNombreCliente().toLowerCase().contains(textobuscar)){
                        clientes.add(c);
                    }
                }
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView nombreClienteItem;
        TextView numerocuentaItem;
        TextView cedulaClienteItem;
        TextView saldoClienteItem;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreClienteItem = itemView.findViewById(R.id.nombreClienteitem);
            numerocuentaItem = itemView.findViewById(R.id.numeroCuentaclienteitem);
            cedulaClienteItem = itemView.findViewById(R.id.cedulaclienteitem);
            saldoClienteItem = itemView.findViewById(R.id.saldoClienteItem);
        }
    }
}
