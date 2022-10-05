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
import com.example.corresponsal.db.DbCorresponsal;
import com.example.corresponsal.entidades.Corresponsales;

public class RegistoCorresponsal extends AppCompatActivity {

    EditText nombreCorresponsal;
    EditText nitCorresponsal;
    EditText correoCorresponsal;
    EditText contrasenaCorresponsal;
    Button botonConfirmarCorresponsal;
    Button botonCancelarCorresponsal;
    ImageView atrasCorresponsal;
    Corresponsales corresponsales;
    DbCorresponsal dbCorresponsal;
    String vacio = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registo_corresponsal);

        nombreCorresponsal = findViewById(R.id.nombreCorresponsal);
        nitCorresponsal = findViewById(R.id.nitCorresponsal);
        correoCorresponsal = findViewById(R.id.correoCorresponsal);
        contrasenaCorresponsal = findViewById(R.id.contraseñaDelCorresponsal);
        botonConfirmarCorresponsal = findViewById(R.id.botonConfirmarCorresponsal);
        botonCancelarCorresponsal = findViewById(R.id.botonCancelarCorresponsal);
        atrasCorresponsal = findViewById(R.id.atrasAdmin02);
        atrasCorresponsal.setVisibility(View.VISIBLE);
        dbCorresponsal = new DbCorresponsal(this);
        corresponsales = new Corresponsales();


        botonCancelarCorresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistoCorresponsal.this, AdminInformacion.class);
                startActivity(intent);
            }
        });


        atrasCorresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistoCorresponsal.this, AdminInformacion.class);
                startActivity(intent);
            }
        });



        botonConfirmarCorresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!nombreCorresponsal.getText().toString().equals(vacio) && !nitCorresponsal.getText().toString().equals(vacio) && !correoCorresponsal.getText().toString().equals(vacio) && !contrasenaCorresponsal.getText().toString().equals(vacio)){

                    corresponsales.setNombreCorresponsal(nombreCorresponsal.getText().toString().trim());
                    corresponsales.setCorreoCorresponsal(correoCorresponsal.getText().toString().trim());
                    corresponsales.setNitCorresponsal(nitCorresponsal.getText().toString().trim());
                    corresponsales.setContraseñaCorresponsal(contrasenaCorresponsal.getText().toString().trim());
                    corresponsales.setSaldoCorresponsal("1000000");

                    Intent intent = new Intent(RegistoCorresponsal.this, ConfirmarCorresponsal.class);
                    intent.putExtra( "NOMBRE" , corresponsales.getNombreCorresponsal());
                    intent.putExtra( "CORREO" , corresponsales.getCorreoCorresponsal());
                    intent.putExtra( "NIT" , corresponsales.getNitCorresponsal());
                    intent.putExtra( "SALDO" , corresponsales.getSaldoCorresponsal());
                    intent.putExtra( "PIN" , corresponsales.getContraseñaCorresponsal());

                    startActivity(intent);
                    limpiar();

                }else {
                    Toast.makeText(RegistoCorresponsal.this, "Tiene algun campo vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void limpiar () {

         nombreCorresponsal.setText("");
         nitCorresponsal.setText("");
         correoCorresponsal.setText("");
         contrasenaCorresponsal.setText("");
    }

    @Override
    public void onBackPressed() {

    }
}