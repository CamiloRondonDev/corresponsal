package com.example.corresponsal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.corresponsal.entidades.Clientes;
import com.example.corresponsal.entidades.Corresponsales;

import java.util.ArrayList;

public class DbClientes extends DbHelper {
    DbHelper dbHelper;
    Context context;
    Clientes clientes;

    public DbClientes(@Nullable Context context) {
        super(context);
        this.context = context;
        dbHelper = new DbHelper(context);
        clientes = new Clientes();
    }

    public long insertarClientes(Clientes clientes) {

        long id = 0;

        try {

            //guardar la informacion en las columnas dependiendo de lo que nos llega por los gets
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(COLUMNA_NOMBRE_USUARIO, clientes.getNombreCliente());
            values.put(COLUMNA_CEDULA_USUARIO, clientes.getNumeroCedula());
            values.put(COLUMNA_CONTRASENA_USUARIO, clientes.getPinUsuario());
            values.put(COLUMNA_SALDO_INICIAL_USUARIO,clientes.getSaldoInicial());
            values.put(COLUMNA_NUMERO_TARJETA,clientes.getNumerotarjeta());
            values.put(COLUMNA_CVV_USUARIO,clientes.getCvv());
            values.put(COLUMNA_FECHA_CREACION_USUARIO,clientes.getFechaCreacion());

            id = db.insert(TABLA_USUARIOS_CORRESPONSAL,null,values);

        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }


    public ArrayList<Clientes> mostrarClientes(){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Clientes> listaclientes = new ArrayList<>();

        Clientes clientes = null;

        try
                (Cursor cursorClientes = db.rawQuery("SELECT * FROM " + TABLA_USUARIOS_CORRESPONSAL , null)) {

            if (cursorClientes.moveToFirst()) {
                do {
                    clientes = new Clientes();

                    clientes.setIdCliente(cursorClientes.getInt(0));
                    clientes.setNombreCliente(cursorClientes.getString(1));
                    clientes.setNumeroCedula(cursorClientes.getString(2));
                    clientes.setSaldoInicial(cursorClientes.getString(3));
                    clientes.setNumerotarjeta(cursorClientes.getString(4));

                    listaclientes.add(clientes);
                } while (cursorClientes.moveToNext());
            }
        }

        return listaclientes;
    }


    public Clientes traerClientesPorCedula (String cc){ // la id que voy a recibir
          DbHelper dbHelper = new DbHelper(context);

          SQLiteDatabase db = dbHelper.getWritableDatabase();

          Clientes clientes = new Clientes();
        try
                (Cursor cursorClientes = db.rawQuery("SELECT * FROM " + TABLA_USUARIOS_CORRESPONSAL + " WHERE " + COLUMNA_CEDULA_USUARIO + "='" + cc + "'", null)) {

            if (cursorClientes.moveToFirst()) {

                clientes.setIdCliente(cursorClientes.getInt(0));
                clientes.setNombreCliente(cursorClientes.getString(1));
                clientes.setNumeroCedula(cursorClientes.getString(2));
                clientes.setSaldoInicial(cursorClientes.getString(3));
                clientes.setNumerotarjeta(cursorClientes.getString(4));
                clientes.setFechaCreacion(cursorClientes.getString(5));
                clientes.setCvv(cursorClientes.getString(6));
                clientes.setPinUsuario(cursorClientes.getString(7));

                cursorClientes.close();
            }
        }
        return clientes ;
    }


    public  boolean actualizarCliente (Clientes clientes) {

        boolean correcto = false;

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("UPDATE " + TABLA_USUARIOS_CORRESPONSAL + " SET   contraseña_usuario = '" + clientes.getPinUsuario() +  "' WHERE id_usuario = '" + clientes.getIdCliente() + "' ");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();

        }
        return correcto;


    }


    public  boolean actualizarSaldoCliente (Clientes clientes) {

        boolean correcto = false;

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("UPDATE " + TABLA_USUARIOS_CORRESPONSAL + " SET   saldo_inicial = '" + clientes.getSaldoInicial() +  "' WHERE cedula_usuario = '" + clientes.getNumeroCedula() + "' ");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();

        }
        return correcto;

    }


    public Clientes traerClientesPorNtarjeta (String ntj){ // la id que voy a recibir
        DbHelper dbHelper = new DbHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Clientes clientes = new Clientes();
        try
                (Cursor cursorClientes = db.rawQuery("SELECT * FROM " + TABLA_USUARIOS_CORRESPONSAL + " WHERE " + COLUMNA_NUMERO_TARJETA + "='" + ntj + "'", null)) {

            if (cursorClientes.moveToFirst()) {


                clientes.setIdCliente(cursorClientes.getInt(0));
                clientes.setNombreCliente(cursorClientes.getString(1));
                clientes.setNumeroCedula(cursorClientes.getString(2));
                clientes.setSaldoInicial(cursorClientes.getString(3));
                clientes.setNumerotarjeta(cursorClientes.getString(4));
                clientes.setFechaCreacion(cursorClientes.getString(5));
                clientes.setCvv(cursorClientes.getString(6));
                clientes.setPinUsuario(cursorClientes.getString(7));

                cursorClientes.close();
            }
        }
        return clientes ;
    }
}