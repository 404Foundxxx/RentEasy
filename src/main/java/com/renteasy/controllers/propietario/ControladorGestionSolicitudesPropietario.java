package com.renteasy.controllers.propietario;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

import com.renteasy.dao.SolicitudAlquilerDAO;
import com.renteasy.dao.PropiedadDAO;
import com.renteasy.dao.UsuarioDAO;
import com.renteasy.models.SolicitudAlquiler;
import com.renteasy.models.Propiedad;
import com.renteasy.models.Usuario;
import com.renteasy.utils.SesionUsuario;
import com.renteasy.views.propietario.FrmGestionDeSolicitudesPropietario;
import com.renteasy.views.propietario.FrmPropietario;

/**
 * Controlador para la gestión de solicitudes de alquiler del propietario
 * Maneja la visualización, aprobación y rechazo de solicitudes
 * 
 * 
 */
public class ControladorGestionSolicitudesPropietario {

    private FrmGestionDeSolicitudesPropietario frmGestionSolicitudes;
    private SolicitudAlquilerDAO solicitudDAO;
    private PropiedadDAO propiedadDAO;
    private UsuarioDAO usuarioDAO;
    private DefaultTableModel modeloTabla;

    public ControladorGestionSolicitudesPropietario() {
    }

    public ControladorGestionSolicitudesPropietario(FrmGestionDeSolicitudesPropietario frmGestionSolicitudes) {
        this.frmGestionSolicitudes = frmGestionSolicitudes;
        this.solicitudDAO = new SolicitudAlquilerDAO();
        this.propiedadDAO = new PropiedadDAO();
        this.usuarioDAO = new UsuarioDAO();
        configurarEventos();
        configurarTabla();
        cargarSolicitudes();
    }

    /**
     * Configura los eventos de los botones
     */
    private void configurarEventos() {
        // Configurar eventos de los botones con los nombres correctos de la vista
        frmGestionSolicitudes.btnVerDetalle.addActionListener(e -> verDetalleSolicitud());
        frmGestionSolicitudes.btnAceptarSolicitud.addActionListener(e -> aprobarSolicitud());
        frmGestionSolicitudes.btnRechazarSolicitud.addActionListener(e -> rechazarSolicitud());
        frmGestionSolicitudes.btnEliminarSolicitud.addActionListener(e -> eliminarSolicitud());
        frmGestionSolicitudes.btnRefrescar.addActionListener(e -> cargarSolicitudes());
        frmGestionSolicitudes.btnVolver.addActionListener(e -> volver());
        frmGestionSolicitudes.btnBuscar.addActionListener(e -> buscarSolicitudes());
        frmGestionSolicitudes.btnLimpiarFiltros.addActionListener(e -> limpiarFiltros());
        
        // Configurar evento del combo box de estado
        frmGestionSolicitudes.cmbEstadoSolicitud.addActionListener(e -> filtrarPorEstado());
    }

