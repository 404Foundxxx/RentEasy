package com.renteasy.controllers;

import com.renteasy.views.FrmContacto;
import com.renteasy.dao.PropiedadDAO;
import com.renteasy.dao.FotoPropiedadDAO;
import com.renteasy.models.Propiedad;
import com.renteasy.models.FotoPropiedad;
import com.renteasy.utils.SesionUsuario;
import com.renteasy.utils.IconManager;
import java.math.BigDecimal;
import com.renteasy.views.FrmInicio;
import com.renteasy.views.FrmLogin;
import com.renteasy.views.FrmPublicarPropiedad;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * Controlador para manejar la lógica de publicación de propiedades en la
 * aplicación RentEasy.
 * 
 * @author gmart
 */
public class ControladorPublicarPropiedad {

    private FrmPublicarPropiedad frmPublicarPropiedad = new FrmPublicarPropiedad();
    private FrmInicio frmInicio = new FrmInicio();
    private FrmContacto frmContacto = new FrmContacto();
    private FrmLogin frmLogin = new FrmLogin();
    private String rutaImagenSeleccionada = null; // Para almacenar la ruta de la imagen seleccionada

    public ControladorPublicarPropiedad() {

    }

    public ControladorPublicarPropiedad(FrmPublicarPropiedad fpp) {
        this.frmPublicarPropiedad = fpp;

        configurarIconos();
        configurarEventos();

    }

