/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renteasy.models;

public class Propiedad {
    private int id;
    private String titulo;
    private String descripcion;
    private String direccion;
    private double precioMensual;
    private String tipo; // Casa, apartamento, local, etc.
    private boolean disponible;

    public Propiedad() {}

    public Propiedad(int id, String titulo, String descripcion, String direccion, double precioMensual, String tipo, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.precioMensual = precioMensual;
        this.tipo = tipo;
        this.disponible = disponible;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public double getPrecioMensual() { return precioMensual; }
    public void setPrecioMensual(double precioMensual) { this.precioMensual = precioMensual; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return "Propiedad{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", precioMensual=" + precioMensual +
                ", disponible=" + disponible +
                '}';
    }
}
