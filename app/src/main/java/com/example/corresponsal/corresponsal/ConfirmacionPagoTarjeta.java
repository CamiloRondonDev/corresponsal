package com.example.corresponsal.corresponsal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.corresponsal.R;
import com.example.corresponsal.db.DbClientes;
import com.example.corresponsal.db.DbCorresponsal;
import com.example.corresponsal.db.DbHistorial;
import com.example.corresponsal.entidades.Clientes;
import com.example.corresponsal.entidades.Corresponsales;
import com.example.corresponsal.entidades.HistorialTransacciones;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConfirmacionPagoTarjeta extends AppCompatActivity {
    TextView nombrePagoTarjeta;
    TextView valorPagoTarjeta;
    TextView numeroCuotas;
    TextView numeroTarjetMirar;
    Button botonConfirmarPagoTarjeta;
    Button botonCancelarPagoTarjeta;
    String numeroTarjeta;
    String valorCuota;
    String numeroCuota;
    Clientes clientes;
    DbClientes dbClientes;
    DbCorresponsal dbCorresponsal;
    Corresponsales corresponsales;
    HistorialTransacciones historialTransacciones;
    DbHistorial dbHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmacion_pago_tarjeta);
        clientes = new Clientes();
        dbClientes = new DbClientes(this);
        dbCorresponsal = new DbCorresponsal(this);
        historialTransacciones = new HistorialTransacciones();
        dbHistorial = new DbHistorial(this);
        nombrePagoTarjeta = findViewById(R.id.nombrePagoTarjeta);
        valorPagoTarjeta = findViewById(R.id.valorPagoTarjeta);
        numeroCuotas = findViewById(R.id.numeroCuotas);
        numeroTarjetMirar = findViewById(R.id.numeroTarjetMirar);
        botonConfirmarPagoTarjeta = findViewById(R.id.botonConfirmarPagoTarjeta);
        botonCancelarPagoTarjeta = findViewById(R.id.botonCancelarPagoTarjeta);
        botonCancelarPagoTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmacionPagoTarjeta.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });

        Bundle extras = getIntent().getExtras();
        numeroTarjeta = extras.getString("NUMERO_TARJETA");
        valorCuota = extras.getString("VALOR_CUOTA");
        numeroCuota = extras.getString("NUMER_CUOTAS");

        clientes =  dbClientes.traerClientesPorNtarjeta(numeroTarjeta);

        nombrePagoTarjeta.setText(clientes.getNombreCliente());
        numeroTarjetMirar.setText(clientes.getNumerotarjeta());
        valorPagoTarjeta.setText(valorCuota);
        numeroCuotas.setText(numeroCuota);

        botonConfirmarPagoTarjeta.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                clientes =  dbClientes.traerClientesPorNtarjeta(numeroTarjeta);
                clientes.setSaldoInicial(String.valueOf( restarCliente(Integer.parseInt(clientes.getSaldoInicial()))));
                dbClientes.actualizarSaldoCliente(clientes);

                corresponsales =  dbCorresponsal.mostrarCorresponsal();
                corresponsales.setSaldoCorresponsal(String.valueOf( sumarCorres(Integer.parseInt(corresponsales.getSaldoCorresponsal()))));
                dbCorresponsal.actualizardaSaldoCorresponsal(corresponsales);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                historialTransacciones.setHoraTransaccion(dtf.format(LocalDateTime.now()));
                historialTransacciones.setTipoTransaccion("Pago con tarjeta");
                historialTransacciones.setMontoTransaccion(String.valueOf( sumarCorres(0)));
                historialTransacciones.setTarjeta(numeroTarjeta);
                historialTransacciones.setCedulaHistorial(numeroTarjeta);
                dbHistorial.insertarHistorial(historialTransacciones);

                Intent intent = new Intent( ConfirmacionPagoTarjeta.this, CorresponInformacion.class);
                startActivity(intent);

            }
        });

    }

    public int sumarCorres (int saldoDeposito ) {

        saldoDeposito = saldoDeposito  + (Integer.parseInt(valorCuota) / Integer.parseInt(numeroCuota) ) ;

        return saldoDeposito;

    }

    public int restarCliente (int saldoC ) {
        saldoC = saldoC   - (Integer.parseInt(valorCuota) / Integer.parseInt(numeroCuota) );
        return saldoC;

    }

    @Override
    public void onBackPressed() {
    }
}