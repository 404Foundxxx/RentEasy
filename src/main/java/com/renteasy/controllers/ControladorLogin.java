package com.renteasy.controllers;

import com.renteasy.models.*;
import com.renteasy.dao.*;
import com.renteasy.utils.SesionUsuario;
import com.renteasy.utils.IconManager;
import com.renteasy.views.FrmAcercaDe;
import com.renteasy.views.FrmInicio;
import com.renteasy.views.FrmLogin;
import com.renteasy.views.FrmRegister;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

/**
 * Controlador para manejar la lógica de inicio de sesión en la aplicación RentEasy.
 *
 * @author gmart
 */
public class ControladorLogin {

    private FrmLogin frmLogin = new FrmLogin();
    private FrmInicio frmInicio = new FrmInicio();
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

        // Configurar iconos
        configurarIconos();

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

        // Label Olvidaste Contraseña Aqui
        this.frmLogin.lblOlvidasteContrasena.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // new ControladorRecuperarContrasena(frmRecuperar);
                // frmRecuperar.setVisible(true);
                // frmLogin.dispose()
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
        Usuario usuario = usuarioDAO.realizarLogin(email, contrasena);
        if (usuario != null) {
            // Login exitoso - Iniciar sesión del usuario
            SesionUsuario.getInstance().iniciarSesion(usuario);
            
            JOptionPane.showMessageDialog(frmLogin, 
                "¡Bienvenido " + usuario.getNombre() + "!", 
                "Login exitoso", 
                JOptionPane.INFORMATION_MESSAGE);
            
            new ControladorInicio(frmInicio);
            frmInicio.setVisible(true);
            frmLogin.dispose();
        } else {
            // Login fallido
            JOptionPane.showMessageDialog(frmLogin, "Credenciales inválidas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Configura los iconos SVG para los elementos de la interfaz de login.
     */
    private void configurarIconos() {
        // Icono para la ventana de login
        IconManager.setIcono(frmLogin, "login", 32);
        
        // Icono para el botón de iniciar sesión
        IconManager.setButtonIcon(frmLogin.btnIniciarSesion, "login", IconManager.SIZE_MEDIUM);
        
        // Iconos para labels de navegación
        if (frmLogin.lblRegistrateAqui != null) {
            IconManager.setLabelIcon(frmLogin.lblRegistrateAqui, "register", IconManager.SIZE_SMALL);
        }
        
        if (frmLogin.lblAcercaDe != null) {
            IconManager.setLabelIcon(frmLogin.lblAcercaDe, "help", IconManager.SIZE_SMALL);
        }
    }

}
