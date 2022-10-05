package com.example.corresponsal.administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.corresponsal.R;

public class ActualizarCliente extends AppCompatActivity {

    ImageView atras;
    EditText nombreClienteActualizar;
    EditText cedulaClienteActualizar;
    EditText pinClienteActualizar;
    EditText confirmarpPinClienteActualizar;
    Button botonConfirmarActualizacionCliente;
    Button botonCancelarActualizacionCliente;
    Button botonConfirmardatoCliente;
    LinearLayout botones;
    String nombreCliente;
    String cedulaUsuario;
    String saldoUsuario;
    String numerTarjeta;
    String fechaCreacion;
    String cvvUsuario;
    String pinUsuario;
    String vacio = "";
    int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar_cliente);

        nombreClienteActualizar = findViewById(R.id.nombreClienteActualizar);
        cedulaClienteActualizar = findViewById(R.id.cedulaClienteActualizar);
        pinClienteActualizar = findViewById(R.id.nuevoPinUsuario);
        confirmarpPinClienteActualizar = findViewById(R.id.nuevoPinUsuarioConfirmar);
        botonConfirmarActualizacionCliente = findViewById(R.id.botonConfirmarActualizarCliente);
        botonCancelarActualizacionCliente = findViewById(R.id.botonCancelarActualizarCliente);
        atras = findViewById(R.id.atrasAdmin02);
        atras.setVisibility(View.VISIBLE);
        botonConfirmardatoCliente= findViewById(R.id.botonConfirmardatoCliente);
        botones = findViewById(R.id.botones01);


        Bundle extras = getIntent().getExtras();
         idUsuario = extras.getInt("ID_USUARIO");
         nombreCliente= extras.getString("NOMBRE_USUARIO");
         cedulaUsuario= extras.getString("CEDULA_USUARIO");
         saldoUsuario= extras.getString("SALDO_USUARIO");
         numerTarjeta= extras.getString("NUM_TARJETA");
         fechaCreacion = extras.getString("FEHA_CREACION_USUARIO");
         cvvUsuario = extras.getString("CVV_USUARIO");
         pinUsuario = extras.getString("PIN_USUARIO");

        nombreClienteActualizar.setText(nombreCliente);
        cedulaClienteActualizar.setText(cedulaUsuario);


        botonCancelarActualizacionCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActualizarCliente.this, AdminInformacion.class);
                startActivity(intent);
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActualizarCliente.this, AdminInformacion.class);
                startActivity(intent);
            }
        });


        botonConfirmardatoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonConfirmardatoCliente.setVisibility(View.GONE);
                botones.setVisibility(View.VISIBLE);
                pinClienteActualizar.setVisibility(View.VISIBLE);
                confirmarpPinClienteActualizar.setVisibility(View.VISIBLE);

            }
        });

        botonConfirmarActualizacionCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( !confirmarpPinClienteActualizar.getText().toString().equals(vacio) && !pinClienteActualizar.getText().toString().equals(vacio) ) {

                    if(confirmarpPinClienteActualizar.getText().toString().equals(pinClienteActualizar.getText().toString()) ){

                        Intent intent = new Intent(ActualizarCliente.this, VerInforActualizarCliente.class);
                        intent.putExtra("ID_USUARIO1", idUsuario);
                        intent.putExtra("NOMBRE_USUARIO1", nombreCliente);
                        intent.putExtra("CEDULA_USUARIO1", cedulaUsuario);
                        intent.putExtra("SALDO_USUARIO1", saldoUsuario);
                        intent.putExtra("NUM_TARJETA1", numerTarjeta);
                        intent.putExtra("FEHA_CREACION_USUARIO1", fechaCreacion);
                        intent.putExtra("CVV_USUARIO1", cvvUsuario);
                        intent.putExtra("PIN_USUARIO1",confirmarpPinClienteActualizar.getText().toString() );
                        startActivity(intent);

                    } else {

                        Toast.makeText(ActualizarCliente.this, "contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(ActualizarCliente.this, "llene todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}