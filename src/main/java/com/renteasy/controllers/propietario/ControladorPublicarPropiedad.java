package com.renteasy.controllers.propietario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.renteasy.dao.PropiedadDAO;
import com.renteasy.dao.FotoPropiedadDAO;
import com.renteasy.models.Propiedad;
import com.renteasy.models.FotoPropiedad;
import com.renteasy.utils.SesionUsuario;
import com.renteasy.views.propietario.FrmPublicarPropiedad;
import com.renteasy.views.propietario.FrmPropietario;

/**
 * Controlador para la gestión de propiedades del propietario
 * Maneja la publicación, edición y eliminación de propiedades
 * 
 * 
 */
public class ControladorPublicarPropiedad {

    private FrmPublicarPropiedad frmPublicarPropiedad;
    private PropiedadDAO propiedadDAO;
    private DefaultTableModel modeloTabla;
    private boolean modoEdicion = false;
    private int propiedadIdEnEdicion = -1;
    private String rutaImagenSeleccionada = null; // Para almacenar la ruta de la imagen seleccionada
    private String rutaImagenActual = null; // Para almacenar la ruta de la imagen actual en edición

    public ControladorPublicarPropiedad() {
    }

    public ControladorPublicarPropiedad(FrmPublicarPropiedad frmPublicarPropiedad) {
        this.frmPublicarPropiedad = frmPublicarPropiedad;
        this.propiedadDAO = new PropiedadDAO();
        configurarEventos();
        configurarTabla();
        cargarPropiedades();
        configurarFormulario();
    }

