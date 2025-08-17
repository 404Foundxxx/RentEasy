package com.renteasy.controllers.inquilino;

import com.renteasy.dao.SolicitudAlquilerDAO;
import com.renteasy.models.SolicitudAlquiler;
import com.renteasy.utils.SesionUsuario;
import com.renteasy.views.inquilino.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class ControladorGestionDeSolicitudesUsuario {

    private FrmGestionDeSolicitudesUsuario frmGestionSolicitudes;
    private SolicitudAlquilerDAO solicitudDAO;
    private DefaultTableModel modeloTabla;

    public ControladorGestionDeSolicitudesUsuario(FrmGestionDeSolicitudesUsuario frmGestionSolicitudes) {
        this.frmGestionSolicitudes = frmGestionSolicitudes;
        this.solicitudDAO = new SolicitudAlquilerDAO();
        inicializarComponentes();
        configurarEventos();
        cargarSolicitudes();
    }

    private void inicializarComponentes() {
        // Configurar el modelo de la tabla
        modeloTabla = (DefaultTableModel) frmGestionSolicitudes.tblSolicitudes.getModel();
        
        // Configurar la tabla para selección única
        frmGestionSolicitudes.tblSolicitudes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void configurarEventos() {
        // Botón Ver Detalle
        frmGestionSolicitudes.btnVerDetalle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verDetalleSolicitud();
            }
        });

        // Botón Eliminar Solicitud
        frmGestionSolicitudes.btnEliminarSolicitud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarSolicitud();
            }
        });

        // Botón Refrescar
        frmGestionSolicitudes.btnRefrescar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarSolicitudes();
            }
        });

        // ComboBox Estado
        frmGestionSolicitudes.cmbEstadoSolicitud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarPorEstado();
            }
        });

        // Botón Volver
        frmGestionSolicitudes.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });
    }

    private void cargarSolicitudes() {
        try {
            // Verificar que hay una sesión activa
            if (!SesionUsuario.getInstance().haySesionActiva()) {
                JOptionPane.showMessageDialog(frmGestionSolicitudes,
                        "No hay una sesión activa.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int inquilinoId = SesionUsuario.getInstance().getUsuarioActual().getId();
            
            // Limpiar la tabla
            modeloTabla.setRowCount(0);
            
            // Obtener las solicitudes del inquilino
            List<SolicitudAlquiler> solicitudes = solicitudDAO.obtenerSolicitudesPorInquilino(inquilinoId);
            
            // Llenar la tabla
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            
            for (SolicitudAlquiler solicitud : solicitudes) {
                Object[] fila = {
                    solicitud.getId(),
                    solicitud.getNombreInquilino() != null ? solicitud.getNombreInquilino() : "Usuario",
                    solicitud.getTituloPropiedad() != null ? solicitud.getTituloPropiedad() : "Propiedad #" + solicitud.getPropiedadId(),
                    solicitud.getMensaje(),
                    solicitud.getEstado(),
                    solicitud.getFechaSolicitud() != null ? formatter.format(solicitud.getFechaSolicitud()) : ""
                };
                modeloTabla.addRow(fila);
            }
            
            // Actualizar contadores
            actualizarContadores(solicitudes);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmGestionSolicitudes,
                    "Error al cargar las solicitudes: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filtrarPorEstado() {
        try {
            String estadoSeleccionado = (String) frmGestionSolicitudes.cmbEstadoSolicitud.getSelectedItem();
            
            if (!SesionUsuario.getInstance().haySesionActiva()) {
                return;
            }

            int inquilinoId = SesionUsuario.getInstance().getUsuarioActual().getId();
            List<SolicitudAlquiler> solicitudes;
            
            if ("Todos".equals(estadoSeleccionado)) {
                solicitudes = solicitudDAO.obtenerSolicitudesPorInquilino(inquilinoId);
            } else {
                // Obtener todas las solicitudes del inquilino y filtrar por estado
                solicitudes = solicitudDAO.obtenerSolicitudesPorInquilino(inquilinoId);
                solicitudes.removeIf(s -> !s.getEstado().equals(estadoSeleccionado));
            }
            
            // Limpiar la tabla
            modeloTabla.setRowCount(0);
            
            // Llenar la tabla con solicitudes filtradas
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            
            for (SolicitudAlquiler solicitud : solicitudes) {
                Object[] fila = {
                    solicitud.getId(),
                    solicitud.getNombreInquilino() != null ? solicitud.getNombreInquilino() : "Usuario",
                    solicitud.getTituloPropiedad() != null ? solicitud.getTituloPropiedad() : "Propiedad #" + solicitud.getPropiedadId(),
                    solicitud.getMensaje(),
                    solicitud.getEstado(),
                    solicitud.getFechaSolicitud() != null ? formatter.format(solicitud.getFechaSolicitud()) : ""
                };
                modeloTabla.addRow(fila);
            }
            
            // Actualizar contadores con todas las solicitudes (no filtradas)
            List<SolicitudAlquiler> todasLasSolicitudes = solicitudDAO.obtenerSolicitudesPorInquilino(inquilinoId);
            actualizarContadores(todasLasSolicitudes);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmGestionSolicitudes,
                    "Error al filtrar las solicitudes: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verDetalleSolicitud() {
        int filaSeleccionada = frmGestionSolicitudes.tblSolicitudes.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(frmGestionSolicitudes,
                    "Debe seleccionar una solicitud para ver los detalles.",
                    "Seleccionar Solicitud", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // Obtener el ID de la solicitud seleccionada
            int solicitudId = (Integer) modeloTabla.getValueAt(filaSeleccionada, 0);
            SolicitudAlquiler solicitud = solicitudDAO.obtenerSolicitudPorId(solicitudId);
            
            if (solicitud != null) {
                // Crear un diálogo con los detalles de la solicitud
                StringBuilder detalles = new StringBuilder();
                detalles.append("=== DETALLES DE LA SOLICITUD ===\n\n");
                detalles.append("ID: ").append(solicitud.getId()).append("\n");
                detalles.append("Propiedad: ").append(solicitud.getTituloPropiedad() != null ? 
                    solicitud.getTituloPropiedad() : "Propiedad #" + solicitud.getPropiedadId()).append("\n");
                detalles.append("Dirección: ").append(solicitud.getDireccionPropiedad() != null ? 
                    solicitud.getDireccionPropiedad() : "No disponible").append("\n");
                detalles.append("Estado: ").append(solicitud.getEstado().toUpperCase()).append("\n");
                detalles.append("Fecha de Solicitud: ").append(
                    solicitud.getFechaSolicitud() != null ? 
                    new SimpleDateFormat("dd/MM/yyyy HH:mm").format(solicitud.getFechaSolicitud()) : 
                    "No disponible").append("\n\n");
                detalles.append("Mensaje:\n").append(solicitud.getMensaje());
                
                JTextArea textArea = new JTextArea(detalles.toString());
                textArea.setEditable(false);
                textArea.setRows(12);
                textArea.setColumns(40);
                JScrollPane scrollPane = new JScrollPane(textArea);
                
                JOptionPane.showMessageDialog(frmGestionSolicitudes, scrollPane,
                        "Detalles de la Solicitud", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmGestionSolicitudes,
                    "Error al mostrar los detalles: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarSolicitud() {
        int filaSeleccionada = frmGestionSolicitudes.tblSolicitudes.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(frmGestionSolicitudes,
                    "Debe seleccionar una solicitud para eliminar.",
                    "Seleccionar Solicitud", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // Obtener el ID y estado de la solicitud seleccionada
            int solicitudId = (Integer) modeloTabla.getValueAt(filaSeleccionada, 0);
            String estado = (String) modeloTabla.getValueAt(filaSeleccionada, 4);
            
            // Verificar si la solicitud puede ser eliminada
            if ("aceptada".equals(estado)) {
                JOptionPane.showMessageDialog(frmGestionSolicitudes,
                        "No se puede eliminar una solicitud que ya ha sido aceptada.",
                        "Solicitud Aceptada", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Confirmar eliminación
            int opcion = JOptionPane.showConfirmDialog(frmGestionSolicitudes,
                    "¿Está seguro que desea eliminar esta solicitud?\n" +
                    "Esta acción no se puede deshacer.",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            
            if (opcion == JOptionPane.YES_OPTION) {
                boolean eliminada = solicitudDAO.eliminarSolicitud(solicitudId);
                
                if (eliminada) {
                    JOptionPane.showMessageDialog(frmGestionSolicitudes,
                            "Solicitud eliminada exitosamente.",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarSolicitudes(); // Recargar la tabla
                } else {
                    JOptionPane.showMessageDialog(frmGestionSolicitudes,
                            "No se pudo eliminar la solicitud.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmGestionSolicitudes,
                    "Error al eliminar la solicitud: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarContadores(List<SolicitudAlquiler> solicitudes) {
        int total = solicitudes.size();
        int pendientes = 0;
        int aceptadas = 0;
        int rechazadas = 0;
        
        for (SolicitudAlquiler solicitud : solicitudes) {
            switch (solicitud.getEstado()) {
                case "pendiente":
                    pendientes++;
                    break;
                case "aceptada":
                    aceptadas++;
                    break;
                case "rechazada":
                    rechazadas++;
                    break;
            }
        }
        
        frmGestionSolicitudes.lblTotalSolicitudes.setText("Total de solicitudes: " + total);
        frmGestionSolicitudes.lblSolicitudesPendiente.setText("Pendientes: " + pendientes);
        frmGestionSolicitudes.lblSolicitudesAceptada.setText("Aceptadas: " + aceptadas);
        frmGestionSolicitudes.lblSolicitudesRechazada.setText("Rechazadas: " + rechazadas);
    }

    private void volver() {
        try {
            FrmInquilino frmInquilino = new FrmInquilino();
            new ControladorInquilino(frmInquilino);
            frmInquilino.setVisible(true);
            frmGestionSolicitudes.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmGestionSolicitudes,
                    "Error al volver al menú principal: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

