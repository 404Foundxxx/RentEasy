package com.renteasy.controllers;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import com.renteasy.dao.PropiedadDAO;
import com.renteasy.dao.FotoPropiedadDAO;
import com.renteasy.models.Propiedad;
import com.renteasy.models.FotoPropiedad;
import com.renteasy.utils.SesionUsuario;
import com.renteasy.utils.IconManager;
import java.util.List;

import com.renteasy.views.FrmContacto;
import com.renteasy.views.FrmDetallePropiedad;
import com.renteasy.views.FrmInicio;
import com.renteasy.views.FrmLogin;
import com.renteasy.views.FrmPublicarPropiedad;

/**
 * Controlador para el formulario de inicio de la aplicación RentEasy.
 * Maneja la visualización de propiedades, filtros de búsqueda y navegación
 * entre las diferentes secciones de la aplicación.
 * 
 * Funcionalidades principales:
 * - Configuración de filtros dinámicos (provincia, ciudad, habitaciones,
 * precio)
 * - Búsqueda de propiedades con filtros aplicados
 * - Visualización de propiedades en formato de tarjetas modernas
 * - Navegación a otras secciones (publicar, contacto, cerrar sesión)
 *
 */
public class ControladorInicio {

    private FrmInicio frmInicio = new FrmInicio();
    private FrmContacto frmContacto = new FrmContacto();
    private FrmLogin frmLogin = new FrmLogin();
    private FrmPublicarPropiedad frmPublicarPropiedad = new FrmPublicarPropiedad();

    /**
     * Constructor por defecto.
     * Inicializa los formularios pero no configura eventos.
     */
    public ControladorInicio() {

    }

