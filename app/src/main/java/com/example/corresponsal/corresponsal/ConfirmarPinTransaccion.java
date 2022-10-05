package com.example.corresponsal.corresponsal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ConfirmarPinTransaccion extends AppCompatActivity {

    EditText pinRetiro;
    Button botonConfirmarpinTransferencia;
    Button botonCancerpinTransferencia;
    String cedulaQuienTransfiere;
    String saldoQuienTransfiere;
    String pinQuienTransfiere;
    int idQuienTransfiere;
    String montoaTransferiar;
    String cedulaAQuienTransfiere;
    String saldoAQuienTransfiere;
    int idAQuienTransfiere;
    Clientes clientes2;
    Clientes clientes1;
    DbClientes dbClientes;
    Corresponsales corresponsales;
    DbCorresponsal dbCorresponsal;
    DbHistorial dbHistorial;
    HistorialTransacciones historialTransacciones;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_pin_transaccion);
        historialTransacciones = new HistorialTransacciones();
        dbHistorial = new DbHistorial(this);
        pinRetiro = findViewById(R.id.pinRetiro);
        botonConfirmarpinTransferencia = findViewById(R.id.botonConfirmarpinTransferencia);
        botonCancerpinTransferencia = findViewById(R.id.botonCancerpinTransferencia);
        clientes1 = new Clientes();
        clientes2 = new Clientes();
        dbClientes  = new DbClientes(this);
        corresponsales = new Corresponsales();
        dbCorresponsal = new DbCorresponsal(this);
        botonCancerpinTransferencia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmarPinTransaccion.this, CorresponInformacion.class);
                startActivity(intent);
            }
        });
        //recibimos la informacion el intent
        Bundle extras = getIntent().getExtras();
        //la persona que transfiere
        cedulaQuienTransfiere = extras.getString("CC_QUIEN_TRANSFIERE");
        saldoQuienTransfiere = (extras.getString("SALDO_QUIEN_TRASNFIERE"));
        pinQuienTransfiere = (extras.getString("PIN_QUIEN_TRASNFIERE"));
        idQuienTransfiere = (extras.getInt("ID_QUIEN_TRASNFIERE"));
        montoaTransferiar = (extras.getString("MONTO_A_TRANFERIR"));

        //a quien le transfieren
        cedulaAQuienTransfiere = (extras.getString("CEDULA_A_QUIEN_TRASNFIERE"));
        saldoAQuienTransfiere = (extras.getString("SALDO_A_QUIEN_TRASNFIERE"));
        idAQuienTransfiere = (extras.getInt("ID_A_QUIEN_TRASNFIERE"));


        botonConfirmarpinTransferencia.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {


                if (pinRetiro.getText().toString().equals(pinQuienTransfiere)){


                    int montoAtransferir = Integer.parseInt(montoaTransferiar)+ 2000;
                    int saldoClienteQueTransfiere= Integer.parseInt(saldoQuienTransfiere);

                    if ( montoAtransferir <= saldoClienteQueTransfiere){

                        clientes1 = dbClientes.traerClientesPorCedula(cedulaQuienTransfiere);//quien transfiere la plata
                        clientes1.setSaldoInicial(String.valueOf( restarCliente1(Integer.parseInt(clientes1.getSaldoInicial()))));
                        dbClientes.actualizarSaldoCliente(clientes1);

                        clientes2 = dbClientes.traerClientesPorCedula(cedulaAQuienTransfiere);//a quien le transfiero la plata
                        clientes2.setSaldoInicial(String.valueOf( sumarcliente2(Integer.parseInt(clientes2.getSaldoInicial()))));
                        dbClientes.actualizarSaldoCliente(clientes2);

                        corresponsales = dbCorresponsal.mostrarCorresponsal();
                        corresponsales.setSaldoCorresponsal(String.valueOf( sumarcorresponsal(Integer.parseInt(corresponsales.getSaldoCorresponsal()))));
                        dbCorresponsal.actualizardaSaldoCorresponsal(corresponsales);

                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                        historialTransacciones.setHoraTransaccion(dtf.format(LocalDateTime.now()));
                        historialTransacciones.setTipoTransaccion("Transaccion");
                        historialTransacciones.setMontoTransaccion(montoaTransferiar);
                        historialTransacciones.setTarjeta("No aplica");
                        historialTransacciones.setCedulaHistorial(cedulaQuienTransfiere);
                        dbHistorial.insertarHistorial(historialTransacciones);

                        Toast.makeText(ConfirmarPinTransaccion.this, "contraseña correcta", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent( ConfirmarPinTransaccion.this, CorresponInformacion.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(ConfirmarPinTransaccion.this, "saldo Insuficiente", Toast.LENGTH_SHORT).show();
                    }

                }else {

                    Toast.makeText(ConfirmarPinTransaccion.this, "contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public int restarCliente1 (int saldoDeposito ) {

        saldoDeposito = saldoDeposito - ((Integer.parseInt(montoaTransferiar) + 2000) );
        return saldoDeposito;

    }

    public int sumarcliente2 (int saldoC ) { //metodo para restarle o sumarle un numero

        saldoC =  saldoC +  (Integer.parseInt(montoaTransferiar));

        return saldoC;

    }

    public int sumarcorresponsal (int saldoC ) { //metodo para restarle o sumarle un numero

        saldoC =  saldoC +  2000;

        return saldoC;

    }

    @Override
    public void onBackPressed() {

    }
}