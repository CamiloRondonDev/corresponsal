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
import com.example.corresponsal.administrador.ConsultarCliente;
import com.example.corresponsal.administrador.ConsultarCorresponsalSoloConsulta;
import com.example.corresponsal.administrador.ListaClientes;
import com.example.corresponsal.administrador.ListaCorresponsales;
import com.example.corresponsal.R;
import com.example.corresponsal.administrador.RegistoCorresponsal;
import com.example.corresponsal.administrador.RegistroClientes;
import com.example.corresponsal.entidades.MenuAdmin;

import java.util.ArrayList;

public class AdaptadorAdministrador extends RecyclerView.Adapter<AdaptadorAdministrador.AdminViewHolder> {
    ArrayList<MenuAdmin> menu;
    Context context;


    public AdaptadorAdministrador(Context context, ArrayList<MenuAdmin> listaMenu) {
        this.context = context;
        this.menu = listaMenu;
    }

    @NonNull
    @Override
    public AdaptadorAdministrador.AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_admin, null, false);
        return new AdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorAdministrador.AdminViewHolder holder, int position) {

int position01 = position;
        Glide.with(context)
                .load(menu.get(position01).getUrlMenu())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imagenMenuAdmin);
        holder.nombreMenuAdmin.setText(menu.get(position01).getNombreMenu());






        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (menu.get(position01).getNombreMenu()) {
                    case "Registrar Cliente" :

                        Intent intent01 = new Intent(context , RegistroClientes.class);
                        context.startActivity(intent01);

                        break;

                    case "Registrar Corresponsal" :
                        Intent intent02 = new Intent(context , RegistoCorresponsal.class);
                        context.startActivity(intent02);
                        break;

                    case "Colsultar Cliente" :
                        Intent intent03 = new Intent(context , ConsultarCliente.class);
                        context.startActivity(intent03);
                        break;

                    case "Consultar Corresponsal" :
                        Intent intent04 = new Intent(context , ConsultarCorresponsalSoloConsulta.class);
                        context.startActivity(intent04);
                        break;


                    case "Listado Clientes" :
                        Intent intent05 = new Intent(context , ListaClientes.class);
                        context.startActivity(intent05);
                        break;


                    case "Listado Corresponsales" :
                        Intent intent06 = new Intent(context , ListaCorresponsales.class);
                        context.startActivity(intent06);
                        break;

                    default:
                        break;




                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class AdminViewHolder extends RecyclerView.ViewHolder {

        TextView nombreMenuAdmin;
        ImageView imagenMenuAdmin;

        public AdminViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreMenuAdmin = itemView.findViewById(R.id.nombre_menu);
            imagenMenuAdmin = itemView.findViewById(R.id.imagen_admin);


        }
    }
}
