package com.example.corresponsal.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corresponsal.R;
import com.example.corresponsal.db.DbCorresponsal;
import com.example.corresponsal.entidades.Corresponsales;

public class ActualizarDatosCorresponsal extends AppCompatActivity {

    TextView contrasenaActual;
    TextView contrasenaNueva;
    TextView confirmarContrasenaNueva;
    ImageView atras;
    Button confirmarActualizacionDatosCorresponsal;
    Button cancelarActualizacionDatosCorresponsal;
    SharedPreference01 sharedPreference01;
    Corresponsales corresponsales;
    DbCorresponsal dbCorresponsal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar_datos_corresponsal);

        contrasenaActual = findViewById(R.id.contraseñaActual);
        contrasenaNueva = findViewById(R.id.contraseñaNueva);
        confirmarContrasenaNueva = findViewById(R.id.confirmarNuevaConstraseña);
        confirmarActualizacionDatosCorresponsal = findViewById(R.id.botonConfirmarActualizacionthis);
        cancelarActualizacionDatosCorresponsal = findViewById(R.id.botonCancelarActualizacionCorresponsalthis);
        atras = findViewById(R.id.atrasCorresponsal_sin_titulo);
        atras.setVisibility(View.VISIBLE);
        sharedPreference01 = new SharedPreference01(this);
        corresponsales = new  Corresponsales();
        dbCorresponsal = new DbCorresponsal(this);
        corresponsales = dbCorresponsal.mostrarCorresponsal();

        cancelarActualizacionDatosCorresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActualizarDatosCorresponsal.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( ActualizarDatosCorresponsal.this, CorresponInformacion.class);
                startActivity(intent);

            }
        });

        confirmarActualizacionDatosCorresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(contrasenaActual.getText().toString().equals(corresponsales.getContraseñaCorresponsal())) {

                    if (contrasenaNueva.getText().toString().equals(confirmarContrasenaNueva.getText().toString())) {
                        Toast.makeText(ActualizarDatosCorresponsal.this, "contraseñas iguales", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ActualizarDatosCorresponsal.this, ConfirmarActualizacionDatosCorresponsal.class);
                        intent.putExtra("ID", contrasenaNueva.getText().toString() );
                        startActivity(intent);

                    } else {
                        Toast.makeText(ActualizarDatosCorresponsal.this, "no son iguales", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ActualizarDatosCorresponsal.this, "la contraseña actual no coincide", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {

    }
}