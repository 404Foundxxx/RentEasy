package com.renteasy.controllers.inicio;

import com.renteasy.models.*;
import com.renteasy.controllers.admin.*;
import com.renteasy.controllers.inquilino.*;
import com.renteasy.controllers.propietario.*;
import com.renteasy.dao.*;
import com.renteasy.utils.*;
import com.renteasy.views.admin.FrmAdmin;
import com.renteasy.views.inicio.*;
import com.renteasy.views.propietario.*;
import com.renteasy.views.inquilino.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

/**
 * Controlador para manejar la lógica de inicio de sesión en la aplicación
 * RentEasy.
 *
 * 
 */
public class ControladorLogin {

    private FrmLogin frmLogin = new FrmLogin();
    private FrmRegister frmRegister = new FrmRegister();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

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
            String email = frmLogin.txtCorreo.getText();
            String contrasena = frmLogin.txtContrasena.getText();
            login(email, contrasena);
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

        // Label Acerca de
        this.frmLogin.lblAcercaDe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FrmAcercaDe frmAcercaDe = new FrmAcercaDe();
                frmAcercaDe.setVisible(true);
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

    /**
     * Método que se encarga de realizar el login del usuario.
     *
     * @param email
     * @param contrasena
     */
    private void login(String email, String contrasena) {
        Usuario usuario = usuarioDAO.validarLoginUsuario(email, contrasena);
        if (usuario != null) {
            // Login exitoso - Iniciar sesión del usuario
            SesionUsuario.getInstance().iniciarSesion(usuario);

            JOptionPane.showMessageDialog(frmLogin,
                    "¡Bienvenido " + usuario.getNombre() + "!",
                    "Login exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

            // Redirigir según el tipo de usuario
            String tipoUsuario = usuario.getTipoUsuario().toLowerCase();

            switch (tipoUsuario) {
                case "admin":
                    // Solo los administradores acceden al panel de administración
                    FrmAdmin frmAdmin = new FrmAdmin();
                    new ControladorAdmin(frmAdmin);
                    frmAdmin.setVisible(true);
                    break;

                case "propietario":
                    FrmPropietario frmPropietario = new FrmPropietario();
                    new ControladorPropietario(frmPropietario);
                    frmPropietario.setVisible(true);
                    break;

                case "inquilino":
                    FrmInquilino frminquilino = new FrmInquilino();
                    new ControladorInquilino(frminquilino);
                    frminquilino.setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(frmLogin, "Tipo de usuario no reconocido", "Error",
                            JOptionPane.ERROR_MESSAGE);
            }

            frmLogin.dispose();
        } else {
            // Login fallido
            JOptionPane.showMessageDialog(frmLogin, "Credenciales inválidas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

