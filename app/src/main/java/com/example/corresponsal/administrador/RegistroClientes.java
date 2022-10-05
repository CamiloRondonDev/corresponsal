package com.example.corresponsal.administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.corresponsal.R;
import com.example.corresponsal.entidades.Clientes;

public class RegistroClientes extends AppCompatActivity {

    EditText nombreCliente;
    EditText numeroCedula;
    EditText saldoInicialCliente;
    Button confirmar;
    Button cancelar;
    String vacio ="";
    ImageView atrasUsuario;
    Clientes clientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_clientes);

        nombreCliente = findViewById(R.id.nombreCliente);
        numeroCedula = findViewById(R.id.cedulaCliente);
        saldoInicialCliente = findViewById(R.id.saldoInicialCliente);
        confirmar = findViewById(R.id.botonConfirmarCliente);
        cancelar = findViewById(R.id.botonCancelarCliente);
        atrasUsuario = findViewById(R.id.atrasAdmin02);
        atrasUsuario.setVisibility(View.VISIBLE);
        clientes = new Clientes();

        atrasUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroClientes.this, AdminInformacion.class);
                startActivity(intent);
            }

        });

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!nombreCliente.getText().toString().equals(vacio) && !numeroCedula.getText().toString().equals(vacio) && !saldoInicialCliente.getText().toString().equals(vacio))
                {
                clientes.setNombreCliente(nombreCliente.getText().toString().trim());
                clientes.setNumeroCedula(numeroCedula.getText().toString().trim());
                clientes.setSaldoInicial(saldoInicialCliente.getText().toString().trim());

                Intent intent = new Intent(RegistroClientes.this, IngresePinUsuarioAdmin.class);
                intent.putExtra("NOMBRE_USUARIO_AD",clientes.getNombreCliente());
                intent.putExtra("CEDULA_CLIENTE_AD", clientes.getNumeroCedula());
                intent.putExtra("SALDO_CLIENTE_AD", clientes.getSaldoInicial());
                startActivity(intent);
                 }else {

                    Toast.makeText(RegistroClientes.this, "tiene algun campo vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}