    /**
     * Constructor con parámetros para inicializar los componentes del frame
     * Propiedades. Configura todos los eventos y filtros del formulario.
     *
     * @param fp El formulario de inicio a controlar
     */
    public ControladorInicio(FrmInicio fp) {
        this.frmInicio = fp;

        // Configurar iconos para la ventana y elementos
        configurarIconos();

        // Configurar filtros iniciales
        configurarFiltrosComboBox();

        // Boton Aplicar filtros - Ejecuta búsqueda con los filtros seleccionados
        this.frmInicio.btnBuscar.addActionListener(e -> {
            aplicarFiltrosBusqueda();
        });

        // Label Publicar Propiedad
        this.frmInicio.lblPublicarPropiedad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorPublicarPropiedad(frmPublicarPropiedad);
                frmPublicarPropiedad.setVisible(true);
                frmInicio.dispose();

            }
        });
        // Label Contacto
        this.frmInicio.lblContacto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorContacto(frmContacto);
                frmContacto.setVisible(true);
                frmInicio.dispose();

            }
        });
        // Label Cerrar Sesion
        this.frmInicio.lblCerrarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Cerrar sesión del usuario actual
                SesionUsuario.getInstance().cerrarSesion();

                new ControladorLogin(frmLogin);
                frmLogin.setVisible(true);
                frmInicio.dispose();

            }
        });

        // Mostrar propiedades al abrir
        mostrarPropiedades();
    }

    /**
     * Configura los iconos SVG para los elementos de la interfaz.
     */
    private void configurarIconos() {
        // Icono para la ventana principal
        IconManager.setIcono(frmInicio, "home", 32);

        // Iconos para los botones y labels principales
        IconManager.setButtonIcon(frmInicio.btnBuscar, "search", IconManager.SIZE_SMALL);

        // Si existen labels de navegación, configurar sus iconos
        if (frmInicio.lblPublicarPropiedad != null) {
            IconManager.setLabelIcon(frmInicio.lblPublicarPropiedad, "add-property", IconManager.SIZE_SMALL);
        }

        if (frmInicio.lblContacto != null) {
            IconManager.setLabelIcon(frmInicio.lblContacto, "contact", IconManager.SIZE_SMALL);
        }

        if (frmInicio.lblCerrarSesion != null) {
            IconManager.setLabelIcon(frmInicio.lblCerrarSesion, "logout", IconManager.SIZE_SMALL);
        }
    }

    /**
     * Muestra las propiedades en el panel principal con diseño de tarjetas
     * modernas.
     * Utiliza todas las propiedades disponibles sin filtros.
     */
    private void mostrarPropiedades() {
        PropiedadDAO dao = new PropiedadDAO();
        List<Propiedad> propiedades = dao.listar();
        mostrarPropiedadesEnPanel(propiedades);
    }

    /**
     * Configura los valores iniciales de los ComboBox de filtros.
     * Carga datos dinámicos desde la base de datos y opciones predefinidas.
     */
    private void configurarFiltrosComboBox() {
        // Configurar ComboBox de Provincia
        configurarComboBoxProvincia();

        // Configurar ComboBox de Ciudad/Sector
        configurarComboBoxCiudad();

        // Configurar ComboBox de Habitaciones
        configurarComboBoxHabitaciones();

        // Configurar ComboBox de Precio
        configurarComboBoxPrecio();
    }

    /**
     * Configura las opciones del ComboBox de provincias.
     * Obtiene provincias únicas desde la base de datos.
     */
    private void configurarComboBoxProvincia() {
        frmInicio.cmbProvincia.removeAllItems();
        frmInicio.cmbProvincia.addItem("Todas las provincias");

        // Obtener provincias únicas de las propiedades registradas
        PropiedadDAO dao = new PropiedadDAO();
        List<Propiedad> propiedades = dao.listar();

        propiedades.stream()
                .map(Propiedad::getProvincia)
                .filter(provincia -> provincia != null && !provincia.trim().isEmpty())
                .distinct()
                .sorted()
                .forEach(provincia -> frmInicio.cmbProvincia.addItem(provincia));
    }

    /**
     * Configura las opciones del ComboBox de ciudades/sectores.
     * Obtiene ciudades únicas desde la base de datos.
     */
    private void configurarComboBoxCiudad() {
        frmInicio.cmbCiudadSector.removeAllItems();
        frmInicio.cmbCiudadSector.addItem("Todas las ciudades");

        // Obtener ciudades únicas de las propiedades registradas
        PropiedadDAO dao = new PropiedadDAO();
        List<Propiedad> propiedades = dao.listar();

        propiedades.stream()
                .map(Propiedad::getCiudad)
                .filter(ciudad -> ciudad != null && !ciudad.trim().isEmpty())
                .distinct()
                .sorted()
                .forEach(ciudad -> frmInicio.cmbCiudadSector.addItem(ciudad));
    }

    /**
     * Configura las opciones del ComboBox de número de habitaciones.
     * Proporciona opciones comunes de habitaciones.
     */
    private void configurarComboBoxHabitaciones() {
        frmInicio.cmbHabitaciones.removeAllItems();
        frmInicio.cmbHabitaciones.addItem("Cualquier cantidad");
        frmInicio.cmbHabitaciones.addItem("1+ habitación");
        frmInicio.cmbHabitaciones.addItem("2+ habitaciones");
        frmInicio.cmbHabitaciones.addItem("3+ habitaciones");
        frmInicio.cmbHabitaciones.addItem("4+ habitaciones");
        frmInicio.cmbHabitaciones.addItem("5+ habitaciones");
    }

    /**
     * Configura las opciones del ComboBox de rangos de precio.
     * Proporciona rangos de precio comunes en USD.
     */
    private void configurarComboBoxPrecio() {
        frmInicio.cmbPrecio.removeAllItems();
        frmInicio.cmbPrecio.addItem("Cualquier precio");
        frmInicio.cmbPrecio.addItem("$0 - $500");
        frmInicio.cmbPrecio.addItem("$500 - $1,000");
        frmInicio.cmbPrecio.addItem("$1,000 - $1,500");
        frmInicio.cmbPrecio.addItem("$1,500 - $2,000");
        frmInicio.cmbPrecio.addItem("$2,000 - $3,000");
        frmInicio.cmbPrecio.addItem("$3,000+");
    }

    /**
     * Método para aplicar los filtros de búsqueda seleccionados
     */
    private void aplicarFiltrosBusqueda() {
        // Obtener valores seleccionados de los filtros
        String provinciaSeleccionada = obtenerProvinciaSeleccionada();
        String ciudadSeleccionada = obtenerCiudadSeleccionada();
        Integer habitacionesMinimas = obtenerHabitacionesMinimas();
        Double[] rangoPrecio = obtenerRangoPrecioSeleccionado();

        // Realizar búsqueda con filtros
        PropiedadDAO dao = new PropiedadDAO();
        List<Propiedad> propiedadesFiltradas = dao.buscarConFiltros(
                ciudadSeleccionada,
                provinciaSeleccionada,
                rangoPrecio[0], // precio mínimo
                rangoPrecio[1], // precio máximo
                habitacionesMinimas,
                "disponible" // solo propiedades disponibles
        );

        // Mostrar resultados filtrados
        mostrarPropiedadesEnPanel(propiedadesFiltradas);

        // Mostrar mensaje de resultados encontrados
        mostrarMensajeResultados(propiedadesFiltradas.size());
    }

    /**
     * Obtiene la provincia seleccionada del ComboBox.
     * 
     * @return La provincia seleccionada o null si es "Todas las provincias"
     */
    private String obtenerProvinciaSeleccionada() {
        String provincia = (String) frmInicio.cmbProvincia.getSelectedItem();
        return (provincia != null && !provincia.equals("Todas las provincias")) ? provincia : null;
    }

    /**
     * Obtiene la ciudad seleccionada del ComboBox.
     * 
     * @return La ciudad seleccionada o null si es "Todas las ciudades"
     */
    private String obtenerCiudadSeleccionada() {
        String ciudad = (String) frmInicio.cmbCiudadSector.getSelectedItem();
        return (ciudad != null && !ciudad.equals("Todas las ciudades")) ? ciudad : null;
    }

    /**
     * Obtiene el número mínimo de habitaciones del ComboBox seleccionado.
     * 
     * @return El número mínimo de habitaciones o null si es "Cualquier cantidad"
     */
    private Integer obtenerHabitacionesMinimas() {
        String habitaciones = (String) frmInicio.cmbHabitaciones.getSelectedItem();
        if (habitaciones == null || habitaciones.equals("Cualquier cantidad")) {
            return null;
        }

        // Extraer número del texto "X+ habitaciones"
        try {
            return Integer.parseInt(habitaciones.substring(0, 1));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Obtiene el rango de precios seleccionado del ComboBox.
     * 
     * @return Array con [precioMinimo, precioMaximo], ambos pueden ser null
     */
    private Double[] obtenerRangoPrecioSeleccionado() {
        String precio = (String) frmInicio.cmbPrecio.getSelectedItem();
        Double[] rango = new Double[2]; // [min, max]

        if (precio == null || precio.equals("Cualquier precio")) {
            return rango; // ambos null
        }

        switch (precio) {
            case "$0 - $500":
                rango[0] = 0.0;
                rango[1] = 500.0;
                break;
            case "$500 - $1,000":
                rango[0] = 500.0;
                rango[1] = 1000.0;
                break;
            case "$1,000 - $1,500":
                rango[0] = 1000.0;
                rango[1] = 1500.0;
                break;
            case "$1,500 - $2,000":
                rango[0] = 1500.0;
                rango[1] = 2000.0;
                break;
            case "$2,000 - $3,000":
                rango[0] = 2000.0;
                rango[1] = 3000.0;
                break;
            case "$3,000+":
                rango[0] = 3000.0;
                rango[1] = null; // sin límite superior
                break;
        }

        return rango;
    }

    /**
     * Muestra las propiedades en el panel con el diseño de tarjetas.
     * 
     * @param propiedades Lista de propiedades a mostrar
     */
    private void mostrarPropiedadesEnPanel(List<Propiedad> propiedades) {
        JPanel panel = frmInicio.getPanelPropiedades();
        panel.removeAll();

        // Configurar el layout del panel principal para grid de 3 columnas
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;

        int columna = 0;
        int fila = 0;

        for (Propiedad p : propiedades) {
            JPanel card = crearTarjetaPropiedadModerna(p);

            gbc.gridx = columna;
            gbc.gridy = fila;
            panel.add(card, gbc);

            columna++;
            if (columna >= 3) {
                columna = 0;
                fila++;
            }
        }

        // Agregar espacio flexible al final
        gbc.gridx = 0;
        gbc.gridy = fila + 1;
        gbc.gridwidth = 3;
        gbc.weighty = 1.0;
        panel.add(new JPanel(), gbc);

        panel.revalidate();
        panel.repaint();
    }

    /**
     * Muestra un mensaje con el número de resultados encontrados.
     * 
     * @param numeroResultados Cantidad de propiedades encontradas
     */
    private void mostrarMensajeResultados(int numeroResultados) {
        String mensaje;
        if (numeroResultados == 0) {
            mensaje = "No se encontraron propiedades con los filtros seleccionados.";
        } else if (numeroResultados == 1) {
            mensaje = "Se encontró 1 propiedad.";
        } else {
            mensaje = String.format("Se encontraron %d propiedades.", numeroResultados);
        }

        JOptionPane.showMessageDialog(
                frmInicio,
                mensaje,
                "Resultados de búsqueda",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Limpia todos los filtros y restablece los ComboBox a sus valores por defecto.
     * Útil para mostrar todas las propiedades nuevamente.
     */
    public void limpiarFiltros() {
        frmInicio.cmbProvincia.setSelectedIndex(0); // "Todas las provincias"
        frmInicio.cmbCiudadSector.setSelectedIndex(0); // "Todas las ciudades"
        frmInicio.cmbHabitaciones.setSelectedIndex(0); // "Cualquier cantidad"
        frmInicio.cmbPrecio.setSelectedIndex(0); // "Cualquier precio"

        // Mostrar todas las propiedades
        mostrarPropiedades();
    }

    /**
     * Crea una tarjeta moderna para mostrar la información de una propiedad
     * Estilo similar a las imágenes de referencia con diseño cuadrado
     *
     * @param p La propiedad a mostrar
     * @return Un JPanel con la tarjeta moderna de la propiedad
     */
    private JPanel crearTarjetaPropiedadModerna(Propiedad p) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(350, 400));
        card.setMaximumSize(new Dimension(350, 400));
        card.setMinimumSize(new Dimension(350, 400));

        // Border redondeado con sombra
        card.setBorder(BorderFactory.createEtchedBorder());

        // Panel de imagen (parte superior)
        JPanel imagenPanel = new JPanel();
        imagenPanel.setLayout(new BorderLayout());
        imagenPanel.setPreferredSize(new Dimension(350, 200));
        imagenPanel.setBackground(new Color(240, 240, 240));

        JLabel imagenLabel = new JLabel();
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagenLabel.setVerticalAlignment(SwingConstants.CENTER);
        imagenPanel.add(imagenLabel, BorderLayout.CENTER);

        // Cargar imagen de la propiedad
        cargarImagenPropiedad(p, imagenLabel);

        card.add(imagenPanel, BorderLayout.NORTH);

        // Panel de información (parte inferior)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel de detalles
        JPanel detallesPanel = new JPanel();
        detallesPanel.setLayout(new BoxLayout(detallesPanel, BoxLayout.Y_AXIS));
        detallesPanel.setBackground(Color.WHITE);

        // Título de la propiedad
        String tipoPropiedad = obtenerTipoPropiedad(p);
        JLabel tituloLabel = new JLabel(tipoPropiedad);
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tituloLabel.setForeground(new Color(51, 51, 51));
        tituloLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Ubicación con icono
        JLabel ubicacionLabel = new JLabel(p.getCiudad() + ", " + p.getProvincia());
        IconManager.setLabelIcon(ubicacionLabel, "location", IconManager.SIZE_SMALL);
        ubicacionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ubicacionLabel.setForeground(new Color(102, 102, 102));
        ubicacionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Precio con icono
        JLabel precioLabel = new JLabel("$" + p.getPrecioMensual() + "/mes");
        IconManager.setLabelIcon(precioLabel, "price", IconManager.SIZE_SMALL);
        precioLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        precioLabel.setForeground(new Color(0, 119, 182));
        precioLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        detallesPanel.add(tituloLabel);
        detallesPanel.add(Box.createVerticalStrut(5));
        detallesPanel.add(ubicacionLabel);
        detallesPanel.add(Box.createVerticalStrut(10));
        detallesPanel.add(precioLabel);

        infoPanel.add(detallesPanel, BorderLayout.CENTER);

        // Botón "Ver más"
        JButton verMasBtn = new JButton("Ver más");
        IconManager.setButtonIcon(verMasBtn, "view", IconManager.SIZE_SMALL);
        verMasBtn.setBackground(new Color(0, 119, 182));
        verMasBtn.setForeground(Color.WHITE);
        verMasBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        verMasBtn.setFocusPainted(false);
        verMasBtn.setBorderPainted(false);
        verMasBtn.setPreferredSize(new Dimension(120, 35));
        verMasBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Añadir acción al botón
        verMasBtn.addActionListener(e -> {
            // Abrir formulario de detalles de la propiedad
            FrmDetallePropiedad frmDetalle = new FrmDetallePropiedad();
            new ControladorDetallePropiedad(frmDetalle, p);
            frmDetalle.setVisible(true);
            frmInicio.dispose();
        });

        JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botonPanel.setBackground(Color.WHITE);
        botonPanel.add(verMasBtn);

        infoPanel.add(botonPanel, BorderLayout.SOUTH);
        card.add(infoPanel, BorderLayout.CENTER);

        return card;
    }

    /**
     * Método para cargar la imagen de la propiedad
     *
     * @param p           La propiedad a cargar
     * @param imagenLabel El JLabel donde se mostrará la imagen
     */
    private void cargarImagenPropiedad(Propiedad p, JLabel imagenLabel) {
        FotoPropiedadDAO fotoDAO = new FotoPropiedadDAO();
        FotoPropiedad fotoPrincipal = fotoDAO.obtenerFotoPrincipal(p.getId());
        String imageUrl = (fotoPrincipal != null)
                ? fotoPrincipal.getUrlFoto()
                : "/images/propiedades/default.jpg";

        // Mostrar mensaje de carga mientras se carga la imagen
        imagenLabel.setText("Cargando imagen...");
        imagenLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        imagenLabel.setForeground(new Color(102, 102, 102));
        imagenLabel.setIcon(null);

        SwingWorker<ImageIcon, Void> worker = new SwingWorker<>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                try {
                    URL url = getClass().getResource(imageUrl);
                    if (url != null) {
                        Image img = ImageIO.read(url);
                        if (img != null) {
                            Image scaled = img.getScaledInstance(350, 200, Image.SCALE_SMOOTH);
                            return new ImageIcon(scaled);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error cargando imagen: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void done() {
                try {
                    ImageIcon icon = get();
                    if (icon != null) {
                        imagenLabel.setIcon(icon);
                        imagenLabel.setText(""); // Limpiar el texto de carga
                    } else {
                        // Imagen por defecto si no se puede cargar
                        imagenLabel.setText("[ X ] Imagen no disponible");
                        imagenLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                        imagenLabel.setForeground(new Color(200, 200, 200));
                    }
                } catch (Exception ex) {
                    imagenLabel.setText("[ X ]");
                    imagenLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
                    imagenLabel.setForeground(Color.GRAY);
                }
            }
        };
        worker.execute();
    }

    /**
     * Método para obtener el tipo de propiedad basado en su descripción.
     * Utiliza palabras clave comunes para determinar el tipo.
     *
     * @param p La propiedad a analizar
     * @return Una cadena descriptiva del tipo de propiedad
     */
    private String obtenerTipoPropiedad(Propiedad p) {
        String descripcion = p.getDescripcion().toLowerCase();
        if (descripcion.contains("apartamento") || descripcion.contains("apto")) {
            return "Acogedor Apartamento";
        } else if (descripcion.contains("casa") || descripcion.contains("vivienda")) {
            return "Casa Familiar";
        } else if (descripcion.contains("villa")) {
            return "Villa";
        } else if (descripcion.contains("estudio")) {
            return "Estudio";
        } else {
            return "Propiedad en Alquiler";
        }
    }
}
