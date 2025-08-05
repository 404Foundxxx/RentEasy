package com.renteasy;

import com.renteasy.utils.Utilities;
import com.renteasy.views.*;

/**
 *
 * @author gmart
 */
public class App {

    public static void main(String[] args) {
        Utilities.FlatLaf();
        new FrmLogin().setVisible(true);
    }
}
