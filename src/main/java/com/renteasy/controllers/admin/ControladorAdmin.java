package com.renteasy.controllers.admin;

import com.renteasy.views.admin.*;
import com.renteasy.views.inquilino.*;
import com.renteasy.views.inicio.*;
import com.renteasy.controllers.inicio.*;
import com.renteasy.controllers.inquilino.*;
import com.renteasy.utils.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador para el panel de administración del sistema RentEasy Maneja la
 * navegación entre los diferentes módulos de mantenimiento Solo accesible para
 * usuarios administradores
 *
 * 
 */
public class ControladorAdmin {

    private FrmAdmin frmAdmin;

    public ControladorAdmin(FrmAdmin frm) {
        this.frmAdmin = frm;
        configurarEventos();
        configurarVentana();
    }

    private void configurarVentana() {
        // Mostrar información del usuario logueado
        if (SesionUsuario.getInstance().getUsuarioActual() != null) {
            String tipoUsuario = SesionUsuario.getInstance().getUsuarioActual().getTipoUsuario();
            String nombreUsuario = SesionUsuario.getInstance().getUsuarioActual().getNombre();
            frmAdmin.lblUsuarioLogueado.setText("Bienvenido: " + nombreUsuario + " (" + tipoUsuario + ")");

            // Verificar que el usuario sea admin
            if (!"admin".equalsIgnoreCase(tipoUsuario)) {
                JOptionPane.showMessageDialog(frmAdmin,
                        "Acceso denegado. Esta sección es solo para administradores.",
                        "Acceso denegado",
                        JOptionPane.ERROR_MESSAGE);

                // Redirigir al usuario al formulario apropiado según su rol
                redirigirSegunRol(tipoUsuario);
                return;
            }
        }
    }

    private void redirigirSegunRol(String tipoUsuario) {
        switch (tipoUsuario.toLowerCase()) {
            case "propietario":
                // Propietarios van al inicio donde pueden publicar propiedades
                FrmBuscarPropiedades frmInicioProp = new FrmBuscarPropiedades();
                new ControladorBuscarPropiedades(frmInicioProp);
                frmInicioProp.setVisible(true);
                break;

            case "inquilino":
                // Inquilinos van al inicio para buscar propiedades
                FrmBuscarPropiedades frmInicioInq = new FrmBuscarPropiedades();
                new ControladorBuscarPropiedades(frmInicioInq);
                frmInicioInq.setVisible(true);
                break;

            default:
                // En caso de rol no reconocido, volver al login
                FrmLogin frmLogin = new FrmLogin();
                new ControladorLogin(frmLogin);
                frmLogin.setVisible(true);
        }
        frmAdmin.dispose();
    }

    private void configurarEventos() {
        // Botón Mantenimiento de Usuarios
        frmAdmin.btnMantenimientoUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMantenimientoUsuarios();
            }
        });

        // Botón Mantenimiento de Propiedades
        frmAdmin.btnMantenimientoPropiedades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMantenimientoPropiedades();
            }
        });

        // Botón Cerrar Sesión
        frmAdmin.btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });

        // Botón Solicitudes
        frmAdmin.btnSolicitudes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirSolicitudes();
            }
        });

        // Botón Soporte
        frmAdmin.btnSoporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirSoporte();
            }
        });
    }

    private void abrirMantenimientoUsuarios() {
        try {
            FrmMantenimientoUsuarios frmMantenimientoUsuarios = new FrmMantenimientoUsuarios();
            new ControladorMantenimientoUsuarios(frmMantenimientoUsuarios);
            frmMantenimientoUsuarios.setVisible(true);
            frmAdmin.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmAdmin,
                    "Error al abrir el mantenimiento de usuarios: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirMantenimientoPropiedades() {
        try {
            // Los administradores van al mantenimiento completo de propiedades
            FrmMantenimientoPropiedades frmMantenimientoPropiedades = new FrmMantenimientoPropiedades();
            new ControladorMantenimientoPropiedades(frmMantenimientoPropiedades);
            frmMantenimientoPropiedades.setVisible(true);
            frmAdmin.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmAdmin,
                    "Error al abrir el mantenimiento de propiedades: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cerrarSesion() {
        int respuesta = JOptionPane.showConfirmDialog(frmAdmin,
                "¿Está seguro que desea cerrar la sesión?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            SesionUsuario.getInstance().cerrarSesion();
            FrmLogin frmLogin = new FrmLogin();
            new ControladorLogin(frmLogin);
            frmLogin.setVisible(true);
            frmAdmin.dispose();
        }
    }

    private void abrirSolicitudes() {
        try {
            FrmSolicitudes frmSolicitudes = new FrmSolicitudes();
            new ControladorSolicitudes(frmSolicitudes);
            frmSolicitudes.setVisible(true);
            frmAdmin.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmAdmin,
                    "Error al abrir la gestión de solicitudes: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirSoporte() {
        JOptionPane.showMessageDialog(frmAdmin,
                "Funcionalidad de soporte en desarrollo",
                "Información", JOptionPane.INFORMATION_MESSAGE);
        // try {
        // FrmSoporte frmSoporte = new FrmSoporte();
        // new ControladorSoporte(frmSoporte);
        // frmSoporte.setVisible(true);
        // frmAdmin.dispose();
        // } catch (Exception e) {
        // JOptionPane.showMessageDialog(frmAdmin,
        // "Error al abrir el centro de soporte: " + e.getMessage(),
        // "Error", JOptionPane.ERROR_MESSAGE);
        // }
    }
}
