package com.renteasy.utils;

import com.renteasy.models.Usuario;

/**
 * Clase para manejar la sesión del usuario actual
 */
public class SesionUsuario {
    private static SesionUsuario instancia;
    private Usuario usuarioActual;
    
    private SesionUsuario() {
        // Constructor privado para patrón Singleton
    }
    
    /**
     * Obtener la instancia única de la sesión
     */
    public static SesionUsuario getInstance() {
        if (instancia == null) {
            instancia = new SesionUsuario();
        }
        return instancia;
    }
    
    /**
     * Iniciar sesión con un usuario
     */
    public void iniciarSesion(Usuario usuario) {
        this.usuarioActual = usuario;
    }
    
    /**
     * Cerrar la sesión actual
     */
    public void cerrarSesion() {
        this.usuarioActual = null;
    }
    
    /**
     * Obtener el usuario actual
     */
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
    /**
     * Verificar si hay una sesión activa
     */
    public boolean haySesionActiva() {
        return usuarioActual != null;
    }
    
    /**
     * Obtener el ID del usuario actual
     */
    public int getIdUsuarioActual() {
        if (usuarioActual != null) {
            return usuarioActual.getId();
        }
        return -1; // Valor por defecto si no hay sesión
    }
    
    /**
     * Obtener el nombre del usuario actual
     */
    public String getNombreUsuarioActual() {
        if (usuarioActual != null) {
            return usuarioActual.getNombre();
        }
        return "Usuario no identificado";
    }
    
    /**
     * Obtener el tipo de usuario actual
     */
    public Usuario.TipoUsuario getTipoUsuarioActual() {
        if (usuarioActual != null) {
            return Usuario.TipoUsuario.fromString(usuarioActual.getTipoUsuario());
        }
        return null;
    }
}
