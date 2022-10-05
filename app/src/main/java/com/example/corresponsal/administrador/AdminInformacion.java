package com.example.corresponsal.administrador;

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
import android.widget.Toast;

import com.example.corresponsal.R;
import com.example.corresponsal.adaptadores.AdaptadorAdministrador;
import com.example.corresponsal.db.DbHelper;
import com.example.corresponsal.entidades.MenuAdmin;

import java.util.ArrayList;

public class AdminInformacion extends AppCompatActivity implements android.widget.PopupMenu.OnMenuItemClickListener, PopupMenu.OnMenuItemClickListener {
    ImageView menuAdmin;
    ArrayList<MenuAdmin> menu;
    RecyclerView menuAdministrador;
    DbHelper dbHelper;
    AdaptadorAdministrador adapatodorAdministrador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_informacion);
        menuAdmin = findViewById(R.id.menuAdmin);
        menuAdmin.setVisibility(View.VISIBLE);
        menu = new ArrayList<>();
        dbHelper = new DbHelper(AdminInformacion.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        menu.add(new MenuAdmin(MENU_CREAR_CLIENTE,MENU_CREAR_CLIENTE_IMAGEN));
        menu.add(new MenuAdmin(MENU_CREAR_REGISTRAR_CORRESPONSAL,MENU_CREAR_CORRESPONSAL_IMAGEN));
        menu.add(new MenuAdmin(MENU_CONSULTAR_CLIENTE,MENU_CONSULTAR_CLIENTE_IMAGEN));
        menu.add(new MenuAdmin(MENU_CONSULTAR_CORRESPONSAL,MENU_CONSULTAR_CORRESPONSAL_IMAGEN));
        menu.add(new MenuAdmin(MENU_LISTADO_CLIENTES,MENU_LISTADO_CLIENTE_IMAGEN));
        menu.add(new MenuAdmin(MENU_LISTADO_CORRESPONSALES,MENU_LISTADO_CORRESPONSALES_IMAGEN));

        adapatodorAdministrador = new AdaptadorAdministrador(this,menu);
        menuAdministrador = findViewById(R.id.menuAdministrador);
        menuAdministrador.setAdapter(adapatodorAdministrador);
        menuAdministrador.setLayoutManager(new GridLayoutManager(this, 2));

    }

    public void showPopup(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();

    }





    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
       switch (menuItem.getItemId()){
           case R.id.Item_Actualizar_Corresponsal:
               Intent intent1 = new Intent(AdminInformacion.this, ConsultaCorresponsalParaActualizar.class);
               startActivity(intent1);

               Toast.makeText(this, "actualizar corresponsal" , Toast.LENGTH_SHORT).show();
               return true;

           case R.id.Item_Actualizar_Clientes:
               Intent intent2 = new Intent(AdminInformacion.this, ConsularClienteParaActualizarlo.class);
               startActivity(intent2);

               Toast.makeText(this, "actualizar cliente" , Toast.LENGTH_SHORT).show();
               return true;

           case R.id.Item_Cerrar_Sesion:
               Intent intent3 = new Intent(AdminInformacion.this, LoginAdministrador.class);
               startActivity(intent3);

               Toast.makeText(this, "cerrar sesion" , Toast.LENGTH_SHORT).show();
               return true;

           default:
               return false;
       }
    }

    @Override
    public void onBackPressed() {

    }
}