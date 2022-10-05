package com.example.corresponsal.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.corresponsal.R;
import com.example.corresponsal.db.DbClientes;
import com.example.corresponsal.db.DbCorresponsal;
import com.example.corresponsal.entidades.Clientes;
import com.example.corresponsal.entidades.Corresponsales;

public class ResultadoConsultaSaldoCliente extends AppCompatActivity {

    TextView nombre_saldo;
    TextView cedula_saldo;
    TextView saldo;
    Button botonConfirmarSaldo;
    Button botonCancelarsaldo;
    String cedula;
    String nombre;
    String saldo_consulta;
    DbClientes dbClientes;
    Clientes clientes;
    Corresponsales corresponsales;
    DbCorresponsal dbCorresponsal;
    SharedPreference01 sharedPreference01;
    String saldoCorres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_consulta_saldo_cliente);
        dbClientes = new DbClientes(this);
        clientes = new Clientes();
        corresponsales = new Corresponsales();
        dbCorresponsal = new DbCorresponsal(this);
        sharedPreference01 = new SharedPreference01(this);
        nombre_saldo = findViewById(R.id.nombre_saldo);
        cedula_saldo = findViewById(R.id.cedula_saldo);
        saldo = findViewById(R.id.saldo);
        botonConfirmarSaldo = findViewById(R.id.botonConfirmarSaldo);
        botonCancelarsaldo = findViewById(R.id.botonCancelarsaldo);


        Bundle extras = getIntent().getExtras();
        cedula = extras.getString("CEDULA_CONSULTA");
        nombre = extras.getString("NOMBRE_SALDO");
        saldo_consulta = extras.getString("SALDO_SALDO");

        nombre_saldo.setText(nombre);
        cedula_saldo.setText(cedula);
        saldo.setText(saldo_consulta);


        botonConfirmarSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( ResultadoConsultaSaldoCliente.this, CorresponInformacion.class);
                        corresponsales = dbCorresponsal.mostrarCorresponsal();
                        saldoCorres = corresponsales.getSaldoCorresponsal();
                        corresponsales.setSaldoCorresponsal(String.valueOf( sumar1000(Integer.parseInt(corresponsales.getSaldoCorresponsal()))));
                        dbCorresponsal.actualizardaSaldoCorresponsal(corresponsales);

                startActivity(intent);
                //restamos el valor al cliente por la consulta
                clientes = dbClientes.traerClientesPorCedula(cedula);
                clientes.setSaldoInicial(String.valueOf( restar1000(Integer.parseInt(clientes.getSaldoInicial()))));
                dbClientes.actualizarSaldoCliente(clientes);

            }
        });

    }
    public int restar1000 (int saldo ) { //metodo para restarle o sumarle un numero

        saldo = saldo  - 1000;

        return saldo;

    }


    public int sumar1000 (int saldoC ) { //metodo para restarle o sumarle un numero

        saldoC = saldoC  + 1000;

        return saldoC;

    }

    @Override
    public void onBackPressed() {

    }
}