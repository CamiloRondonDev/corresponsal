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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConfirmarRetiro extends AppCompatActivity {

    TextView nombreRetiro;
    TextView mostrarNumeroCuenta;
    TextView montoDe;
    Button botonConfirmarRetiro;
    Button botonCancelarRetiro;
    String montoaRetirar;
    String cedula;
    String nombre;
    String saldoActual;
    Clientes clientes;
    DbClientes dbClientes;
    Corresponsales corresponsales;
    DbCorresponsal dbCorresponsal;
    HistorialTransacciones historialTransacciones;
    DbHistorial dbHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_retiro);
        clientes = new Clientes();
        dbClientes = new DbClientes(this);
        corresponsales = new Corresponsales();
        dbCorresponsal = new DbCorresponsal(this);
        historialTransacciones = new HistorialTransacciones();
        dbHistorial = new DbHistorial(this);

        nombreRetiro = findViewById(R.id.nombreRetiro);
        mostrarNumeroCuenta = findViewById(R.id.mostrarNumeroCuenta);
        montoDe = findViewById(R.id.montoDe);
        botonConfirmarRetiro = findViewById(R.id.botonConfirmarRetiro);
        botonCancelarRetiro = findViewById(R.id.botonCancelarRetiro);


        Bundle extras = getIntent().getExtras();
        montoaRetirar = extras.getString("MONTO_RETIRAR");
        cedula = extras.getString("CEDULA");
        nombre = extras.getString("NOMBRE");
        saldoActual = extras.getString("SALDO_ACTUAL");

        nombreRetiro.setText(nombre);
        mostrarNumeroCuenta.setText(cedula);
        montoDe.setText(montoaRetirar);


        botonCancelarRetiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( ConfirmarRetiro.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });

        botonConfirmarRetiro.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {


                int precio = (Integer.parseInt(montoaRetirar) +2000);
                int valor = (Integer.parseInt(saldoActual));


                if ( precio <= valor)
                {  //aca le restamos el valor a retirar al cliente
                    clientes = dbClientes.traerClientesPorCedula(cedula);
                    clientes.setSaldoInicial(String.valueOf( restar(Integer.parseInt(clientes.getSaldoInicial()))));
                    dbClientes.actualizarSaldoCliente(clientes);
                    //aca le sumamos la ganancia al corresponsal
                    corresponsales = dbCorresponsal.mostrarCorresponsal();
                    corresponsales.setSaldoCorresponsal(String.valueOf( sumar(Integer.parseInt(corresponsales.getSaldoCorresponsal()))));
                    dbCorresponsal.actualizardaSaldoCorresponsal(corresponsales);

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    historialTransacciones.setHoraTransaccion(dtf.format(LocalDateTime.now()));
                    historialTransacciones.setTipoTransaccion("Retiro");
                    historialTransacciones.setMontoTransaccion(montoaRetirar);
                    historialTransacciones.setTarjeta("No aplica");
                    historialTransacciones.setCedulaHistorial(cedula);
                    dbHistorial.insertarHistorial(historialTransacciones);

                    aletDialog10();

                }else {
                    Toast.makeText(ConfirmarRetiro.this, "Saldo insuficiente", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public int restar (int saldoDeposito ) {

        saldoDeposito = saldoDeposito - (Integer.parseInt(montoaRetirar) +  2000 );
        return saldoDeposito;

    }

    public int sumar (int saldoC ) { //metodo para restarle o sumarle un numero

        saldoC = ( saldoC  + 2000) -  (Integer.parseInt(montoaRetirar));

        return saldoC;
    }

    private void aletDialog10 () {
        new AlertDialog.Builder(this)
                .setTitle("Retiro exitoso")
                .setMessage("¿desea ir a inicio?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent( ConfirmarRetiro.this,CorresponInformacion.class);
                        startActivity(intent);
                    }
                }).show();
    }

    @Override
    public void onBackPressed() {

    }
}
