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
import com.example.corresponsal.entidades.Clientes;

public class CrearClienteCorresonsal extends AppCompatActivity {

    TextView nombreDelCliente;
    TextView numerocedulacliente;
    TextView saldoInicialCliente;
    String vacio = "";
    Button confirmarCrearCliente;
    Button cancelarCrearCliente;
    ImageView atras;
    Clientes clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_cliente_corresonsal);

        nombreDelCliente = findViewById(R.id.nombreDelCliente);
        numerocedulacliente = findViewById(R.id.numearoDeCedulaCliente);
        saldoInicialCliente = findViewById(R.id.saldoInicialClienteCorresponsal);
        confirmarCrearCliente = findViewById(R.id.botonConfirmarCrearCliente);
        cancelarCrearCliente = findViewById(R.id.botonCancelarCrearCliente);
        atras = findViewById(R.id.atrasCorresponsal_sin_titulo);
        atras.setVisibility(View.VISIBLE);
        clientes = new Clientes();

        cancelarCrearCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CrearClienteCorresonsal.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CrearClienteCorresonsal.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });

            confirmarCrearCliente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(!numerocedulacliente.getText().toString().equals(vacio) && !nombreDelCliente.getText().toString().equals(vacio) &&
                            !saldoInicialCliente.getText().toString().equals(vacio)){

                        clientes.setNombreCliente(nombreDelCliente.getText().toString().trim());
                        clientes.setNumeroCedula(numerocedulacliente.getText().toString().trim());
                        clientes.setSaldoInicial(saldoInicialCliente.getText().toString().trim());

                        Intent intent = new Intent(CrearClienteCorresonsal.this, IngresePin.class);
                        intent.putExtra("NOMBRE_USUARIO",clientes.getNombreCliente());
                        intent.putExtra("CEDULA_CLIENTE", clientes.getNumeroCedula());
                        intent.putExtra("SALDO_CLIENTE", clientes.getSaldoInicial());
                        startActivity(intent);
                    }else {
                        Toast.makeText(CrearClienteCorresonsal.this, "tiene algun campo vacio", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
    @Override
    public void onBackPressed() {

    }
}