package com.renteasy.views;

import com.renteasy.utils.Utilities;

/**
 *
 * @author gmart
 */
public class LoginGUI extends javax.swing.JFrame {

    public LoginGUI() {
        initComponents();
        Utilities.setIconoVentana(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lblSingIn = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        sprUser = new javax.swing.JSeparator();
        txtPassword = new javax.swing.JTextField();
        sprPassword = new javax.swing.JSeparator();
        btnSingIn = new javax.swing.JButton();
        lblSJoinNow = new javax.swing.JLabel();
        lblAreYou = new javax.swing.JLabel();
        lblIcon = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RentEasy - Sing In");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        pnlPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSingIn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSingIn.setForeground(new java.awt.Color(0, 124, 140));
        lblSingIn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSingIn.setText("Sign in");
        pnlPrincipal.add(lblSingIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 80, -1));

        lblUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUser.setForeground(new java.awt.Color(0, 0, 0));
        lblUser.setText("User");
        pnlPrincipal.add(lblUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 160, 30, -1));

        txtUser.setBackground(new java.awt.Color(255, 255, 255));
        txtUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUser.setForeground(new java.awt.Color(0, 0, 0));
        txtUser.setBorder(null);
        pnlPrincipal.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 340, 30));

        lblPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(0, 0, 0));
        lblPassword.setText("Password");
        pnlPrincipal.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 250, 60, -1));
        pnlPrincipal.add(sprUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, 340, 20));

        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(0, 0, 0));
        txtPassword.setBorder(null);
        pnlPrincipal.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 340, 30));
        pnlPrincipal.add(sprPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, 340, 20));

        btnSingIn.setBackground(new java.awt.Color(0, 124, 140));
        btnSingIn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSingIn.setForeground(new java.awt.Color(255, 255, 255));
        btnSingIn.setText("Sing in ");
        btnSingIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlPrincipal.add(btnSingIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 370, 130, 40));

        lblSJoinNow.setForeground(new java.awt.Color(0, 153, 255));
        lblSJoinNow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSJoinNow.setText("Sign Up");
        lblSJoinNow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSJoinNow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSJoinNowMouseClicked(evt);
            }
        });
        pnlPrincipal.add(lblSJoinNow, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 430, 50, -1));

        lblAreYou.setText("Don't have an account?");
        pnlPrincipal.add(lblAreYou, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 430, -1, -1));

        lblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Easy.png"))); // NOI18N
        pnlPrincipal.add(lblIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 360));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/EasyFondo.png"))); // NOI18N
        pnlPrincipal.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(pnlPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblSJoinNowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSJoinNowMouseClicked
        RegistroGUI registroGUI = new RegistroGUI();
        registroGUI.setVisible(true);
        dispose();
    }//GEN-LAST:event_lblSJoinNowMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSingIn;
    private javax.swing.JLabel lblAreYou;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblSJoinNow;
    private javax.swing.JLabel lblSingIn;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JSeparator sprPassword;
    private javax.swing.JSeparator sprUser;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
