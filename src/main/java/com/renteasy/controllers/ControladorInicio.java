package com.renteasy.controllers;

import com.renteasy.views.FrmContacto;
import com.renteasy.views.FrmInicio;
import com.renteasy.views.FrmPropiedades;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author gmart
 */
public class ControladorInicio {

    private FrmInicio frmInicio = new FrmInicio();
    private FrmPropiedades frmPropiedades = new FrmPropiedades();
    private FrmContacto frmContacto = new FrmContacto();

    public ControladorInicio() {

    }

    /**
     * Constructor con parámetros para inicializar los componentes del frame
     * inicio
     *
     * @param fi
     */
    public ControladorInicio(FrmInicio fi) {
        this.frmInicio = fi;

        // Boton Buscar
        this.frmInicio.btnBuscar.addActionListener(e -> {

        });

        // TextField Ciudad, sector o código postal.
        this.frmInicio.txtCiudadSectorOCodigoPostal.addActionListener(e -> {

        });

        // Boton Ver Todas las Propiedades
        this.frmInicio.btnVerTodasLasPropiedades.addActionListener(e -> {
            new ControladorPropiedades(frmPropiedades);
            frmPropiedades.setVisible(true);
            frmInicio.dispose();
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

        // Label Propiedades
        this.frmInicio.lblPropiedades.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorPropiedades(frmPropiedades);
                frmPropiedades.setVisible(true);
                frmInicio.dispose();

            }
        });

    }
}
