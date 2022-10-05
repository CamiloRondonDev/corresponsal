package com.example.corresponsal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper  extends SQLiteOpenHelper {

    //CREAMOS LAS CONSTANTES PARA LA BASE DE DATOS

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "corresponsal.db";
    public static final String TABLA_USUARIOS_CORRESPONSAL = "tabla_usuarios";
    public static final String TABLA_CORRESPONSALES = "tabla_corresponsales";
    public static final String TABLA_HISTORIAL = "tabla_historial";

    //VARIABLES PARA USUARIOS
    public static final String COLUMNA_ID_USUARIO = "id_usuario";
    public static final String COLUMNA_NOMBRE_USUARIO = "nombre_usuario";
    public static final String COLUMNA_CEDULA_USUARIO = "cedula_usuario";
    public static final String COLUMNA_SALDO_INICIAL_USUARIO = "saldo_inicial";
    public static final String COLUMNA_NUMERO_TARJETA = "numero_tarjeta";
    public static final String COLUMNA_CONTRASENA_USUARIO = "contraseña_usuario";
    public static final String COLUMNA_CVV_USUARIO= "cvv";
    public static final String COLUMNA_FECHA_CREACION_USUARIO= "fecha";



    //VARIABLES PARA CORRESPONSAL
    public static final String COLUMNA_ID_CORRESPONSAL = "id_corresponsal";
    public static final String COLUMNA_NOMBRE_CORRESPONSAL = "nombre_corresponsal";
    public static final String COLUMNA_NIT_CORRESPONSAL = "NIT_corresponsal";
    public static final String COLUMNA_CORREO_CORRESPONSAL = "correo_corresponsal";
    public static final String COLUMNA_SALDO_CORRESPONSAL = "saldo_corresponsal";
    public static final String COLUMNA_PIN_CORRESPONSAL = "pin_corresponsal";

    //variables para historial
    public static final String COLUMNA_ID_HISTORIAL = "id_historial";
    public static final String COLUMNA_HORA_HISTORIAL = "hora";
    public static final String COLUMNA_TIPO_TRANSACCION = "tipo_transaccion";
    public static final String COLUMNA_MONTO_TRANSACCION = "monto_transaccion";
    public static final String COLUMNA_CEDULA_TRANSACCION = "cedula_transaccion";
    public static final String COLUMNA_NUMERO_TARJETA_HISTORIAL = "tarjeta_historial";



    public DbHelper( @Nullable Context context ) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override //CREAMOS LAS TABLAS PARA LA BASE DE DATOS

    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_USUARIOS_CORRESPONSAL + "(" +
                COLUMNA_ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT ," +//0
                COLUMNA_NOMBRE_USUARIO + " TEXT NOT NULL," +//1
                COLUMNA_CEDULA_USUARIO + "  TEXT NOT NULL ," +//2
                COLUMNA_SALDO_INICIAL_USUARIO + " TEXT NOT NULL ," +//3
                COLUMNA_NUMERO_TARJETA + " TEXT NOT NULL ," +//4
                COLUMNA_FECHA_CREACION_USUARIO + " TEXT NOT NULL ," +//5
                COLUMNA_CVV_USUARIO + " TEXT NOT NULL ," +//6
                COLUMNA_CONTRASENA_USUARIO + " TEXT NOT NULL )" );//7



        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_CORRESPONSALES + "(" +
                COLUMNA_ID_CORRESPONSAL + " INTEGER PRIMARY KEY AUTOINCREMENT ," +//0
                COLUMNA_NOMBRE_CORRESPONSAL + " TEXT NOT NULL," +//1
                COLUMNA_NIT_CORRESPONSAL + "  TEXT NOT NULL ," +//2
                COLUMNA_CORREO_CORRESPONSAL + " TEXT NOT NULL ," +//3
                COLUMNA_SALDO_CORRESPONSAL + " TEXT NOT NULL ," +//4
                COLUMNA_PIN_CORRESPONSAL + " TEXT NOT NULL )" );//5



        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_HISTORIAL + "(" +
                COLUMNA_ID_HISTORIAL + " INTEGER PRIMARY KEY AUTOINCREMENT ," +//0
                COLUMNA_HORA_HISTORIAL + "  TEXT NOT NULL ," +//1
                COLUMNA_TIPO_TRANSACCION + " TEXT NOT NULL ," +//2
                COLUMNA_MONTO_TRANSACCION + " TEXT NOT NULL ," +//3
                COLUMNA_NUMERO_TARJETA_HISTORIAL + " TEXT NOT NULL ," +//4
                COLUMNA_CEDULA_TRANSACCION + " TEXT NOT NULL )" );//5
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
