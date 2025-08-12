package com.renteasy.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Contrato {
    private int id;
    private int propiedadId;
    private int propietarioId;
    private int inquilinoId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal montoMensual;
    private boolean firmadoPropietario;
    private boolean firmadoInquilino;
    private String estado; // "activo", "finalizado", "cancelado", "pendiente"
    private Timestamp creadoEn;
    
    // Constructor vacío
    public Contrato() {}
    
    // Constructor completo
    public Contrato(int id, int propiedadId, int propietarioId, int inquilinoId,
                    LocalDate fechaInicio, LocalDate fechaFin, BigDecimal montoMensual,
                    boolean firmadoPropietario, boolean firmadoInquilino, 
                    String estado, Timestamp creadoEn) {
        this.id = id;
        this.propiedadId = propiedadId;
        this.propietarioId = propietarioId;
        this.inquilinoId = inquilinoId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.montoMensual = montoMensual;
        this.firmadoPropietario = firmadoPropietario;
        this.firmadoInquilino = firmadoInquilino;
        this.estado = estado;
        this.creadoEn = creadoEn;
    }
    
    // Constructor para crear nuevo contrato (sin ID)
    public Contrato(int propiedadId, int propietarioId, int inquilinoId,
                    LocalDate fechaInicio, LocalDate fechaFin, BigDecimal montoMensual,
                    String estado) {
        this.propiedadId = propiedadId;
        this.propietarioId = propietarioId;
        this.inquilinoId = inquilinoId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.montoMensual = montoMensual;
        this.firmadoPropietario = false;
        this.firmadoInquilino = false;
        this.estado = estado;
    }
    
    // Constructor para mantener compatibilidad con código anterior
    public Contrato(int id, int propiedadId, int propietarioId, int inquilinoId, LocalDate fechaInicio, 
                    LocalDate fechaFin, BigDecimal montoMensual, String estado) {
        this.id = id;
        this.propiedadId = propiedadId;
        this.propietarioId = propietarioId;
        this.inquilinoId = inquilinoId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.montoMensual = montoMensual;
        this.estado = estado;
        this.firmadoPropietario = false;
        this.firmadoInquilino = false;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getPropiedadId() {
        return propiedadId;
    }
    
    public void setPropiedadId(int propiedadId) {
        this.propiedadId = propiedadId;
    }
    
    // Método de compatibilidad con código anterior
    public int getIdPropiedad() {
        return propiedadId;
    }
    
    public void setIdPropiedad(int idPropiedad) {
        this.propiedadId = idPropiedad;
    }
    
    public int getPropietarioId() {
        return propietarioId;
    }
    
    public void setPropietarioId(int propietarioId) {
        this.propietarioId = propietarioId;
    }
    
    public int getInquilinoId() {
        return inquilinoId;
    }
    
    public void setInquilinoId(int inquilinoId) {
        this.inquilinoId = inquilinoId;
    }
    
    // Método de compatibilidad con código anterior
    public int getIdInquilino() {
        return inquilinoId;
    }
    
    public void setIdInquilino(int idInquilino) {
        this.inquilinoId = idInquilino;
    }
    
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public BigDecimal getMontoMensual() {
        return montoMensual;
    }
    
    public void setMontoMensual(BigDecimal montoMensual) {
        this.montoMensual = montoMensual;
    }
    
    public boolean isFirmadoPropietario() {
        return firmadoPropietario;
    }
    
    public void setFirmadoPropietario(boolean firmadoPropietario) {
        this.firmadoPropietario = firmadoPropietario;
    }
    
    public boolean isFirmadoInquilino() {
        return firmadoInquilino;
    }
    
    public void setFirmadoInquilino(boolean firmadoInquilino) {
        this.firmadoInquilino = firmadoInquilino;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public Timestamp getCreadoEn() {
        return creadoEn;
    }
    
    public void setCreadoEn(Timestamp creadoEn) {
        this.creadoEn = creadoEn;
    }
    
    // Métodos de utilidad
    public boolean estaCompletamenteFirmado() {
        return firmadoPropietario && firmadoInquilino;
    }
    
    public boolean estaActivo() {
        return "activo".equalsIgnoreCase(estado);
    }
    
    public boolean estaPendienteFirma() {
        return !firmadoPropietario || !firmadoInquilino;
    }
    
    public String getEstadoFirma() {
        if (firmadoPropietario && firmadoInquilino) {
            return "Completamente firmado";
        } else if (firmadoPropietario) {
            return "Firmado por propietario - Pendiente inquilino";
        } else if (firmadoInquilino) {
            return "Firmado por inquilino - Pendiente propietario";
        } else {
            return "Pendiente de firmas";
        }
    }
    
    public long getDuracionEnMeses() {
        if (fechaInicio != null && fechaFin != null) {
            return java.time.temporal.ChronoUnit.MONTHS.between(fechaInicio, fechaFin);
        }
        return 0;
    }
    
    public BigDecimal getMontoTotal() {
        if (montoMensual != null) {
            long meses = getDuracionEnMeses();
            return montoMensual.multiply(BigDecimal.valueOf(meses));
        }
        return BigDecimal.ZERO;
    }
    
    // Validación básica
    public boolean esValido() {
        return propiedadId > 0 &&
               propietarioId > 0 &&
               inquilinoId > 0 &&
               fechaInicio != null &&
               fechaFin != null &&
               fechaInicio.isBefore(fechaFin) &&
               montoMensual != null &&
               montoMensual.compareTo(BigDecimal.ZERO) > 0 &&
               estado != null && !estado.trim().isEmpty();
    }
    
    @Override
    public String toString() {
        return "Contrato{" +
                "id=" + id +
                ", propiedadId=" + propiedadId +
                ", propietarioId=" + propietarioId +
                ", inquilinoId=" + inquilinoId +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", montoMensual=" + montoMensual +
                ", firmadoPropietario=" + firmadoPropietario +
                ", firmadoInquilino=" + firmadoInquilino +
                ", estado='" + estado + '\'' +
                ", creadoEn=" + creadoEn +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contrato contrato = (Contrato) obj;
        return id == contrato.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}