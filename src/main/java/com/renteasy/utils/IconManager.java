package com.renteasy.utils;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Clase utilitaria para manejar iconos SVG en la aplicación RentEasy.
 * Utiliza FlatLaf para cargar y mostrar iconos SVG escalables.
 * 
 * @author gmart
 */
public class IconManager {
    
    // Ruta base donde se encuentran los iconos SVG
    private static final String ICONS_PATH = "icons/";
    
    // Tamaños predefinidos para diferentes usos
    public static final int SIZE_SMALL = 16;
    public static final int SIZE_MEDIUM = 24;
    public static final int SIZE_LARGE = 32;
    public static final int SIZE_XLARGE = 48;
    
    /**
     * Obtiene un ícono SVG escalado al tamaño especificado.
     * 
     * @param iconName Nombre del archivo SVG (sin extensión)
     * @param width Ancho del ícono
     * @param height Alto del ícono
     * @return FlatSVGIcon escalado o null si no se encuentra
     */
    public static FlatSVGIcon getIcon(String iconName, int width, int height) {
        try {
            String rutaCompleta = ICONS_PATH + iconName + ".svg";
            return new FlatSVGIcon(rutaCompleta, width, height);
        } catch (Exception e) {
            System.err.println("Error al cargar el ícono: " + iconName + " - " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Obtiene un ícono SVG con tamaño cuadrado.
     * 
     * @param iconName Nombre del archivo SVG (sin extensión)
     * @param size Tamaño cuadrado del ícono
     * @return FlatSVGIcon escalado o null si no se encuentra
     */
    public static FlatSVGIcon getIcon(String iconName, int size) {
        return getIcon(iconName, size, size);
    }
    
    /**
     * Obtiene un ícono SVG con tamaño mediano por defecto (24x24).
     * 
     * @param iconName Nombre del archivo SVG (sin extensión)
     * @return FlatSVGIcon escalado o null si no se encuentra
     */
    public static FlatSVGIcon getIcon(String iconName) {
        return getIcon(iconName, SIZE_MEDIUM);
    }
    
    /**
     * Asigna un ícono SVG a un JFrame o JInternalFrame.
     * 
     * @param window La ventana a la que se le quiere asignar el ícono
     * @param iconName Nombre del archivo SVG (sin extensión)
     * @param width Ancho del ícono
     * @param height Alto del ícono
     */
    public static void setIcono(Object window, String iconName, int width, int height) {
        FlatSVGIcon svgIcon = getIcon(iconName, width, height);
        
        if (svgIcon == null) {
            System.err.println("No se pudo cargar el ícono: " + iconName);
            return;
        }
        
        if (window instanceof JFrame frame) {
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = img.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            svgIcon.paintIcon(null, g2d, 0, 0);
            g2d.dispose();
            frame.setIconImage(img);
        } else if (window instanceof JInternalFrame internalFrame) {
            internalFrame.setFrameIcon(svgIcon);
        } else {
            throw new IllegalArgumentException("Tipo de componente no soportado: " + window.getClass().getSimpleName());
        }
    }
    
    /**
     * Asigna un ícono SVG a un JFrame o JInternalFrame con tamaño cuadrado.
     * 
     * @param window La ventana a la que se le quiere asignar el ícono
     * @param iconName Nombre del archivo SVG (sin extensión)
     * @param size Tamaño cuadrado del ícono
     */
    public static void setIcono(Object window, String iconName, int size) {
        setIcono(window, iconName, size, size);
    }
    
    /**
     * Asigna un ícono SVG a un JFrame o JInternalFrame con tamaño mediano por defecto.
     * 
     * @param window La ventana a la que se le quiere asignar el ícono
     * @param iconName Nombre del archivo SVG (sin extensión)
     */
    public static void setIcono(Object window, String iconName) {
        setIcono(window, iconName, SIZE_LARGE);
    }
    
    /**
     * Configura un botón con un ícono SVG.
     * 
     * @param button El botón al que se le asignará el ícono
     * @param iconName Nombre del archivo SVG (sin extensión)
     * @param size Tamaño del ícono
     */
    public static void setButtonIcon(AbstractButton button, String iconName, int size) {
        FlatSVGIcon icon = getIcon(iconName, size);
        if (icon != null) {
            button.setIcon(icon);
        }
    }
    
    /**
     * Configura un botón con un ícono SVG y tamaño mediano por defecto.
     * 
     * @param button El botón al que se le asignará el ícono
     * @param iconName Nombre del archivo SVG (sin extensión)
     */
    public static void setButtonIcon(AbstractButton button, String iconName) {
        setButtonIcon(button, iconName, SIZE_MEDIUM);
    }
    
    /**
     * Configura un label con un ícono SVG.
     * 
     * @param label El label al que se le asignará el ícono
     * @param iconName Nombre del archivo SVG (sin extensión)
     * @param size Tamaño del ícono
     */
    public static void setLabelIcon(JLabel label, String iconName, int size) {
        FlatSVGIcon icon = getIcon(iconName, size);
        if (icon != null) {
            label.setIcon(icon);
        }
    }
    
    /**
     * Configura un label con un ícono SVG y tamaño mediano por defecto.
     * 
     * @param label El label al que se le asignará el ícono
     * @param iconName Nombre del archivo SVG (sin extensión)
     */
    public static void setLabelIcon(JLabel label, String iconName) {
        setLabelIcon(label, iconName, SIZE_MEDIUM);
    }
    
    /**
     * Crea un botón con ícono y texto.
     * 
     * @param text Texto del botón
     * @param iconName Nombre del archivo SVG (sin extensión)
     * @param iconSize Tamaño del ícono
     * @return JButton configurado con ícono y texto
     */
    public static JButton createIconButton(String text, String iconName, int iconSize) {
        JButton button = new JButton(text);
        setButtonIcon(button, iconName, iconSize);
        return button;
    }
    
    /**
     * Crea un botón con ícono y texto usando tamaño mediano por defecto.
     * 
     * @param text Texto del botón
     * @param iconName Nombre del archivo SVG (sin extensión)
     * @return JButton configurado con ícono y texto
     */
    public static JButton createIconButton(String text, String iconName) {
        return createIconButton(text, iconName, SIZE_MEDIUM);
    }
    
    /**
     * Crea un label con ícono y texto.
     * 
     * @param text Texto del label
     * @param iconName Nombre del archivo SVG (sin extensión)
     * @param iconSize Tamaño del ícono
     * @return JLabel configurado con ícono y texto
     */
    public static JLabel createIconLabel(String text, String iconName, int iconSize) {
        JLabel label = new JLabel(text);
        setLabelIcon(label, iconName, iconSize);
        return label;
    }
    
    /**
     * Crea un label con ícono y texto usando tamaño mediano por defecto.
     * 
     * @param text Texto del label
     * @param iconName Nombre del archivo SVG (sin extensión)
     * @return JLabel configurado con ícono y texto
     */
    public static JLabel createIconLabel(String text, String iconName) {
        return createIconLabel(text, iconName, SIZE_MEDIUM);
    }
}
