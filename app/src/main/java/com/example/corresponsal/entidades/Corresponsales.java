package com.example.corresponsal.entidades;

public class Corresponsales {

    private int id_corresponsal;
    private String nombreCorresponsal;
    private String nitCorresponsal;
    private String correoCorresponsal;
    private String saldoCorresponsal;



    public String getSaldoCorresponsal() {
        return saldoCorresponsal;
    }

    public void setSaldoCorresponsal(String saldoCorresponsal) {
        this.saldoCorresponsal = saldoCorresponsal;
    }

    private String contraseñaCorresponsal;



    public int getId_corresponsal() {
        return id_corresponsal;
    }

    public void setId_corresponsal(int id_corresponsal) {
        this.id_corresponsal = id_corresponsal;
    }

    public String getNombreCorresponsal() {
        return nombreCorresponsal;
    }

    public void setNombreCorresponsal(String nombreCorresponsal) {
        this.nombreCorresponsal = nombreCorresponsal;
    }

    public String getNitCorresponsal() {
        return nitCorresponsal;
    }

    public void setNitCorresponsal(String nitCorresponsal) {
        this.nitCorresponsal = nitCorresponsal;
    }

    public String getCorreoCorresponsal() {
        return correoCorresponsal;
    }

    public void setCorreoCorresponsal(String correoCorresponsal) {
        this.correoCorresponsal = correoCorresponsal;
    }

    public String getContraseñaCorresponsal() {
        return contraseñaCorresponsal;
    }

    public void setContraseñaCorresponsal(String contraseñaCorresponsal) {
        this.contraseñaCorresponsal = contraseñaCorresponsal;
    }

}
