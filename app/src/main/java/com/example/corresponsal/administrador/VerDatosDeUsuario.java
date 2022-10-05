package com.example.corresponsal.administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.corresponsal.R;

public class VerDatosDeUsuario extends AppCompatActivity {
    TextView numeroTarjetaUsu;
    TextView cvvUsu;
    TextView fechaUsu;
    TextView nombreClienteUsu;
    TextView saldoUsu;
    TextView american;
    TextView visa;
    TextView mastercard;
    TextView unionplay;
    Button botonConfirmarConsultaUsu;
    String idUser;
    String nombreUser;
    String cedulaUser;
    String saldoUser;
    String numtarjetaUser;
    String fechaCreacionUser;
    String cvvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_datos_de_usuario);
        numeroTarjetaUsu = findViewById(R.id.numeroTarjetaUsu);
        cvvUsu = findViewById(R.id.cvvUsu);
        fechaUsu = findViewById(R.id.fechaUsu);
        nombreClienteUsu = findViewById(R.id.nombreClienteUsu);
        saldoUsu = findViewById(R.id.saldoUsu);
        botonConfirmarConsultaUsu = findViewById(R.id.botonConfirmarConsultaUsu);
        american = findViewById(R.id.american);
        visa = findViewById(R.id.visa);
        mastercard = findViewById(R.id.mastercard);
        unionplay = findViewById(R.id.unionplay);


        Bundle extras = getIntent().getExtras();
        idUser = extras.getString("ID_USUARIO");
        nombreUser = (extras.getString("NOMBRE_USUARIO"));
        cedulaUser = (extras.getString("CEDULA_USUARIO"));
        saldoUser = (extras.getString("SALDO_USUARIO"));
        numtarjetaUser = (extras.getString("NUM_TARJETA"));
        fechaCreacionUser = (extras.getString("FEHA_CREACION_USUARIO"));
        cvvUser = (extras.getString("CVV_USUARIO"));

        //capturar el primer caractes del numero de tarjeta
        String numeroInicial =  String.valueOf(numtarjetaUser.charAt(0));

        cvvUsu.setText(cvvUser);
        numeroTarjetaUsu.setText(numtarjetaUser);
        fechaUsu.setText(fechaCreacionUser);
        nombreClienteUsu.setText(nombreUser);
        saldoUsu.setText(saldoUser);

       // con el primer caracter de la tarjeta mostramos el tipo de tarjeta
        switch (numeroInicial) {
            case "3":
                american = findViewById(R.id.american);
                american.setVisibility(View.VISIBLE);
                break;
            case "4":
                visa = findViewById(R.id.visa);
                visa.setVisibility(View.VISIBLE);
                break;
            case "5":
                mastercard = findViewById(R.id.mastercard);
                mastercard.setVisibility(View.VISIBLE);
                break;
            case "6":
                unionplay = findViewById(R.id.unionplay);
                unionplay.setVisibility(View.VISIBLE);
                break;
            default:
                System.out.println("tipo de tarjeta desconocido");

        }


        botonConfirmarConsultaUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(VerDatosDeUsuario.this, AdminInformacion.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}