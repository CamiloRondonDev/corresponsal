package com.example.corresponsal.administrador;

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
import com.example.corresponsal.db.DbHistorial;
import com.example.corresponsal.entidades.Clientes;
import com.example.corresponsal.entidades.HistorialTransacciones;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConfirmarDatosUsuarioAdmin extends AppCompatActivity {
    TextView confirmarNombreClienteAdmin;
    TextView confirmarCedulaClienteAdmin;
    TextView confirmarSaldoinicailClienteAdmin;
    Button botonConfirmarDatosUsuarioAdmin;
    Button botonCancelarDatosUsuarioAdmin;
    String nombre02;
    String pin02;
    String cedula02;
    String saldo02;
    Clientes clientes;
    DbClientes dbClientes;
    HistorialTransacciones historialTransacciones;
    DbHistorial dbHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_datos_usuario_admin);
        clientes = new Clientes();
        historialTransacciones = new HistorialTransacciones();
        dbHistorial = new DbHistorial(this);
        confirmarNombreClienteAdmin = findViewById(R.id.confirmarNombreClienteAdmin);
        confirmarCedulaClienteAdmin = findViewById(R.id.confirmarCedulaClienteAdmin);
        confirmarSaldoinicailClienteAdmin = findViewById(R.id.confirmarSaldoinicailClienteAdmin);
        botonConfirmarDatosUsuarioAdmin = findViewById(R.id.botonConfirmarDatosUsuarioAdmin);
        botonCancelarDatosUsuarioAdmin = findViewById(R.id.botonCancelarDatosUsuarioAdmin);
        dbClientes = new DbClientes(this);

        botonCancelarDatosUsuarioAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmarDatosUsuarioAdmin.this, AdminInformacion.class);
                startActivity(intent);
            }
        });

        Bundle extras = getIntent().getExtras();
        nombre02 = extras.getString("NOMBRE_USUARIO_AD");
        pin02 = (extras.getString("PIN_USUARIO_AD"));
        cedula02 = (extras.getString("CEDULA_CLIENTE_AD"));
        saldo02 = (extras.getString("SALDO_CLIENTE_AD"));

        confirmarNombreClienteAdmin.setText(nombre02);
        confirmarCedulaClienteAdmin.setText(cedula02);
        confirmarSaldoinicailClienteAdmin.setText(saldo02);


        botonConfirmarDatosUsuarioAdmin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {


                clientes.setNombreCliente(nombre02);
                clientes.setNumeroCedula(cedula02);
                clientes.setPinUsuario(pin02);
                clientes.setSaldoInicial(saldo02);

                String numeroTarjeta01 = generarnumerorandumAdmin(clientes.getNumeroCedula(), 16);
                clientes.setNumerotarjeta(numeroTarjeta01);


                String cvv = generarCVVAdmin();
                clientes.setCvv(cvv);

                int numero = (int) (22 + Math.round(Math.random() * 8));
                int mes = (int) (1 + Math.round(Math.random() * 12));
                if(mes<9){
                    clientes.setFechaCreacion(String.valueOf("0"+mes+"/"+numero));
                }else{
                    clientes.setFechaCreacion(String.valueOf(mes+"/"+numero));
                }
                dbClientes.insertarClientes(clientes);



                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                historialTransacciones.setHoraTransaccion(dtf.format(LocalDateTime.now()));
                historialTransacciones.setTipoTransaccion("Registro Cliente");
                historialTransacciones.setMontoTransaccion(saldo02);
                historialTransacciones.setTarjeta(numeroTarjeta01);
                historialTransacciones.setCedulaHistorial(cedula02);
                dbHistorial.insertarHistorial(historialTransacciones);

                aletDialog07();



            }
        });



    }

    //metodos para generar el numero de la tarjeta
    private String generarnumerorandumAdmin(String clientes, int lenght) {
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
    private String generarCVVAdmin() {

        int numero1 = (int) (1 + Math.round(Math.random() * 7));
        int numero2 = (int) (1 + Math.round(Math.random() * 7));
        int numero3 = (int) (1 + Math.round(Math.random() * 7));
        //aca concatenamos los 3 numeros
        String cvv = String.valueOf(numero1).concat(String.valueOf(numero2).concat(String.valueOf(numero3)));
        return cvv;

    }

    private void aletDialog07 () {
        new AlertDialog.Builder(this)
                .setTitle("Cliente Registrado")
                .setMessage("¿desea ir a inicio?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(ConfirmarDatosUsuarioAdmin.this, AdminInformacion.class);
                        Toast.makeText(ConfirmarDatosUsuarioAdmin.this, "Cliente Registrado", Toast.LENGTH_SHORT).show();
                        startActivity(intent);


                    }
                }).show();

    }

    @Override
    public void onBackPressed() {

    }

}