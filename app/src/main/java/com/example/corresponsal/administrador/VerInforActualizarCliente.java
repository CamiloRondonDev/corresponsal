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
import com.example.corresponsal.db.DbClientes;
import com.example.corresponsal.entidades.Clientes;

public class VerInforActualizarCliente extends AppCompatActivity {
    TextView confirNombreClinte;
    TextView confirmarNumeroCC;
    TextView confirmarsaldoClient;
    Button botonConfirmarActualcliente;
    Button botonCancelarActuDatosacliente;
    int idusuaro02;
    String nombreusuario02;
    String ccUsuario02;
    String saldoUsuario02;
    String numerTarjetaUsuario02;
    String fechaCreacionusuaro02;
    String cvvUsuaro02;
    String pinUsuario02;
    DbClientes dbClientes;
    Clientes clientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_infor_actualizar_cliente);
        confirNombreClinte = findViewById(R.id.confirNombreClinte);
        confirmarNumeroCC = findViewById(R.id.confirmarNumeroCC);
        confirmarsaldoClient = findViewById(R.id.confirmarsaldoClient);
        botonConfirmarActualcliente = findViewById(R.id.botonConfirmarActualcliente);
        botonCancelarActuDatosacliente = findViewById(R.id.botonCancelarActuDatosacliente);
        dbClientes = new DbClientes(this);
        clientes = new Clientes();




        Bundle extras = getIntent().getExtras();
        idusuaro02 = extras.getInt("ID_USUARIO1");
        nombreusuario02 = extras.getString("NOMBRE_USUARIO1");
        ccUsuario02 = (extras.getString("CEDULA_USUARIO1"));
        saldoUsuario02 = (extras.getString("SALDO_USUARIO1"));
        numerTarjetaUsuario02 = (extras.getString("NUM_TARJETA1"));
        fechaCreacionusuaro02 = (extras.getString("FEHA_CREACION_USUARIO1"));
        cvvUsuaro02 = (extras.getString("CVV_USUARIO1"));
        pinUsuario02 = (extras.getString("PIN_USUARIO1"));

         //mostramos la informacion en pantalla
        confirNombreClinte.setText(nombreusuario02);
        confirmarNumeroCC.setText(ccUsuario02);
        confirmarsaldoClient.setText(saldoUsuario02);

        //la seteamos para luego guardarla
        clientes.setNombreCliente(nombreusuario02);
        clientes.setPinUsuario(pinUsuario02);
        clientes.setIdCliente(idusuaro02);


        botonConfirmarActualcliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aletDialog06();

            }
        });

    }

    private void aletDialog06 () {
        new AlertDialog.Builder(this)
                .setTitle("Cliente Actualizado Correctamente")
                .setMessage("¿desea ir a inicio?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //guardamos la informacion
                        dbClientes.actualizarCliente(clientes);

                        Toast.makeText(VerInforActualizarCliente.this, "Cliente Actualizado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent( VerInforActualizarCliente.this, AdminInformacion.class);
                        startActivity(intent);
                    }
                }).show();
    }

    @Override
    public void onBackPressed() {

    }
}