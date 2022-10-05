package com.example.corresponsal.corresponsal;
//import static com.example.corresponsal.Constantes.*;
import static com.example.corresponsal.Constantes.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corresponsal.administrador.LoginAdministrador;
import com.example.corresponsal.R;
import com.example.corresponsal.adaptadores.AdaptadorMenuCorresponsal;
import com.example.corresponsal.db.DbCorresponsal;
import com.example.corresponsal.db.DbHelper;
import com.example.corresponsal.entidades.Corresponsales;
import com.example.corresponsal.entidades.MenuCorresponsal;

import java.util.ArrayList;

public class CorresponInformacion extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    TextView nombreCorresponsalBarner;
    TextView saldoCorresponsalBarner;
    TextView numeroCuentaCorresponsalBarner;
    ImageView menuCorresponsal;
    RecyclerView recyclerView;
    ArrayList<MenuCorresponsal> menuCorres;
    DbHelper dbHelper;
    SharedPreference01 sharedPreference01;
    AdaptadorMenuCorresponsal adapatodormenucorresponsal;
    Corresponsales corresponsales;
    DbCorresponsal dbCorresponsal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correspon_informacion);
        sharedPreference01 = new SharedPreference01(this);
        corresponsales = new Corresponsales();
        dbCorresponsal = new DbCorresponsal(this);

        saldoCorresponsalBarner = findViewById(R.id.saldoCorresponsalBarner);
        numeroCuentaCorresponsalBarner = findViewById(R.id.numCuentaCorresponsalBarner);
        menuCorresponsal = findViewById(R.id.menuCorresponsal);
        menuCorresponsal.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.menuRecycleaCorresponsal);
        nombreCorresponsalBarner = findViewById(R.id.nombreCorresponsalBarner);


        corresponsales = dbCorresponsal.mostrarCorresponsal();
        saldoCorresponsalBarner.setText(corresponsales.getSaldoCorresponsal());
        nombreCorresponsalBarner.setText(corresponsales.getNombreCorresponsal());
        numeroCuentaCorresponsalBarner.setText(corresponsales.getNitCorresponsal());

        menuCorres = new ArrayList<>();
        dbHelper = new DbHelper(CorresponInformacion.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        menuCorres.add(new MenuCorresponsal(MENU_PAGO_CON_TARGETA ,MENU_PAGO_CON_TARGETA_IMAGEN));
        menuCorres.add(new MenuCorresponsal(MENU_RETIROS,MENU_RETIROS_IMAGEN));
        menuCorres.add(new MenuCorresponsal(MENU_DEPOSITOS, MENU_DEPOSITOS_IMAGEN));
        menuCorres.add(new MenuCorresponsal(MENU_TRANSFERENCIAS, MENU_TRANSFERENCIAS_IMAGEN));
        menuCorres.add(new MenuCorresponsal(MENU_HISTORIAL_TRANSFERENCIAS, MENU_HISTORIAL_TRANSFERENCIAS_IMAGEN));
        menuCorres.add(new MenuCorresponsal(MENU_CONSULTA_SALDO_CORRESPONSAL, MENU_CONSULTA_SALDO_CORRESPONSAL_IMAGEN));

        adapatodormenucorresponsal = new AdaptadorMenuCorresponsal(this , menuCorres);
        recyclerView = findViewById(R.id.menuRecycleaCorresponsal);
        recyclerView.setAdapter(adapatodormenucorresponsal);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }
    public void showPopup(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu_corresponsal);
        popupMenu.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()){
            case R.id.Item_Actualizar_Datos:

                Toast.makeText(this, "actualizar corresponsal" , Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(CorresponInformacion.this, ActualizarDatosCorresponsal.class);
                startActivity(intent1);

                return true;


            case R.id.Item_Crear_Cliente:

                Toast.makeText(this, "Crear Cliente" , Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(CorresponInformacion.this, CrearClienteCorresonsal.class);
                startActivity(intent2);
                return true;


            case R.id.Item_Cerrar_Sesion_Corr:

                Toast.makeText(this, "cerrar sesion" , Toast.LENGTH_SHORT).show();

                Intent intent3 = new Intent(CorresponInformacion.this, LoginAdministrador.class);
                startActivity(intent3);
                return true;

            default:
                return false;
        }
    }

    @Override
    public void onBackPressed() {

    }
}