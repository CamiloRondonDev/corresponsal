package com.example.corresponsal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.corresponsal.corresponsal.SharedPreference01;
import com.example.corresponsal.entidades.Corresponsales;

import java.util.ArrayList;

public class DbCorresponsal extends DbHelper{
    DbHelper dbHelper;
    Context context;
    Corresponsales corresponsales;
    SharedPreference01 sharedPreference01;

    public DbCorresponsal(@Nullable Context context) {
        super(context);
        this.context = context;
        dbHelper = new DbHelper(context);
        corresponsales = new Corresponsales();
        sharedPreference01 = new SharedPreference01(context);
  }

    public long insertarCorresponsales (Corresponsales corresponsales) {

        long id = 0;
        try {

            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(COLUMNA_NOMBRE_CORRESPONSAL, corresponsales.getNombreCorresponsal());
            values.put(COLUMNA_NIT_CORRESPONSAL, corresponsales.getNitCorresponsal());
            values.put(COLUMNA_CORREO_CORRESPONSAL, corresponsales.getCorreoCorresponsal());
            values.put(COLUMNA_SALDO_CORRESPONSAL, corresponsales.getSaldoCorresponsal());
            values.put(COLUMNA_PIN_CORRESPONSAL, corresponsales.getContraseñaCorresponsal());


            id = db.insert(TABLA_CORRESPONSALES, null , values); // colocamos values ya que en el vienen los datos  ...igualarlo a la id...

        } catch (Exception ex) {
            ex.toString();
        }


        return id;

    }


    public  boolean actualizardatoCorresponsal(Corresponsales corresponsales) {

        boolean correcto = false;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + TABLA_CORRESPONSALES + " SET   pin_corresponsal = '" + corresponsales.getContraseñaCorresponsal() +  "' WHERE id_corresponsal = '" + corresponsales.getId_corresponsal() + "' ");

            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();

        }
        return correcto;
    }


    public int login (Corresponsales corresponsales){


        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int proceso = 0;
        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLA_CORRESPONSALES , null  );

        if(cursor != null && cursor.moveToFirst()){
            do {
                if (cursor.getString(3).equals(corresponsales.getCorreoCorresponsal())&& cursor.getString(5).equals(corresponsales.getContraseñaCorresponsal())){
                    proceso++;
                }
            }while (cursor.moveToNext());
        } return proceso;
    }



    public Corresponsales mostrarCorresponsal(){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Corresponsales corresponsales = null;
        try
                (Cursor cursorCorresponsales = db.rawQuery("SELECT * FROM " + TABLA_CORRESPONSALES + " WHERE " + COLUMNA_CORREO_CORRESPONSAL + "='" + sharedPreference01.getSharedPreference() + "'", null)) {

            if (cursorCorresponsales.moveToFirst()) {
                do {
                    corresponsales = new Corresponsales();
                    corresponsales.setId_corresponsal(cursorCorresponsales.getInt(0));
                    corresponsales.setNombreCorresponsal(cursorCorresponsales.getString(1));
                    corresponsales.setNitCorresponsal(cursorCorresponsales.getString(2));
                    corresponsales.setSaldoCorresponsal(cursorCorresponsales.getString(4));
                    corresponsales.setContraseñaCorresponsal(cursorCorresponsales.getString(5));

                } while (cursorCorresponsales.moveToNext());

                cursorCorresponsales.close();
            }
        }
        return corresponsales;
    }



    public Corresponsales traerCorresponsalesPorID (int id){ // la id que voy a recibir
        DbHelper dbHelper = new DbHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Corresponsales corresponsales = new Corresponsales();

        try
                (Cursor cursorCorresponsales = db.rawQuery("SELECT * FROM " + TABLA_CORRESPONSALES + " WHERE " + COLUMNA_ID_CORRESPONSAL + "='" + id + "'", null)) {

            if (cursorCorresponsales.moveToFirst()) {


                corresponsales.setId_corresponsal(cursorCorresponsales.getInt(0));
                corresponsales.setNombreCorresponsal(cursorCorresponsales.getString(1));
                corresponsales.setNitCorresponsal(cursorCorresponsales.getString(2));
                corresponsales.setCorreoCorresponsal(cursorCorresponsales.getString(3));
                corresponsales.setSaldoCorresponsal(cursorCorresponsales.getString(4));
                corresponsales.setContraseñaCorresponsal(cursorCorresponsales.getString(5));

                cursorCorresponsales.close();

            }
        }
        return corresponsales;
    }


    public Corresponsales traerCorresponsalesPorNIT (String nit){ // la id que voy a recibir
        DbHelper dbHelper = new DbHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Corresponsales corresponsales = new Corresponsales();

        try
                (Cursor cursorCorresponsales = db.rawQuery("SELECT * FROM " + TABLA_CORRESPONSALES + " WHERE " + COLUMNA_NIT_CORRESPONSAL + "='" + nit + "'", null)) {

            if (cursorCorresponsales.moveToFirst()) {


                corresponsales.setId_corresponsal(cursorCorresponsales.getInt(0));
                corresponsales.setNombreCorresponsal(cursorCorresponsales.getString(1));
                corresponsales.setNitCorresponsal(cursorCorresponsales.getString(2));
                corresponsales.setCorreoCorresponsal(cursorCorresponsales.getString(3));
                corresponsales.setSaldoCorresponsal(cursorCorresponsales.getString(4));
                corresponsales.setContraseñaCorresponsal(cursorCorresponsales.getString(5));

                cursorCorresponsales.close();

            }
        }
        return corresponsales;
    }
//para mostrarlos con el adaptador
    public ArrayList<Corresponsales> mostrarCorresponsales(){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Corresponsales> listacorresponsales = new ArrayList<>();

        Corresponsales corresponsales = null;

        try
                (Cursor cursorCorresponsales = db.rawQuery("SELECT * FROM " + TABLA_CORRESPONSALES , null)) {

            if (cursorCorresponsales.moveToFirst()) {
                do {
                    corresponsales= new Corresponsales();

                    corresponsales.setId_corresponsal(cursorCorresponsales.getInt(0));
                    corresponsales.setNombreCorresponsal(cursorCorresponsales.getString(1));
                    corresponsales.setNitCorresponsal(cursorCorresponsales.getString(2));
                    corresponsales.setCorreoCorresponsal(cursorCorresponsales.getString(3));
                    corresponsales.setSaldoCorresponsal(cursorCorresponsales.getString(4));
                    corresponsales.setContraseñaCorresponsal(cursorCorresponsales.getString(5));


                    listacorresponsales.add(corresponsales);

                } while (cursorCorresponsales.moveToNext());
            }
        }

        return listacorresponsales;
    }



    public  boolean actualizardaSaldoCorresponsal(Corresponsales corresponsales) {

        boolean correcto = false;

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("UPDATE " + TABLA_CORRESPONSALES + " SET   saldo_corresponsal = '" + corresponsales.getSaldoCorresponsal() +  "' WHERE correo_corresponsal = '" + sharedPreference01.getSharedPreference()  + "' ");

            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();

        }
        return correcto;


    }

}
