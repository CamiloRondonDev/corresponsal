package com.example.corresponsal.administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.corresponsal.R;
import com.example.corresponsal.db.DbCorresponsal;
import com.example.corresponsal.entidades.Corresponsales;

public class VerDatosCorresponsal extends AppCompatActivity {
    TextView mostrarNombreCorresponsal;
    TextView mostrarNitCorresponsal;
    TextView mostarSaldoCorresponsal;
    TextView mostrarCoreoCorresponsal;
    Button habilitar;
    Button deshabilitar;
    String nombre;
    String saldo;
    String correo;
    String nit;
   DbCorresponsal dbCorresponsal;
   Corresponsales corresponsales;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_datos_corresponsal);
        dbCorresponsal = new DbCorresponsal(this);
        corresponsales = new Corresponsales();
        mostrarNombreCorresponsal = findViewById(R.id.mostrarNombreCorresponsal);
        mostrarNitCorresponsal = findViewById(R.id.mostrarNitCorresponsal);
        mostarSaldoCorresponsal = findViewById(R.id.mostarSaldoCorresponsal);
        mostrarCoreoCorresponsal = findViewById(R.id.mostrarCoreoCorresponsal);
        habilitar = findViewById(R.id.habilitar);
        deshabilitar = findViewById(R.id.deshabilitar);

        Bundle extras = getIntent().getExtras();
        nit = extras.getString("NITCORRESP");
        nombre = extras.getString("NOMBRE_CORRES");
        saldo = extras.getString("SALDO_CORRES");
        correo = extras.getString("CORREO_CORRES");

        mostrarNombreCorresponsal.setText(nombre);
        mostrarNitCorresponsal.setText(nit);
        mostarSaldoCorresponsal.setText(saldo);
        mostrarCoreoCorresponsal.setText(correo);

        habilitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        deshabilitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }
}