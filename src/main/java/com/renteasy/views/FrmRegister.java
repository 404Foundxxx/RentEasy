package com.renteasy.views;

/**
 *
 * @author gmart
 */
public class FrmRegister extends javax.swing.JFrame {

    public FrmRegister() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        btnCrearCuenta = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblIniciarSesion = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        txtCorreo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro - RentEasy");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("agilizar tu búsqueda.");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 610, 370, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Crea una cuenta para guardar tus propiedades favoritas y");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 590, 370, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("RentEasy");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 380, 370, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(85, 148, 161));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("nuevo hogar.");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 460, 370, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(85, 148, 161));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("El primer paso hacia tu");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 430, 370, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Fondo.png"))); // NOI18N
        jLabel2.setText("R");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 542, 655));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(85, 148, 161));
        jLabel3.setText("Crea Tu Cuenta");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        jLabel1.setText("Es rápido, fácil y gratis.");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        txtApellido.setToolTipText("");
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 368, 42));

        btnCrearCuenta.setBackground(new java.awt.Color(85, 148, 161));
        btnCrearCuenta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCrearCuenta.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearCuenta.setText("Crear Cuenta");
        btnCrearCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnCrearCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, 368, 43));

        jLabel4.setText("¿Ya tienes una cuenta?");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 570, -1, -1));

        lblIniciarSesion.setForeground(new java.awt.Color(85, 148, 161));
        lblIniciarSesion.setText("Inicia sesión");
        lblIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(lblIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 570, -1, -1));
        jPanel1.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 368, 42));

        txtCorreo.setToolTipText("");
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 368, 42));

        txtNombre.setToolTipText("");
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 368, 42));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCrearCuenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel lblIniciarSesion;
    public javax.swing.JTextField txtApellido;
    public javax.swing.JPasswordField txtContrasena;
    public javax.swing.JTextField txtCorreo;
    public javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
