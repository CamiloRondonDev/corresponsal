package com.example.corresponsal.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corresponsal.R;
import com.example.corresponsal.db.DbClientes;
import com.example.corresponsal.db.DbCorresponsal;
import com.example.corresponsal.entidades.Clientes;
import com.example.corresponsal.entidades.Corresponsales;

public class PagoConTarjeta extends AppCompatActivity {
    EditText numeroDeTarjeta;
    EditText mm;
    EditText dd;
    EditText nombreDelCliente;
    EditText valoraPagar;
    Spinner spinner;
    ImageView felchaAtras;
    SharedPreference01 sharedPreference01;
    Corresponsales corresponsales;
    TextView nombreCorresponsalBarner;
    TextView saldoCorresponsalBarner;
    EditText cvv;
    TextView numeroCuentaCorresponsalBarner;
    DbCorresponsal dbCorresponsal;
    Clientes clientes;
    DbClientes dbClientes;
    Button botonConfirmarCliente;
    Button botonCancelarCliente;
    String cuotas;
    String vacio = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pago_con_tarjeta);
        sharedPreference01 = new SharedPreference01(this);
        corresponsales = new Corresponsales();
        dbCorresponsal =  new DbCorresponsal(this);
        clientes = new Clientes();
        dbClientes = new DbClientes(this);
        numeroDeTarjeta = findViewById(R.id.numeroTarjeta);
        mm = findViewById(R.id.mm);
        cvv = findViewById(R.id.cvv);
        dd = findViewById(R.id.dd);
        nombreDelCliente = findViewById(R.id.nombredeCliente);
        valoraPagar = findViewById(R.id.valoraPagar);
        botonCancelarCliente = findViewById(R.id.botonCancelarCliente00);
        botonConfirmarCliente = findViewById(R.id.botonConfirmarCliente00);

        //para el spinner
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cuotas, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                cuotas = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        felchaAtras = findViewById(R.id.atrasCorresponsal);
        felchaAtras.setVisibility(View.VISIBLE);
        nombreCorresponsalBarner = findViewById(R.id.nombreCorresponsalBarner);
        saldoCorresponsalBarner = findViewById(R.id.saldoCorresponsalBarner);
        numeroCuentaCorresponsalBarner = findViewById(R.id.numCuentaCorresponsalBarner);

        corresponsales = dbCorresponsal.mostrarCorresponsal();

        saldoCorresponsalBarner.setText(corresponsales.getSaldoCorresponsal());
        nombreCorresponsalBarner.setText(corresponsales.getNombreCorresponsal());
        numeroCuentaCorresponsalBarner.setText(corresponsales.getNitCorresponsal());

        botonCancelarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PagoConTarjeta.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });

        botonConfirmarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clientes =  dbClientes.traerClientesPorNtarjeta(numeroDeTarjeta.getText().toString());


                if (clientes.getNombreCliente() == null & clientes.getCvv() == null && clientes.getNombreCliente() == null ){

                    Toast.makeText(PagoConTarjeta.this, "Numero de tarjeta no registrada", Toast.LENGTH_SHORT).show();

                }else{

                    String nombreP = nombreDelCliente.getText().toString().trim();
                    String nombre = clientes.getNombreCliente();
                    String cvvp = cvv.getText().toString().trim();
                    String cvvC = clientes.getCvv();

                    if ( nombre.equals(nombreP ) && cvvC.equals(cvvp) && !valoraPagar.getText().toString().equals(vacio))  {

                        Intent intent = new Intent( PagoConTarjeta.this, ConfirmacionPagoTarjeta.class );
                        intent.putExtra("NUMERO_TARJETA", clientes.getNumerotarjeta());
                        intent.putExtra("VALOR_CUOTA", valoraPagar.getText().toString());
                        intent.putExtra("NUMER_CUOTAS", cuotas);
                        startActivity(intent);
                    }else {
                        Toast.makeText(PagoConTarjeta.this, "confirmar datos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        felchaAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PagoConTarjeta.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
    }
}