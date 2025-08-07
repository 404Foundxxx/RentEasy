package com.renteasy.controllers;

import com.renteasy.views.FrmLogin;
import com.renteasy.views.FrmRegister;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author gmart
 */
public class ControladorRegister {

    private FrmLogin frmLogin = new FrmLogin();
    private FrmRegister frmRegister = new FrmRegister();

    public ControladorRegister() {

    }

    /**
     * Constructor con parámetros para inicializar los componentes del frame
     * Register
     *
     * @param fr
     */
    public ControladorRegister(FrmRegister fr) {
        this.frmRegister = fr;
        
        configurarPlaceholders();

        // Botón Crear Cuenta
        this.frmRegister.btnCrearCuenta.addActionListener(e -> {

        });

        // Label para volver a iniciar sesión
        this.frmRegister.lblIniciarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorLogin(frmLogin);
                frmLogin.setVisible(true);
                frmRegister.dispose();
            }
        });

    }

    /**
     * Método que se encarga de inicializar los placeholders de los campos del
     * formulario de registro.
     */
    private void configurarPlaceholders() {
        // Campo de Nombre
        frmRegister.txtNombre.putClientProperty("JTextField.placeholderText", "Nombre");

        // Campo de Apellido
        frmRegister.txtApellido.putClientProperty("JTextField.placeholderText", "Apellido");

        // Campo de Correo Electrónico
        frmRegister.txtCorreo.putClientProperty("JTextField.placeholderText", "Correo electrónico");

        // Campo de Contraseña
        frmRegister.txtContrasena.putClientProperty("JTextField.placeholderText", "Contraseña");
    }

}
