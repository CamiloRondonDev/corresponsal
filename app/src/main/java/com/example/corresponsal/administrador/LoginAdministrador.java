package com.example.corresponsal.administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corresponsal.R;
import com.example.corresponsal.corresponsal.CorresponInformacion;
import com.example.corresponsal.corresponsal.SharedPreference01;
import com.example.corresponsal.db.DbCorresponsal;
import com.example.corresponsal.entidades.Corresponsales;

public class LoginAdministrador extends AppCompatActivity {
    EditText nombreUsuario;
    EditText contraseña;
    Button botonIniciarSesionAdmin;
    String admin = "Admin@wposs.com" ;
    String contraseñaAdmin = "Admin123*";
    DbCorresponsal dbCorresponsal;
    Corresponsales corresponsales;
    SharedPreference01 sharedPreference01;
    int id =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_administrador);
        botonIniciarSesionAdmin  = findViewById(R.id.botonIniciarSesion);
        nombreUsuario = findViewById(R.id.loginCorresponsal);
        contraseña = findViewById(R.id.contraseñaCorresponsal);
        dbCorresponsal = new DbCorresponsal(LoginAdministrador.this);
        corresponsales = new Corresponsales();
        sharedPreference01 = new SharedPreference01(this);


        botonIniciarSesionAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( admin.equals(nombreUsuario.getText().toString()) && contraseñaAdmin.equals(contraseña.getText().toString())) {

                    Toast.makeText(LoginAdministrador.this, "Bienvenido Admin", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginAdministrador.this, AdminInformacion.class);
                    startActivity(intent);

                } else{
                    //tener esta informacion aca para poder hacaer login
                    corresponsales.setCorreoCorresponsal(nombreUsuario.getText().toString());
                    corresponsales.setContraseñaCorresponsal(contraseña.getText().toString());
                    id = dbCorresponsal.login(corresponsales);

                    if (id == 1){

                        //aca guardo la informacion que viene por el layaut en el texto lo guardo en el sharePreference
                        sharedPreference01.setSharedPreference(corresponsales.getCorreoCorresponsal());

                        Intent intent = new Intent(LoginAdministrador.this, CorresponInformacion.class);
                        startActivity(intent);

                        Toast.makeText(LoginAdministrador.this, "Bienvenido Usuario", Toast.LENGTH_SHORT).show();

                    }else{

                        Toast.makeText(LoginAdministrador.this, "incorrecto", Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}