package com.example.corresponsal.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

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

public class RetirosCorresponsal extends AppCompatActivity {
    EditText numeroCedulaRetirar;
    EditText montoaRetirar;
    Button confirmarRetiro;
    Button cancelarRetiro;
    ImageView flechaAtras;
    SharedPreference01 sharedPreference01;
    Corresponsales corresponsales;
    TextView nombreCorresponsalBarner;
    TextView pinParaRetiros;
    TextView confirmarPinParaRetiros;
    TextView saldoCorresponsalBarner;
    TextView numeroCuentaCorresponsalBarner;
    DbCorresponsal dbCorresponsal;
    String cedula;
    Clientes clientes;
    DbClientes dbClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retiros_corresponsal);
        sharedPreference01 = new SharedPreference01(this);
        corresponsales = new Corresponsales();
        dbCorresponsal = new DbCorresponsal(this);
        clientes = new Clientes();
        dbClientes = new DbClientes(this);
        numeroCedulaRetirar = findViewById(R.id.numeroCedulaRetirar);
        montoaRetirar = findViewById(R.id.montoaRetirar);
        confirmarRetiro = findViewById(R.id.botonConfirmarRetiro);
        cancelarRetiro = findViewById(R.id.botonCancelarRetiro);
        nombreCorresponsalBarner = findViewById(R.id.nombreCorresponsalBarner);
        saldoCorresponsalBarner = findViewById(R.id.saldoCorresponsalBarner);
        numeroCuentaCorresponsalBarner = findViewById(R.id.numCuentaCorresponsalBarner);
        flechaAtras = findViewById(R.id.atrasCorresponsal);
        pinParaRetiros = findViewById(R.id.pinParaRetiros);
        confirmarPinParaRetiros = findViewById(R.id.confirmarPinParaRetiros);
        corresponsales = dbCorresponsal.mostrarCorresponsal();
        saldoCorresponsalBarner.setText(corresponsales.getSaldoCorresponsal());
        nombreCorresponsalBarner.setText(corresponsales.getNombreCorresponsal());
        numeroCuentaCorresponsalBarner.setText(corresponsales.getNitCorresponsal());

        flechaAtras.setVisibility(View.VISIBLE);
        flechaAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RetirosCorresponsal.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });

        cancelarRetiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( RetirosCorresponsal.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });

        confirmarRetiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aletDialog09();
            }
        });


    }


    private void aletDialog09() {
        new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("Retiro tiene un costo de 2.000 pesos que se descontaran directamente de su cuenta , desea continuar")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        cedula = numeroCedulaRetirar.getText().toString();
                        clientes = dbClientes.traerClientesPorCedula(cedula);
                        String cc = clientes.getNumeroCedula();
                        String pin = clientes.getPinUsuario();
                        String nombre = clientes.getNombreCliente();
                        String saldoActual = clientes.getSaldoInicial();
                        String vacio = "";


                       if(cedula.equals(clientes.getNumeroCedula())){

                           if (!numeroCedulaRetirar.getText().toString().equals(vacio) && !montoaRetirar.getText().toString().equals(vacio)){


                               if (confirmarPinParaRetiros.getText().toString().equals(pinParaRetiros.getText().toString())) {

                                   if (pin.equals(confirmarPinParaRetiros.getText().toString()) && pin.equals(pinParaRetiros.getText().toString())) {


                                       Intent intent = new Intent(RetirosCorresponsal.this, ConfirmarRetiro.class);

                                       intent.putExtra("MONTO_RETIRAR", montoaRetirar.getText().toString());
                                       intent.putExtra("CEDULA", cc);
                                       intent.putExtra("NOMBRE", nombre);
                                       intent.putExtra("SALDO_ACTUAL", saldoActual);

                                       startActivity(intent);

                                   } else {
                                       Toast.makeText(RetirosCorresponsal.this, "Pin incorrecto", Toast.LENGTH_SHORT).show();
                                   }

                               } else {
                                   Toast.makeText(RetirosCorresponsal.this, "contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                               }


                           }else {
                               Toast.makeText(RetirosCorresponsal.this, "llenar todos los campos", Toast.LENGTH_SHORT).show();
                           }

                       } else {
                           Toast.makeText(RetirosCorresponsal.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                       }
                    }
                })

                //POR SI DECEAMOS CANCELAR LA ACCION ESTE CODIGO NOS SIRVE

                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("mensaje", "se cancelo la accion");

                        Intent intent = new Intent(RetirosCorresponsal.this, CorresponInformacion.class);
                        startActivity(intent);
                    }
                }).show();
    }

    @Override
    public void onBackPressed() {

    }
}