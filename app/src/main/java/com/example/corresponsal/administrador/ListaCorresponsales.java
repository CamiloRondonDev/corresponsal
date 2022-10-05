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
import com.example.corresponsal.adaptadores.AdaptadorMostrarCorresponsales;
import com.example.corresponsal.db.DbCorresponsal;
import com.example.corresponsal.entidades.Corresponsales;

import java.util.ArrayList;

public class ListaCorresponsales extends AppCompatActivity implements SearchView.OnQueryTextListener{
    TextView tituloBarnerCorresponsal;
    ImageView flehaAtrasListaCorresponsal;
    RecyclerView recyclerViewCorresponsal;
    SearchView buscarClientesCorresponsal;
    ArrayList<Corresponsales> listacorresponsales;
    DbCorresponsal dbCorresponsal;
    AdaptadorMostrarCorresponsales adaptadorMostrarCorresponsales;
    SearchView buscarenListaClientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_corresponsales);

        tituloBarnerCorresponsal = findViewById(R.id.tituloListadoClientes);
        tituloBarnerCorresponsal.setText("Listado Corresponsales");
        flehaAtrasListaCorresponsal = findViewById(R.id.atrasAdmin03);
        flehaAtrasListaCorresponsal.setVisibility(View.VISIBLE);
        buscarClientesCorresponsal = findViewById(R.id.buscarenLista);
        listacorresponsales = new ArrayList<>();
        dbCorresponsal = new DbCorresponsal(this);
        buscarenListaClientes = findViewById(R.id.buscarenListaClientes);

        recyclerViewCorresponsal = findViewById(R.id.recycleListadoCorresponsales);
        recyclerViewCorresponsal.setLayoutManager(new GridLayoutManager(this, 1));
        adaptadorMostrarCorresponsales = new AdaptadorMostrarCorresponsales(this,dbCorresponsal.mostrarCorresponsales());
        recyclerViewCorresponsal.setAdapter(adaptadorMostrarCorresponsales);



        flehaAtrasListaCorresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaCorresponsales.this, AdminInformacion.class);
                startActivity(intent);
            }
        });

        buscarenListaClientes.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    //implementamos el seach
    @Override
    public boolean onQueryTextChange(String s) {
        adaptadorMostrarCorresponsales.filtradoCorresponsales(s);
        return false;
    }
    @Override
    public void onBackPressed() {

    }
}