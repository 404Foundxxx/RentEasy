package com.renteasy;

import com.renteasy.controllers.inicio.ControladorLogin;
import com.renteasy.utils.Utilities;
import com.renteasy.views.inicio.*;

public class App {

    public static void main(String[] args) {
        Utilities.FlatLaf();

        FrmLogin frmLogin = new FrmLogin();
        new ControladorLogin(frmLogin);
        frmLogin.setVisible(true);
    }
}

