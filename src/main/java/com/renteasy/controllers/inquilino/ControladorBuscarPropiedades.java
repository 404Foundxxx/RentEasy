package com.renteasy.controllers.inquilino;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.renteasy.dao.*;
import com.renteasy.models.*;
import com.renteasy.views.inquilino.*;

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
public class ControladorBuscarPropiedades {

    private FrmBuscarPropiedades frmBuscarPropiedades = new FrmBuscarPropiedades();

    public ControladorBuscarPropiedades() {

    }

    public ControladorBuscarPropiedades(FrmBuscarPropiedades fp) {
        this.frmBuscarPropiedades = fp;
        configurarFiltrosComboBox();
        configurarEventos();
        mostrarPropiedades();
    }

    private void configurarEventos() {
        frmBuscarPropiedades.btnBuscar.addActionListener(e -> aplicarFiltrosBusqueda());

        // Label Volver
        frmBuscarPropiedades.lblVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                volver();
            }
        });
        frmBuscarPropiedades.btnLimpiarFiltros.addActionListener(e -> limpiarFiltros());
    }

    private void volver() {
        try {
            FrmInquilino frmInquilino = new FrmInquilino();
            new ControladorInquilino(frmInquilino);
            frmInquilino.setVisible(true);
            frmBuscarPropiedades.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmBuscarPropiedades,
                    "Error al abrir el mantenimiento de usuarios: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
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
        frmBuscarPropiedades.cmbProvincia.removeAllItems();
        frmBuscarPropiedades.cmbProvincia.addItem("Todas las provincias");

        // Obtener provincias únicas de las propiedades registradas
        PropiedadDAO dao = new PropiedadDAO();
        var propiedades = dao.listar();

        propiedades.stream()
                .map(Propiedad::getProvincia)
                .filter(provincia -> provincia != null && !provincia.trim().isEmpty())
                .distinct()
                .sorted()
                .forEach(provincia -> frmBuscarPropiedades.cmbProvincia.addItem(provincia));
    }

    /**
     * Configura las opciones del ComboBox de ciudades/sectores.
     * Obtiene ciudades únicas desde la base de datos.
     */
    private void configurarComboBoxCiudad() {
        frmBuscarPropiedades.cmbCiudadSector.removeAllItems();
        frmBuscarPropiedades.cmbCiudadSector.addItem("Todas las ciudades");

        // Obtener ciudades únicas de las propiedades registradas
        PropiedadDAO dao = new PropiedadDAO();
        List<Propiedad> propiedades = dao.listar();

        propiedades.stream()
                .map(Propiedad::getCiudad)
                .filter(ciudad -> ciudad != null && !ciudad.trim().isEmpty())
                .distinct()
                .sorted()
                .forEach(ciudad -> frmBuscarPropiedades.cmbCiudadSector.addItem(ciudad));
    }

    /**
     * Configura las opciones del ComboBox de número de habitaciones.
     * Proporciona opciones comunes de habitaciones.
     */
    private void configurarComboBoxHabitaciones() {
        frmBuscarPropiedades.cmbHabitaciones.removeAllItems();
        frmBuscarPropiedades.cmbHabitaciones.addItem("Cualquier cantidad");
        frmBuscarPropiedades.cmbHabitaciones.addItem("1+ habitación");
        frmBuscarPropiedades.cmbHabitaciones.addItem("2+ habitaciones");
        frmBuscarPropiedades.cmbHabitaciones.addItem("3+ habitaciones");
        frmBuscarPropiedades.cmbHabitaciones.addItem("4+ habitaciones");
        frmBuscarPropiedades.cmbHabitaciones.addItem("5+ habitaciones");
    }

    /**
     * Configura las opciones del ComboBox de rangos de precio.
     * Proporciona rangos de precio comunes en USD.
     */
    private void configurarComboBoxPrecio() {
        frmBuscarPropiedades.cmbPrecio.removeAllItems();
        frmBuscarPropiedades.cmbPrecio.addItem("Cualquier precio");
        frmBuscarPropiedades.cmbPrecio.addItem("$0 - $500");
        frmBuscarPropiedades.cmbPrecio.addItem("$500 - $1,000");
        frmBuscarPropiedades.cmbPrecio.addItem("$1,000 - $1,500");
        frmBuscarPropiedades.cmbPrecio.addItem("$1,500 - $2,000");
        frmBuscarPropiedades.cmbPrecio.addItem("$2,000 - $3,000");
        frmBuscarPropiedades.cmbPrecio.addItem("$3,000+");
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
        String provincia = (String) frmBuscarPropiedades.cmbProvincia.getSelectedItem();
        return (provincia != null && !provincia.equals("Todas las provincias")) ? provincia : null;
    }

    /**
     * Obtiene la ciudad seleccionada del ComboBox.
     * 
     * @return La ciudad seleccionada o null si es "Todas las ciudades"
     */
    private String obtenerCiudadSeleccionada() {
        String ciudad = (String) frmBuscarPropiedades.cmbCiudadSector.getSelectedItem();
        return (ciudad != null && !ciudad.equals("Todas las ciudades")) ? ciudad : null;
    }

    /**
     * Obtiene el número mínimo de habitaciones del ComboBox seleccionado.
     * 
     * @return El número mínimo de habitaciones o null si es "Cualquier cantidad"
     */
    private Integer obtenerHabitacionesMinimas() {
        String habitaciones = (String) frmBuscarPropiedades.cmbHabitaciones.getSelectedItem();
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
        String precio = (String) frmBuscarPropiedades.cmbPrecio.getSelectedItem();
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
        JPanel panel = frmBuscarPropiedades.getPanelPropiedades();
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
                frmBuscarPropiedades,
                mensaje,
                "Resultados de búsqueda",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Limpia todos los filtros y restablece los ComboBox a sus valores por defecto.
     * Útil para mostrar todas las propiedades nuevamente.
     */
    public void limpiarFiltros() {
        frmBuscarPropiedades.cmbProvincia.setSelectedIndex(0); // "Todas las provincias"
        frmBuscarPropiedades.cmbCiudadSector.setSelectedIndex(0); // "Todas las ciudades"
        frmBuscarPropiedades.cmbHabitaciones.setSelectedIndex(0); // "Cualquier cantidad"
        frmBuscarPropiedades.cmbPrecio.setSelectedIndex(0); // "Cualquier precio"

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
        ubicacionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ubicacionLabel.setForeground(new Color(102, 102, 102));
        ubicacionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Precio con icono
        JLabel precioLabel = new JLabel("$" + p.getPrecioMensual() + "/mes");
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
            frmBuscarPropiedades.dispose();
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
    // Cache de imágenes ya escaladas
    private final Map<String, ImageIcon> cacheImagenes = new HashMap<>();

    private void cargarImagenPropiedad(Propiedad p, JLabel imagenLabel) {
        FotoPropiedadDAO fotoDAO = new FotoPropiedadDAO();
        FotoPropiedad fotoPrincipal = fotoDAO.obtenerFotoPrincipal(p.getId());
        String imageUrl = (fotoPrincipal != null && fotoPrincipal.getUrlFoto() != null)
                ? fotoPrincipal.getUrlFoto()
                : "/images/propiedades/default.jpg";

        imagenLabel.setText("Cargando...");
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagenLabel.setVerticalAlignment(SwingConstants.CENTER);
        imagenLabel.setIcon(null);

        // Revisar si ya está en caché
        if (cacheImagenes.containsKey(imageUrl)) {
            imagenLabel.setIcon(cacheImagenes.get(imageUrl));
            imagenLabel.setText("");
            return;
        }

        SwingWorker<ImageIcon, Void> worker = new SwingWorker<>() {
            @Override
            protected ImageIcon doInBackground() {
                try {
                    Image img = null;

                    // Soporta URL HTTP, recurso del JAR y archivo local
                    if (imageUrl.startsWith("http")) {
                        img = ImageIO.read(new URL(imageUrl));
                    } else {
                        URL recurso = getClass().getResource(imageUrl);
                        if (recurso != null) {
                            img = ImageIO.read(recurso);
                        } else {
                            java.io.File archivo = new java.io.File(imageUrl);
                            if (archivo.exists()) {
                                img = ImageIO.read(archivo);
                            }
                        }
                    }

                    if (img != null) {
                        Image scaled = img.getScaledInstance(350, 200, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(scaled);
                        cacheImagenes.put(imageUrl, icon); // Guardar en caché
                        return icon;
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
                        imagenLabel.setText("");
                    } else {
                        imagenLabel.setText("[ X ] Imagen no disponible");
                        imagenLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                        imagenLabel.setForeground(Color.GRAY);
                    }
                } catch (Exception ex) {
                    imagenLabel.setText("[ X ]");
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

