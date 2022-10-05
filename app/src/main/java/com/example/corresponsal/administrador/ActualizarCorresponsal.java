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
import com.example.corresponsal.db.DbCorresponsal;
import com.example.corresponsal.entidades.Corresponsales;

public class ActualizarCorresponsal extends AppCompatActivity {

    EditText nombreCorresponsalActualizar;
    EditText nitCorresponsalActualizar;
    EditText correoCorresponsalActualizar;
    EditText contraseñaCorresponsalActualizar;
    EditText confirmarContraseña;
    Button botonConfirmarActualiarCorresponsal;
    Button botonCancelarActualiarCorresponsal;
    Button botonConfirmar;
    ImageView atrasActualizarCorresponsal;
    Corresponsales corresponsales;
    String nitActu;
    String nombreActu;
    String correoActu;
    String saldo;
    String vacio = "";
    DbCorresponsal dbCorresponsal;
    LinearLayout botones;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar_corresponsal);

        nombreCorresponsalActualizar = findViewById(R.id.nombreCorresponsalActualizar);
        nitCorresponsalActualizar = findViewById(R.id.nitCorresponsalActualizar);
        correoCorresponsalActualizar = findViewById(R.id.correoCorresponsalActalizar);
        contraseñaCorresponsalActualizar = findViewById(R.id.contraseñanueva);
        confirmarContraseña = findViewById(R.id.confirmarContraseña);
        botonConfirmarActualiarCorresponsal = findViewById(R.id.botonConfirmarCorresponsalActualizar);
        botonCancelarActualiarCorresponsal = findViewById(R.id.botonCancelarCorresponsalActualizar);
        atrasActualizarCorresponsal = findViewById(R.id.atrasAdmin02);
        atrasActualizarCorresponsal.setVisibility(View.VISIBLE);
        botonConfirmar = findViewById(R.id.confirmarActualizacion);
        corresponsales = new Corresponsales();
        dbCorresponsal = new DbCorresponsal(this);
        botones = findViewById( R.id.botones);

        //recibir informacion del intent
        Bundle extras = getIntent().getExtras();
        nombreActu = extras.getString("NOMBRE_CORRES");
        nitActu = extras.getString("NIT");
        correoActu = extras.getString("CORREO_CORESP");
        saldo = extras.getString("SALDO_CORRES");
        id = extras.getInt("ID_CORRES");

        nombreCorresponsalActualizar.setText(nombreActu);
        nitCorresponsalActualizar.setText(nitActu);
        correoCorresponsalActualizar.setText(correoActu);


        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonConfirmar.setVisibility(View.GONE);

                botones.setVisibility(View.VISIBLE);
                contraseñaCorresponsalActualizar.setVisibility(View.VISIBLE);
                confirmarContraseña.setVisibility(View.VISIBLE);

            }
        });

        botonCancelarActualiarCorresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActualizarCorresponsal.this, AdminInformacion.class);
                startActivity(intent);
            }
        });


        atrasActualizarCorresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActualizarCorresponsal.this, AdminInformacion.class);
                startActivity(intent);
            }


        });


        botonConfirmarActualiarCorresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!confirmarContraseña.getText().toString().equals(vacio) && !contraseñaCorresponsalActualizar.getText().toString().equals(vacio) ){

                    if(confirmarContraseña.getText().toString().equals(contraseñaCorresponsalActualizar.getText().toString())){

                        corresponsales.setContraseñaCorresponsal(confirmarContraseña.getText().toString());
                        corresponsales.setNitCorresponsal(nitActu);
                        corresponsales.setId_corresponsal(id);
                        dbCorresponsal.actualizardatoCorresponsal(corresponsales);

                        Intent intent = new Intent(ActualizarCorresponsal.this, ConfirmarActDatosCorresponsal.class);


                        intent.putExtra("ID_CORRES", id);
                        intent.putExtra("NIT", nitActu );
                        intent.putExtra("NOMBRE_CORRES", nombreActu );
                        intent.putExtra("CORREO_CORESP", correoActu);
                        intent.putExtra("SALDO_CORRES", saldo);
                        intent.putExtra("CONTRASEÑA_CORRES", confirmarContraseña.getText().toString() );

                        startActivity(intent);

                    }else {
                        Toast.makeText(ActualizarCorresponsal.this, "contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(ActualizarCorresponsal.this, "debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}