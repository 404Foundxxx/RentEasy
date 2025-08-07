package com.renteasy.controllers;

import com.renteasy.views.FrmInicio;
import com.renteasy.views.FrmLogin;
import com.renteasy.views.FrmRegister;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author gmart
 */
public class ControladorLogin {

    private FrmLogin frmLogin = new FrmLogin();
    private FrmInicio frmInicio = new FrmInicio();
    private FrmRegister frmRegister = new FrmRegister();

    // Construtor vacio
    public ControladorLogin() {

    }

    /**
     * Constructor con parámetros para inicializar los componentes del frame
     * Login
     *
     * @param fl
     */
    public ControladorLogin(FrmLogin fl) {
        this.frmLogin = fl;

        configurarPlaceholdersLogin();

        // Boton Iniciar Sesion
        this.frmLogin.btnIniciarSesion.addActionListener(e -> {
            new ControladorInicio(frmInicio);
            frmInicio.setVisible(true);
            frmLogin.dispose();
        });

        // Label Registrate Aqui
        this.frmLogin.lblRegistrateAqui.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorRegister(frmRegister);
                frmRegister.setVisible(true);
                frmLogin.dispose();
            }
        });

        // Label Olvidaste Contraseña Aqui
        this.frmLogin.lblOlvidasteContrasena.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                new ControladorRecuperarContrasena(frmRecuperar);
//                frmRecuperar.setVisible(true);
//                frmLogin.dispose()
            }
        });

    }

    /**
     * Método que se encarga de inicializar los placeholders de los campos del
     * formulario de inicio de sesión.
     */
    private void configurarPlaceholdersLogin() {
        // Campo de Correo Electrónico
        frmLogin.txtCorreo.putClientProperty("JTextField.placeholderText", "Correo electrónico");

        // Campo de Contraseña
        frmLogin.txtContrasena.putClientProperty("JTextField.placeholderText", "Contraseña");
    }

}
