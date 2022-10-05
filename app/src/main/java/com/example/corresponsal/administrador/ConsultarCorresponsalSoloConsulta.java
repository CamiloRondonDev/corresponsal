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

public class ConsultarCorresponsalSoloConsulta extends AppCompatActivity {

    EditText nitCorresponsalColsulta;
    Button botonConfirmarConsultaCorresonsal;
    Button botonCancelarConsultaCorresonsal;
    ImageView atrasConsultaCorresponsal;
    DbCorresponsal dbCorresponsal;
    Corresponsales corresponsales;
    String nit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar_corresponsal);

        nitCorresponsalColsulta = findViewById(R.id.nitConsultarCorresponsal);
        botonConfirmarConsultaCorresonsal = findViewById(R.id.botonConfirmarConsultaCorresponsal);
        botonCancelarConsultaCorresonsal = findViewById(R.id.botonCancelarConsultaCorresponsal);
        atrasConsultaCorresponsal = findViewById(R.id.atrasAdmin02);
        atrasConsultaCorresponsal.setVisibility(View.VISIBLE);
        dbCorresponsal = new DbCorresponsal(this);
        corresponsales = new Corresponsales();


        botonConfirmarConsultaCorresonsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nit = nitCorresponsalColsulta.getText().toString();
                corresponsales = dbCorresponsal.traerCorresponsalesPorNIT(nit);
                String nitCorrres = corresponsales.getNitCorresponsal();

                if(nit.equals(nitCorrres)) {
                    Intent intent = new Intent(ConsultarCorresponsalSoloConsulta.this, VerInformacionClienteSoloVerla.class);

                    intent.putExtra("ID_CORRES", corresponsales.getId_corresponsal());
                    intent.putExtra("NIT", corresponsales.getNitCorresponsal() );
                    intent.putExtra("NOMBRE_CORRES", corresponsales.getNombreCorresponsal() );
                    intent.putExtra("CORREO_CORESP", corresponsales.getCorreoCorresponsal() );
                    intent.putExtra("SALDO_CORRES", corresponsales.getSaldoCorresponsal() );
                    intent.putExtra("CONTRASEÑA_CORRES", corresponsales.getContraseñaCorresponsal());

                    startActivity(intent);
                }else  {
                    Toast.makeText(ConsultarCorresponsalSoloConsulta.this, "NIT no registrado", Toast.LENGTH_SHORT).show();
                }

            }
        });

        atrasConsultaCorresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarCorresponsalSoloConsulta.this, AdminInformacion.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}