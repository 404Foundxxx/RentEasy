package com.renteasy.models;

import java.sql.Timestamp;

public class SolicitudAlquiler {
    private int id;
    private int inquilinoId;
    private int propiedadId;
    private String mensaje;
    private String estado; // "pendiente", "aceptada", "rechazada"
    private Timestamp fechaSolicitud;
    
    // Campos adicionales para información enriquecida (de JOINs)
    private String nombreInquilino;
    private String emailInquilino;
    private String tituloPropiedad;
    private String direccionPropiedad;
    
    // Constructor vacío
    public SolicitudAlquiler() {}
    
    // Constructor completo
    public SolicitudAlquiler(int id, int inquilinoId, int propiedadId, String mensaje,
                           String estado, Timestamp fechaSolicitud) {
        this.id = id;
        this.inquilinoId = inquilinoId;
        this.propiedadId = propiedadId;
        this.mensaje = mensaje;
        this.estado = estado;
        this.fechaSolicitud = fechaSolicitud;
    }
    
    // Constructor para crear nueva solicitud (sin ID ni timestamp)
    public SolicitudAlquiler(int inquilinoId, int propiedadId, String mensaje) {
        this.inquilinoId = inquilinoId;
        this.propiedadId = propiedadId;
        this.mensaje = mensaje;
        this.estado = "pendiente"; // Estado por defecto
    }
    
    // Constructor para mantener compatibilidad con código anterior
    public SolicitudAlquiler(int id, int propiedadId, int usuarioSolicitante, 
                           Timestamp fechaSolicitud, String estado) {
        this.id = id;
        this.propiedadId = propiedadId;
        this.inquilinoId = usuarioSolicitante;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
        this.mensaje = ""; // Mensaje por defecto
    }
    
    // Getters y Setters principales
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getInquilinoId() {
        return inquilinoId;
    }
    
    public void setInquilinoId(int inquilinoId) {
        this.inquilinoId = inquilinoId;
    }
    
    public int getPropiedadId() {
        return propiedadId;
    }
    
    public void setPropiedadId(int propiedadId) {
        this.propiedadId = propiedadId;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public Timestamp getFechaSolicitud() {
        return fechaSolicitud;
    }
    
    public void setFechaSolicitud(Timestamp fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    
    // Getters y Setters para campos adicionales
    public String getNombreInquilino() {
        return nombreInquilino;
    }
    
    public void setNombreInquilino(String nombreInquilino) {
        this.nombreInquilino = nombreInquilino;
    }
    
    public String getEmailInquilino() {
        return emailInquilino;
    }
    
    public void setEmailInquilino(String emailInquilino) {
        this.emailInquilino = emailInquilino;
    }
    
    public String getTituloPropiedad() {
        return tituloPropiedad;
    }
    
    public void setTituloPropiedad(String tituloPropiedad) {
        this.tituloPropiedad = tituloPropiedad;
    }
    
    public String getDireccionPropiedad() {
        return direccionPropiedad;
    }
    
    public void setDireccionPropiedad(String direccionPropiedad) {
        this.direccionPropiedad = direccionPropiedad;
    }
    
    // Métodos de compatibilidad con código anterior
    public int getIdPropiedad() {
        return propiedadId;
    }
    
    public void setIdPropiedad(int idPropiedad) {
        this.propiedadId = idPropiedad;
    }
    
    public int getIdUsuarioSolicitante() {
        return inquilinoId;
    }
    
    public void setIdUsuarioSolicitante(int idUsuarioSolicitante) {
        this.inquilinoId = idUsuarioSolicitante;
    }
    
    // Métodos de utilidad
    public boolean esPendiente() {
        return "pendiente".equalsIgnoreCase(estado);
    }
    
    public boolean esAceptada() {
        return "aceptada".equalsIgnoreCase(estado);
    }
    
    public boolean esRechazada() {
        return "rechazada".equalsIgnoreCase(estado);
    }
    
    public void aceptar() {
        this.estado = "aceptada";
    }
    
    public void rechazar() {
        this.estado = "rechazada";
    }
    
    public String getEstadoDescripcion() {
        switch (estado.toLowerCase()) {
            case "pendiente":
                return "Pendiente de revisión";
            case "aceptada":
                return "Solicitud aceptada";
            case "rechazada":
                return "Solicitud rechazada";
            default:
                return "Estado desconocido";
        }
    }
    
    public String getInformacionCompleta() {
        StringBuilder info = new StringBuilder();
        info.append("Solicitud ID: ").append(id);
        if (nombreInquilino != null) {
            info.append(" - Inquilino: ").append(nombreInquilino);
        }
        if (tituloPropiedad != null) {
            info.append(" - Propiedad: ").append(tituloPropiedad);
        }
        if (direccionPropiedad != null) {
            info.append(" (").append(direccionPropiedad).append(")");
        }
        info.append(" - Estado: ").append(getEstadoDescripcion());
        return info.toString();
    }
    
    // Validación básica
    public boolean esValida() {
        return inquilinoId > 0 &&
               propiedadId > 0 &&
               mensaje != null && !mensaje.trim().isEmpty() &&
               estado != null && !estado.trim().isEmpty();
    }
    
    @Override
    public String toString() {
        return "SolicitudAlquiler{" +
                "id=" + id +
                ", inquilinoId=" + inquilinoId +
                ", propiedadId=" + propiedadId +
                ", mensaje='" + mensaje + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaSolicitud=" + fechaSolicitud +
                ", nombreInquilino='" + nombreInquilino + '\'' +
                ", tituloPropiedad='" + tituloPropiedad + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SolicitudAlquiler that = (SolicitudAlquiler) obj;
        return id == that.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}