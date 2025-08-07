package com.renteasy.views;

/**
 *
 * @author gmart
 */
public class FrmContacto extends javax.swing.JFrame {

    public FrmContacto() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblSalir = new javax.swing.JLabel();
        lblPublicarPropiedad = new javax.swing.JLabel();
        lblInicio = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtAsunto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMensaje = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Contacto - RentEasy");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSalir.setForeground(new java.awt.Color(255, 255, 255));
        lblSalir.setText("Cerrar Sesión");
        lblSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(lblSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, -1, 60));

        lblPublicarPropiedad.setForeground(new java.awt.Color(255, 255, 255));
        lblPublicarPropiedad.setText("Publicar Propiedad");
        lblPublicarPropiedad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(lblPublicarPropiedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, -1, 60));

        lblInicio.setForeground(new java.awt.Color(255, 255, 255));
        lblInicio.setText("Inicio");
        lblInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(lblInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, -1, 60));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("RentEasy");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Header.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 0, 830, 60));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel4.setText("¿Tienes alguna pregunta? Estamos aquí para ayudarte.");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        txtNombre.setText("Tu Nombre");
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 440, 40));

        txtEmail.setText("Tu Email");
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 440, 40));

        txtAsunto.setText("Asunto");
        jPanel1.add(txtAsunto, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 440, 40));

        txtMensaje.setColumns(20);
        txtMensaje.setRows(5);
        txtMensaje.setText("Tu Mensaje");
        jScrollPane1.setViewportView(txtMensaje);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 440, 206));

        btnEnviar.setBackground(new java.awt.Color(85, 148, 161));
        btnEnviar.setForeground(new java.awt.Color(0, 0, 0));
        btnEnviar.setText("Enviar");
        jPanel1.add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 570, 85, 32));

        jLabel10.setText("Av. Winston Churchill 123");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 288, -1, -1));

        jLabel11.setText("Santo Domingo, República Dominicana");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Nuestra Oficina");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, -1, -1));

        jLabel13.setText("(809) 555-1234");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 370, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Teléfono");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Email");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 410, -1, -1));

        jLabel14.setText("info@renteasy.com");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 440, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(85, 148, 161));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Ponte en Contacto con Nosotros");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 820, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 640));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblInicio;
    public javax.swing.JLabel lblPublicarPropiedad;
    public javax.swing.JLabel lblSalir;
    private javax.swing.JTextField txtAsunto;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtMensaje;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
