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

import com.example.corresponsal.R;
import com.example.corresponsal.db.DbClientes;
import com.example.corresponsal.db.DbCorresponsal;
import com.example.corresponsal.db.DbHistorial;
import com.example.corresponsal.entidades.Clientes;
import com.example.corresponsal.entidades.Corresponsales;
import com.example.corresponsal.entidades.HistorialTransacciones;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConfirmarDatosDepisito extends AppCompatActivity {
    TextView cedulaQuienDeposita;
    TextView cedulaAquienDeposita;
    TextView valorAdepositar;
    Button botonConfirmarDatosDeposito;
    Button botonCancelarDatosDeposito;
    String cedulaquienDeposita;
    String cedulaAquienDepositan;
    String cantidadDepositar;
    DbClientes dbClientes;
    Clientes clientes;
    Corresponsales corresponsales;
    DbCorresponsal dbCorresponsal;
    HistorialTransacciones historialTransacciones;
    DbHistorial dbHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_datos_depisito);
        dbClientes = new DbClientes(this);
        clientes = new Clientes();
        dbCorresponsal = new DbCorresponsal(this);
        corresponsales = new Corresponsales();
        historialTransacciones = new HistorialTransacciones();
        dbHistorial = new DbHistorial(this);
        cedulaQuienDeposita = findViewById(R.id.cedulaQuienDeposita);
        cedulaAquienDeposita = findViewById(R.id.cedulaAquienDeposita);
        valorAdepositar = findViewById(R.id.valorAdepositar);
        botonConfirmarDatosDeposito = findViewById(R.id.botonConfirmarDatosDeposito);
        botonCancelarDatosDeposito = findViewById(R.id.botonCancelarDatosDeposito);
        botonCancelarDatosDeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmarDatosDepisito.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });


        Bundle extras = getIntent().getExtras();

        cedulaquienDeposita = extras.getString("CC_QUE_DEPOSITA");
        cedulaAquienDepositan = extras.getString("CC_A_QUIEN_DEPOSITAN");
        cantidadDepositar = String.valueOf(extras.getString("VALOR_A_DEPOSITAR"));

        cedulaQuienDeposita.setText(cedulaquienDeposita);
        cedulaAquienDeposita.setText(cedulaAquienDepositan);
        valorAdepositar.setText(cantidadDepositar);

        botonConfirmarDatosDeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aletDialog04();


            }
        });

    }

    public int sumarDeposito (int saldoDeposito ) {

        saldoDeposito = saldoDeposito  + Integer.parseInt(cantidadDepositar)  ;

        return saldoDeposito;

    }

    public int sumarmil (int saldoC ) { //metodo para restarle o sumarle un numero

        saldoC = saldoC  + (Integer.parseInt(cantidadDepositar) + 1000);

        return saldoC;

    }

    private void aletDialog04 () {
        new AlertDialog.Builder(this)
                .setTitle("Valor depositado correctamente")
                .setMessage("¿desea ir a inicio?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent( ConfirmarDatosDepisito.this,CorresponInformacion.class);
                        clientes =  dbClientes.traerClientesPorCedula(String.valueOf(cedulaAquienDepositan));
                        clientes.setSaldoInicial(String.valueOf( sumarDeposito(Integer.parseInt(clientes.getSaldoInicial()))));
                        dbClientes.actualizarSaldoCliente(clientes);

                        corresponsales = dbCorresponsal.mostrarCorresponsal();
                        corresponsales.setSaldoCorresponsal(String.valueOf(sumarmil(Integer.parseInt(corresponsales.getSaldoCorresponsal()))));
                        dbCorresponsal.actualizardaSaldoCorresponsal(corresponsales);

                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                        historialTransacciones.setHoraTransaccion(dtf.format(LocalDateTime.now()));
                        historialTransacciones.setTipoTransaccion("Deposito");
                        historialTransacciones.setMontoTransaccion(cantidadDepositar);
                        historialTransacciones.setTarjeta("No aplica");
                        historialTransacciones.setCedulaHistorial(cedulaquienDeposita);
                        dbHistorial.insertarHistorial(historialTransacciones);
                        startActivity(intent);

                    }
                }).show();

    }

    @Override
    public void onBackPressed() {

    }
}