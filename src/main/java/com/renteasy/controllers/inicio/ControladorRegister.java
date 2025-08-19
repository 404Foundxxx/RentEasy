package com.renteasy.controllers.inicio;

import com.renteasy.dao.*;
import com.renteasy.models.Usuario;
import com.renteasy.views.inicio.FrmLogin;
import com.renteasy.views.inicio.FrmRegister;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class ControladorRegister {

    private FrmLogin frmLogin = new FrmLogin();
    private FrmRegister frmRegister = new FrmRegister();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

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

        // Llenar ComboBox de Tipo de Usuario solo con PROPIETARIO e INQUILINO
        this.frmRegister.cmbTipoUsuario.removeAllItems();
        this.frmRegister.cmbTipoUsuario.addItem(Usuario.TipoUsuario.PROPIETARIO.getValor());
        this.frmRegister.cmbTipoUsuario.addItem(Usuario.TipoUsuario.INQUILINO.getValor());

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
        // Validación adicional: No permitir registro de usuarios admin
        if ("admin".equalsIgnoreCase(tipoUsuario)) {
            JOptionPane.showMessageDialog(frmRegister, 
                    "No está permitido registrar usuarios con tipo 'admin'.", 
                    "Registro no permitido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (usuarioDAO.registrarUsuarioConIdBCrypt(nombre, email, contrasena, telefono, tipoUsuario) > 0) {
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

