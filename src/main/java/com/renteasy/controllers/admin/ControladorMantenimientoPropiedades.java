package com.renteasy.controllers.admin;

import com.renteasy.views.admin.FrmAdmin;
import com.renteasy.views.admin.FrmMantenimientoPropiedades;
import com.renteasy.dao.*;
import com.renteasy.models.*;
import com.renteasy.utils.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.util.List;

/**
 * Controlador para el mantenimiento de propiedades Maneja todas las operaciones
 * CRUD sobre la tabla propiedades
 *
 * 
 */
public class ControladorMantenimientoPropiedades {

    private FrmMantenimientoPropiedades frmMantenimientoPropiedades;
    private PropiedadDAO propiedadDAO;
    private UsuarioDAO usuarioDAO;
    private DefaultTableModel modeloTabla;
    private boolean modoEdicion = false;

    public ControladorMantenimientoPropiedades() {

    }

    public ControladorMantenimientoPropiedades(FrmMantenimientoPropiedades frm) {
        this.frmMantenimientoPropiedades = frm;
        this.propiedadDAO = new PropiedadDAO();
        this.usuarioDAO = new UsuarioDAO();
        configurarTabla();
        configurarEventos();
        cargarPropietarios();
        cargarCiudades();
        cargarPropiedades();
        limpiarFormulario();
        habilitarFormulario(false);
    }