    /**
     * Configura la tabla de solicitudes
     */
    private void configurarTabla() {
        String[] columnas = {"ID", "Propiedad", "Inquilino", "Fecha Solicitud", "Estado", "Mensaje"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };
        
        // Usar directamente la tabla de la vista
        frmGestionSolicitudes.tblSolicitudes.setModel(modeloTabla);
        frmGestionSolicitudes.tblSolicitudes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * Carga las solicitudes de las propiedades del propietario
     */
    private void cargarSolicitudes() {
        try {
            SesionUsuario sesion = SesionUsuario.getInstance();
            if (!sesion.haySesionActiva()) {
                mostrarError("No hay una sesión activa");
                return;
            }

            int propietarioId = sesion.getUsuarioActual().getId();
            
            // Obtener propiedades del propietario
            List<Propiedad> propiedades = propiedadDAO.listarPorPropietario(propietarioId);
            
            // Limpiar tabla
            modeloTabla.setRowCount(0);
            
            // Cargar solicitudes para cada propiedad
            for (Propiedad propiedad : propiedades) {
                List<SolicitudAlquiler> solicitudes = solicitudDAO.obtenerPorPropiedad(propiedad.getId());
                
                for (SolicitudAlquiler solicitud : solicitudes) {
                    // Obtener el nombre del inquilino
                    String nombreInquilino = obtenerNombreInquilino(solicitud.getInquilinoId());
                    
                    Object[] fila = {
                        solicitud.getId(),
                        propiedad.getTitulo(),
                        nombreInquilino,
                        solicitud.getFechaSolicitud(),
                        solicitud.getEstado(),
                        solicitud.getMensaje()
                    };
                    modeloTabla.addRow(fila);
                }
            }
            
            actualizarEstadisticas();
            
        } catch (Exception e) {
            mostrarError("Error al cargar solicitudes: " + e.getMessage());
        }
    }

    /**
     * Aprueba la solicitud seleccionada
     */
    private void aprobarSolicitud() {
        try {
            JTable tabla = frmGestionSolicitudes.tblSolicitudes;
            if (tabla.getSelectedRow() < 0) {
                mostrarError("Seleccione una solicitud para aprobar");
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(
                frmGestionSolicitudes,
                "¿Está seguro que desea aprobar esta solicitud?",
                "Confirmar aprobación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                int solicitudId = (int) modeloTabla.getValueAt(tabla.getSelectedRow(), 0);
                boolean aprobado = solicitudDAO.actualizarEstadoSolicitud(solicitudId, "aprobada");
                
                if (aprobado) {
                    mostrarInformacion("Solicitud aprobada exitosamente");
                    cargarSolicitudes();
                } else {
                    mostrarError("Error al aprobar la solicitud");
                }
            }
            
        } catch (Exception e) {
            mostrarError("Error al aprobar solicitud: " + e.getMessage());
        }
    }

    /**
     * Rechaza la solicitud seleccionada
     */
    private void rechazarSolicitud() {
        try {
            JTable tabla = frmGestionSolicitudes.tblSolicitudes;
            if (tabla.getSelectedRow() < 0) {
                mostrarError("Seleccione una solicitud para rechazar");
                return;
            }

            String motivo = JOptionPane.showInputDialog(
                frmGestionSolicitudes,
                "Ingrese el motivo del rechazo (opcional):",
                "Motivo de rechazo",
                JOptionPane.QUESTION_MESSAGE
            );

            if (motivo != null) { // Usuario no canceló
                int solicitudId = (int) modeloTabla.getValueAt(tabla.getSelectedRow(), 0);
                boolean rechazado = solicitudDAO.actualizarEstadoSolicitud(solicitudId, "rechazada");
                
                if (rechazado) {
                    mostrarInformacion("Solicitud rechazada exitosamente");
                    cargarSolicitudes();
                } else {
                    mostrarError("Error al rechazar la solicitud");
                }
            }
            
        } catch (Exception e) {
            mostrarError("Error al rechazar solicitud: " + e.getMessage());
        }
    }

    /**
     * Muestra el detalle de la solicitud seleccionada
     */
    private void verDetalleSolicitud() {
        try {
            JTable tabla = frmGestionSolicitudes.tblSolicitudes;
            if (tabla.getSelectedRow() < 0) {
                mostrarError("Seleccione una solicitud para ver el detalle");
                return;
            }

            int solicitudId = (int) modeloTabla.getValueAt(tabla.getSelectedRow(), 0);
            SolicitudAlquiler solicitud = solicitudDAO.obtenerPorId(solicitudId);
            
            if (solicitud != null) {
                // Obtener información del inquilino
                Usuario inquilino = usuarioDAO.obtenerPorId(solicitud.getInquilinoId());
                String nombreInquilino = (inquilino != null) ? inquilino.getNombre() : "Usuario no encontrado";
                String emailInquilino = (inquilino != null) ? inquilino.getEmail() : "N/A";
                String telefonoInquilino = (inquilino != null) ? inquilino.getTelefono() : "N/A";
                
                String detalle = String.format(
                    "ID: %d\n" +
                    "Inquilino: %s\n" +
                    "Email: %s\n" +
                    "Teléfono: %s\n" +
                    "Propiedad ID: %d\n" +
                    "Fecha Solicitud: %s\n" +
                    "Estado: %s\n" +
                    "Mensaje: %s",
                    solicitud.getId(),
                    nombreInquilino,
                    emailInquilino,
                    telefonoInquilino,
                    solicitud.getPropiedadId(),
                    solicitud.getFechaSolicitud(),
                    solicitud.getEstado(),
                    solicitud.getMensaje()
                );
                
                JOptionPane.showMessageDialog(
                    frmGestionSolicitudes,
                    detalle,
                    "Detalle de Solicitud",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                mostrarError("No se pudo obtener el detalle de la solicitud");
            }
            
        } catch (Exception e) {
            mostrarError("Error al mostrar detalle: " + e.getMessage());
        }
    }

    /**
     * Vuelve al formulario principal de propietario
     */
    private void volver() {
        try {
            FrmPropietario frmPropietario = new FrmPropietario();
            new ControladorPropietario(frmPropietario);
            frmPropietario.setVisible(true);
            frmGestionSolicitudes.dispose();
        } catch (Exception e) {
            mostrarError("Error al volver: " + e.getMessage());
        }
    }

    /**
     * Muestra un mensaje de error
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
            frmGestionSolicitudes,
            mensaje,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Muestra un mensaje de información
     */
    private void mostrarInformacion(String mensaje) {
        JOptionPane.showMessageDialog(
            frmGestionSolicitudes,
            mensaje,
            "Información",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Elimina la solicitud seleccionada
     */
    private void eliminarSolicitud() {
        try {
            JTable tabla = frmGestionSolicitudes.tblSolicitudes;
            if (tabla.getSelectedRow() < 0) {
                mostrarError("Seleccione una solicitud para eliminar");
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(
                frmGestionSolicitudes,
                "¿Está seguro que desea eliminar esta solicitud?\nEsta acción no se puede deshacer.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                int solicitudId = (int) modeloTabla.getValueAt(tabla.getSelectedRow(), 0);
                boolean eliminado = solicitudDAO.eliminar(solicitudId);
                
                if (eliminado) {
                    mostrarInformacion("Solicitud eliminada exitosamente");
                    cargarSolicitudes();
                } else {
                    mostrarError("Error al eliminar la solicitud");
                }
            }
            
        } catch (Exception e) {
            mostrarError("Error al eliminar solicitud: " + e.getMessage());
        }
    }

    /**
     * Busca solicitudes según los filtros especificados
     */
    private void buscarSolicitudes() {
        try {
            String propiedadBuscar = frmGestionSolicitudes.txtPropiedadBuscar.getText().trim();
            String inquilinoBuscar = frmGestionSolicitudes.txtInquilinoBuscar.getText().trim();
            
            // Si ambos campos están vacíos, cargar todas las solicitudes
            if (propiedadBuscar.isEmpty() && inquilinoBuscar.isEmpty()) {
                cargarSolicitudes();
                return;
            }
            
            // Implementar búsqueda filtrada
            SesionUsuario sesion = SesionUsuario.getInstance();
            if (!sesion.haySesionActiva()) {
                mostrarError("No hay una sesión activa");
                return;
            }

            int propietarioId = sesion.getUsuarioActual().getId();
            List<Propiedad> propiedades = propiedadDAO.listarPorPropietario(propietarioId);
            
            // Limpiar tabla
            modeloTabla.setRowCount(0);
            
            // Cargar solicitudes filtradas
            for (Propiedad propiedad : propiedades) {
                // Filtrar por nombre de propiedad si se especifica
                if (!propiedadBuscar.isEmpty() && 
                    !propiedad.getTitulo().toLowerCase().contains(propiedadBuscar.toLowerCase())) {
                    continue;
                }
                
                List<SolicitudAlquiler> solicitudes = solicitudDAO.obtenerPorPropiedad(propiedad.getId());
                
                for (SolicitudAlquiler solicitud : solicitudes) {
                    // Obtener el nombre del inquilino
                    String nombreInquilino = obtenerNombreInquilino(solicitud.getInquilinoId());
                    
                    // Filtrar por nombre del inquilino si se especifica
                    if (!inquilinoBuscar.isEmpty() && 
                        !nombreInquilino.toLowerCase().contains(inquilinoBuscar.toLowerCase())) {
                        continue;
                    }
                    
                    Object[] fila = {
                        solicitud.getId(),
                        propiedad.getTitulo(),
                        nombreInquilino,
                        solicitud.getFechaSolicitud(),
                        solicitud.getEstado(),
                        solicitud.getMensaje()
                    };
                    modeloTabla.addRow(fila);
                }
            }
            
            actualizarEstadisticas();
            
        } catch (Exception e) {
            mostrarError("Error al buscar solicitudes: " + e.getMessage());
        }
    }

    /**
     * Limpia los filtros de búsqueda
     */
    private void limpiarFiltros() {
        frmGestionSolicitudes.txtPropiedadBuscar.setText("");
        frmGestionSolicitudes.txtInquilinoBuscar.setText("");
        frmGestionSolicitudes.cmbEstadoSolicitud.setSelectedIndex(0); // "Todos"
        cargarSolicitudes();
    }

    /**
     * Filtra las solicitudes por estado
     */
    private void filtrarPorEstado() {
        try {
            String estadoSeleccionado = (String) frmGestionSolicitudes.cmbEstadoSolicitud.getSelectedItem();
            
            if ("Todos".equals(estadoSeleccionado)) {
                cargarSolicitudes();
                return;
            }
            
            SesionUsuario sesion = SesionUsuario.getInstance();
            if (!sesion.haySesionActiva()) {
                mostrarError("No hay una sesión activa");
                return;
            }

            int propietarioId = sesion.getUsuarioActual().getId();
            List<Propiedad> propiedades = propiedadDAO.listarPorPropietario(propietarioId);
            
            // Limpiar tabla
            modeloTabla.setRowCount(0);
            
            // Cargar solicitudes filtradas por estado
            for (Propiedad propiedad : propiedades) {
                List<SolicitudAlquiler> solicitudes = solicitudDAO.obtenerPorPropiedad(propiedad.getId());
                
                for (SolicitudAlquiler solicitud : solicitudes) {
                    if (estadoSeleccionado.equals(solicitud.getEstado())) {
                        // Obtener el nombre del inquilino
                        String nombreInquilino = obtenerNombreInquilino(solicitud.getInquilinoId());
                        
                        Object[] fila = {
                            solicitud.getId(),
                            propiedad.getTitulo(),
                            nombreInquilino,
                            solicitud.getFechaSolicitud(),
                            solicitud.getEstado(),
                            solicitud.getMensaje()
                        };
                        modeloTabla.addRow(fila);
                    }
                }
            }
            
            actualizarEstadisticas();
            
        } catch (Exception e) {
            mostrarError("Error al filtrar por estado: " + e.getMessage());
        }
    }

    /**
     * Actualiza las estadísticas mostradas en la interfaz
     */
    private void actualizarEstadisticas() {
        int totalSolicitudes = modeloTabla.getRowCount();
        int solicitudesPendientes = 0;
        
        for (int i = 0; i < totalSolicitudes; i++) {
            String estado = (String) modeloTabla.getValueAt(i, 4);
            if ("pendiente".equals(estado)) {
                solicitudesPendientes++;
            }
        }
        
        frmGestionSolicitudes.lblTotalSolicitudes.setText("Total de solicitudes: " + totalSolicitudes);
        frmGestionSolicitudes.lblSolicitudesPendientes.setText("Pendientes: " + solicitudesPendientes);
    }
    
    /**
     * Obtiene el nombre del inquilino por su ID
     * @param inquilinoId ID del inquilino
     * @return Nombre del inquilino o "Usuario no encontrado" si no existe
     */
    private String obtenerNombreInquilino(int inquilinoId) {
        Usuario inquilino = usuarioDAO.obtenerPorId(inquilinoId);
        return (inquilino != null) ? inquilino.getNombre() : "Usuario no encontrado";
    }
}

