package com.renteasy.utils;

import com.formdev.flatlaf.FlatLightLaf;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author gmart
 */
public class Utilities {

    public static void FlatLaf() {
        FlatLightLaf.setup();
        UIManager.put("PasswordField.showRevealButton", true);
    }

    /**
     * Método para establecer el ícono de la ventana.
     *
     * @param frame El marco de la ventana.
     */
    public static void setIconoVentana(JFrame frame) {
        URL location = Utilities.class.getResource("/images/EasyPequeno.png");
        if (location != null) {
            ImageIcon icon = new ImageIcon(location);
            frame.setIconImage(icon.getImage());
        } else {
            System.err.println("No se encontró el ícono en /images/EasyPequeno.png");
        }
    }

}


