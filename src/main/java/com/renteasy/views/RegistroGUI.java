package com.renteasy.views;

import com.renteasy.utils.Utilities;

/**
 *
 * @author gmart
 */
public class RegistroGUI extends javax.swing.JFrame {

    public RegistroGUI() {
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
        btnSingIn = new javax.swing.JButton();
        lblSJoinNow = new javax.swing.JLabel();
        lblAreYou = new javax.swing.JLabel();
        lblIcon = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();
        lblUser1 = new javax.swing.JLabel();
        txtUser1 = new javax.swing.JTextField();
        lblUser2 = new javax.swing.JLabel();
        txtUser2 = new javax.swing.JTextField();
        lblUser3 = new javax.swing.JLabel();
        txtUser3 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RentEasy - Create your account");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        pnlPrincipal.setLayout(null);

        lblSingIn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSingIn.setForeground(new java.awt.Color(0, 124, 140));
        lblSingIn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSingIn.setText("Create your account");
        pnlPrincipal.add(lblSingIn);
        lblSingIn.setBounds(180, 20, 230, 32);

        lblUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUser.setForeground(new java.awt.Color(0, 0, 0));
        lblUser.setText("Name");
        pnlPrincipal.add(lblUser);
        lblUser.setBounds(140, 80, 40, 20);

        txtUser.setBackground(new java.awt.Color(255, 255, 255));
        txtUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUser.setForeground(new java.awt.Color(0, 0, 0));
        txtUser.setBorder(null);
        pnlPrincipal.add(txtUser);
        txtUser.setBounds(140, 100, 290, 40);

        btnSingIn.setBackground(new java.awt.Color(0, 124, 140));
        btnSingIn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSingIn.setForeground(new java.awt.Color(255, 255, 255));
        btnSingIn.setText("Sign Up");
        btnSingIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlPrincipal.add(btnSingIn);
        btnSingIn.setBounds(140, 460, 290, 40);

        lblSJoinNow.setForeground(new java.awt.Color(0, 153, 255));
        lblSJoinNow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSJoinNow.setText("Log in");
        lblSJoinNow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSJoinNow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSJoinNowMouseClicked(evt);
            }
        });
        pnlPrincipal.add(lblSJoinNow);
        lblSJoinNow.setBounds(270, 510, 60, 16);

        lblAreYou.setText("Already have an account?");
        pnlPrincipal.add(lblAreYou);
        lblAreYou.setBounds(140, 510, 140, 16);

        lblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Easy.png"))); // NOI18N
        pnlPrincipal.add(lblIcon);
        lblIcon.setBounds(580, 0, 270, 440);

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/EasyFondoMediano.png"))); // NOI18N
        pnlPrincipal.add(lblFondo);
        lblFondo.setBounds(580, 0, 270, 600);

        lblUser1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUser1.setForeground(new java.awt.Color(0, 0, 0));
        lblUser1.setText("Email");
        pnlPrincipal.add(lblUser1);
        lblUser1.setBounds(140, 240, 40, 20);

        txtUser1.setBackground(new java.awt.Color(255, 255, 255));
        txtUser1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUser1.setForeground(new java.awt.Color(0, 0, 0));
        txtUser1.setBorder(null);
        pnlPrincipal.add(txtUser1);
        txtUser1.setBounds(140, 260, 290, 40);

        lblUser2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUser2.setForeground(new java.awt.Color(0, 0, 0));
        lblUser2.setText("Phone");
        pnlPrincipal.add(lblUser2);
        lblUser2.setBounds(140, 160, 40, 20);

        txtUser2.setBackground(new java.awt.Color(255, 255, 255));
        txtUser2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUser2.setForeground(new java.awt.Color(0, 0, 0));
        txtUser2.setBorder(null);
        pnlPrincipal.add(txtUser2);
        txtUser2.setBounds(140, 180, 290, 40);

        lblUser3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUser3.setForeground(new java.awt.Color(0, 0, 0));
        lblUser3.setText("Password");
        pnlPrincipal.add(lblUser3);
        lblUser3.setBounds(140, 320, 60, 20);

        txtUser3.setBackground(new java.awt.Color(255, 255, 255));
        txtUser3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUser3.setForeground(new java.awt.Color(0, 0, 0));
        txtUser3.setBorder(null);
        pnlPrincipal.add(txtUser3);
        txtUser3.setBounds(140, 340, 290, 40);
        pnlPrincipal.add(jSeparator1);
        jSeparator1.setBounds(140, 380, 290, 20);
        pnlPrincipal.add(jSeparator2);
        jSeparator2.setBounds(140, 140, 290, 20);
        pnlPrincipal.add(jSeparator3);
        jSeparator3.setBounds(140, 220, 290, 20);
        pnlPrincipal.add(jSeparator4);
        jSeparator4.setBounds(140, 300, 290, 20);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("The password must be at least 8 characters long.");
        pnlPrincipal.add(jLabel1);
        jLabel1.setBounds(140, 380, 260, 14);

        getContentPane().add(pnlPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblSJoinNowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSJoinNowMouseClicked
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.setVisible(true);
        dispose();
    }//GEN-LAST:event_lblSJoinNowMouseClicked

    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSingIn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblAreYou;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblSJoinNow;
    private javax.swing.JLabel lblSingIn;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblUser1;
    private javax.swing.JLabel lblUser2;
    private javax.swing.JLabel lblUser3;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTextField txtUser;
    private javax.swing.JTextField txtUser1;
    private javax.swing.JTextField txtUser2;
    private javax.swing.JTextField txtUser3;
    // End of variables declaration//GEN-END:variables
}
