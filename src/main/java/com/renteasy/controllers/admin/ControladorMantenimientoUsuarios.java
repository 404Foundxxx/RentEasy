package com.renteasy.controllers.admin;

import com.renteasy.views.admin.FrmAdmin;
import com.renteasy.views.admin.FrmMantenimientoUsuarios;
import com.renteasy.dao.*;
import com.renteasy.models.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Controlador para el mantenimiento de usuarios
 * Maneja todas las operaciones CRUD sobre la tabla usuarios
 * 
 * 
 */
public class ControladorMantenimientoUsuarios {

    private FrmMantenimientoUsuarios frmMantenimientoUsuarios;
    private UsuarioDAO usuarioDAO;
    private DefaultTableModel modeloTabla;
    private boolean modoEdicion = false;

    public ControladorMantenimientoUsuarios(FrmMantenimientoUsuarios frm) {
        this.frmMantenimientoUsuarios = frm;
        this.usuarioDAO = new UsuarioDAO();
        configurarTabla();
        configurarEventos();
        cargarUsuarios();
        limpiarFormulario();
        habilitarFormulario(false);
    }

    private void configurarTabla() {
        modeloTabla = (DefaultTableModel) frmMantenimientoUsuarios.tblUsuarios.getModel();

        // Configurar selección de tabla
        frmMantenimientoUsuarios.tblUsuarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDatosSeleccionados();
            }
        });
    }

    private void configurarEventos() {
        // Botón Nuevo
        frmMantenimientoUsuarios.btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoUsuario();
            }
        });

        // Botón Editar
        frmMantenimientoUsuarios.btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarUsuario();
            }
        });

        // Botón Eliminar
        frmMantenimientoUsuarios.btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });

        // Botón Guardar
        frmMantenimientoUsuarios.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarUsuario();
            }
        });

        // Botón Cancelar
        frmMantenimientoUsuarios.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarOperacion();
            }
        });

        // Botón Refrescar
        frmMantenimientoUsuarios.btnRefrescar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarUsuarios();
            }
        });

        // Botón Buscar
        frmMantenimientoUsuarios.btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuarios();
            }
        });

        // Botón Limpiar
        frmMantenimientoUsuarios.btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarBusqueda();
            }
        });

        // Botón Volver
        frmMantenimientoUsuarios.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverMenu();
            }
        });
    }

    private void cargarUsuarios() {
        modeloTabla.setRowCount(0);
        List<Usuario> usuarios = usuarioDAO.listar();

        for (Usuario usuario : usuarios) {
            Object[] fila = {
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getEmail(),
                    usuario.getTelefono(),
                    usuario.getTipoUsuario(),
                    usuario.getCreadoEn()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void cargarDatosSeleccionados() {
        int filaSeleccionada = frmMantenimientoUsuarios.tblUsuarios.getSelectedRow();
        if (filaSeleccionada >= 0) {
            frmMantenimientoUsuarios.txtId.setText(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
            frmMantenimientoUsuarios.txtNombre.setText(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
            frmMantenimientoUsuarios.txtEmail.setText(modeloTabla.getValueAt(filaSeleccionada, 2).toString());
            frmMantenimientoUsuarios.txtTelefono.setText(modeloTabla.getValueAt(filaSeleccionada, 3).toString());
            frmMantenimientoUsuarios.cmbTipoUsuario
                    .setSelectedItem(modeloTabla.getValueAt(filaSeleccionada, 4).toString());
            frmMantenimientoUsuarios.txtContrasena.setText(""); // No mostrar contraseña por seguridad
        }
    }

    private void nuevoUsuario() {
        limpiarFormulario();
        modoEdicion = false;
        habilitarFormulario(true);
        frmMantenimientoUsuarios.txtNombre.requestFocus();
    }

    private void editarUsuario() {
        int filaSeleccionada = frmMantenimientoUsuarios.tblUsuarios.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(frmMantenimientoUsuarios,
                    "Debe seleccionar un usuario para editar.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        modoEdicion = true;
        habilitarFormulario(true);
        frmMantenimientoUsuarios.txtEmail.setEditable(false); // No permitir cambiar email
        frmMantenimientoUsuarios.txtNombre.requestFocus();
    }

    private void eliminarUsuario() {
        int filaSeleccionada = frmMantenimientoUsuarios.tblUsuarios.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(frmMantenimientoUsuarios,
                    "Debe seleccionar un usuario para eliminar.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(frmMantenimientoUsuarios,
                "¿Está seguro que desea eliminar este usuario?\n" +
                        "Esta acción no se puede deshacer.",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                int id = Integer.parseInt(frmMantenimientoUsuarios.txtId.getText());
                if (usuarioDAO.eliminar(id)) {
                    JOptionPane.showMessageDialog(frmMantenimientoUsuarios,
                            "Usuario eliminado exitosamente.",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarUsuarios();
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(frmMantenimientoUsuarios,
                            "Error al eliminar el usuario.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frmMantenimientoUsuarios,
                        "Error al eliminar: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void guardarUsuario() {
        if (!validarDatos()) {
            return;
        }

        try {
            String nombre = frmMantenimientoUsuarios.txtNombre.getText().trim();
            String email = frmMantenimientoUsuarios.txtEmail.getText().trim();
            String contrasena = new String(frmMantenimientoUsuarios.txtContrasena.getPassword());
            String telefono = frmMantenimientoUsuarios.txtTelefono.getText().trim();
            String tipoUsuario = frmMantenimientoUsuarios.cmbTipoUsuario.getSelectedItem().toString();

            boolean exito = false;

            if (modoEdicion) {
                // Actualizar usuario existente
                int id = Integer.parseInt(frmMantenimientoUsuarios.txtId.getText());
                Usuario usuario = usuarioDAO.obtenerPorId(id);
                if (usuario != null) {
                    usuario.setNombre(nombre);
                    usuario.setTelefono(telefono);
                    usuario.setTipoUsuario(tipoUsuario);
                    
                    // Actualizar datos básicos primero
                    exito = usuarioDAO.actualizar(usuario);
                    
                    // Si hay cambio de contraseña, actualizarla con BCrypt
                    if (!contrasena.isEmpty() && exito) {
                        exito = usuarioDAO.actualizarContrasenaConBCrypt(id, contrasena);
                    }
                }
            } else {
                // Crear nuevo usuario
                if (contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(frmMantenimientoUsuarios,
                            "La contraseña es obligatoria para usuarios nuevos.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                exito = usuarioDAO.registrarUsuarioConIdBCrypt(nombre, email, contrasena, telefono, tipoUsuario) > 0;
            }

            if (exito) {
                JOptionPane.showMessageDialog(frmMantenimientoUsuarios,
                        "Usuario " + (modoEdicion ? "actualizado" : "creado") + " exitosamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarUsuarios();
                limpiarFormulario();
                habilitarFormulario(false);
            } else {
                JOptionPane.showMessageDialog(frmMantenimientoUsuarios,
                        "Error al " + (modoEdicion ? "actualizar" : "crear") + " el usuario.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmMantenimientoUsuarios,
                    "Error al guardar: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarDatos() {
        if (frmMantenimientoUsuarios.txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frmMantenimientoUsuarios,
                    "El nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            frmMantenimientoUsuarios.txtNombre.requestFocus();
            return false;
        }

        if (frmMantenimientoUsuarios.txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frmMantenimientoUsuarios,
                    "El email es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            frmMantenimientoUsuarios.txtEmail.requestFocus();
            return false;
        }

        // Validar formato de email básico
        String email = frmMantenimientoUsuarios.txtEmail.getText().trim();
        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(frmMantenimientoUsuarios,
                    "El formato del email no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            frmMantenimientoUsuarios.txtEmail.requestFocus();
            return false;
        }

        return true;
    }

    private void cancelarOperacion() {
        limpiarFormulario();
        habilitarFormulario(false);
        modoEdicion = false;
    }

    private void buscarUsuarios() {
        String textoBusqueda = frmMantenimientoUsuarios.txtBuscar.getText().trim();
        String tipoFiltro = frmMantenimientoUsuarios.cmbFiltroTipo.getSelectedItem().toString();

        modeloTabla.setRowCount(0);
        List<Usuario> usuarios;

        if (tipoFiltro.equals("Todos")) {
            usuarios = usuarioDAO.listar();
        } else {
            usuarios = usuarioDAO.listarPorTipo(tipoFiltro);
        }

        // Filtrar por texto de búsqueda si se proporciona
        for (Usuario usuario : usuarios) {
            if (textoBusqueda.isEmpty() ||
                    usuario.getNombre().toLowerCase().contains(textoBusqueda.toLowerCase()) ||
                    usuario.getEmail().toLowerCase().contains(textoBusqueda.toLowerCase())) {

                Object[] fila = {
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getEmail(),
                        usuario.getTelefono(),
                        usuario.getTipoUsuario(),
                        usuario.getCreadoEn()
                };
                modeloTabla.addRow(fila);
            }
        }
    }

    private void limpiarBusqueda() {
        frmMantenimientoUsuarios.txtBuscar.setText("");
        frmMantenimientoUsuarios.cmbFiltroTipo.setSelectedIndex(0);
        cargarUsuarios();
    }

    private void limpiarFormulario() {
        frmMantenimientoUsuarios.txtId.setText("");
        frmMantenimientoUsuarios.txtNombre.setText("");
        frmMantenimientoUsuarios.txtEmail.setText("");
        frmMantenimientoUsuarios.txtContrasena.setText("");
        frmMantenimientoUsuarios.txtTelefono.setText("");
        frmMantenimientoUsuarios.cmbTipoUsuario.setSelectedIndex(0);
    }

    private void habilitarFormulario(boolean habilitar) {
        frmMantenimientoUsuarios.txtNombre.setEditable(habilitar);
        frmMantenimientoUsuarios.txtEmail.setEditable(habilitar);
        frmMantenimientoUsuarios.txtContrasena.setEditable(habilitar);
        frmMantenimientoUsuarios.txtTelefono.setEditable(habilitar);
        frmMantenimientoUsuarios.cmbTipoUsuario.setEnabled(habilitar);
        frmMantenimientoUsuarios.btnGuardar.setEnabled(habilitar);
        frmMantenimientoUsuarios.btnCancelar.setEnabled(habilitar);
    }

    private void volverMenu() {
        FrmAdmin frmAdmin = new FrmAdmin();
        new ControladorAdmin(frmAdmin);
        frmAdmin.setVisible(true);
        frmMantenimientoUsuarios.dispose();
    }
}

