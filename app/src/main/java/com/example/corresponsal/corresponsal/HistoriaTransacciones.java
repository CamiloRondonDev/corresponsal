package com.example.corresponsal.corresponsal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.corresponsal.R;
import com.example.corresponsal.adaptadores.AdaptadorHistorial;
import com.example.corresponsal.db.DbHistorial;
import com.example.corresponsal.entidades.HistorialTransacciones;

import java.util.ArrayList;

public class HistoriaTransacciones extends AppCompatActivity implements SearchView.OnQueryTextListener{
    RecyclerView recyclerView;
    ImageView atras;
    DbHistorial dbHistorial;
    SearchView buscarClientesCorresponsal;
    ArrayList<HistorialTransacciones>historialTransacciones;
    AdaptadorHistorial adaptadorHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historia_transacciones);
        atras = findViewById(R.id.atrasCorresponsalseach);
        atras.setVisibility(View.VISIBLE);
        dbHistorial = new DbHistorial(this);
        historialTransacciones = new ArrayList<>();
        buscarClientesCorresponsal = findViewById(R.id.buscarenLista);

        recyclerView = findViewById(R.id.recycleHistorialTransacciones);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adaptadorHistorial = new AdaptadorHistorial(this, dbHistorial.mostrarHistorial());
        recyclerView.setAdapter(adaptadorHistorial);
        buscarClientesCorresponsal.setOnQueryTextListener(this);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoriaTransacciones.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s)

    {adaptadorHistorial.filtrado(s);
        return false;
    }

    @Override
    public void onBackPressed() {
    }
}