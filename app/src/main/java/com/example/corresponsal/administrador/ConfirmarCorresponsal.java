package com.example.corresponsal.administrador;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corresponsal.R;
import com.example.corresponsal.db.DbCorresponsal;
import com.example.corresponsal.db.DbHistorial;
import com.example.corresponsal.entidades.Corresponsales;
import com.example.corresponsal.entidades.HistorialTransacciones;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConfirmarCorresponsal extends AppCompatActivity {
    TextView confirmarNombreCorresponsal;
    TextView confirmarNitCorresponsal;
    TextView confirmarSaldoCorresponsal;
    TextView confirmarCorreoCorresponsal;
    Button botonconfirmarDatosCorresponsal;
    Button botoncancelarConfirmacionDatosCorresponsal;
    String nombre, correo, nit, saldo, pin;
    Corresponsales corresponsales;
    DbCorresponsal dbCorresponsal;
    int id = 0;
    HistorialTransacciones historialTransacciones;
    RegistoCorresponsal registoCorresponsal;
    DbHistorial dbHistorial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_corresponsal);
        corresponsales = new Corresponsales();
        dbCorresponsal = new DbCorresponsal(this);
        historialTransacciones = new HistorialTransacciones();
        dbHistorial = new DbHistorial(this);
        confirmarNombreCorresponsal = findViewById(R.id.confirmarNombreCorresponsal);
        confirmarNitCorresponsal = findViewById(R.id.confirmarNitCorresponsal);
        confirmarSaldoCorresponsal = findViewById(R.id.confirmarSaldoCorresponsal);
        confirmarCorreoCorresponsal = findViewById(R.id.confirmarCorreoCorresponsal);
        botonconfirmarDatosCorresponsal = findViewById(R.id.botonConfirmarDatosCorresponsal);
        botoncancelarConfirmacionDatosCorresponsal = findViewById(R.id.botonCancelarConfirmacionCorresponsal);
        registoCorresponsal = new RegistoCorresponsal();

        botoncancelarConfirmacionDatosCorresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( ConfirmarCorresponsal.this, AdminInformacion.class);
                startActivity(intent);
            }
        });

        Bundle extras = getIntent().getExtras();
        nombre = (extras.getString("NOMBRE"));
        nit  = (extras.getString("NIT"));
        correo = (extras.getString("CORREO"));
        saldo  = (extras.getString("SALDO"));
        pin  = (extras.getString("PIN"));


        confirmarNombreCorresponsal.setText(nombre);
        confirmarCorreoCorresponsal.setText(correo);
        confirmarSaldoCorresponsal.setText(saldo);
        confirmarNitCorresponsal.setText(nit);


            botonconfirmarDatosCorresponsal.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View view) {

                    corresponsales.setNombreCorresponsal(confirmarNombreCorresponsal.getText().toString().trim());
                    corresponsales.setCorreoCorresponsal(confirmarCorreoCorresponsal.getText().toString().trim());
                    corresponsales.setNitCorresponsal(confirmarNitCorresponsal.getText().toString().trim());
                    corresponsales.setContraseñaCorresponsal(pin);
                    corresponsales.setSaldoCorresponsal("1000000");
                    dbCorresponsal.insertarCorresponsales(corresponsales);


                    //para la fecha con la hora actual del telefono
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    historialTransacciones.setHoraTransaccion(dtf.format(LocalDateTime.now()));

                    historialTransacciones.setTipoTransaccion("crear corresponsal");
                    historialTransacciones.setMontoTransaccion("no aplica");
                    historialTransacciones.setTarjeta("no aplica");
                    historialTransacciones.setCedulaHistorial(confirmarNitCorresponsal.getText().toString().trim());
                    dbHistorial.insertarHistorial(historialTransacciones);
                    aletDialog08();

                }
            });

    }

    private void aletDialog08 () {
        new AlertDialog.Builder(this)
                .setTitle("Corresponsal Registrado")
                .setMessage("¿desea ir a inicio?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent insent = new Intent(ConfirmarCorresponsal.this, AdminInformacion.class);
                        Toast.makeText(ConfirmarCorresponsal.this, "Corresponsal Registrado" , Toast.LENGTH_SHORT).show();
                        startActivity(insent);

                    }
                }).show();

    }

    @Override
    public void onBackPressed() {

    }
}