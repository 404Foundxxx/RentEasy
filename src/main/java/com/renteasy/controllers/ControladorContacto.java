package com.renteasy.controllers;

import com.renteasy.views.FrmContacto;
import com.renteasy.views.FrmInicio;
import com.renteasy.views.FrmInicio;
import com.renteasy.views.FrmLogin;
import com.renteasy.views.FrmPublicarPropiedad;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author gmart
 */
public class ControladorContacto {

    private FrmContacto frmContacto = new FrmContacto();
    private FrmInicio frmInicio = new FrmInicio();
    private FrmPublicarPropiedad frmPublicarPropiedad = new FrmPublicarPropiedad();
    private FrmLogin frmLogin = new FrmLogin();

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

        // Label Inicio
        this.frmContacto.lblInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorInicio(frmInicio);
                frmInicio.setVisible(true);
                frmContacto.dispose();

            }
        });

        // Label Propidades
        this.frmContacto.lblPublicarPropiedad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorPublicarPropiedad(frmPublicarPropiedad);
                frmPublicarPropiedad.setVisible(true);
                frmContacto.dispose();

            }
        });

        // Label Cerrar Sesion
        this.frmContacto.lblCerrarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorLogin(frmLogin);
                frmLogin.setVisible(true);
                frmContacto.dispose();

            }
        });

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
