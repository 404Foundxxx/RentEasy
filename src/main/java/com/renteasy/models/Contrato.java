package com.renteasy.models;

import java.sql.Date;

public class Contrato {
    private int id;
    private int idPropiedad;
    private int idInquilino;
    private Date fechaInicio;
    private Date fechaFin;
    private double montoMensual;
    private String estado;

    public Contrato() {}

    public Contrato(int id, int idPropiedad, int idInquilino, Date fechaInicio, Date fechaFin, double montoMensual, String estado) {
        this.id = id;
        this.idPropiedad = idPropiedad;
        this.idInquilino = idInquilino;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.montoMensual = montoMensual;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdPropiedad() { return idPropiedad; }
    public void setIdPropiedad(int idPropiedad) { this.idPropiedad = idPropiedad; }
    public int getIdInquilino() { return idInquilino; }
    public void setIdInquilino(int idInquilino) { this.idInquilino = idInquilino; }
    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }
    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }
    public double getMontoMensual() { return montoMensual; }
    public void setMontoMensual(double montoMensual) { this.montoMensual = montoMensual; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Contrato{" +
                "id=" + id +
                ", idPropiedad=" + idPropiedad +
                ", idInquilino=" + idInquilino +
                ", montoMensual=" + montoMensual +
                ", estado='" + estado + '\'' +
                '}';
    }
}
