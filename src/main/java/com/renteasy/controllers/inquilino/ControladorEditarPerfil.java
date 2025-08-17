package com.renteasy.controllers.inquilino;

import com.renteasy.dao.UsuarioDAO;
import com.renteasy.models.Usuario;
import com.renteasy.utils.SesionUsuario;
import com.renteasy.views.inquilino.FrmEditarPerfilUsuario;
import com.renteasy.views.inquilino.FrmInquilino;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorEditarPerfil {

    private static final Logger LOGGER = Logger.getLogger(ControladorEditarPerfil.class.getName());

    private FrmEditarPerfilUsuario frmEditarPerfilUsuario;
    private UsuarioDAO usuarioDAO;
    private Usuario usuarioActual;

    public ControladorEditarPerfil(FrmEditarPerfilUsuario frmEditarPerfilUsuario) {
        this.frmEditarPerfilUsuario = frmEditarPerfilUsuario;
        this.usuarioDAO = new UsuarioDAO();
        this.usuarioActual = SesionUsuario.getInstance().getUsuarioActual();

        configurarEventos();
        cargarDatosUsuario();
    }

    private void configurarEventos() {
        // Evento para el botón Guardar
        frmEditarPerfilUsuario.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });

        // Evento para el botón Cancelar
        frmEditarPerfilUsuario.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarCambios();
            }
        });

        // Evento para el botón Volver
        frmEditarPerfilUsuario.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });
    }

    /**
     * Carga los datos del usuario actual en los campos del formulario
     */
    private void cargarDatosUsuario() {
        if (usuarioActual != null) {
            frmEditarPerfilUsuario.txtNombre.setText(usuarioActual.getNombre());
            frmEditarPerfilUsuario.txtEmail.setText(usuarioActual.getEmail());
            frmEditarPerfilUsuario.txtTelefono.setText(usuarioActual.getTelefono());
            // La contraseña se deja vacía por seguridad
            frmEditarPerfilUsuario.txtContrasena.setText("");
        } else {
            mostrarError("No hay usuario en sesión");
            frmEditarPerfilUsuario.dispose();
        }
    }

    /**
     * Guarda los cambios realizados en el perfil del usuario
     */
    private void guardarCambios() {
        try {
            // Validar campos obligatorios
            if (!validarCampos()) {
                return;
            }

            // Obtener datos del formulario
            String nombre = frmEditarPerfilUsuario.txtNombre.getText().trim();
            String email = frmEditarPerfilUsuario.txtEmail.getText().trim();
            String telefono = frmEditarPerfilUsuario.txtTelefono.getText().trim();
            String contrasena = new String(frmEditarPerfilUsuario.txtContrasena.getPassword());

            // Verificar si el email ya existe en otro usuario
            if (!email.equals(usuarioActual.getEmail()) && usuarioDAO.existeEmail(email)) {
                mostrarError("El email ya está registrado en el sistema");
                return;
            }

            // Crear usuario actualizado
            Usuario usuarioActualizado = new Usuario();
            usuarioActualizado.setId(usuarioActual.getId());
            usuarioActualizado.setNombre(nombre);
            usuarioActualizado.setEmail(email);
            usuarioActualizado.setTelefono(telefono);
            usuarioActualizado.setTipoUsuario(usuarioActual.getTipoUsuario());

            // Mantener la contraseña actual (no la cambiamos aquí)
            usuarioActualizado.setContrasena(usuarioActual.getContrasena());

            // Actualizar datos básicos en la base de datos
            boolean exito = usuarioDAO.actualizar(usuarioActualizado);
            
            // Si se proporcionó nueva contraseña, actualizarla con BCrypt
            if (!contrasena.isEmpty() && exito) {
                exito = usuarioDAO.actualizarContrasenaConBCrypt(usuarioActual.getId(), contrasena);
            }

            // Actualizar en la base de datos
            if (exito) {
                // Actualizar la sesión con los nuevos datos
                SesionUsuario.getInstance().iniciarSesion(usuarioActualizado);
                this.usuarioActual = usuarioActualizado;

                mostrarExito("Perfil actualizado correctamente");
                LOGGER.info("Perfil actualizado para usuario: " + email);

                // Recargar los datos en el formulario
                cargarDatosUsuario();
            } else {
                mostrarError("Error al actualizar el perfil. Inténtelo nuevamente.");
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al guardar cambios del perfil", e);
            mostrarError("Error inesperado al guardar los cambios");
        }
    }

    /**
     * Cancela los cambios y recarga los datos originales
     */
    private void cancelarCambios() {
        int opcion = JOptionPane.showConfirmDialog(
                frmEditarPerfilUsuario,
                "¿Está seguro de que desea cancelar los cambios?",
                "Confirmar cancelación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (opcion == JOptionPane.YES_OPTION) {
            cargarDatosUsuario();
            mostrarInfo("Cambios cancelados");
        }
    }

    /**
     * Cierra el formulario y vuelve a la ventana anterior
     */
    private void volver() {
        int opcion = JOptionPane.showConfirmDialog(
                frmEditarPerfilUsuario,
                "¿Está seguro de que desea salir?\nLos cambios no guardados se perderán.",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (opcion == JOptionPane.YES_OPTION) {
            FrmInquilino frmInquilino = new FrmInquilino();
            new ControladorInquilino(frmInquilino);
            frmInquilino.setVisible(true);
            frmEditarPerfilUsuario.dispose();
        }
    }

    /**
     * Valida que todos los campos obligatorios estén completos y sean válidos
     */
    private boolean validarCampos() {
        String nombre = frmEditarPerfilUsuario.txtNombre.getText().trim();
        String email = frmEditarPerfilUsuario.txtEmail.getText().trim();
        String contrasena = new String(frmEditarPerfilUsuario.txtContrasena.getPassword());

        // Validar nombre
        if (nombre.isEmpty()) {
            mostrarError("El nombre es obligatorio");
            frmEditarPerfilUsuario.txtNombre.requestFocus();
            return false;
        }

        if (nombre.length() < 2) {
            mostrarError("El nombre debe tener al menos 2 caracteres");
            frmEditarPerfilUsuario.txtNombre.requestFocus();
            return false;
        }

        // Validar email
        if (email.isEmpty()) {
            mostrarError("El email es obligatorio");
            frmEditarPerfilUsuario.txtEmail.requestFocus();
            return false;
        }

        if (!validarFormatoEmail(email)) {
            mostrarError("El formato del email no es válido");
            frmEditarPerfilUsuario.txtEmail.requestFocus();
            return false;
        }

        // Validar contraseña solo si se proporcionó una nueva
        if (!contrasena.isEmpty() && contrasena.length() < 6) {
            mostrarError("La contraseña debe tener al menos 6 caracteres");
            frmEditarPerfilUsuario.txtContrasena.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * Valida el formato del email usando expresión regular
     */
    private boolean validarFormatoEmail(String email) {
        String patron = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(patron);
    }

    /**
     * Muestra un mensaje de error
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                frmEditarPerfilUsuario,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje de éxito
     */
    private void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(
                frmEditarPerfilUsuario,
                mensaje,
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje informativo
     */
    private void mostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(
                frmEditarPerfilUsuario,
                mensaje,
                "Información",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Obtiene el usuario actual de la sesión
     */
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    /**
     * Obtiene la vista del formulario
     */
    public FrmEditarPerfilUsuario getVista() {
        return frmEditarPerfilUsuario;
    }
}

