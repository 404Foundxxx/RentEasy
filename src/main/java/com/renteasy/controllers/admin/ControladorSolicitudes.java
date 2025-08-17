package com.renteasy.controllers.admin;

import com.renteasy.views.admin.FrmAdmin;
import com.renteasy.views.admin.FrmSolicitudes;
import com.renteasy.models.*;
import com.renteasy.dao.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.renteasy.utils.SesionUsuario;

/**
 * Controlador para manejar la lógica de solicitudes de alquiler en la
 * aplicación RentEasy.
 *
 * 
 */
public class ControladorSolicitudes {

    private static final Logger LOGGER = Logger.getLogger(ControladorSolicitudes.class.getName());
    private FrmSolicitudes frmSolicitudes;
    private SolicitudAlquilerDAO solicitudDAO;
    private UsuarioDAO usuarioDAO;
    private PropiedadDAO propiedadDAO;

    public ControladorSolicitudes() {
    }

    public ControladorSolicitudes(FrmSolicitudes frm) {
        this.frmSolicitudes = frm;
        this.solicitudDAO = new SolicitudAlquilerDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.propiedadDAO = new PropiedadDAO();
        configurarEventos();
        cargarSolicitudes();
    }

    /**
     * Método auxiliar para mostrar mensajes
     */
    private void mostrarMensaje(String mensaje, String titulo, int tipoMensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, tipoMensaje);
    }

    /**
     * Configura los eventos de los botones del formulario
     */
    private void configurarEventos() {

        // Botón ver detalle
        frmSolicitudes.btnVerDetalle.addActionListener(e -> verDetalleSolicitud());

        // Botón aceptar solicitud
        frmSolicitudes.btnAceptarSolicitud.addActionListener(e -> aceptarSolicitud());

        // Botón rechazar solicitud
        frmSolicitudes.btnRechazarSolicitud.addActionListener(e -> rechazarSolicitud());

        // Botón eliminar solicitud
        frmSolicitudes.btnEliminarSolicitud.addActionListener(e -> eliminarSolicitud());

        // Botón refrescar
        frmSolicitudes.btnRefrescar.addActionListener(e -> cargarSolicitudes());

        // Botón buscar
        frmSolicitudes.btnBuscar.addActionListener(e -> buscarSolicitudes());

        // Botón limpiar filtros
        frmSolicitudes.btnLimpiarFiltros.addActionListener(e -> limpiarFiltros());

        // Filtro por estado
        frmSolicitudes.cmbEstadoSolicitud.addActionListener(e -> filtrarPorEstado());

        // Botón volver
        frmSolicitudes.btnVolver.addActionListener(e -> volverMenu());
    }

    /**
     * Carga todas las solicitudes en la tabla
     */
    private void cargarSolicitudes() {
        try {
            List<SolicitudAlquiler> solicitudes = solicitudDAO.obtenerTodas();
            cargarDatosEnTabla(solicitudes);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar solicitudes", e);
            mostrarMensaje("Error al cargar las solicitudes: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Carga los datos de solicitudes en la tabla
     */
    private void cargarDatosEnTabla(List<SolicitudAlquiler> solicitudes) {
        DefaultTableModel modelo = (DefaultTableModel) frmSolicitudes.tblSolicitudes.getModel();
        modelo.setRowCount(0);

        int pendientes = 0;

        for (SolicitudAlquiler solicitud : solicitudes) {
            String nombreInquilino = obtenerNombreUsuario(solicitud.getInquilinoId());
            String nombrePropiedad = obtenerNombrePropiedad(solicitud.getPropiedadId());

            Object[] fila = {
                    solicitud.getId(),
                    nombreInquilino,
                    nombrePropiedad,
                    solicitud.getMensaje() != null ? solicitud.getMensaje() : "Sin mensaje",
                    solicitud.getEstado(),
                    solicitud.getFechaSolicitud()
            };
            modelo.addRow(fila);

            if ("pendiente".equals(solicitud.getEstado())) {
                pendientes++;
            }
        }

        frmSolicitudes.lblTotalSolicitudes.setText("Total de solicitudes: " + solicitudes.size());
        frmSolicitudes.lblSolicitudesPendientes.setText("Pendientes: " + pendientes);
    }

    /**
     * Obtiene el nombre de un usuario por su ID
     */
    private String obtenerNombreUsuario(int usuarioId) {
        try {
            Usuario usuario = usuarioDAO.obtenerPorId(usuarioId);
            return usuario != null ? usuario.getNombre() : "Usuario no encontrado";
        } catch (Exception e) {
            return "Error al obtener usuario";
        }
    }

    /**
     * Obtiene el nombre de una propiedad por su ID
     */
    private String obtenerNombrePropiedad(int propiedadId) {
        try {
            Propiedad propiedad = propiedadDAO.obtenerPorId(propiedadId);
            return propiedad != null ? propiedad.getTitulo() : "Propiedad no encontrada";
        } catch (Exception e) {
            return "Error al obtener propiedad";
        }
    }

    /**
     * Muestra el detalle de la solicitud seleccionada
     */
    private void verDetalleSolicitud() {
        int filaSeleccionada = frmSolicitudes.tblSolicitudes.getSelectedRow();
        if (filaSeleccionada == -1) {
            mostrarMensaje("Por favor seleccione una solicitud",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int solicitudId = (Integer) frmSolicitudes.tblSolicitudes.getValueAt(filaSeleccionada, 0);
        String mensaje = (String) frmSolicitudes.tblSolicitudes.getValueAt(filaSeleccionada, 3);

        mostrarMensaje("Solicitud ID: " + solicitudId + "\n\nMensaje:\n" + mensaje,
                "Detalle de la Solicitud", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Acepta la solicitud seleccionada
     */
    private void aceptarSolicitud() {
        int filaSeleccionada = frmSolicitudes.tblSolicitudes.getSelectedRow();
        if (filaSeleccionada == -1) {
            mostrarMensaje("Por favor seleccione una solicitud",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String estadoActual = (String) frmSolicitudes.tblSolicitudes.getValueAt(filaSeleccionada, 4);
        if (!"pendiente".equals(estadoActual)) {
            mostrarMensaje("Solo se pueden aceptar solicitudes pendientes",
                    "Estado inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(frmSolicitudes,
                "¿Está seguro de que desea aceptar esta solicitud?",
                "Confirmar Aceptación",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                int solicitudId = (Integer) frmSolicitudes.tblSolicitudes.getValueAt(filaSeleccionada, 0);
                boolean actualizado = solicitudDAO.actualizarEstado(solicitudId, "aceptada");

                if (actualizado) {
                    mostrarMensaje("Solicitud aceptada exitosamente",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarSolicitudes();
                } else {
                    mostrarMensaje("No se pudo aceptar la solicitud",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error al aceptar solicitud", e);
                mostrarMensaje("Error al aceptar la solicitud: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Rechaza la solicitud seleccionada
     */
    private void rechazarSolicitud() {
        int filaSeleccionada = frmSolicitudes.tblSolicitudes.getSelectedRow();
        if (filaSeleccionada == -1) {
            mostrarMensaje("Por favor seleccione una solicitud",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String estadoActual = (String) frmSolicitudes.tblSolicitudes.getValueAt(filaSeleccionada, 4);
        if (!"pendiente".equals(estadoActual)) {
            mostrarMensaje("Solo se pueden rechazar solicitudes pendientes",
                    "Estado inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(frmSolicitudes,
                "¿Está seguro de que desea rechazar esta solicitud?",
                "Confirmar Rechazo",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                int solicitudId = (Integer) frmSolicitudes.tblSolicitudes.getValueAt(filaSeleccionada, 0);
                boolean actualizado = solicitudDAO.actualizarEstado(solicitudId, "rechazada");

                if (actualizado) {
                    mostrarMensaje("Solicitud rechazada exitosamente",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarSolicitudes();
                } else {
                    mostrarMensaje("No se pudo rechazar la solicitud",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error al rechazar solicitud", e);
                mostrarMensaje("Error al rechazar la solicitud: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Elimina la solicitud seleccionada
     */
    private void eliminarSolicitud() {
        int filaSeleccionada = frmSolicitudes.tblSolicitudes.getSelectedRow();
        if (filaSeleccionada == -1) {
            mostrarMensaje("Por favor seleccione una solicitud",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(frmSolicitudes,
                "¿Está seguro de que desea eliminar esta solicitud?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                int solicitudId = (Integer) frmSolicitudes.tblSolicitudes.getValueAt(filaSeleccionada, 0);
                boolean eliminado = solicitudDAO.eliminar(solicitudId);

                if (eliminado) {
                    mostrarMensaje("Solicitud eliminada exitosamente",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarSolicitudes();
                } else {
                    mostrarMensaje("No se pudo eliminar la solicitud",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error al eliminar solicitud", e);
                mostrarMensaje("Error al eliminar la solicitud: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Busca solicitudes por propiedad o inquilino
     */
    private void buscarSolicitudes() {
        String propiedadBuscar = frmSolicitudes.txtPropiedadBuscar.getText().trim();
        String inquilinoBuscar = frmSolicitudes.txtInquilinoBuscar.getText().trim();

        if (propiedadBuscar.isEmpty() && inquilinoBuscar.isEmpty()) {
            cargarSolicitudes();
            return;
        }

        try {
            List<SolicitudAlquiler> todasSolicitudes = solicitudDAO.obtenerTodas();
            List<SolicitudAlquiler> solicitudesFiltradas = todasSolicitudes.stream()
                    .filter(solicitud -> {
                        boolean coincidePropiedad = propiedadBuscar.isEmpty() ||
                                obtenerNombrePropiedad(solicitud.getPropiedadId()).toLowerCase()
                                        .contains(propiedadBuscar.toLowerCase());

                        boolean coincideInquilino = inquilinoBuscar.isEmpty() ||
                                obtenerNombreUsuario(solicitud.getInquilinoId()).toLowerCase()
                                        .contains(inquilinoBuscar.toLowerCase());

                        return coincidePropiedad && coincideInquilino;
                    })
                    .collect(java.util.stream.Collectors.toList());

            cargarDatosEnTabla(solicitudesFiltradas);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al buscar solicitudes", e);
            mostrarMensaje("Error al buscar solicitudes: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Filtra solicitudes por estado
     */
    private void filtrarPorEstado() {
        String estadoSeleccionado = (String) frmSolicitudes.cmbEstadoSolicitud.getSelectedItem();

        if ("Todos".equals(estadoSeleccionado)) {
            cargarSolicitudes();
            return;
        }

        try {
            List<SolicitudAlquiler> todasSolicitudes = solicitudDAO.obtenerTodas();
            List<SolicitudAlquiler> solicitudesFiltradas = todasSolicitudes.stream()
                    .filter(solicitud -> solicitud.getEstado().equals(estadoSeleccionado))
                    .collect(java.util.stream.Collectors.toList());

            cargarDatosEnTabla(solicitudesFiltradas);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al filtrar solicitudes", e);
            mostrarMensaje("Error al filtrar solicitudes: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Limpia todos los filtros y campos de búsqueda
     */
    private void limpiarFiltros() {
        frmSolicitudes.txtPropiedadBuscar.setText("");
        frmSolicitudes.txtInquilinoBuscar.setText("");
        frmSolicitudes.cmbEstadoSolicitud.setSelectedIndex(0);
        cargarSolicitudes();
    }

    private void volverMenu() {
        String tipoUsuario = SesionUsuario.getInstance().getUsuarioActual().getTipoUsuario();

        if ("admin".equalsIgnoreCase(tipoUsuario)) {
            // Los admins van al panel de administración
            FrmAdmin frmAdmin = new FrmAdmin();
            new ControladorAdmin(frmAdmin);
            frmAdmin.setVisible(true);
        }
        frmSolicitudes.dispose();
    }
}

