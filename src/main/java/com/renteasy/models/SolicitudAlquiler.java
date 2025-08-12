package com.renteasy.models;

import java.sql.Date;

public class SolicitudAlquiler {
    private int id;
    private int idPropiedad;
    private int idUsuarioSolicitante;
    private Date fechaSolicitud;
    private String estado;

    public SolicitudAlquiler() {}

    public SolicitudAlquiler(int id, int idPropiedad, int idUsuarioSolicitante, Date fechaSolicitud, String estado) {
        this.id = id;
        this.idPropiedad = idPropiedad;
        this.idUsuarioSolicitante = idUsuarioSolicitante;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdPropiedad() { return idPropiedad; }
    public void setIdPropiedad(int idPropiedad) { this.idPropiedad = idPropiedad; }
    public int getIdUsuarioSolicitante() { return idUsuarioSolicitante; }
    public void setIdUsuarioSolicitante(int idUsuarioSolicitante) { this.idUsuarioSolicitante = idUsuarioSolicitante; }
    public Date getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(Date fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "SolicitudAlquiler{" +
                "id=" + id +
                ", idPropiedad=" + idPropiedad +
                ", idUsuarioSolicitante=" + idUsuarioSolicitante +
                ", estado='" + estado + '\'' +
                '}';
    }
}
