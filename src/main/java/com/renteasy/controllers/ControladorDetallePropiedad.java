package com.renteasy.controllers;

import com.renteasy.dao.FotoPropiedadDAO;
import com.renteasy.dao.SolicitudAlquilerDAO;
import com.renteasy.models.FotoPropiedad;
import com.renteasy.models.Propiedad;
import com.renteasy.models.SolicitudAlquiler;
import com.renteasy.utils.SesionUsuario;
import com.renteasy.views.FrmContacto;
import com.renteasy.views.FrmDetallePropiedad;
import com.renteasy.views.FrmInicio;
import com.renteasy.views.FrmLogin;
import com.renteasy.views.FrmPublicarPropiedad;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * Controlador para el formulario de detalles de propiedad
 * 
 * @author gmart
 */
public class ControladorDetallePropiedad {

    private FrmDetallePropiedad frmDetallePropiedad = new FrmDetallePropiedad();
    private FrmInicio frmInicio = new FrmInicio();
    private FrmContacto frmContacto = new FrmContacto();
    private FrmLogin frmLogin = new FrmLogin();
    private FrmPublicarPropiedad frmPublicarPropiedad = new FrmPublicarPropiedad();
    private Propiedad propiedadActual;

    public ControladorDetallePropiedad() {
    }

    public ControladorDetallePropiedad(FrmDetallePropiedad frmDetallePropiedad, Propiedad propiedad) {
        this.frmDetallePropiedad = frmDetallePropiedad;
        this.propiedadActual = propiedad;

        configurarEventos();
        cargarDatosPropiedad();
        cargarImagenPropiedad();
    }

