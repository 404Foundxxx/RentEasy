package com.renteasy.controllers.inquilino;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import com.renteasy.controllers.inicio.*;
import com.renteasy.utils.*;
import com.renteasy.views.inicio.*;
import com.renteasy.views.inquilino.*;

public class ControladorInquilino {

    private FrmInquilino frmInquilino;

    public ControladorInquilino() {
    }

    public ControladorInquilino(FrmInquilino fInquilino) {
        this.frmInquilino = fInquilino;
        configurarEventos();
    }

    private void configurarEventos() {
        // Botón Buscar Propiedades
        frmInquilino.btnBuscarPropiedades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirBuscarPropiedades();
            }
        });

        // Botón Mantenimiento de Propiedades
        frmInquilino.btnGestionSolicitudes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirGestionSolicitudes();
            }
        });

        // Botón Solicitudes
        frmInquilino.btnEditarPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirEditarPerfil();
            }
        });
        // Botón Cerrar Sesión
        frmInquilino.btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
    }

    private void abrirBuscarPropiedades() {
        try {
            FrmBuscarPropiedades frmBuscarPropiedades = new FrmBuscarPropiedades();
            new ControladorBuscarPropiedades(frmBuscarPropiedades);
            frmBuscarPropiedades.setVisible(true);
            frmInquilino.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmInquilino,
                    "Error al abrir el mantenimiento de usuarios: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirGestionSolicitudes() {
        try {
            FrmGestionDeSolicitudesUsuario frmGestionSolicitudes = new FrmGestionDeSolicitudesUsuario();
            new ControladorGestionDeSolicitudesUsuario(frmGestionSolicitudes);
            frmGestionSolicitudes.setVisible(true);
            frmInquilino.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmInquilino,
                    "Error al abrir la gestión de solicitudes: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirEditarPerfil() {
        try {
            FrmEditarPerfilUsuario frmEditarPerfilUsuario = new FrmEditarPerfilUsuario();
            new ControladorEditarPerfil(frmEditarPerfilUsuario);
            frmEditarPerfilUsuario.setVisible(true);
            frmInquilino.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmInquilino,
                    "Error al abrir el editar perfil: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cerrarSesion() {
        int respuesta = JOptionPane.showConfirmDialog(frmInquilino,
                "¿Está seguro que desea cerrar la sesión?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            SesionUsuario.getInstance().cerrarSesion();
            FrmLogin frmLogin = new FrmLogin();
            new ControladorLogin(frmLogin);
            frmLogin.setVisible(true);
            frmInquilino.dispose();
        }
    }
}
