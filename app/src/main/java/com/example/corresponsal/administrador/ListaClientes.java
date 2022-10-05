package com.example.corresponsal.administrador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.corresponsal.R;
import com.example.corresponsal.adaptadores.AdaptadorMostrarClientes;
import com.example.corresponsal.db.DbClientes;
import com.example.corresponsal.entidades.Clientes;

import java.util.ArrayList;

public class ListaClientes extends AppCompatActivity  implements SearchView.OnQueryTextListener{
    TextView tituloBarner;
    ImageView flehaAtrasListaClientes;
    RecyclerView recyclerView;
    SearchView buscarClientes;
    ArrayList<Clientes> listaclientes;
    AdaptadorMostrarClientes adaptadorMostrarClientes;
    DbClientes dbClientes;
    SearchView buscarenListaClientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_clientes);
        listaclientes = new ArrayList<>();
        dbClientes = new DbClientes(this);

        buscarenListaClientes = findViewById(R.id.buscarenListaClientes);
        tituloBarner = findViewById(R.id.tituloListadoClientes);
        tituloBarner.setText("Listado Clientes");
        flehaAtrasListaClientes = findViewById(R.id.atrasAdmin03);
        flehaAtrasListaClientes.setVisibility(View.VISIBLE);
        buscarClientes = findViewById(R.id.buscarenLista);


        //aca le damos funcionalidad al adaptador con el recycler
        recyclerView = findViewById(R.id.recycleListadoClientes);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adaptadorMostrarClientes = new AdaptadorMostrarClientes(this, dbClientes.mostrarClientes());
        recyclerView.setAdapter(adaptadorMostrarClientes);


        flehaAtrasListaClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaClientes.this, AdminInformacion.class);
                startActivity(intent);
            }
        });


        buscarenListaClientes.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adaptadorMostrarClientes.filtrado(s);
        return false;
    }

    @Override
    public void onBackPressed() {

    }
}