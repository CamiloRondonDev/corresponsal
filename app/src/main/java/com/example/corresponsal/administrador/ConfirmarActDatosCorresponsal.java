package com.example.corresponsal.administrador;

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

public class ConfirmarActDatosCorresponsal extends AppCompatActivity {
    TextView confirmarNombreCorresponsalActu;
    TextView confirmarNitCorresponsalActu;
    TextView confirmarSaldoCorresponsalActu;
    TextView confirmarCorreoCorresponsalActu;
    Button botonConfirmarDatosCorresponsalActu;
    Button botonCancelarConfirmacionCorresponsalActu;
    String nombreActu,nitActu,correoActu,saldoActu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_act_datos_corresponsal);

        confirmarNombreCorresponsalActu = findViewById(R.id.confirmarNombreCorresponsalActu);
        confirmarNitCorresponsalActu = findViewById(R.id.confirmarNitCorresponsalActu);
        confirmarSaldoCorresponsalActu = findViewById(R.id.confirmarSaldoCorresponsalActu);
        confirmarCorreoCorresponsalActu = findViewById(R.id.confirmarCorreoCorresponsalActu);
        botonConfirmarDatosCorresponsalActu = findViewById(R.id.botonConfirmarDatosCorresponsalActu);
        botonCancelarConfirmacionCorresponsalActu = findViewById(R.id.botonCancelarConfirmacionCorresponsalActu);


        botonCancelarConfirmacionCorresponsalActu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( ConfirmarActDatosCorresponsal.this, AdminInformacion.class);
                startActivity(intent);
            }
        });


        Bundle extras = getIntent().getExtras();
        nombreActu = extras.getString("NOMBRE_CORRES");
        nitActu = extras.getString("NIT");
        correoActu = extras.getString("CORREO_CORESP");
        saldoActu = extras.getString("SALDO_CORRES");

        confirmarNombreCorresponsalActu.setText(nombreActu);
        confirmarNitCorresponsalActu.setText(nitActu);
        confirmarCorreoCorresponsalActu.setText(correoActu);
        confirmarSaldoCorresponsalActu.setText(saldoActu);

        botonConfirmarDatosCorresponsalActu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aletDialog05();

            }
        });

    }

    private void aletDialog05 () {
        new AlertDialog.Builder(this)
                .setTitle("Corresponsal Actualizado")
                .setMessage("¿desea ir a inicio?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(ConfirmarActDatosCorresponsal.this,AdminInformacion.class);
                        Toast.makeText(ConfirmarActDatosCorresponsal.this, "datos guardados", Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                    }
                }).show();
    }

    @Override
    public void onBackPressed() {

    }
}