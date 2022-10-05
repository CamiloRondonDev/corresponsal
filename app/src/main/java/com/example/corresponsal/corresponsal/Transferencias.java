package com.example.corresponsal.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Transferencias extends AppCompatActivity {
    EditText numeroCCaTransferir;
    EditText numeroCCaQueTransfiere;
    EditText montoaTransferir;
    Button botonConfirmarTransferencia;
    Button botonCancerTransferencia;
    ImageView atras;
    SharedPreference01 sharedPreference01;
    Corresponsales corresponsales;
    TextView nombreCorresponsalBarner;
    TextView saldoCorresponsalBarner;
    TextView numeroCuentaCorresponsalBarner;
    DbCorresponsal dbCorresponsal;
    Clientes clientes1;
    Clientes clientes2;
    DbClientes dbClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transferencias);
        sharedPreference01 = new SharedPreference01(this);
        corresponsales = new Corresponsales();
        dbCorresponsal =  new DbCorresponsal(this);
        clientes1 = new Clientes();
        clientes2 = new Clientes();
        dbClientes = new DbClientes(this);
        numeroCCaTransferir = findViewById(R.id.numeroCCaTransferir);//la que va a transferir
        numeroCCaQueTransfiere = findViewById(R.id.numeroCCaQueTransfiere);//la que va  a recibir la transferencia
        montoaTransferir = findViewById(R.id.montoaTransferir);
        botonConfirmarTransferencia = findViewById(R.id.botonConfirmarTransferencia);
        botonCancerTransferencia = findViewById(R.id.botonCancerTransferencia);
        nombreCorresponsalBarner = findViewById(R.id.nombreCorresponsalBarner);
        saldoCorresponsalBarner = findViewById(R.id.saldoCorresponsalBarner);
        numeroCuentaCorresponsalBarner = findViewById(R.id.numCuentaCorresponsalBarner);
        atras = findViewById(R.id.atrasCorresponsal);
        atras.setVisibility(View.VISIBLE);

        botonCancerTransferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Transferencias.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });

        //para mostrar la informacion en el barner NO BORRAR
        corresponsales = dbCorresponsal.mostrarCorresponsal();
        saldoCorresponsalBarner.setText(corresponsales.getSaldoCorresponsal());
        nombreCorresponsalBarner.setText(corresponsales.getNombreCorresponsal());
        numeroCuentaCorresponsalBarner.setText(corresponsales.getNitCorresponsal());


        botonConfirmarTransferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clientes1 = dbClientes.traerClientesPorCedula(numeroCCaTransferir.getText().toString());// quein va a transfeirar

                clientes2 = dbClientes.traerClientesPorCedula(numeroCCaQueTransfiere.getText().toString());// a quien le voy a transferir


                  if (numeroCCaQueTransfiere.getText().toString().equals(clientes2.getNumeroCedula()) && numeroCCaTransferir.getText().toString().equals(clientes1.getNumeroCedula()) ){


                      Intent intent = new Intent(Transferencias.this , ConfirmarPinTransaccion.class);
                      //datos cedula 1

                      intent.putExtra( "CC_QUIEN_TRANSFIERE" , clientes1.getNumeroCedula()); //QUIEN TRANSFIERE
                      intent.putExtra( "SALDO_QUIEN_TRASNFIERE" , clientes1.getSaldoInicial());//QUIEN TRANSFIERE
                      intent.putExtra( "PIN_QUIEN_TRASNFIERE" , clientes1.getPinUsuario());//QUIEN TRANSFIERE
                      intent.putExtra( "ID_QUIEN_TRASNFIERE" , clientes1.getIdCliente());//QUIEN TRANSFIERE

                      //valor transferencia
                      intent.putExtra( "MONTO_A_TRANFERIR" , montoaTransferir.getText().toString());

                      //datos de cedula 25
                      intent.putExtra( "CEDULA_A_QUIEN_TRASNFIERE" , clientes2.getNumeroCedula());//A QUIEN LE TRANSFIEREN
                      intent.putExtra( "SALDO_A_QUIEN_TRASNFIERE" , clientes2.getSaldoInicial());//A QUIEN LE TRANSFIEREN
                      intent.putExtra( "ID_A_QUIEN_TRASNFIERE" , clientes2.getIdCliente());//A QUIEN LE TRANSFIEREN

                      startActivity(intent);


                      Toast.makeText(Transferencias.this, "correcto", Toast.LENGTH_SHORT).show();

                  } else {
                      Toast.makeText(Transferencias.this, "Favor Verificar Cedulas", Toast.LENGTH_SHORT).show();

                }


            }
        });




        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Transferencias.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}