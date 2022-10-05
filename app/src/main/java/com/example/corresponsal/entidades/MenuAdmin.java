package com.example.corresponsal.entidades;

public class MenuAdmin {

    private String nombreMenu;
    private String urlImagenMenu;

    public MenuAdmin(String nombreMenu, String urlImagenMenu) {
        this.nombreMenu= nombreMenu;
        this.urlImagenMenu = urlImagenMenu;

    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public String getUrlMenu() {
        return urlImagenMenu;
    }

    public void setUrlMenu(String urlMenu) {
        this.urlImagenMenu = urlMenu;
    }
}
