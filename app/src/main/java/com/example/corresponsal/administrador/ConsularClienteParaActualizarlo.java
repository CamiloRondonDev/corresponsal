package com.example.corresponsal.administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corresponsal.R;
import com.example.corresponsal.db.DbClientes;
import com.example.corresponsal.entidades.Clientes;

public class ConsularClienteParaActualizarlo extends AppCompatActivity {
    EditText cedulaConsultarClienteparaActualizar;
    Button botonConfirmarConsultaClienteparaActualizar;
    Button botonCancelarConsultaClienteParaActualizar;
    String cc;
    Clientes clientes;
    DbClientes dbClientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consular_cliente_para_actualizarlo);

        cedulaConsultarClienteparaActualizar = findViewById(R.id.cedulaConsultarClienteparaActualizar);
        botonConfirmarConsultaClienteparaActualizar = findViewById(R.id.botonConfirmarConsultaClienteparaActualizar);
        botonCancelarConsultaClienteParaActualizar = findViewById(R.id.botonCancelarConsultaClienteParaActualizar);
        botonCancelarConsultaClienteParaActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( ConsularClienteParaActualizarlo.this , AdminInformacion.class);
                startActivity(intent);
            }
        });
        clientes = new Clientes();
        dbClientes = new DbClientes(this);


        botonConfirmarConsultaClienteparaActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cc = cedulaConsultarClienteparaActualizar.getText().toString();
                clientes = dbClientes.traerClientesPorCedula(cc);
                String cedula = clientes.getNumeroCedula();

                if (cc.equals(cedula)) {

                    Intent intent = new Intent(ConsularClienteParaActualizarlo.this, ActualizarCliente.class);
                    intent.putExtra("ID_USUARIO", clientes.getIdCliente());
                    intent.putExtra("NOMBRE_USUARIO", clientes.getNombreCliente());
                    intent.putExtra("CEDULA_USUARIO", clientes.getNumeroCedula());
                    intent.putExtra("SALDO_USUARIO", clientes.getSaldoInicial());
                    intent.putExtra("NUM_TARJETA", clientes.getNumerotarjeta());
                    intent.putExtra("FEHA_CREACION_USUARIO", clientes.getFechaCreacion());
                    intent.putExtra("CVV_USUARIO", clientes.getCvv());
                    intent.putExtra("PIN_USUARIO", clientes.getPinUsuario());
                    startActivity(intent);

                }else {
                    Toast.makeText(ConsularClienteParaActualizarlo.this, "Cliente no registrado", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    @Override
    public void onBackPressed() {

    }
}

