package com.example.corresponsal.administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corresponsal.R;
import com.example.corresponsal.entidades.Clientes;

public class IngresePinUsuarioAdmin extends AppCompatActivity {

    EditText ingresePin;
    Button botonConfirmarPinUsuarioAdmin;
    Button botonCancelarPinUsuarioAdmin;
    String nombre01;
    String saldo01;
    String cedula01;
    String vacio = "";
    Clientes clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingrese_pin_usuario_admin);
        clientes = new Clientes();

        ingresePin = findViewById(R.id.pinUsuarioAdmin);
        botonConfirmarPinUsuarioAdmin = findViewById(R.id.botonConfirmarPinUsuarioAdmin);
        botonCancelarPinUsuarioAdmin = findViewById(R.id.botonCancelarPinUsuarioAdmin);

        botonCancelarPinUsuarioAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IngresePinUsuarioAdmin.this, AdminInformacion.class);
                startActivity(intent);
            }
        });

        Bundle extras = getIntent().getExtras();
        nombre01 = extras.getString("NOMBRE_USUARIO_AD");
        cedula01 = (extras.getString("CEDULA_CLIENTE_AD"));
        saldo01 = (extras.getString("SALDO_CLIENTE_AD"));


        botonConfirmarPinUsuarioAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!ingresePin.getText().toString().equals(vacio)) {

                    clientes.setPinUsuario(ingresePin.getText().toString().trim());

                    Intent intent = new Intent(IngresePinUsuarioAdmin.this, ConfirmarDatosUsuarioAdmin.class);
                    intent.putExtra("NOMBRE_USUARIO_AD", nombre01);
                    intent.putExtra("CEDULA_CLIENTE_AD", cedula01);
                    intent.putExtra("SALDO_CLIENTE_AD", saldo01);
                    intent.putExtra("PIN_USUARIO_AD", ingresePin.getText().toString().trim());
                    startActivity(intent);
                }else {
                    Toast.makeText(IngresePinUsuarioAdmin.this, "debe ingresar un pin", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}