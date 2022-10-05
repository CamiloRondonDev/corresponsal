package com.example.corresponsal.entidades;

public class MenuCorresponsal {
    private String nombreMenuCorresponsal;
    private String urlImagenMenuCorresponsal;


    public MenuCorresponsal(String nombreMenuCorresponsal, String urlImagenMenuCorresponsal) {
        this.nombreMenuCorresponsal= nombreMenuCorresponsal;
        this.urlImagenMenuCorresponsal = urlImagenMenuCorresponsal;

    }



    public String getNombreMenuCorresponsal() {
        return nombreMenuCorresponsal;
    }

    public void setNombreMenuCorresponsal(String nombreMenuCorresponsal) {
        this.nombreMenuCorresponsal = nombreMenuCorresponsal;
    }

    public String getUrlImagenMenuCorresponsal() {
        return urlImagenMenuCorresponsal;
    }

    public void setUrlImagenMenuCorresponsal(String urlImagenMenuCorresponsal) {
        this.urlImagenMenuCorresponsal = urlImagenMenuCorresponsal;
    }




}
