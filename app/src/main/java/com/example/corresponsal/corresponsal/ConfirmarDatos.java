package com.example.corresponsal.corresponsal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corresponsal.R;
import com.example.corresponsal.db.DbClientes;
import com.example.corresponsal.db.DbCorresponsal;
import com.example.corresponsal.db.DbHistorial;
import com.example.corresponsal.entidades.Clientes;
import com.example.corresponsal.entidades.Corresponsales;
import com.example.corresponsal.entidades.HistorialTransacciones;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ConfirmarDatos extends AppCompatActivity {
    TextView mostrarNombreCliente;
    TextView mostrarCedulaCliente;
    TextView mostrarSaldoInicalCliente;
    String pin;
    Button confirmar;
    Button cancelar;
    String nombre, cedula, saldo;
    Clientes clientes;
    DbClientes dbClientes;
    HistorialTransacciones historialTransacciones;
    DbHistorial dbHistorial;
    Corresponsales corresponsales;
    DbCorresponsal dbCorresponsal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_datos);
        historialTransacciones = new HistorialTransacciones();
        dbHistorial = new DbHistorial(this);
        mostrarNombreCliente = findViewById(R.id.confirmarNombreCliente);
        mostrarCedulaCliente = findViewById(R.id.confirmarCedulaCliente);
        mostrarSaldoInicalCliente = findViewById(R.id.confirmarSaldoinicailCliente);
        confirmar = findViewById(R.id.botonConfirmarDatosUsuario);
        cancelar = findViewById(R.id.botonCancelarDatosUsuario);
        clientes = new Clientes();
        dbClientes = new DbClientes(this);
        corresponsales = new Corresponsales();
        dbCorresponsal = new DbCorresponsal(this);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmarDatos.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });
        Bundle extras = getIntent().getExtras();

        nombre = extras.getString("NOMBRE_USUARIO");
        pin = (extras.getString("PIN_USUARIO"));
        cedula = (extras.getString("CEDULA_CLIENTE"));
        saldo = (extras.getString("SALDO_CLIENTE"));


        mostrarNombreCliente.setText(nombre);
        mostrarCedulaCliente.setText(cedula);
        mostrarSaldoInicalCliente.setText(saldo);


        confirmar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {


                //cargamos la informacion
                clientes.setNombreCliente(nombre);
                clientes.setNumeroCedula(cedula);
                clientes.setPinUsuario(pin);
                clientes.setSaldoInicial(saldo);

                //cargamos el numero de la tarjeta
                String numeroTarjeta = generarnumerorandum(clientes.getNumeroCedula(), 16);
                clientes.setNumerotarjeta(numeroTarjeta);
               // generarCVV();

                //cargamos numero cvv
                String cvv = generarCVV();
                clientes.setCvv(cvv);

                //cargamos la fecha
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM");
                Date fechaActual = new Date();
                clientes.setFechaCreacion(simpleDateFormat.format(fechaActual));


                //enviamos la informacion que cargamos al metodo para que lo guarde en la base de datos
                dbClientes.insertarClientes(clientes);


                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                historialTransacciones.setHoraTransaccion(dtf.format(LocalDateTime.now()));

                historialTransacciones.setTipoTransaccion("Registro Cliente");
                historialTransacciones.setMontoTransaccion(saldo);
                historialTransacciones.setTarjeta(numeroTarjeta);
                historialTransacciones.setCedulaHistorial(cedula);
                dbHistorial.insertarHistorial(historialTransacciones);

                corresponsales = dbCorresponsal.mostrarCorresponsal();
                corresponsales.setSaldoCorresponsal(String.valueOf( sumarcomision(Integer.parseInt(corresponsales.getSaldoCorresponsal()))));
                dbCorresponsal.actualizardaSaldoCorresponsal(corresponsales);

                aletDialog01();
            }
        });

    }

    public int sumarcomision (int saldoDeposito ) {

        saldoDeposito = saldoDeposito  + 10000  ;

        return saldoDeposito;

    }

    //metodos para generar el numero de la tarjeta
    private String generarnumerorandum(String clientes, int lenght) {
        int numero = (int) (3 + Math.round(Math.random() * 3));

        String starCard = numero + clientes;
        StringBuilder numeroAcuns = new StringBuilder(lenght);
        int fill = lenght - starCard.length();
        numeroAcuns.append(starCard);
        while (fill-- > 0)
            numeroAcuns.append(Math.round(Math.random() * 9));
        return numeroAcuns.toString();
    }


    //meotodo para generar el cvv
    private String generarCVV() {

        int numero1 = (int) (3 + Math.round(Math.random() * 3));
        int numero2 = (int) (3 + Math.round(Math.random() * 3));
        int numero3 = (int) (3 + Math.round(Math.random() * 3));
        //aca concatenamos los 3 numeros
        String cvv = String.valueOf(numero1).concat(String.valueOf(numero2).concat(String.valueOf(numero3)));
        return cvv;

    }


    private void aletDialog01 () {
        new AlertDialog.Builder(this)
                .setTitle("Cliente Registrado")
                .setMessage("¿desea ir a inicio?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(ConfirmarDatos.this,CorresponInformacion.class);
                        Toast.makeText(ConfirmarDatos.this, "Cliente Registrado", Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                    }
                }).show();

    }

    @Override
    public void onBackPressed() {

    }

}