    /**
     * Configura los eventos de los botones
     */
    private void configurarEventos() {
        // Botón Nuevo
        frmPublicarPropiedad.btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                habilitarModoNuevo();
            }
        });

        // Botón Guardar
        frmPublicarPropiedad.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPropiedad();
            }
        });

        // Botón Editar
        frmPublicarPropiedad.btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarPropiedad();
            }
        });

        // Botón Eliminar
        frmPublicarPropiedad.btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPropiedad();
            }
        });

        // Botón Cancelar
        frmPublicarPropiedad.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarOperacion();
            }
        });

        // Botón Limpiar
        frmPublicarPropiedad.btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
            }
        });

        // Botón Subir Imagen
        frmPublicarPropiedad.btnSubirImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarImagen();
            }
        });

        // Botón Refrescar
        frmPublicarPropiedad.btnRefrescar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPropiedades();
            }
        });

        // Botón Volver
        frmPublicarPropiedad.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });

        // Filtro por estado
        if (frmPublicarPropiedad.cmbFiltroEstado != null) {
            frmPublicarPropiedad.cmbFiltroEstado.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    filtrarPorEstado();
                }
            });
        }
    }

    /**
     * Configura la tabla de propiedades
     */
    private void configurarTabla() {
        String[] columnas = {"ID", "Título", "Ciudad", "Precio", "Habitaciones", "Baños", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };
        
        // Configurar la tabla
        if (frmPublicarPropiedad.tblPropiedades != null) {
            frmPublicarPropiedad.tblPropiedades.setModel(modeloTabla);
            frmPublicarPropiedad.tblPropiedades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            // Configurar el ancho de las columnas
            frmPublicarPropiedad.tblPropiedades.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
            frmPublicarPropiedad.tblPropiedades.getColumnModel().getColumn(1).setPreferredWidth(200); // Título
            frmPublicarPropiedad.tblPropiedades.getColumnModel().getColumn(2).setPreferredWidth(120); // Ciudad
            frmPublicarPropiedad.tblPropiedades.getColumnModel().getColumn(3).setPreferredWidth(100); // Precio
            frmPublicarPropiedad.tblPropiedades.getColumnModel().getColumn(4).setPreferredWidth(80);  // Habitaciones
            frmPublicarPropiedad.tblPropiedades.getColumnModel().getColumn(5).setPreferredWidth(60);  // Baños
            frmPublicarPropiedad.tblPropiedades.getColumnModel().getColumn(6).setPreferredWidth(100); // Estado
            
            // Listener para selección de fila
            frmPublicarPropiedad.tblPropiedades.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int filaSeleccionada = frmPublicarPropiedad.tblPropiedades.getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        cargarDatosEnFormulario(filaSeleccionada);
                    }
                }
            });
        }
    }

    /**
     * Configura el estado inicial del formulario
     */
    private void configurarFormulario() {
        deshabilitarFormulario();
        habilitarBotones(true, false, false, false, false, false);
    }

    /**
     * Carga las propiedades del propietario logueado
     */
    private void cargarPropiedades() {
        try {
            SesionUsuario sesion = SesionUsuario.getInstance();
            if (!sesion.haySesionActiva()) {
                mostrarError("No hay una sesión activa");
                return;
            }

            int propietarioId = sesion.getUsuarioActual().getId();
            List<Propiedad> propiedades = propiedadDAO.listarPorPropietario(propietarioId);
            
            // Limpiar tabla
            modeloTabla.setRowCount(0);
            
            // Llenar tabla
            if (propiedades != null && !propiedades.isEmpty()) {
                for (Propiedad propiedad : propiedades) {
                    Object[] fila = {
                        propiedad.getId(),
                        propiedad.getTitulo(),
                        propiedad.getCiudad(),
                        "$" + propiedad.getPrecioMensual().toString(),
                        propiedad.getHabitaciones(),
                        propiedad.getBanos(),
                        propiedad.getEstado()
                    };
                    modeloTabla.addRow(fila);
                }
                
                // Actualizar la tabla para refrescar la vista
                if (frmPublicarPropiedad.tblPropiedades != null) {
                    frmPublicarPropiedad.tblPropiedades.revalidate();
                    frmPublicarPropiedad.tblPropiedades.repaint();
                }
            } else {
                // Si no hay propiedades, mostrar mensaje informativo en la consola de logs
                System.out.println("No se encontraron propiedades para el propietario ID: " + propietarioId);
            }
            
        } catch (Exception e) {
            mostrarError("Error al cargar propiedades: " + e.getMessage());
            e.printStackTrace(); // Para debug
        }
    }

    /**
     * Habilita el modo para crear una nueva propiedad
     */
    private void habilitarModoNuevo() {
        modoEdicion = false;
        propiedadIdEnEdicion = -1;
        rutaImagenActual = null;
        limpiarFormulario();
        habilitarFormulario();
        habilitarBotones(false, true, false, false, true, true);
    }

    /**
     * Guarda una propiedad (nueva o editada)
     */
    private void guardarPropiedad() {
        try {
            if (!validarFormulario()) {
                return;
            }

            Propiedad propiedad = crearPropiedadDesdeFormulario();
            
            if (modoEdicion) {
                // Actualizar propiedad existente
                propiedad.setId(propiedadIdEnEdicion);
                boolean actualizado = propiedadDAO.actualizar(propiedad);
                if (actualizado) {
                    // Si se seleccionó una nueva imagen, actualizarla
                    if (rutaImagenSeleccionada != null && !rutaImagenSeleccionada.isEmpty()) {
                        FotoPropiedadDAO fotoDAO = new FotoPropiedadDAO();
                        
                        // Eliminar foto anterior si existe
                        if (rutaImagenActual != null && !rutaImagenActual.isEmpty()) {
                            fotoDAO.eliminarFotosPorPropiedad(propiedadIdEnEdicion);
                        }
                        
                        // Agregar nueva foto
                        FotoPropiedad foto = new FotoPropiedad(propiedadIdEnEdicion, rutaImagenSeleccionada);
                        fotoDAO.agregarFoto(foto);
                    }
                    // Si no se seleccionó nueva imagen, mantener la existente
                    
                    mostrarInformacion("Propiedad actualizada exitosamente");
                    cargarPropiedades();
                    cancelarOperacion();
                } else {
                    mostrarError("Error al actualizar la propiedad");
                }
            } else {
                // Crear nueva propiedad
                int idGenerado = propiedadDAO.registrarPropiedad(propiedad);
                if (idGenerado > 0) {
                    // Si se seleccionó una imagen, guardarla en la tabla fotos_propiedad
                    if (rutaImagenSeleccionada != null && !rutaImagenSeleccionada.isEmpty()) {
                        FotoPropiedadDAO fotoDAO = new FotoPropiedadDAO();
                        FotoPropiedad foto = new FotoPropiedad(idGenerado, rutaImagenSeleccionada);
                        fotoDAO.agregarFoto(foto);
                    }
                    mostrarInformacion("Propiedad registrada exitosamente con ID: " + idGenerado);
                    cargarPropiedades();
                    cancelarOperacion();
                } else {
                    mostrarError("Error al registrar la propiedad");
                }
            }
            
        } catch (Exception e) {
            mostrarError("Error al guardar: " + e.getMessage());
        }
    }

    /**
     * Habilita el modo edición para la propiedad seleccionada
     */
    private void editarPropiedad() {
        try {
            if (frmPublicarPropiedad.tblPropiedades == null || frmPublicarPropiedad.tblPropiedades.getSelectedRow() < 0) {
                mostrarError("Seleccione una propiedad para editar");
                return;
            }

            modoEdicion = true;
            propiedadIdEnEdicion = (int) modeloTabla.getValueAt(frmPublicarPropiedad.tblPropiedades.getSelectedRow(), 0);
            habilitarFormulario();
            habilitarBotones(false, true, false, false, true, true);
            
        } catch (Exception e) {
            mostrarError("Error al editar: " + e.getMessage());
        }
    }

    /**
     * Elimina la propiedad seleccionada
     */
    private void eliminarPropiedad() {
        try {
            if (frmPublicarPropiedad.tblPropiedades == null || frmPublicarPropiedad.tblPropiedades.getSelectedRow() < 0) {
                mostrarError("Seleccione una propiedad para eliminar");
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(
                frmPublicarPropiedad,
                "¿Está seguro que desea eliminar esta propiedad?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                int propiedadId = (int) modeloTabla.getValueAt(frmPublicarPropiedad.tblPropiedades.getSelectedRow(), 0);
                boolean eliminado = propiedadDAO.eliminar(propiedadId);
                
                if (eliminado) {
                    mostrarInformacion("Propiedad eliminada exitosamente");
                    cargarPropiedades();
                    limpiarFormulario();
                    deshabilitarFormulario();
                    habilitarBotones(true, false, false, false, false, false);
                } else {
                    mostrarError("Error al eliminar la propiedad");
                }
            }
            
        } catch (Exception e) {
            mostrarError("Error al eliminar: " + e.getMessage());
        }
    }

    /**
     * Cancela la operación actual
     */
    private void cancelarOperacion() {
        modoEdicion = false;
        propiedadIdEnEdicion = -1;
        rutaImagenActual = null;
        limpiarFormulario();
        deshabilitarFormulario();
        habilitarBotones(true, false, false, false, false, false);
    }

    /**
     * Carga los datos de la propiedad seleccionada en el formulario
     */
    private void cargarDatosEnFormulario(int fila) {
        try {
            int propiedadId = (int) modeloTabla.getValueAt(fila, 0);
            Propiedad propiedad = propiedadDAO.obtenerPorId(propiedadId);
            
            if (propiedad != null) {
                frmPublicarPropiedad.txtTituloPropiedad.setText(propiedad.getTitulo());
                frmPublicarPropiedad.txtDescripcion.setText(propiedad.getDescripcion());
                frmPublicarPropiedad.txtDireccion.setText(propiedad.getDireccion());
                frmPublicarPropiedad.txtCiudad.setText(propiedad.getCiudad());
                frmPublicarPropiedad.txtProvincia.setText(propiedad.getProvincia());
                frmPublicarPropiedad.txtPrecio.setText(propiedad.getPrecioMensual().toString());
                frmPublicarPropiedad.txtHabitaciones.setText(String.valueOf(propiedad.getHabitaciones()));
                frmPublicarPropiedad.txtBanos.setText(String.valueOf(propiedad.getBanos()));
                frmPublicarPropiedad.txtArea.setText(String.valueOf(propiedad.getAreaM2()));
                
                // Cargar imagen existente si la hay
                cargarImagenExistente(propiedadId);
                
                // Habilitar botones de edición y eliminación
                habilitarBotones(true, false, true, true, false, true);
            }
        } catch (Exception e) {
            mostrarError("Error al cargar datos: " + e.getMessage());
        }
    }

    /**
     * Crea un objeto Propiedad desde los datos del formulario
     */
    private Propiedad crearPropiedadDesdeFormulario() {
        Propiedad propiedad = new Propiedad();
        
        SesionUsuario sesion = SesionUsuario.getInstance();
        propiedad.setPropietarioId(sesion.getUsuarioActual().getId());
        propiedad.setTitulo(frmPublicarPropiedad.txtTituloPropiedad.getText().trim());
        propiedad.setDescripcion(frmPublicarPropiedad.txtDescripcion.getText().trim());
        propiedad.setDireccion(frmPublicarPropiedad.txtDireccion.getText().trim());
        propiedad.setCiudad(frmPublicarPropiedad.txtCiudad.getText().trim());
        propiedad.setProvincia(frmPublicarPropiedad.txtProvincia.getText().trim());
        propiedad.setPrecioMensual(new BigDecimal(frmPublicarPropiedad.txtPrecio.getText().trim()));
        propiedad.setHabitaciones(Integer.parseInt(frmPublicarPropiedad.txtHabitaciones.getText().trim()));
        propiedad.setBanos(Integer.parseInt(frmPublicarPropiedad.txtBanos.getText().trim()));
        propiedad.setAreaM2(Integer.parseInt(frmPublicarPropiedad.txtArea.getText().trim()));
        propiedad.setEstado("disponible");
        
        return propiedad;
    }

    /**
     * Valida los datos del formulario
     */
    private boolean validarFormulario() {
        if (frmPublicarPropiedad.txtTituloPropiedad.getText().trim().isEmpty()) {
            mostrarError("El título es obligatorio");
            frmPublicarPropiedad.txtTituloPropiedad.requestFocus();
            return false;
        }

        if (frmPublicarPropiedad.txtDescripcion.getText().trim().isEmpty()) {
            mostrarError("La descripción es obligatoria");
            frmPublicarPropiedad.txtDescripcion.requestFocus();
            return false;
        }

        if (frmPublicarPropiedad.txtDireccion.getText().trim().isEmpty()) {
            mostrarError("La dirección es obligatoria");
            frmPublicarPropiedad.txtDireccion.requestFocus();
            return false;
        }

        if (frmPublicarPropiedad.txtCiudad.getText().trim().isEmpty()) {
            mostrarError("La ciudad es obligatoria");
            frmPublicarPropiedad.txtCiudad.requestFocus();
            return false;
        }

        try {
            BigDecimal precio = new BigDecimal(frmPublicarPropiedad.txtPrecio.getText().trim());
            if (precio.compareTo(BigDecimal.ZERO) <= 0) {
                mostrarError("El precio debe ser mayor a 0");
                frmPublicarPropiedad.txtPrecio.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarError("El precio debe ser un número válido");
            frmPublicarPropiedad.txtPrecio.requestFocus();
            return false;
        }

        try {
            int habitaciones = Integer.parseInt(frmPublicarPropiedad.txtHabitaciones.getText().trim());
            if (habitaciones < 1) {
                mostrarError("El número de habitaciones debe ser mayor a 0");
                frmPublicarPropiedad.txtHabitaciones.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarError("El número de habitaciones debe ser un número válido");
            frmPublicarPropiedad.txtHabitaciones.requestFocus();
            return false;
        }

        try {
            int banos = Integer.parseInt(frmPublicarPropiedad.txtBanos.getText().trim());
            if (banos < 1) {
                mostrarError("El número de baños debe ser mayor a 0");
                frmPublicarPropiedad.txtBanos.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarError("El número de baños debe ser un número válido");
            frmPublicarPropiedad.txtBanos.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * Filtra las propiedades por estado
     */
    private void filtrarPorEstado() {
        // Implementar filtrado por estado si es necesario
        cargarPropiedades();
    }

    /**
     * Limpia todos los campos del formulario
     */
    private void limpiarFormulario() {
        frmPublicarPropiedad.txtTituloPropiedad.setText("");
        frmPublicarPropiedad.txtDescripcion.setText("");
        frmPublicarPropiedad.txtDireccion.setText("");
        frmPublicarPropiedad.txtCiudad.setText("");
        frmPublicarPropiedad.txtProvincia.setText("");
        frmPublicarPropiedad.txtPrecio.setText("");
        frmPublicarPropiedad.txtHabitaciones.setText("");
        frmPublicarPropiedad.txtBanos.setText("");
        frmPublicarPropiedad.txtArea.setText("");
        
        // Limpiar referencias de imágenes
        rutaImagenSeleccionada = null;
        rutaImagenActual = null;
        if (frmPublicarPropiedad.btnSubirImagen != null) {
            frmPublicarPropiedad.btnSubirImagen.setText("Subir");
        }
    }

    /**
     * Habilita los campos del formulario
     */
    private void habilitarFormulario() {
        frmPublicarPropiedad.txtTituloPropiedad.setEnabled(true);
        frmPublicarPropiedad.txtDescripcion.setEnabled(true);
        frmPublicarPropiedad.txtDireccion.setEnabled(true);
        frmPublicarPropiedad.txtCiudad.setEnabled(true);
        frmPublicarPropiedad.txtProvincia.setEnabled(true);
        frmPublicarPropiedad.txtPrecio.setEnabled(true);
        frmPublicarPropiedad.txtHabitaciones.setEnabled(true);
        frmPublicarPropiedad.txtBanos.setEnabled(true);
        frmPublicarPropiedad.txtArea.setEnabled(true);
        frmPublicarPropiedad.btnSubirImagen.setEnabled(true);
    }

    /**
     * Deshabilita los campos del formulario
     */
    private void deshabilitarFormulario() {
        frmPublicarPropiedad.txtTituloPropiedad.setEnabled(false);
        frmPublicarPropiedad.txtDescripcion.setEnabled(false);
        frmPublicarPropiedad.txtDireccion.setEnabled(false);
        frmPublicarPropiedad.txtCiudad.setEnabled(false);
        frmPublicarPropiedad.txtProvincia.setEnabled(false);
        frmPublicarPropiedad.txtPrecio.setEnabled(false);
        frmPublicarPropiedad.txtHabitaciones.setEnabled(false);
        frmPublicarPropiedad.txtBanos.setEnabled(false);
        frmPublicarPropiedad.txtArea.setEnabled(false);
        frmPublicarPropiedad.btnSubirImagen.setEnabled(false);
    }

    /**
     * Habilita/deshabilita botones según el estado
     */
    private void habilitarBotones(boolean nuevo, boolean guardar, boolean editar, 
                                 boolean eliminar, boolean cancelar, boolean limpiar) {
        frmPublicarPropiedad.btnNuevo.setEnabled(nuevo);
        frmPublicarPropiedad.btnGuardar.setEnabled(guardar);
        frmPublicarPropiedad.btnEditar.setEnabled(editar);
        frmPublicarPropiedad.btnEliminar.setEnabled(eliminar);
        frmPublicarPropiedad.btnCancelar.setEnabled(cancelar);
        frmPublicarPropiedad.btnLimpiar.setEnabled(limpiar);
    }

    /**
     * Vuelve al formulario principal de propietario
     */
    private void volver() {
        try {
            FrmPropietario frmPropietario = new FrmPropietario();
            new ControladorPropietario(frmPropietario);
            frmPropietario.setVisible(true);
            frmPublicarPropiedad.dispose();
        } catch (Exception e) {
            mostrarError("Error al volver: " + e.getMessage());
        }
    }

    /**
     * Muestra un mensaje de error
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
            frmPublicarPropiedad,
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
            frmPublicarPropiedad,
            mensaje,
            "Información",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Carga la imagen existente de una propiedad si la tiene
     */
    private void cargarImagenExistente(int propiedadId) {
        try {
            FotoPropiedadDAO fotoDAO = new FotoPropiedadDAO();
            FotoPropiedad fotoExistente = fotoDAO.obtenerFotoPrincipal(propiedadId);
            
            if (fotoExistente != null && fotoExistente.getUrlFoto() != null) {
                rutaImagenActual = fotoExistente.getUrlFoto();
                rutaImagenSeleccionada = null; // Limpiar nueva selección
                
                // Actualizar el botón para mostrar que tiene imagen
                if (frmPublicarPropiedad.btnSubirImagen != null) {
                    // Extraer el nombre del archivo de la ruta
                    String nombreArchivo = extraerNombreArchivo(rutaImagenActual);
                    frmPublicarPropiedad.btnSubirImagen.setText("📷 " + nombreArchivo);
                }
                
                System.out.println("Imagen cargada para propiedad ID " + propiedadId + ": " + rutaImagenActual);
            } else {
                rutaImagenActual = null;
                rutaImagenSeleccionada = null;
                
                // Restaurar texto original del botón
                if (frmPublicarPropiedad.btnSubirImagen != null) {
                    frmPublicarPropiedad.btnSubirImagen.setText("Subir");
                }
                
                System.out.println("No se encontró imagen para la propiedad ID: " + propiedadId);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar imagen existente: " + e.getMessage());
            rutaImagenActual = null;
        }
    }

    /**
     * Extrae el nombre del archivo de una ruta
     */
    private String extraerNombreArchivo(String ruta) {
        if (ruta == null || ruta.isEmpty()) {
            return "Sin imagen";
        }
        
        // Si la ruta contiene barras, obtener la última parte
        int ultimaBarra = Math.max(ruta.lastIndexOf('/'), ruta.lastIndexOf('\\'));
        if (ultimaBarra >= 0 && ultimaBarra < ruta.length() - 1) {
            String nombreCompleto = ruta.substring(ultimaBarra + 1);
            // Limitar la longitud del nombre para que no sea muy largo en el botón
            if (nombreCompleto.length() > 15) {
                return nombreCompleto.substring(0, 12) + "...";
            }
            return nombreCompleto;
        }
        
        return "Imagen";
    }

    /**
     * Método para seleccionar una imagen de la propiedad
     */
    private void seleccionarImagen() {
        // Si estamos en modo edición y hay una imagen actual, preguntar si desea cambiar
        if (modoEdicion && rutaImagenActual != null && !rutaImagenActual.isEmpty()) {
            int opcion = JOptionPane.showConfirmDialog(
                frmPublicarPropiedad,
                "Esta propiedad ya tiene una imagen asociada.\n¿Desea cambiarla por una nueva?",
                "Cambiar imagen",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (opcion != JOptionPane.YES_OPTION) {
                return; // No cambiar la imagen
            }
        }
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar imagen de la propiedad");

        // Filtro para imágenes
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
                "Imágenes (JPG, PNG, GIF)", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filtro);

        int resultado = fileChooser.showOpenDialog(frmPublicarPropiedad);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();

            try {
                // Crear directorio de imágenes si no existe
                Path directorioImagenes = Paths.get("src/main/resources/images/propiedades");
                if (!Files.exists(directorioImagenes)) {
                    Files.createDirectories(directorioImagenes);
                }

                // Generar nombre único para la imagen
                String extension = getExtensionArchivo(archivoSeleccionado.getName());
                String nombreArchivo = "propiedad_" + System.currentTimeMillis() + "." + extension;
                Path destino = directorioImagenes.resolve(nombreArchivo);

                // Copiar archivo a la carpeta de recursos
                Files.copy(archivoSeleccionado.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);

                // Guardar ruta relativa para usar en la aplicación
                rutaImagenSeleccionada = "/images/propiedades/" + nombreArchivo;

                // Actualizar texto del botón para mostrar que se seleccionó una imagen
                if (frmPublicarPropiedad.btnSubirImagen != null) {
                    frmPublicarPropiedad.btnSubirImagen.setText("✓ Nueva imagen seleccionada");
                }

                mostrarInformacion("Imagen seleccionada correctamente: " + archivoSeleccionado.getName());

            } catch (IOException e) {
                mostrarError("Error al guardar la imagen: " + e.getMessage());
            }
        }
    }

    /**
     * Método para obtener la extensión de un archivo
     */
    private String getExtensionArchivo(String nombreArchivo) {
        int ultimoPunto = nombreArchivo.lastIndexOf('.');
        if (ultimoPunto > 0 && ultimoPunto < nombreArchivo.length() - 1) {
            return nombreArchivo.substring(ultimoPunto + 1).toLowerCase();
        }
        return "jpg"; // Extensión por defecto
    }
}

