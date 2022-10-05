package com.example.corresponsal.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corresponsal.R;
import com.example.corresponsal.db.DbCorresponsal;
import com.example.corresponsal.entidades.Corresponsales;

public class ConfirmarActualizacionDatosCorresponsal extends AppCompatActivity {
     TextView confirmarNombreCorres;
    TextView confirmarNitCorres;
    TextView confirmarsaldoCorres;
    TextView confirmarCorreoCorres;
     Button botonConfirmarActualDatosCorreponsal;
     Button botonCancelarActuDatosaCorres;
     SharedPreference01 sharedPreference01;
     Corresponsales corresponsales;
     DbCorresponsal dbCorresponsal;
     int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_actualizacion_datos_corresponsal);
        sharedPreference01 = new SharedPreference01(this);
        dbCorresponsal = new DbCorresponsal(this);
        confirmarNombreCorres = findViewById(R.id.confirmarNombreCorres);
        confirmarNitCorres = findViewById(R.id.confirmarNitCorres);
        confirmarsaldoCorres = findViewById(R.id.confirmarsaldoCorres);
        confirmarCorreoCorres = findViewById(R.id.confirmarCorreoCorres);
        botonConfirmarActualDatosCorreponsal = findViewById(R.id.botonConfirmarActualDatosCorreponsal);
        botonCancelarActuDatosaCorres = findViewById(R.id.botonCancelarActuDatosaCorres);


        corresponsales = dbCorresponsal.mostrarCorresponsal();
        id = corresponsales.getId_corresponsal();
        corresponsales = dbCorresponsal.traerCorresponsalesPorID(id);

        confirmarNombreCorres.setText(corresponsales.getNombreCorresponsal());
        confirmarNitCorres.setText(corresponsales.getNitCorresponsal());
        confirmarsaldoCorres.setText(corresponsales.getSaldoCorresponsal());
        confirmarCorreoCorres.setText(corresponsales.getCorreoCorresponsal());


        botonConfirmarActualDatosCorreponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aletDialog();

                Bundle extras = getIntent().getExtras(); ///para recibir la informacion del intent que nos trae la informacion del id
                 String nueva ;
                 nueva = extras.getString("ID");

                corresponsales.setContraseñaCorresponsal(nueva);
                dbCorresponsal.actualizardatoCorresponsal(corresponsales);

            }
        });

    }
    private void aletDialog () {
        new AlertDialog.Builder(this)
                .setTitle("Datos Actualizados")
                .setMessage("¿desea ir a inicio?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(ConfirmarActualizacionDatosCorresponsal.this, CorresponInformacion.class);
                        Toast.makeText(ConfirmarActualizacionDatosCorresponsal.this, "se ha actualizado su contraseña", Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                    }
                }).show();
    }
}