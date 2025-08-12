package com.renteasy.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Modelo de datos para la entidad Usuario
 * Representa un usuario en el sistema RentEasy
 * 
 * @author gmart
 */
public class Usuario {
    
    // Atributos
    private int id;
    private String nombre;
    private String email;
    private String contrasena;  
    private String telefono;
    private String tipoUsuario;
    private Timestamp creadoEn;
    
    // Enumeración para tipos de usuario
    public enum TipoUsuario {
        PROPIETARIO("propietario"),
        INQUILINO("inquilino"),
        ADMIN("admin");
        
        private final String valor;
        
        TipoUsuario(String valor) {
            this.valor = valor;
        }
        
        public String getValor() {
            return valor;
        }
        
        public static TipoUsuario fromString(String tipo) {
            for (TipoUsuario t : TipoUsuario.values()) {
                if (t.valor.equalsIgnoreCase(tipo)) {
                    return t;
                }
            }
            return null;
        }
    }
    
    // Constructor vacío
    public Usuario() {
    }
    
    // Constructor sin ID (para nuevos registros)
    public Usuario(String nombre, String email, String contrasena, String telefono, String tipoUsuario) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
    }
    
    // Constructor completo (para registros existentes)
    public Usuario(int id, String nombre, String email, String contrasena, String telefono, 
                   String tipoUsuario, Timestamp creadoEn) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
        this.creadoEn = creadoEn;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    public Timestamp getCreadoEn() {
        return creadoEn;
    }
    
    public void setCreadoEn(Timestamp creadoEn) {
        this.creadoEn = creadoEn;
    }
    
    // Métodos de utilidad
    
    /**
     * Obtiene el tipo de usuario como enum
     */
    public TipoUsuario getTipoUsuarioEnum() {
        return TipoUsuario.fromString(this.tipoUsuario);
    }
    
    /**
     * Establece el tipo de usuario usando enum
     */
    public void setTipoUsuario(TipoUsuario tipo) {
        this.tipoUsuario = tipo.getValor();
    }
    
    /**
     * Verifica si el usuario es propietario
     */
    public boolean esPropietario() {
        return "propietario".equalsIgnoreCase(this.tipoUsuario);
    }
    
    /**
     * Verifica si el usuario es inquilino
     */
    public boolean esInquilino() {
        return "inquilino".equalsIgnoreCase(this.tipoUsuario);
    }
    
    /**
     * Verifica si el usuario es administrador
     */
    public boolean esAdmin() {
        return "admin".equalsIgnoreCase(this.tipoUsuario);
    }
    
    /**
     * Obtiene la fecha de creación como LocalDateTime
     */
    public LocalDateTime getFechaCreacion() {
        return creadoEn != null ? creadoEn.toLocalDateTime() : null;
    }
    
    /**
     * Valida si el email tiene un formato básico válido
     */
    public boolean emailValido() {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    /**
     * Valida si la contraseña cumple requisitos mínimos
     */
    public boolean contrasenaValida() {
        return contrasena != null && contrasena.length() >= 6;
    }
    
    /**
     * Obtiene el nombre completo formateado (para mostrar en UI)
     */
    public String getNombreFormateado() {
        return nombre != null ? nombre.trim() : "";
    }
    
    /**
     * Obtiene información resumida del usuario (sin contraseña)
     */
    public String getResumen() {
        return String.format("ID: %d, Nombre: %s, Email: %s, Tipo: %s", 
                           id, nombre, email, tipoUsuario);
    }
    
    // Métodos Object
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Usuario usuario = (Usuario) obj;
        return id == usuario.id && 
               Objects.equals(email, usuario.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                ", creadoEn=" + creadoEn +
                '}';
    }
    
    /**
     * Crea una copia del usuario sin la contraseña (para seguridad)
     */
    public Usuario sinContrasena() {
        Usuario copia = new Usuario();
        copia.id = this.id;
        copia.nombre = this.nombre;
        copia.email = this.email;
        copia.telefono = this.telefono;
        copia.tipoUsuario = this.tipoUsuario;
        copia.creadoEn = this.creadoEn;
        copia.contrasena = null; // No incluir contraseña
        return copia;
    }
    
    /**
     * Verifica si el usuario está completo (todos los campos requeridos)
     */
    public boolean estaCompleto() {
        return nombre != null && !nombre.trim().isEmpty() &&
               email != null && emailValido() &&
               contrasena != null && contrasenaValida() &&
               tipoUsuario != null && !tipoUsuario.trim().isEmpty();
    }
}
