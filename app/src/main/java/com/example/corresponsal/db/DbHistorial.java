package com.example.corresponsal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.corresponsal.entidades.Clientes;
import com.example.corresponsal.entidades.Corresponsales;
import com.example.corresponsal.entidades.HistorialTransacciones;

import java.util.ArrayList;

public class DbHistorial extends  DbHelper{
    Context context;
    HistorialTransacciones historialTransacciones;
    DbHelper dbHelper;


    public DbHistorial(@Nullable Context context) {
        super(context);
        this.context = context;
        historialTransacciones = new HistorialTransacciones();
        dbHelper = new DbHelper(context);
    }

    public long insertarHistorial (HistorialTransacciones historialTransacciones) {

        long id = 0;
        try {

            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(COLUMNA_HORA_HISTORIAL, historialTransacciones.getHoraTransaccion());
            values.put(COLUMNA_TIPO_TRANSACCION, historialTransacciones.getTipoTransaccion());
            values.put(COLUMNA_MONTO_TRANSACCION, historialTransacciones.getMontoTransaccion());
            values.put(COLUMNA_CEDULA_TRANSACCION, historialTransacciones.getCedulaHistorial());
            values.put(COLUMNA_NUMERO_TARJETA_HISTORIAL, historialTransacciones.getTarjeta());


            id = db.insert(TABLA_HISTORIAL, null , values); // colocamos values ya que en el vienen los datos  ...igualarlo a la id...

        } catch (Exception ex) {
            ex.toString();
        }

        return id;

    }


    public ArrayList<HistorialTransacciones> mostrarHistorial(){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<HistorialTransacciones> listaHistorial = new ArrayList<>();

        HistorialTransacciones historialTransacciones = null;

        try
                (Cursor cursorHistorial = db.rawQuery("SELECT * FROM " + TABLA_HISTORIAL , null)) {

            if (cursorHistorial.moveToFirst()) {
                do {
                    historialTransacciones = new HistorialTransacciones();

                    historialTransacciones.setIdHistorial(cursorHistorial.getInt(0));
                    historialTransacciones.setHoraTransaccion(cursorHistorial.getString(1));
                    historialTransacciones.setTipoTransaccion(cursorHistorial.getString(2));
                    historialTransacciones.setMontoTransaccion(cursorHistorial.getString(3));
                    historialTransacciones.setTarjeta(cursorHistorial.getString(4));
                    historialTransacciones.setCedulaHistorial(cursorHistorial.getString(5));

                    listaHistorial.add(historialTransacciones);
                } while (cursorHistorial.moveToNext());
            }
        }
        return listaHistorial;
    }
}
