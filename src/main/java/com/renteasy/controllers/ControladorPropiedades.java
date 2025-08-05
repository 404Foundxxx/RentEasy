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
public class ControladorPropiedades {

    private FrmPropiedades frmPropiedades = new FrmPropiedades();
    private FrmInicio frmInicio = new FrmInicio();
    private FrmContacto frmContacto = new FrmContacto();

    public ControladorPropiedades() {

    }

    /**
     * Constructor con parámetros para inicializar los componentes del frame
     * Propiedades
     *
     * @param fp
     */
    public ControladorPropiedades(FrmPropiedades fp) {
        this.frmPropiedades = fp;

        // Boton Aplicar filtros
        this.frmPropiedades.btnAplicarFiltros.addActionListener(e -> {

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
