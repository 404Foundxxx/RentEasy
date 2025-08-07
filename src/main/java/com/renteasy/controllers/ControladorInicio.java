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
public class ControladorInicio {

    private FrmInicio frmPropiedades = new FrmInicio();
    private FrmInicio frmInicio = new FrmInicio();
    private FrmContacto frmContacto = new FrmContacto();

    public ControladorInicio() {

    }

    /**
     * Constructor con parámetros para inicializar los componentes del frame
     * Propiedades
     *
     * @param fp
     */
    public ControladorInicio(FrmInicio fp) {
        this.frmPropiedades = fp;

        // Boton Aplicar filtros
        this.frmPropiedades.btnBuscar.addActionListener(e -> {

        });
        // Label Inicio
        this.frmPropiedades.lblInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorInicio(frmInicio);
                frmInicio.setVisible(true);
                frmPropiedades.dispose();

            }
        });
        // Label Contacto
        this.frmPropiedades.lblContacto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorContacto(frmContacto);
                frmContacto.setVisible(true);
                frmPropiedades.dispose();

            }
        });
    }
}
