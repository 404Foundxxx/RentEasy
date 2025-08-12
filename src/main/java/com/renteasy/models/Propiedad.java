/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renteasy.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Propiedad {
    private int id;
    private int propietarioId;
    private String titulo;
    private String descripcion;
    private String direccion;
    private String ciudad;
    private String provincia;
    private BigDecimal precioMensual;
    private int habitaciones;
    private int banos;
    private int areaM2;
    private String estado; // disponible, ocupada, mantenimiento, etc.
    private Timestamp creadoEn;
    
    // Constructor vacío
    public Propiedad() {}
    
    // Constructor completo
    public Propiedad(int id, int propietarioId, String titulo, String descripcion, 
                     String direccion, String ciudad, String provincia, 
                     BigDecimal precioMensual, int habitaciones, int banos, 
                     int areaM2, String estado, Timestamp creadoEn) {
        this.id = id;
        this.propietarioId = propietarioId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.precioMensual = precioMensual;
        this.habitaciones = habitaciones;
        this.banos = banos;
        this.areaM2 = areaM2;
        this.estado = estado;
        this.creadoEn = creadoEn;
    }
    
    // Constructor sin ID (para inserciones)
    public Propiedad(int propietarioId, String titulo, String descripcion, 
                     String direccion, String ciudad, String provincia, 
                     BigDecimal precioMensual, int habitaciones, int banos, 
                     int areaM2, String estado) {
        this.propietarioId = propietarioId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.precioMensual = precioMensual;
        this.habitaciones = habitaciones;
        this.banos = banos;
        this.areaM2 = areaM2;
        this.estado = estado;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getPropietarioId() {
        return propietarioId;
    }
    
    public void setPropietarioId(int propietarioId) {
        this.propietarioId = propietarioId;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getCiudad() {
        return ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public String getProvincia() {
        return provincia;
    }
    
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    public BigDecimal getPrecioMensual() {
        return precioMensual;
    }
    
    public void setPrecioMensual(BigDecimal precioMensual) {
        this.precioMensual = precioMensual;
    }
    
    public int getHabitaciones() {
        return habitaciones;
    }
    
    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }
    
    public int getBanos() {
        return banos;
    }
    
    public void setBanos(int banos) {
        this.banos = banos;
    }
    
    public int getAreaM2() {
        return areaM2;
    }
    
    public void setAreaM2(int areaM2) {
        this.areaM2 = areaM2;
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
    public boolean isDisponible() {
        return "disponible".equalsIgnoreCase(this.estado);
    }
    
    public void setDisponible(boolean disponible) {
        this.estado = disponible ? "disponible" : "ocupada";
    }
    
    // Método para obtener dirección completa
    public String getDireccionCompleta() {
        return String.format("%s, %s, %s", direccion, ciudad, provincia);
    }
    
    // Método para validar datos básicos
    public boolean esValida() {
        return titulo != null && !titulo.trim().isEmpty() &&
               direccion != null && !direccion.trim().isEmpty() &&
               ciudad != null && !ciudad.trim().isEmpty() &&
               provincia != null && !provincia.trim().isEmpty() &&
               precioMensual != null && precioMensual.compareTo(BigDecimal.ZERO) > 0 &&
               habitaciones > 0 && banos > 0 && areaM2 > 0;
    }
    
    @Override
    public String toString() {
        return "Propiedad{" +
                "id=" + id +
                ", propietarioId=" + propietarioId +
                ", titulo='" + titulo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", precioMensual=" + precioMensual +
                ", habitaciones=" + habitaciones +
                ", banos=" + banos +
                ", areaM2=" + areaM2 +
                ", estado='" + estado + '\'' +
                ", creadoEn=" + creadoEn +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Propiedad propiedad = (Propiedad) obj;
        return id == propiedad.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}