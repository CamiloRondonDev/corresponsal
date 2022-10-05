package com.example.corresponsal.corresponsal;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference01 {

    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;


    public SharedPreference01(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("base_sp", Context.MODE_PRIVATE);
        editor = sp.edit();
    }


    public void setSharedPreference (String datoGuardar){
        editor.putString("dato", datoGuardar);
        editor.apply();

    }

    public String getSharedPreference() {
        return sp.getString ( "dato", "dato no encontrado" );
    }


}
