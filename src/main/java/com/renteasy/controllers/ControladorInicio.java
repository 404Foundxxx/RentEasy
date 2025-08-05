package com.renteasy.controllers;

import com.renteasy.views.FrmInicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author gmart
 */
public class ControladorInicio implements ActionListener {

    FrmInicio frmInicio = new FrmInicio();

    public ControladorInicio() {

    }

    public ControladorInicio(FrmInicio fi) {

        this.frmInicio = fi;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
