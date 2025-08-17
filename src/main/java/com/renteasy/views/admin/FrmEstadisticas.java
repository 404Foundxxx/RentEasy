package com.renteasy.views.admin;

/**
 * Formulario para mostrar estadísticas del sistema
 */
public class FrmEstadisticas extends javax.swing.JFrame {

    public FrmEstadisticas() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblTotalPropiedades = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblTotalUsuarios = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblContratosActivos = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblIngresosMensuales = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lblPropiedadesDisponibles = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblPropiedadesAlquiladas = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblOcupacion = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        lblSolicitudesPendientes = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblTicketsAbiertos = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblPagosPendientes = new javax.swing.JLabel();
        btnRefrescar = new javax.swing.JButton();
        btnGenerarReporte = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Estadísticas del Sistema - RentEasy");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(41, 128, 185));
        jLabel1.setText("Estadísticas del Sistema");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 300, 40));

        jPanel2.setBackground(new java.awt.Color(52, 152, 219));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalPropiedades.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTotalPropiedades.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalPropiedades.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalPropiedades.setText("0");
        jPanel2.add(lblTotalPropiedades, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Total Propiedades");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 170, 100));

        jPanel3.setBackground(new java.awt.Color(46, 204, 113));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalUsuarios.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTotalUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalUsuarios.setText("0");
        jPanel3.add(lblTotalUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 50));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Total Usuarios");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 170, 100));

        jPanel4.setBackground(new java.awt.Color(155, 89, 182));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblContratosActivos.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblContratosActivos.setForeground(new java.awt.Color(255, 255, 255));
        lblContratosActivos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContratosActivos.setText("0");
        jPanel4.add(lblContratosActivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 50));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Contratos Activos");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, 20));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 170, 100));

        jPanel5.setBackground(new java.awt.Color(230, 126, 34));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIngresosMensuales.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblIngresosMensuales.setForeground(new java.awt.Color(255, 255, 255));
        lblIngresosMensuales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIngresosMensuales.setText("$0.00");
        jPanel5.add(lblIngresosMensuales, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 50));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Ingresos Mensuales");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, 20));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, 170, 100));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado de Propiedades", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Propiedades Disponibles:");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 160, 25));

        lblPropiedadesDisponibles.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPropiedadesDisponibles.setForeground(new java.awt.Color(39, 174, 96));
        lblPropiedadesDisponibles.setText("0");
        jPanel6.add(lblPropiedadesDisponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 50, 25));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Propiedades Alquiladas:");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 65, 160, 25));

        lblPropiedadesAlquiladas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPropiedadesAlquiladas.setForeground(new java.awt.Color(231, 76, 60));
        lblPropiedadesAlquiladas.setText("0");
        jPanel6.add(lblPropiedadesAlquiladas, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 65, 50, 25));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Tasa de Ocupación:");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 130, 25));

        lblOcupacion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOcupacion.setForeground(new java.awt.Color(52, 152, 219));
        lblOcupacion.setText("0%");
        jPanel6.add(lblOcupacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 50, 25));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 280, 150));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado del Sistema", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Solicitudes Pendientes:");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, 25));

        lblSolicitudesPendientes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSolicitudesPendientes.setForeground(new java.awt.Color(230, 126, 34));
        lblSolicitudesPendientes.setText("0");
        jPanel7.add(lblSolicitudesPendientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 50, 25));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Tickets de Soporte:");
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 65, 130, 25));

        lblTicketsAbiertos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTicketsAbiertos.setForeground(new java.awt.Color(231, 76, 60));
        lblTicketsAbiertos.setText("0");
        jPanel7.add(lblTicketsAbiertos, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 65, 50, 25));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Pagos Pendientes:");
        jPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 130, 25));

        lblPagosPendientes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPagosPendientes.setForeground(new java.awt.Color(142, 68, 173));
        lblPagosPendientes.setText("0");
        jPanel7.add(lblPagosPendientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 50, 25));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, 250, 150));

        btnRefrescar.setBackground(new java.awt.Color(52, 152, 219));
        btnRefrescar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRefrescar.setForeground(new java.awt.Color(255, 255, 255));
        btnRefrescar.setText("Refrescar Datos");
        btnRefrescar.setBorder(null);
        btnRefrescar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnRefrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 150, 35));

        btnGenerarReporte.setBackground(new java.awt.Color(142, 68, 173));
        btnGenerarReporte.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGenerarReporte.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarReporte.setText("Generar Reporte");
        btnGenerarReporte.setBorder(null);
        btnGenerarReporte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnGenerarReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 410, 150, 35));

        btnVolver.setBackground(new java.awt.Color(95, 106, 106));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.setBorder(null);
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 410, 80, 35));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 740, 10));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 470));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnGenerarReporte;
    public javax.swing.JButton btnRefrescar;
    public javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JLabel lblContratosActivos;
    public javax.swing.JLabel lblIngresosMensuales;
    public javax.swing.JLabel lblOcupacion;
    public javax.swing.JLabel lblPagosPendientes;
    public javax.swing.JLabel lblPropiedadesAlquiladas;
    public javax.swing.JLabel lblPropiedadesDisponibles;
    public javax.swing.JLabel lblSolicitudesPendientes;
    public javax.swing.JLabel lblTicketsAbiertos;
    public javax.swing.JLabel lblTotalPropiedades;
    public javax.swing.JLabel lblTotalUsuarios;
    // End of variables declaration//GEN-END:variables
}

