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
        lblInicio = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblPropiedades = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInicio.setForeground(new java.awt.Color(255, 255, 255));
        lblInicio.setText("Inicio");
        lblInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(lblInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 0, -1, 60));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Ponte en Contacto con Nosotros");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1180, 70));

        lblPropiedades.setForeground(new java.awt.Color(255, 255, 255));
        lblPropiedades.setText("Propiedades");
        lblPropiedades.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(lblPropiedades, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, -1, 60));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("RentEasy");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Header.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 0, 1180, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FondoInicio.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, -1));

        jLabel4.setText("¿Tienes alguna pregunta? Estamos aquí para ayudarte.");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 285, -1, -1));

        jTextField1.setText("Tu Nombre");
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 319, 670, 40));

        jTextField2.setText("Tu Email");
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 371, 670, 40));

        jTextField3.setText("Asunto");
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 423, 670, 40));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Tu Mensaje");
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 481, 670, 206));

        jButton1.setText("Enviar");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 705, 85, 32));

        jLabel10.setText("Av. Winston Churchill 123");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 410, -1, -1));

        jLabel11.setText("Santo Domingo, República Dominicana");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 423, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Nuestra Oficina");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 380, -1, -1));

        jLabel13.setText("(809) 555-1234");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 490, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Teléfono");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 460, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Email");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 530, -1, -1));

        jLabel14.setText("info@renteasy.com");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 560, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 858));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    public javax.swing.JLabel lblInicio;
    public javax.swing.JLabel lblPropiedades;
    // End of variables declaration//GEN-END:variables
}
