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

public class Deposito extends AppCompatActivity {
    EditText numeroCCaDepositar;
    EditText numeroCCaQuienDeposita;
    EditText montoaDepositar;
    Button botonConfirmarDeposito;
    Button botonCancelarDeposito;
    ImageView felchaAtras;
    SharedPreference01 sharedPreference01;
    Corresponsales corresponsales;
    TextView nombreCorresponsalBarner;
    TextView saldoCorresponsalBarner;
    TextView numeroCuentaCorresponsalBarner;
    DbCorresponsal dbCorresponsal;
    String vacio = "";
    Clientes clientes;
    DbClientes dbClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deposito);
        sharedPreference01 = new SharedPreference01(this);
        corresponsales = new Corresponsales();
        dbCorresponsal =  new DbCorresponsal(this);
        clientes = new Clientes();
        dbClientes = new DbClientes(this);
        numeroCCaDepositar = findViewById(R.id.numeroCCaDepositar);
        numeroCCaQuienDeposita = findViewById(R.id.numeroCCaQuienDeposita);
        montoaDepositar = findViewById(R.id.montoaDepositar);
        botonConfirmarDeposito = findViewById(R.id.botonConfirmarDeposito);
        botonCancelarDeposito = findViewById(R.id.botonCancelarDeposito);
        nombreCorresponsalBarner = findViewById(R.id.nombreCorresponsalBarner);
        saldoCorresponsalBarner = findViewById(R.id.saldoCorresponsalBarner);
        numeroCuentaCorresponsalBarner = findViewById(R.id.numCuentaCorresponsalBarner);

        corresponsales = dbCorresponsal.mostrarCorresponsal();
        saldoCorresponsalBarner.setText(corresponsales.getSaldoCorresponsal());
        nombreCorresponsalBarner.setText(corresponsales.getNombreCorresponsal());
        numeroCuentaCorresponsalBarner.setText(corresponsales.getNitCorresponsal());

        felchaAtras = findViewById(R.id.atrasCorresponsal);
        felchaAtras.setVisibility(View.VISIBLE);
        botonCancelarDeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Deposito.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });

        botonConfirmarDeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!numeroCCaDepositar.getText().toString().equals(vacio) && !numeroCCaQuienDeposita.getText().toString().equals(vacio) &&  !montoaDepositar.getText().toString().equals(vacio) ){

                 clientes = dbClientes.traerClientesPorCedula(numeroCCaQuienDeposita.getText().toString());



                    if (clientes.getNumeroCedula() == null){

                        Toast.makeText(Deposito.this, "cedula no registrada", Toast.LENGTH_SHORT).show();

                    }else {
                        Intent intent = new Intent(Deposito.this, ConfirmarDatosDepisito.class);
                        intent.putExtra( "CC_QUE_DEPOSITA" , numeroCCaDepositar.getText().toString() );
                        intent.putExtra( "CC_A_QUIEN_DEPOSITAN" , numeroCCaQuienDeposita.getText().toString() );
                        intent.putExtra( "VALOR_A_DEPOSITAR" , montoaDepositar.getText().toString() );
                        startActivity(intent);
                    }

                }else {
                    Toast.makeText(Deposito.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });


        felchaAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Deposito.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}