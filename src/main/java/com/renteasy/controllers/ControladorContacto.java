package com.renteasy.controllers;

import com.renteasy.views.FrmContacto;
import com.renteasy.views.FrmInicio;
import com.renteasy.views.FrmInicio;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author gmart
 */
public class ControladorContacto {

    private FrmContacto frmContacto = new FrmContacto();
    private FrmInicio frmInicio = new FrmInicio();
    private FrmInicio frmPropiedades = new FrmInicio();

    public ControladorContacto() {

    }

    /**
     *
     * Constructor con parámetros para inicializar los componentes del frame
     * Contacto
     *
     * @param fc
     */
    public ControladorContacto(FrmContacto fc) {
        this.frmContacto = fc;

        // Label Propidades
        this.frmContacto.lblPublicarPropiedad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorInicio(frmPropiedades);
                frmPropiedades.setVisible(true);
                frmContacto.dispose();

            }
        });

        // Label Inicio
        this.frmContacto.lblInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorInicio(frmInicio);
                frmInicio.setVisible(true);
                frmContacto.dispose();

            }
        });

    }

}