    private void configurarTabla() {
        modeloTabla = (DefaultTableModel) frmMantenimientoPropiedades.tblPropiedades.getModel();

        // Configurar selección de tabla
        frmMantenimientoPropiedades.tblPropiedades.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDatosSeleccionados();
            }
        });
    }

    private void configurarEventos() {
        // Botón Nuevo
        frmMantenimientoPropiedades.btnNuevo.addActionListener(e -> nuevaPropiedad());

        // Botón Editar
        frmMantenimientoPropiedades.btnEditar.addActionListener(e -> editarPropiedad());

        // Botón Eliminar
        frmMantenimientoPropiedades.btnEliminar.addActionListener(e -> eliminarPropiedad());

        // Botón Guardar
        frmMantenimientoPropiedades.btnGuardar.addActionListener(e -> guardarPropiedad());

        // Botón Cancelar
        frmMantenimientoPropiedades.btnCancelar.addActionListener(e -> cancelarOperacion());

        // Botón Refrescar
        frmMantenimientoPropiedades.btnRefrescar.addActionListener(e -> cargarPropiedades());

        // Botón Buscar
        frmMantenimientoPropiedades.btnBuscar.addActionListener(e -> buscarPropiedades());

        // Botón Limpiar
        frmMantenimientoPropiedades.btnLimpiar.addActionListener(e -> limpiarBusqueda());

        // Botón Volver
        frmMantenimientoPropiedades.btnVolver.addActionListener(e -> volverMenu());
    }

    private void cargarPropietarios() {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        List<Usuario> propietarios = usuarioDAO.listarPorTipo("propietario");

        for (Usuario propietario : propietarios) {
            modelo.addElement(propietario.getId() + " - " + propietario.getNombre());
        }

        frmMantenimientoPropiedades.cmbPropietario.setModel(modelo);
    }

    private void cargarCiudades() {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Todas las ciudades");

        // Obtener ciudades únicas de las propiedades existentes
        List<Propiedad> propiedades = propiedadDAO.listar();
        java.util.Set<String> ciudadesUnicas = new java.util.HashSet<>();

        for (Propiedad propiedad : propiedades) {
            if (propiedad.getCiudad() != null && !propiedad.getCiudad().trim().isEmpty()) {
                ciudadesUnicas.add(propiedad.getCiudad());
            }
        }

        for (String ciudad : ciudadesUnicas) {
            modelo.addElement(ciudad);
        }

        frmMantenimientoPropiedades.cmbFiltroCiudad.setModel(modelo);
    }

    private void cargarPropiedades() {
        modeloTabla.setRowCount(0);
        String tipoUsuario = SesionUsuario.getInstance().getUsuarioActual().getTipoUsuario().toLowerCase();
        List<Propiedad> propiedades;

        switch (tipoUsuario) {
            case "admin":
                // Admin ve todas las propiedades
                propiedades = propiedadDAO.listar();
                break;

            default:
                propiedades = new java.util.ArrayList<>();
        }

        for (Propiedad propiedad : propiedades) {
            // Obtener nombre del propietario
            Usuario propietario = usuarioDAO.obtenerPorId(propiedad.getPropietarioId());
            String nombrePropietario = propietario != null ? propietario.getNombre() : "N/A";

            Object[] fila = {
                    propiedad.getId(),
                    propiedad.getTitulo(),
                    propiedad.getCiudad(),
                    "$" + propiedad.getPrecioMensual(),
                    propiedad.getHabitaciones(),
                    propiedad.getBanos(),
                    propiedad.getEstado(),
                    nombrePropietario
            };
            modeloTabla.addRow(fila);
        }
    }

    private void cargarDatosSeleccionados() {
        int filaSeleccionada = frmMantenimientoPropiedades.tblPropiedades.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int id = Integer.parseInt(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
            Propiedad propiedad = propiedadDAO.obtenerPorId(id);

            if (propiedad != null) {
                frmMantenimientoPropiedades.txtId.setText(String.valueOf(propiedad.getId()));
                frmMantenimientoPropiedades.txtTituloPropiedad.setText(propiedad.getTitulo());
                frmMantenimientoPropiedades.txtDescripcion.setText(propiedad.getDescripcion());
                frmMantenimientoPropiedades.txtDireccion.setText(propiedad.getDireccion());
                frmMantenimientoPropiedades.txtCiudad.setText(propiedad.getCiudad());
                frmMantenimientoPropiedades.txtProvincia.setText(propiedad.getProvincia());
                frmMantenimientoPropiedades.txtPrecio.setText(propiedad.getPrecioMensual().toString());
                frmMantenimientoPropiedades.txtHabitaciones.setText(String.valueOf(propiedad.getHabitaciones()));
                frmMantenimientoPropiedades.txtBanos.setText(String.valueOf(propiedad.getBanos()));
                frmMantenimientoPropiedades.txtArea.setText(String.valueOf(propiedad.getAreaM2()));
                frmMantenimientoPropiedades.cmbEstado.setSelectedItem(propiedad.getEstado());

                // Seleccionar propietario
                for (int i = 0; i < frmMantenimientoPropiedades.cmbPropietario.getItemCount(); i++) {
                    String item = frmMantenimientoPropiedades.cmbPropietario.getItemAt(i);
                    if (item.startsWith(propiedad.getPropietarioId() + " - ")) {
                        frmMantenimientoPropiedades.cmbPropietario.setSelectedIndex(i);
                        break;
                    }
                }
            }
        }
    }

    private void nuevaPropiedad() {
        limpiarFormulario();
        modoEdicion = false;
        habilitarFormulario(true);
        frmMantenimientoPropiedades.txtTituloPropiedad.requestFocus();
    }

    private void editarPropiedad() {
        int filaSeleccionada = frmMantenimientoPropiedades.tblPropiedades.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(frmMantenimientoPropiedades,
                    "Debe seleccionar una propiedad para editar.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        modoEdicion = true;
        habilitarFormulario(true);
        frmMantenimientoPropiedades.txtTituloPropiedad.requestFocus();
    }

    private void eliminarPropiedad() {
        int filaSeleccionada = frmMantenimientoPropiedades.tblPropiedades.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(frmMantenimientoPropiedades,
                    "Debe seleccionar una propiedad para eliminar.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(frmMantenimientoPropiedades,
                "¿Está seguro que desea eliminar esta propiedad?\n"
                        + "Esta acción no se puede deshacer.",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                int id = Integer.parseInt(frmMantenimientoPropiedades.txtId.getText());
                if (propiedadDAO.eliminar(id)) {
                    JOptionPane.showMessageDialog(frmMantenimientoPropiedades,
                            "Propiedad eliminada exitosamente.",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarPropiedades();
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(frmMantenimientoPropiedades,
                            "Error al eliminar la propiedad.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frmMantenimientoPropiedades,
                        "Error al eliminar: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void guardarPropiedad() {
        if (!validarDatos()) {
            return;
        }

        try {
            // Obtener datos del formulario
            String titulo = frmMantenimientoPropiedades.txtTituloPropiedad.getText().trim();
            String descripcion = frmMantenimientoPropiedades.txtDescripcion.getText().trim();
            String direccion = frmMantenimientoPropiedades.txtDireccion.getText().trim();
            String ciudad = frmMantenimientoPropiedades.txtCiudad.getText().trim();
            String provincia = frmMantenimientoPropiedades.txtProvincia.getText().trim();
            BigDecimal precio = new BigDecimal(frmMantenimientoPropiedades.txtPrecio.getText().trim());
            int habitaciones = Integer.parseInt(frmMantenimientoPropiedades.txtHabitaciones.getText().trim());
            int banos = Integer.parseInt(frmMantenimientoPropiedades.txtBanos.getText().trim());
            int area = Integer.parseInt(frmMantenimientoPropiedades.txtArea.getText().trim());
            String estado = frmMantenimientoPropiedades.cmbEstado.getSelectedItem().toString();

            // Obtener ID del propietario
            int propietarioId;
            String tipoUsuario = SesionUsuario.getInstance().getUsuarioActual().getTipoUsuario().toLowerCase();

            if (tipoUsuario.equals("admin")) {
                // Admin puede seleccionar cualquier propietario
                String propietarioItem = frmMantenimientoPropiedades.cmbPropietario.getSelectedItem().toString();
                propietarioId = Integer.parseInt(propietarioItem.split(" - ")[0]);
            } else {
                // Propietario solo puede crear propiedades para sí mismo
                propietarioId = SesionUsuario.getInstance().getUsuarioActual().getId();
            }

            boolean exito = false;

            if (modoEdicion) {
                // Actualizar propiedad existente
                int id = Integer.parseInt(frmMantenimientoPropiedades.txtId.getText());
                Propiedad propiedad = propiedadDAO.obtenerPorId(id);
                if (propiedad != null) {
                    propiedad.setTitulo(titulo);
                    propiedad.setDescripcion(descripcion);
                    propiedad.setDireccion(direccion);
                    propiedad.setCiudad(ciudad);
                    propiedad.setProvincia(provincia);
                    propiedad.setPrecioMensual(precio);
                    propiedad.setHabitaciones(habitaciones);
                    propiedad.setBanos(banos);
                    propiedad.setAreaM2(area);
                    propiedad.setEstado(estado);
                    propiedad.setPropietarioId(propietarioId);
                    exito = propiedadDAO.actualizar(propiedad);
                }
            } else {
                // Crear nueva propiedad
                Propiedad nuevaPropiedad = new Propiedad();
                nuevaPropiedad.setPropietarioId(propietarioId);
                nuevaPropiedad.setTitulo(titulo);
                nuevaPropiedad.setDescripcion(descripcion);
                nuevaPropiedad.setDireccion(direccion);
                nuevaPropiedad.setCiudad(ciudad);
                nuevaPropiedad.setProvincia(provincia);
                nuevaPropiedad.setPrecioMensual(precio);
                nuevaPropiedad.setHabitaciones(habitaciones);
                nuevaPropiedad.setBanos(banos);
                nuevaPropiedad.setAreaM2(area);
                nuevaPropiedad.setEstado(estado);

                int idGenerado = propiedadDAO.registrarPropiedad(nuevaPropiedad);
                exito = idGenerado > 0;
            }

            if (exito) {
                JOptionPane.showMessageDialog(frmMantenimientoPropiedades,
                        "Propiedad " + (modoEdicion ? "actualizada" : "creada") + " exitosamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarPropiedades();
                cargarCiudades(); // Recargar ciudades por si se agregó una nueva
                limpiarFormulario();
                habilitarFormulario(false);
            } else {
                JOptionPane.showMessageDialog(frmMantenimientoPropiedades,
                        "Error al " + (modoEdicion ? "actualizar" : "crear") + " la propiedad.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frmMantenimientoPropiedades,
                    "Por favor, ingrese valores numéricos válidos en los campos correspondientes.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmMantenimientoPropiedades,
                    "Error al guardar: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarDatos() {
        if (frmMantenimientoPropiedades.txtTituloPropiedad.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frmMantenimientoPropiedades,
                    "El título es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            frmMantenimientoPropiedades.txtTituloPropiedad.requestFocus();
            return false;
        }

        if (frmMantenimientoPropiedades.txtDireccion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frmMantenimientoPropiedades,
                    "La dirección es obligatoria.", "Error", JOptionPane.ERROR_MESSAGE);
            frmMantenimientoPropiedades.txtDireccion.requestFocus();
            return false;
        }

        if (frmMantenimientoPropiedades.txtPrecio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frmMantenimientoPropiedades,
                    "El precio es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            frmMantenimientoPropiedades.txtPrecio.requestFocus();
            return false;
        }

        if (frmMantenimientoPropiedades.cmbPropietario.getSelectedIndex() < 0
                && SesionUsuario.getInstance().getUsuarioActual().getTipoUsuario().equalsIgnoreCase("admin")) {
            JOptionPane.showMessageDialog(frmMantenimientoPropiedades,
                    "Debe seleccionar un propietario.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validar que precio sea un número válido
        try {
            new BigDecimal(frmMantenimientoPropiedades.txtPrecio.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frmMantenimientoPropiedades,
                    "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            frmMantenimientoPropiedades.txtPrecio.requestFocus();
            return false;
        }

        return true;
    }

    private void cancelarOperacion() {
        limpiarFormulario();
        habilitarFormulario(false);
        modoEdicion = false;
    }

    private void buscarPropiedades() {
        String estadoFiltro = frmMantenimientoPropiedades.cmbFiltroEstado.getSelectedItem().toString();
        String ciudadFiltro = frmMantenimientoPropiedades.cmbFiltroCiudad.getSelectedItem().toString();

        modeloTabla.setRowCount(0);
        List<Propiedad> propiedades;

        if (estadoFiltro.equals("Todos")) {
            propiedades = propiedadDAO.listar();
        } else {
            propiedades = propiedadDAO.listarPorEstado(estadoFiltro);
        }

        // Aplicar filtros adicionales
        for (Propiedad propiedad : propiedades) {
            boolean cumpleFiltros = true;

            // Filtro por ciudad
            if (!ciudadFiltro.equals("Todas las ciudades")) {
                if (!ciudadFiltro.equals(propiedad.getCiudad())) {
                    cumpleFiltros = false;
                }
            }

            if (cumpleFiltros) {
                // Obtener nombre del propietario
                Usuario propietario = usuarioDAO.obtenerPorId(propiedad.getPropietarioId());
                String nombrePropietario = propietario != null ? propietario.getNombre() : "N/A";

                Object[] fila = {
                        propiedad.getId(),
                        propiedad.getTitulo(),
                        propiedad.getCiudad(),
                        "$" + propiedad.getPrecioMensual(),
                        propiedad.getHabitaciones(),
                        propiedad.getBanos(),
                        propiedad.getEstado(),
                        nombrePropietario
                };
                modeloTabla.addRow(fila);
            }
        }
    }

    private void limpiarBusqueda() {
        frmMantenimientoPropiedades.cmbFiltroEstado.setSelectedIndex(0);
        frmMantenimientoPropiedades.cmbFiltroCiudad.setSelectedIndex(0);
        cargarPropiedades();
    }

    private void limpiarFormulario() {
        frmMantenimientoPropiedades.txtId.setText("");
        frmMantenimientoPropiedades.txtTituloPropiedad.setText("");
        frmMantenimientoPropiedades.txtDescripcion.setText("");
        frmMantenimientoPropiedades.txtDireccion.setText("");
        frmMantenimientoPropiedades.txtCiudad.setText("");
        frmMantenimientoPropiedades.txtProvincia.setText("");
        frmMantenimientoPropiedades.txtPrecio.setText("");
        frmMantenimientoPropiedades.txtHabitaciones.setText("");
        frmMantenimientoPropiedades.txtBanos.setText("");
        frmMantenimientoPropiedades.txtArea.setText("");
        frmMantenimientoPropiedades.cmbEstado.setSelectedIndex(0);
        if (frmMantenimientoPropiedades.cmbPropietario.getItemCount() > 0) {
            frmMantenimientoPropiedades.cmbPropietario.setSelectedIndex(0);
        }
    }

    private void habilitarFormulario(boolean habilitar) {
        frmMantenimientoPropiedades.txtTituloPropiedad.setEditable(habilitar);
        frmMantenimientoPropiedades.txtDescripcion.setEditable(habilitar);
        frmMantenimientoPropiedades.txtDireccion.setEditable(habilitar);
        frmMantenimientoPropiedades.txtCiudad.setEditable(habilitar);
        frmMantenimientoPropiedades.txtProvincia.setEditable(habilitar);
        frmMantenimientoPropiedades.txtPrecio.setEditable(habilitar);
        frmMantenimientoPropiedades.txtHabitaciones.setEditable(habilitar);
        frmMantenimientoPropiedades.txtBanos.setEditable(habilitar);
        frmMantenimientoPropiedades.txtArea.setEditable(habilitar);
        frmMantenimientoPropiedades.cmbEstado.setEnabled(habilitar);
        frmMantenimientoPropiedades.cmbPropietario.setEnabled(habilitar);
        frmMantenimientoPropiedades.btnGuardar.setEnabled(habilitar);
        frmMantenimientoPropiedades.btnCancelar.setEnabled(habilitar);
    }

    private void volverMenu() {
        // Verificar si el usuario es admin para redirigir al panel correcto
        String tipoUsuario = SesionUsuario.getInstance().getUsuarioActual().getTipoUsuario();

        if ("admin".equalsIgnoreCase(tipoUsuario)) {
            // Los admins van al panel de administración
            FrmAdmin frmAdmin = new FrmAdmin();
            new ControladorAdmin(frmAdmin);
            frmAdmin.setVisible(true);
        }
        frmMantenimientoPropiedades.setVisible(false);
    }
}

