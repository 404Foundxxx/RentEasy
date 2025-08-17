package com.renteasy.controllers.propietario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import com.renteasy.controllers.inicio.*;
import com.renteasy.utils.*;
import com.renteasy.views.inicio.*;
import com.renteasy.views.propietario.*;

/**
 * Controlador principal para el módulo de propietario
 * Maneja la navegación y eventos del formulario principal de propietario
 * 
 * 
 */
public class ControladorPropietario {

    private FrmPropietario frmPropietario;

    public ControladorPropietario() {
    }

    public ControladorPropietario(FrmPropietario frmPropietario) {
        this.frmPropietario = frmPropietario;
        configurarEventos();
        configurarFormulario();
    }

    /**
     * Configura los eventos de los botones del formulario
     */
    private void configurarEventos() {
        // Botón Gestión de Propiedades (Publicar Propiedades)
        frmPropietario.btnPublicarPropiedad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirGestionPropiedades();
            }
        });

        // Botón Gestión de Solicitudes
        frmPropietario.btnSolicitudes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirGestionSolicitudes();
            }
        });
        frmPropietario.btnEditarPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirEditarPerfil();
            }
        });

        // Botón Cerrar Sesión
        frmPropietario.btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
    }

    /**
     * Configura los elementos iniciales del formulario
     */
    private void configurarFormulario() {
        try {
            // Mostrar información del usuario logueado
            SesionUsuario sesion = SesionUsuario.getInstance();
            if (sesion.haySesionActiva()) {
                frmPropietario.lblUsuarioLogueado.setText(
                        "Bienvenido: " + sesion.getUsuarioActual().getNombre());
            }
        } catch (Exception e) {
            mostrarError("Error al configurar el formulario: " + e.getMessage());
        }
    }

    /**
     * Abre el formulario de gestión de propiedades
     */
    private void abrirGestionPropiedades() {
        try {
            FrmPublicarPropiedad frmPublicarPropiedad = new FrmPublicarPropiedad();
            new ControladorPublicarPropiedad(frmPublicarPropiedad);
            frmPublicarPropiedad.setVisible(true);
            frmPropietario.dispose();
        } catch (Exception e) {
            mostrarError("Error al abrir la gestión de propiedades: " + e.getMessage());
        }
    }

    /**
     * Abre el formulario de gestión de solicitudes
     */
    private void abrirGestionSolicitudes() {
        try {
            FrmGestionDeSolicitudesPropietario frmGestionSolicitudes = new FrmGestionDeSolicitudesPropietario();
            new ControladorGestionSolicitudesPropietario(frmGestionSolicitudes);
            frmGestionSolicitudes.setVisible(true);
            frmPropietario.dispose();
        } catch (Exception e) {
            mostrarError("Error al abrir la gestión de solicitudes: " + e.getMessage());
        }
    }

    private void abrirEditarPerfil() {
        try {
            FrmEditarPerfilPropietario frmEditarPerfilPropietario = new FrmEditarPerfilPropietario();
            new ControladorEditarPerfil(frmEditarPerfilPropietario);
            frmEditarPerfilPropietario.setVisible(true);
            frmPropietario.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmPropietario,
                    "Error al abrir el editar perfil: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Cierra la sesión actual y regresa al login
     */
    private void cerrarSesion() {
        try {
            int confirmacion = JOptionPane.showConfirmDialog(
                    frmPropietario,
                    "¿Está seguro que desea cerrar sesión?",
                    "Confirmar cierre de sesión",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                // Limpiar la sesión
                SesionUsuario.getInstance().cerrarSesion();

                // Abrir formulario de login
                FrmLogin frmLogin = new FrmLogin();
                new ControladorLogin(frmLogin);
                frmLogin.setVisible(true);

                // Cerrar formulario actual
                frmPropietario.dispose();
            }
        } catch (Exception e) {
            mostrarError("Error al cerrar sesión: " + e.getMessage());
        }
    }

    /**
     * Muestra un mensaje de error
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                frmPropietario,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
