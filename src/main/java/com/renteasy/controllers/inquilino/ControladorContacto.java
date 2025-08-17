package com.renteasy.controllers.inquilino;

import com.renteasy.views.inquilino.FrmBuscarPropiedades;
import com.renteasy.views.inquilino.FrmContacto;
import com.renteasy.views.inquilino.FrmInquilino;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class ControladorContacto {

    private FrmContacto frmContacto = new FrmContacto();

    public ControladorContacto() {

    }

    /**
     *
     * Constructor con parámetros para inicializar los componentes del frame
     * Contacto
     *
     * @param fc
     */
    public ControladorContacto(FrmContacto fc) {
        this.frmContacto = fc;

        configurarPlaceholdersContacto();
        configurarEventos();

    }

    /*
     * Configura los eventos de los componentes del formulario de contacto.
     */
    private void configurarEventos() {
        // Label Buscar Propiedades
        frmContacto.lblBuscarPropiedades.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirBuscarPropiedades();
            }
        });

        // Label Volver
        frmContacto.lblVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                volver();
            }
        });
    }

    private void abrirBuscarPropiedades() {
        try {
            FrmBuscarPropiedades frmBuscarPropiedades = new FrmBuscarPropiedades();
            new ControladorBuscarPropiedades(frmBuscarPropiedades);
            frmBuscarPropiedades.setVisible(true);
            frmContacto.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmContacto,
                    "Error al abrir el mantenimiento de usuarios: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volver() {
        try {
            FrmInquilino frmInquilino = new FrmInquilino();
            new ControladorInquilino(frmInquilino);
            frmInquilino.setVisible(true);
            frmContacto.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmContacto,
                    "Error al abrir el mantenimiento de usuarios: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método que configura los placeholders de los campos del formulario de
     * contacto.
     */
    private void configurarPlaceholdersContacto() {
        // Campo de Nombre
        frmContacto.txtNombre.putClientProperty("JTextField.placeholderText", "Tu Nombre");

        // Campo de Email
        frmContacto.txtEmail.putClientProperty("JTextField.placeholderText", "Tu Email");

        // Campo de Asunto
        frmContacto.txtAsunto.putClientProperty("JTextField.placeholderText", "Asunto");
    }

}

