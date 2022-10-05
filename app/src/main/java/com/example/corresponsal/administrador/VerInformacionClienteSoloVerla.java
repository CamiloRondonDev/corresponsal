package com.example.corresponsal.administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.corresponsal.R;

public class VerInformacionClienteSoloVerla extends AppCompatActivity {
    TextView mostrarNombreCorresponsal;
    TextView mostrarNitCorresponsal;
    TextView mostarSaldoCorresponsal;
    TextView mostrarCoreoCorresponsal;
    Button Aceptar;
    Button cancelar;
    ImageView atras02;
    int id;
    String nit;
    String nombre;
    String correo;
    String saldo;
    String contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_informacion_cliente_solo_verla);
        mostrarNombreCorresponsal = findViewById(R.id.mostrarNombreCorresponsal0);
        mostrarNitCorresponsal = findViewById(R.id.mostrarNitCorresponsal0);
        mostarSaldoCorresponsal = findViewById(R.id.mostarSaldoCorresponsal0);
        mostrarCoreoCorresponsal = findViewById(R.id.mostrarCoreoCorresponsal0);
        Aceptar = findViewById(R.id.Aceptar);
        cancelar = findViewById(R.id.cancelar);
        atras02 = findViewById(R.id.atrasAdmin02);
        atras02.setVisibility(View.VISIBLE);


        Bundle extras = getIntent().getExtras();
        id = extras.getInt("ID_CORRES");
        nit = extras.getString("NIT");
        nombre = extras.getString("NOMBRE_CORRES");
        correo = extras.getString("CORREO_CORESP");
        saldo = extras.getString("SALDO_CORRES");
        contraseña = extras.getString("CONTRASEÑA_CORRES");

        mostrarNombreCorresponsal.setText(nombre);
        mostrarNitCorresponsal.setText(nit);
        mostarSaldoCorresponsal.setText(saldo);
        mostrarCoreoCorresponsal.setText(correo);

        atras02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerInformacionClienteSoloVerla.this , AdminInformacion.class);
                startActivity(intent);
            }
        });

        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent02 = new Intent(VerInformacionClienteSoloVerla.this , AdminInformacion.class);
                startActivity(intent02);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent03 = new Intent(VerInformacionClienteSoloVerla.this , AdminInformacion.class);
                startActivity(intent03);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}