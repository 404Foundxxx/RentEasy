package com.renteasy.controllers;

import com.renteasy.views.FrmLogin;
import com.renteasy.views.FrmRegister;
import com.renteasy.dao.*;
import com.renteasy.models.Usuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

/**
 *
 * @author gmart
 */
public class ControladorRegister {

    private FrmLogin frmLogin = new FrmLogin();
    private FrmRegister frmRegister = new FrmRegister();
    private usuarioDAO usuarioDAO = new usuarioDAO();

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
            String nombre = frmRegister.txtNombre.getText();
            String email = frmRegister.txtEmail.getText();
            String contrasena = frmRegister.txtContrasena.getText();
            String telefono = frmRegister.txtTelefono.getText();
            String tipoUsuario = frmRegister.cmbTipoUsuario.getSelectedItem().toString();

            registrar(nombre, email, contrasena, telefono, tipoUsuario);

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

        // Campo de Teléfono
        frmRegister.txtTelefono.putClientProperty("JTextField.placeholderText", "Teléfono");

        // Campo de Correo Electrónico
        frmRegister.txtEmail.putClientProperty("JTextField.placeholderText", "Correo electrónico");

        // Campo de Contraseña
        frmRegister.txtContrasena.putClientProperty("JTextField.placeholderText", "Contraseña");
    }

    /**
     * Método que se encarga de realizar el registro del usuario.
     *
     * @param nombre
     * @param email
     * @param contrasena
     * @param telefono
     * @param tipoUsuario
     */
    private void registrar(String nombre, String email, String contrasena, String telefono, String tipoUsuario) {
        if (usuarioDAO.registrarUsuario(nombre, email, contrasena, telefono, tipoUsuario)) {
            JOptionPane.showMessageDialog(frmRegister, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            new ControladorLogin(frmLogin);
            frmLogin.setVisible(true);
            frmRegister.dispose();
        } else {
            JOptionPane.showMessageDialog(frmRegister, "Error al registrar usuario", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
