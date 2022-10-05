package com.example.corresponsal.entidades;

public class HistorialTransacciones {

    private int idHistorial;
    private String fechaTransaccion;
    private String horaTransaccion;
    private String tipoTransaccion;
    private String montoTransaccion;
    private String tarjeta;


    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    private String cedulaHistorial;




    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getHoraTransaccion() {
        return horaTransaccion;
    }

    public void setHoraTransaccion(String horaTransaccion) {
        this.horaTransaccion = horaTransaccion;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getMontoTransaccion() {
        return montoTransaccion;
    }

    public void setMontoTransaccion(String montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }

    public String getCedulaHistorial() {
        return cedulaHistorial;
    }

    public void setCedulaHistorial(String cedulaHistorial) {
        this.cedulaHistorial = cedulaHistorial;
    }
}
