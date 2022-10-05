package com.example.corresponsal.administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corresponsal.R;
import com.example.corresponsal.db.DbCorresponsal;
import com.example.corresponsal.entidades.Corresponsales;

public class ConsultaCorresponsalParaActualizar extends AppCompatActivity {

    EditText consultarCorresponsal;
    Button botonConfirmarConsultaCorres;
    Button botonCancelarConsultaCorresponsal;
    Corresponsales corresponsales;
    DbCorresponsal dbCorresponsal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_corresposnal);
        dbCorresponsal = new DbCorresponsal(this);
        corresponsales = new Corresponsales();

        consultarCorresponsal = findViewById(R.id.consultarCorresponsal);
        botonConfirmarConsultaCorres = findViewById(R.id.botonConfirmarConsultaCorres);
        botonCancelarConsultaCorresponsal = findViewById(R.id.botonCancelarConsultaCorresponsal);
        botonCancelarConsultaCorresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( ConsultaCorresponsalParaActualizar.this, AdminInformacion.class);
                startActivity(intent);
            }
        });


        botonConfirmarConsultaCorres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nit = consultarCorresponsal.getText().toString();
                corresponsales = dbCorresponsal.traerCorresponsalesPorNIT(nit);

                if (nit.equals(corresponsales.getNitCorresponsal())){

                    Intent intent = new Intent(ConsultaCorresponsalParaActualizar.this, ActualizarCorresponsal.class);

                    intent.putExtra("ID_CORRES", corresponsales.getId_corresponsal());
                    intent.putExtra("NIT", corresponsales.getNitCorresponsal() );
                    intent.putExtra("NOMBRE_CORRES", corresponsales.getNombreCorresponsal() );
                    intent.putExtra("CORREO_CORESP", corresponsales.getCorreoCorresponsal() );
                    intent.putExtra("SALDO_CORRES", corresponsales.getSaldoCorresponsal() );
                    intent.putExtra("CONTRASEÑA_CORRES", corresponsales.getNitCorresponsal() );

                    startActivity(intent);

                } else {
                    Toast.makeText(ConsultaCorresponsalParaActualizar.this, "NIT no registrado", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    @Override
    public void onBackPressed() {

    }
}