    private void configurarEventos() {
        // Label Inicio
        this.frmPublicarPropiedad.lblInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorInicio(frmInicio);
                frmInicio.setVisible(true);
                frmPublicarPropiedad.dispose();
            }
        });

        // Botón Publicar Propiedad
        this.frmPublicarPropiedad.btnPublicarPropiedad.addActionListener(e -> {
            registrarYMostrar();
        });

        // Botón Subir Imagen
        this.frmPublicarPropiedad.btnSubir.addActionListener(e -> {
            seleccionarImagen();
        });
        // Label Contacto
        this.frmPublicarPropiedad.lblContacto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorContacto(frmContacto);
                frmContacto.setVisible(true);
                frmPublicarPropiedad.dispose();

            }
        });
        // Label Cerrar Sesion
        this.frmPublicarPropiedad.lblCerrarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Cerrar sesión del usuario actual
                SesionUsuario.getInstance().cerrarSesion();

                new ControladorLogin(frmLogin);
                frmLogin.setVisible(true);
                frmPublicarPropiedad.dispose();

            }
        });
    }


    /**
     * Método para registrar la propiedad y mostrar un mensaje de éxito.
     */
    private void registrarYMostrar() {
        try {
            // Verificar que hay un usuario logueado
            SesionUsuario sesion = SesionUsuario.getInstance();
            if (!sesion.haySesionActiva()) {
                JOptionPane.showMessageDialog(frmPublicarPropiedad,
                        "Debe iniciar sesión para publicar una propiedad",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar que los campos obligatorios estén completos
            if (frmPublicarPropiedad.txtTitulo.getText().trim().isEmpty() ||
                    frmPublicarPropiedad.txtDescripcion.getText().trim().isEmpty() ||
                    frmPublicarPropiedad.txtCiudad.getText().trim().isEmpty() ||
                    frmPublicarPropiedad.txtProvincia.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(frmPublicarPropiedad, "Por favor complete todos los campos obligatorios");
                return;
            }

            // Recoger datos reales del formulario
            String titulo = frmPublicarPropiedad.txtTitulo.getText().trim();
            String descripcion = frmPublicarPropiedad.txtDescripcion.getText().trim();
            String direccion = titulo; // Usar título como dirección por ahora, o agregar campo específico
            String ciudad = frmPublicarPropiedad.txtCiudad.getText().trim();
            String provincia = frmPublicarPropiedad.txtProvincia.getText().trim();
            BigDecimal precio = new BigDecimal(frmPublicarPropiedad.jSpinner1.getValue().toString());
            int habitaciones = (int) frmPublicarPropiedad.spnHabitaciones.getValue();
            int banos = (int) frmPublicarPropiedad.spnBanos.getValue();
            int areaM2 = (int) frmPublicarPropiedad.spnAreaM2.getValue();
            int propietarioId = sesion.getIdUsuarioActual(); // Obtener ID del usuario logueado
            String estado = "disponible";

            Propiedad p = new Propiedad(propietarioId, titulo, descripcion, direccion, ciudad, provincia, precio,
                    habitaciones, banos, areaM2, estado);
            PropiedadDAO dao = new PropiedadDAO();
            int idGenerado = dao.registrarPropiedad(p);

            if (idGenerado > 0) {
                // Si se seleccionó una imagen, guardarla en la tabla fotos_propiedad
                if (rutaImagenSeleccionada != null && !rutaImagenSeleccionada.isEmpty()) {
                    FotoPropiedadDAO fotoDAO = new FotoPropiedadDAO();
                    FotoPropiedad foto = new FotoPropiedad(idGenerado, rutaImagenSeleccionada);
                    fotoDAO.agregarFoto(foto);
                }

                JOptionPane.showMessageDialog(frmPublicarPropiedad,
                        "Propiedad registrada exitosamente con ID: " + idGenerado);

                // Limpiar formulario después del registro exitoso
                frmPublicarPropiedad.txtTitulo.setText("");
                frmPublicarPropiedad.txtDescripcion.setText("");
                frmPublicarPropiedad.txtCiudad.setText("Ej: Piantini");
                frmPublicarPropiedad.txtProvincia.setText("Ej: Distrito Nacional");
                frmPublicarPropiedad.jSpinner1.setValue(0);
                frmPublicarPropiedad.spnHabitaciones.setValue(0);
                frmPublicarPropiedad.spnBanos.setValue(0);
                frmPublicarPropiedad.spnAreaM2.setValue(0);
                rutaImagenSeleccionada = null; // Limpiar imagen seleccionada
                frmPublicarPropiedad.btnSubir.setText("Subir"); // Restablecer texto del botón

                // Ir al inicio y mostrar la nueva propiedad
                FrmInicio frmInicio = new FrmInicio();
                new ControladorInicio(frmInicio);
                frmInicio.setVisible(true);
                frmPublicarPropiedad.dispose();
            } else {
                JOptionPane.showMessageDialog(frmPublicarPropiedad,
                        "Error al registrar la propiedad. Intente nuevamente.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmPublicarPropiedad, "Error al registrar propiedad: " + ex.getMessage());
        }
    }


    /**
     * Método para seleccionar una imagen de la propiedad.
     */
    private void seleccionarImagen() {
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
                frmPublicarPropiedad.btnSubir.setText("✓ Imagen seleccionada");

                JOptionPane.showMessageDialog(frmPublicarPropiedad,
                        "Imagen seleccionada correctamente: " + archivoSeleccionado.getName());

            } catch (IOException e) {
                JOptionPane.showMessageDialog(frmPublicarPropiedad,
                        "Error al guardar la imagen: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /*
     * Método para obtener la extensión de un archivo.
     */
    private String getExtensionArchivo(String nombreArchivo) {
        int puntoIndex = nombreArchivo.lastIndexOf('.');
        if (puntoIndex > 0 && puntoIndex < nombreArchivo.length() - 1) {
            return nombreArchivo.substring(puntoIndex + 1).toLowerCase();
        }
        return "jpg"; // Extensión por defecto
    }

    /**
     * Configura los iconos SVG para los elementos de la interfaz de publicar
     * propiedad.
     */
    private void configurarIconos() {
        // Icono para la ventana de publicar propiedad
        IconManager.setIcono(frmPublicarPropiedad, "add-property", 32);

        // Iconos para botones
        IconManager.setButtonIcon(frmPublicarPropiedad.btnPublicarPropiedad, "save", IconManager.SIZE_MEDIUM);
        IconManager.setButtonIcon(frmPublicarPropiedad.btnSubir, "upload", IconManager.SIZE_MEDIUM);

        // Iconos para labels de navegación
        if (frmPublicarPropiedad.lblInicio != null) {
            IconManager.setLabelIcon(frmPublicarPropiedad.lblInicio, "home", IconManager.SIZE_SMALL);
        }

        if (frmPublicarPropiedad.lblContacto != null) {
            IconManager.setLabelIcon(frmPublicarPropiedad.lblContacto, "contact", IconManager.SIZE_SMALL);
        }

        if (frmPublicarPropiedad.lblCerrarSesion != null) {
            IconManager.setLabelIcon(frmPublicarPropiedad.lblCerrarSesion, "logout", IconManager.SIZE_SMALL);
        }
    }

}
