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
import com.example.corresponsal.db.DbClientes;
import com.example.corresponsal.entidades.Clientes;

public class ConsultarCliente extends AppCompatActivity {

    EditText numeroCedulaClienteConsulta;
    Button confirmarConsultaCliente;
    Button cancelarConsultaCliente;
    ImageView flechaatrasConsultaUsuarios;
    DbClientes dbClientes ;
    String cc;
    Clientes clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar_cliente);

        numeroCedulaClienteConsulta = findViewById(R.id.cedulaConsultarCliente);
        confirmarConsultaCliente = findViewById(R.id.botonConfirmarConsultaCliente);
        cancelarConsultaCliente = findViewById(R.id.botonCancelarConsultaCliente);
        flechaatrasConsultaUsuarios = findViewById(R.id.atrasAdmin02);
        flechaatrasConsultaUsuarios.setVisibility(View.VISIBLE);
        dbClientes = new DbClientes(this);
        clientes = new Clientes();

        cancelarConsultaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarCliente.this, AdminInformacion.class);
                startActivity(intent);
            }
        });


        confirmarConsultaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                cc = numeroCedulaClienteConsulta.getText().toString();


                 clientes = dbClientes.traerClientesPorCedula(cc);
                String numCedula  = clientes.getNumeroCedula();
                if (cc.equals(numCedula)){
                    Intent intent = new Intent(ConsultarCliente.this, VerDatosDeUsuario.class);
                    intent.putExtra("ID_USUARIO", clientes.getIdCliente());
                    intent.putExtra("NOMBRE_USUARIO", clientes.getNombreCliente());
                    intent.putExtra("CEDULA_USUARIO", clientes.getNumeroCedula());
                    intent.putExtra("SALDO_USUARIO", clientes.getSaldoInicial());
                    intent.putExtra("NUM_TARJETA", clientes.getNumerotarjeta());
                    intent.putExtra("FEHA_CREACION_USUARIO", clientes.getFechaCreacion());
                    intent.putExtra("CVV_USUARIO", clientes.getCvv());
                    intent.putExtra("PIN_USUARIO", clientes.getPinUsuario());
                    startActivity(intent);

                } else {
                    Toast.makeText(ConsultarCliente.this, "cedula no registrada", Toast.LENGTH_SHORT).show();
                }

            }
        });


        flechaatrasConsultaUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarCliente.this, AdminInformacion.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {

    }
}