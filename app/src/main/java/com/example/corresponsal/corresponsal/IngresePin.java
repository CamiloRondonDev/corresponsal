package com.example.corresponsal.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corresponsal.R;
import com.example.corresponsal.entidades.Clientes;

public class IngresePin extends AppCompatActivity {

    EditText pinUsuario;
    Button botonAceptarPin;
    Button botonCancelarPin;
    Clientes clientes;
    String nombre, pin, cedula,saldo;
    String vacio = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingrese_pin);
        clientes = new Clientes();
        pinUsuario = findViewById(R.id.pinUsuario);
        botonAceptarPin = findViewById(R.id.botonConfirmarPinUsuario);
        botonCancelarPin = findViewById(R.id.botonCancelarPinUsuario);

        Bundle extras = getIntent().getExtras();
        nombre = extras.getString("NOMBRE_USUARIO");
        pin = (extras.getString("PIN_USUARIO"));
        cedula = (extras.getString("CEDULA_CLIENTE"));
        saldo = (extras.getString("SALDO_CLIENTE"));

        botonAceptarPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!pinUsuario.getText().toString().equals(vacio))  {

                    clientes.setPinUsuario(pinUsuario.getText().toString());

                    Intent intent = new Intent(IngresePin.this, ConfirmarDatos.class);

                    intent.putExtra("NOMBRE_USUARIO",nombre);
                    intent.putExtra("CEDULA_CLIENTE",cedula);
                    intent.putExtra("SALDO_CLIENTE",saldo);
                    intent.putExtra("PIN_USUARIO",pinUsuario.getText().toString());
                    startActivity(intent);

                } else {
                    Toast.makeText(IngresePin.this, "campo vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonCancelarPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IngresePin.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

}