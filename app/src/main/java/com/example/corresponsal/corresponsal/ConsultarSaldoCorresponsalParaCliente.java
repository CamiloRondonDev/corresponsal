package com.example.corresponsal.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corresponsal.R;
import com.example.corresponsal.db.DbClientes;
import com.example.corresponsal.db.DbCorresponsal;
import com.example.corresponsal.entidades.Clientes;
import com.example.corresponsal.entidades.Corresponsales;

public class ConsultarSaldoCorresponsalParaCliente extends AppCompatActivity {

    TextView  nombreCorresponsalBarner;
    EditText numeroCedulaConsultar;
    ImageView atras;
    Clientes clientes;
    DbClientes dbClientes;
    Button botonConfirmarconsultasaldo;
    Button botonCancelarDeposito;
    SharedPreference01 sharedPreference01;
    Corresponsales corresponsales;
    DbCorresponsal dbCorresponsal;
    String vacio ="";


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar_saldo_corresponsal);
        sharedPreference01 = new SharedPreference01(this);
        corresponsales = new Corresponsales();
        dbCorresponsal  = new DbCorresponsal(this);
        nombreCorresponsalBarner = findViewById(R.id.nombreCorresponsalBarner);
        nombreCorresponsalBarner.setText("Consultar Saldo");
        numeroCedulaConsultar = findViewById(R.id.numeroCCconsular);
        atras = findViewById(R.id.atrasCorresponsal_sin_titulo);
        atras.setVisibility(View.VISIBLE);
        botonConfirmarconsultasaldo = findViewById(R.id.botonConfirmarconsultasaldo);
        botonCancelarDeposito = findViewById(R.id.botonCancelarDeposito);
        clientes = new Clientes();
        dbClientes = new DbClientes(this);


        botonCancelarDeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarSaldoCorresponsalParaCliente.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });
        botonConfirmarconsultasaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!numeroCedulaConsultar.getText().toString().equals(vacio) ) {
                    aletDialog03();
                } else {
                    Toast.makeText(ConsultarSaldoCorresponsalParaCliente.this, "campo vacio", Toast.LENGTH_SHORT).show();
                }





            }
        });


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarSaldoCorresponsalParaCliente.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });

    }

    private void aletDialog03 () {
        new AlertDialog.Builder(this)
                //.setTitle("Cliente Registrado")

                .setMessage("consultar el saldo tiene un costo de 1.000, que se descontaran directamente de su cuenta , desea continuar")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        String cc = numeroCedulaConsultar.getText().toString().trim();
                        clientes = dbClientes.traerClientesPorCedula(cc);

                        if ( cc.equals(clientes.getNumeroCedula())){
                            //siempre hacer esto para que se cargen los set y los get y poder trabajar con ellos

                            String nombreSaldo = clientes.getNombreCliente();
                            String  saldo = String.valueOf(clientes.getSaldoInicial());

                            Intent intent1 = new Intent(ConsultarSaldoCorresponsalParaCliente.this, ResultadoConsultaSaldoCliente.class);
                            intent1.putExtra("CEDULA_CONSULTA",cc);
                            intent1.putExtra("NOMBRE_SALDO",nombreSaldo);
                            intent1.putExtra("SALDO_SALDO",saldo);

                            startActivity(intent1);
                        }else {
                            Toast.makeText(ConsultarSaldoCorresponsalParaCliente.this, "cedula no registrada", Toast.LENGTH_SHORT).show();
                        }

                    }
                })

                  //para cuando sea negativo el alertDialog
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                     @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       Log.d("mensaje", "se cancelo la accion");
                     }
                 }).show();
    }
    @Override
    public void onBackPressed() {

    }
}