    private void configurarEventos() {
        // Navegación - Inicio
        frmDetallePropiedad.lblInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorInicio(frmInicio);
                frmInicio.setVisible(true);
                frmDetallePropiedad.dispose();
            }
        });

        // Navegación - Publicar Propiedad
        frmDetallePropiedad.lblPublicarPropiedad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorPublicarPropiedad(frmPublicarPropiedad);
                frmPublicarPropiedad.setVisible(true);
                frmDetallePropiedad.dispose();
            }
        });

        // Navegación - Contacto
        frmDetallePropiedad.lblContacto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorContacto(frmContacto);
                frmContacto.setVisible(true);
                frmDetallePropiedad.dispose();
            }
        });

        // Navegación - Cerrar Sesión
        frmDetallePropiedad.lblCerrarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SesionUsuario.getInstance().cerrarSesion();
                new ControladorLogin(frmLogin);
                frmLogin.setVisible(true);
                frmDetallePropiedad.dispose();
            }
        });

        // Botón Volver
        frmDetallePropiedad.btnVolver.addActionListener(e -> {
            new ControladorInicio(frmInicio);
            frmInicio.setVisible(true);
            frmDetallePropiedad.dispose();
        });

        // Botón Solicitar Alquiler
        frmDetallePropiedad.btnSolicitarAlquiler.addActionListener(e -> {
            solicitarAlquiler();
        });
    }

    /**
     * Método para cargar los datos de la propiedad
     */
    private void cargarDatosPropiedad() {
        if (propiedadActual != null) {
            // Determinar el tipo de propiedad
            String tipoPropiedad = obtenerTipoPropiedad(propiedadActual);

            // Actualizar la información en el formulario
            actualizarInformacion(
                    tipoPropiedad,
                    propiedadActual.getCiudad() + ", " + propiedadActual.getProvincia(),
                    String.valueOf(propiedadActual.getHabitaciones()),
                    String.valueOf(propiedadActual.getBanos()),
                    String.valueOf(propiedadActual.getAreaM2()),
                    propiedadActual.getDescripcion(),
                    propiedadActual.getPrecioMensual().toString());
        }
    }

    /**
     * Método para actualizar la información de la propiedad
     */
    public void actualizarInformacion(String titulo, String ubicacion, String habitaciones,
            String banos, String areaM2, String descripcion, String precio) {
        frmDetallePropiedad.lblTitulo.setText(titulo);
        frmDetallePropiedad.lblUbicacion.setText(ubicacion);
        frmDetallePropiedad.lblHabitaciones.setText(habitaciones + " habs");
        frmDetallePropiedad.lblBanos.setText(banos + " baños");
        frmDetallePropiedad.lblAreaM2.setText(areaM2 + " m²");
        frmDetallePropiedad.txtDescripcion.setText(descripcion);
        frmDetallePropiedad.lblPrecio.setText("$" + precio + "/mes");
    }

    /**
     * Método para cargar la imagen de la propiedad
     */
    private void cargarImagenPropiedad() {
        if (propiedadActual != null) {
            SwingWorker<ImageIcon, Void> worker = new SwingWorker<>() {
                @Override
                protected ImageIcon doInBackground() throws Exception {
                    try {
                        FotoPropiedadDAO fotoDAO = new FotoPropiedadDAO();
                        FotoPropiedad fotoPrincipal = fotoDAO.obtenerFotoPrincipal(propiedadActual.getId());

                        if (fotoPrincipal != null && fotoPrincipal.getUrlFoto() != null) {
                            String rutaImagen = fotoPrincipal.getUrlFoto();

                            // Intentar cargar desde recursos primero
                            try {
                                java.net.URL url = getClass().getResource(rutaImagen);
                                if (url != null) {
                                    Image img = ImageIO.read(url);
                                    if (img != null) {
                                        // Redimensionar para el panel de imagen
                                        Image scaled = img.getScaledInstance(500, 400, Image.SCALE_SMOOTH);
                                        return new ImageIcon(scaled);
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("No se pudo cargar desde recursos: " + e.getMessage());
                            }

                            // Intentar cargar desde archivo local
                            try {
                                File archivoImagen = new File(rutaImagen);
                                if (archivoImagen.exists()) {
                                    Image img = ImageIO.read(archivoImagen);
                                    if (img != null) {
                                        Image scaled = img.getScaledInstance(500, 400, Image.SCALE_SMOOTH);
                                        return new ImageIcon(scaled);
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("No se pudo cargar desde archivo: " + e.getMessage());
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error al cargar imagen: " + e.getMessage());
                    }
                    return null;
                }

                @Override
                protected void done() {
                    try {
                        ImageIcon icon = get();
                        establecerImagen(icon);
                    } catch (Exception ex) {
                        System.out.println("Error al establecer imagen: " + ex.getMessage());
                        establecerImagen(null);
                    }
                }
            };
            worker.execute();
        }
    }

    /**
     * Método para establecer la imagen
     * 
     * @param imagen La imagen a establecer
     */
    public void establecerImagen(javax.swing.ImageIcon imagen) {
        if (imagen != null) {
            // Redimensionar la imagen para que se ajuste al panel
            java.awt.Image img = imagen.getImage();
            java.awt.Image scaledImg = img.getScaledInstance(frmDetallePropiedad.panelImagen.getWidth(),
                    frmDetallePropiedad.panelImagen.getHeight(), java.awt.Image.SCALE_SMOOTH);
            frmDetallePropiedad.lblImagen.setIcon(new javax.swing.ImageIcon(scaledImg));
            frmDetallePropiedad.lblImagen.setText("");
        } else {
            frmDetallePropiedad.lblImagen.setIcon(null);
            frmDetallePropiedad.lblImagen.setText("Sin imagen disponible");
            frmDetallePropiedad.lblImagen.setFont(new java.awt.Font("Segoe UI", 0, 16));
            frmDetallePropiedad.lblImagen.setForeground(new java.awt.Color(108, 117, 125));
        }
    }

    /**
     * Método para solicitar el alquiler de una propiedad
     */
    private void solicitarAlquiler() {
        SesionUsuario sesion = SesionUsuario.getInstance();

        // Verificar si hay sesión activa
        if (!sesion.haySesionActiva()) {
            JOptionPane.showMessageDialog(frmDetallePropiedad,
                    "Debe iniciar sesión para solicitar el alquiler de una propiedad",
                    "Iniciar Sesión Requerido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verificar que no sea el propietario de la propiedad
        if (sesion.getIdUsuarioActual() == propiedadActual.getPropietarioId()) {
            JOptionPane.showMessageDialog(frmDetallePropiedad,
                    "No puede solicitar el alquiler de su propia propiedad",
                    "Acción no permitida",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Solicitar mensaje personalizado
        String mensaje = JOptionPane.showInputDialog(frmDetallePropiedad,
                "Ingrese un mensaje para el propietario (opcional):",
                "Solicitud de Alquiler",
                JOptionPane.QUESTION_MESSAGE);

        // Si el usuario cancela, no continuar
        if (mensaje == null) {
            return;
        }

        // Si no escribió mensaje, usar uno por defecto
        if (mensaje.trim().isEmpty()) {
            mensaje = "Estoy interesado en alquilar esta propiedad. Me gustaría obtener más información.";
        }

        // Crear la solicitud
        SolicitudAlquiler solicitud = new SolicitudAlquiler();
        solicitud.setInquilinoId(sesion.getIdUsuarioActual());
        solicitud.setPropiedadId(propiedadActual.getId());
        solicitud.setMensaje(mensaje);

        // Guardar en la base de datos
        SolicitudAlquilerDAO solicitudDAO = new SolicitudAlquilerDAO();
        boolean exitoso = solicitudDAO.crearSolicitud(solicitud);

        if (exitoso) {
            JOptionPane.showMessageDialog(frmDetallePropiedad,
                    "Su solicitud de alquiler ha sido enviada exitosamente.\n" +
                            "El propietario será notificado y se pondrá en contacto con usted.",
                    "Solicitud Enviada",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frmDetallePropiedad,
                    "Hubo un error al enviar su solicitud. Por favor, inténtelo nuevamente.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método para obtener el tipo de propiedad basado en su descripción y título.
     *
     * @param p La propiedad de la cual se desea obtener el tipo.
     * @return Una cadena que representa el tipo de propiedad.
     */
    private String obtenerTipoPropiedad(Propiedad p) {
        String descripcion = p.getDescripcion().toLowerCase();
        String titulo = p.getTitulo().toLowerCase();

        if (titulo.contains("villa") || descripcion.contains("villa")) {
            return "Villa con Piscina";
        } else if (titulo.contains("apartamento") || descripcion.contains("apartamento") || titulo.contains("apto")) {
            return "Acogedor Apartamento";
        } else if (titulo.contains("casa") || descripcion.contains("casa") || titulo.contains("vivienda")) {
            return "Casa Familiar";
        } else if (titulo.contains("estudio") || descripcion.contains("estudio")) {
            return "Estudio Moderno";
        } else if (titulo.contains("penthouse") || descripcion.contains("penthouse")) {
            return "Penthouse de Lujo";
        } else {
            return "Propiedad en Alquiler";
        }
    }
}
