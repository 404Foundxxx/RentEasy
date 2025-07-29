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
        LoginGUI lgui = new LoginGUI();
        lgui.setVisible(true);
    }
}
