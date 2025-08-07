package com.renteasy.controllers;

import com.renteasy.views.FrmContacto;
import com.renteasy.views.FrmInicio;
import com.renteasy.views.FrmLogin;
import com.renteasy.views.FrmPublicarPropiedad;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author gmart
 */
public class ControladorInicio {

    private FrmInicio frmInicio = new FrmInicio();
    private FrmContacto frmContacto = new FrmContacto();
    private FrmLogin frmLogin = new FrmLogin();
    private FrmPublicarPropiedad frmPublicarPropiedad = new FrmPublicarPropiedad();

    public ControladorInicio() {

    }

    /**
     * Constructor con parámetros para inicializar los componentes del frame
     * Propiedades
     *
     * @param fp
     */
    public ControladorInicio(FrmInicio fp) {
        this.frmInicio = fp;

        // Boton Aplicar filtros
        this.frmInicio.btnBuscar.addActionListener(e -> {

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
                new ControladorLogin(frmLogin);
                frmLogin.setVisible(true);
                frmInicio.dispose();

            }
        });
    }
}
