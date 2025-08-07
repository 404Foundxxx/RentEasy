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
public class ControladorPublicarPropiedad {

    private FrmPublicarPropiedad frmPublicarPropiedad = new FrmPublicarPropiedad();
    private FrmInicio frmInicio = new FrmInicio();
    private FrmContacto frmContacto = new FrmContacto();
    private FrmLogin frmLogin = new FrmLogin();

    public ControladorPublicarPropiedad() {

    }

    public ControladorPublicarPropiedad(FrmPublicarPropiedad fpp) {
        this.frmPublicarPropiedad = fpp;

        // Label Inicio
        this.frmPublicarPropiedad.lblInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorInicio(frmInicio);
                frmInicio.setVisible(true);
                frmPublicarPropiedad.dispose();
            }
        });
        // Label Contacto
        this.frmPublicarPropiedad.lblContacto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorContacto(frmContacto);
                frmContacto.setVisible(true);
                frmPublicarPropiedad.dispose();

            }
        });
        // Label Cerrar Sesion
        this.frmPublicarPropiedad.lblCerrarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ControladorLogin(frmLogin);
                frmLogin.setVisible(true);
                frmPublicarPropiedad.dispose();

            }
        });
    }

}
