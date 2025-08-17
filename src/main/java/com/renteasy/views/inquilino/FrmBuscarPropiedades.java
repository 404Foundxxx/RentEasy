package com.renteasy.views.inquilino;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class FrmBuscarPropiedades extends javax.swing.JFrame {

    public FrmBuscarPropiedades() {
        initComponents();
    }

    // @SuppressWarnings("unchecked") innecesario
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnLimpiarFiltros = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        cmbCiudadSector = new javax.swing.JComboBox<>();
        cmbProvincia = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        cmbHabitaciones = new javax.swing.JComboBox<>();
        lblHabitaciones1 = new javax.swing.JLabel();
        lblCiudadSector = new javax.swing.JLabel();
        cmbPrecio = new javax.swing.JComboBox<>();
        lblHabitaciones = new javax.swing.JLabel();
        lblProvincia = new javax.swing.JLabel();
        lblVolver = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Buscar Propiedades - RentEasy");
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLimpiarFiltros.setBackground(new java.awt.Color(149, 165, 166));
        btnLimpiarFiltros.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpiarFiltros.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarFiltros.setText("Limpiar");
        btnLimpiarFiltros.setBorder(null);
        btnLimpiarFiltros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.add(btnLimpiarFiltros, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 130, 90, 30));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 119, 182));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Encuentra tu próximo hogar");
        jPanel3.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 810, 50));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jScrollPane1.setViewportView(jPanel1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 1240, 450));

        cmbCiudadSector.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel3.add(cmbCiudadSector, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 140, 30));

        cmbProvincia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel3.add(cmbProvincia, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 140, 30));

        btnBuscar.setBackground(new java.awt.Color(0, 119, 182));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.setBorder(null);
        jPanel3.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 130, 100, 30));

        cmbHabitaciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel3.add(cmbHabitaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 140, 30));

        lblHabitaciones1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHabitaciones1.setText("Precio (USD)");
        jPanel3.add(lblHabitaciones1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, -1, -1));

        lblCiudadSector.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCiudadSector.setText("Ciudad / Sector");
        jPanel3.add(lblCiudadSector, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, -1));

        cmbPrecio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel3.add(cmbPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 130, 140, 30));

        lblHabitaciones.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHabitaciones.setText("Habitaciones");
        jPanel3.add(lblHabitaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, -1, -1));

        lblProvincia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblProvincia.setText("Provincia ");
        jPanel3.add(lblProvincia, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, -1));

        lblVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblVolver.setForeground(new java.awt.Color(0, 119, 182));
        lblVolver.setText("Volver");
        lblVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.add(lblVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 20, -1, 30));

        lblMarca.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblMarca.setForeground(new java.awt.Color(0, 119, 182));
        lblMarca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMarca.setText("Buscar Propiedades ");
        jPanel3.add(lblMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 250, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Obtiene el panel donde se muestran las propiedades
     * @return JPanel contenedor de propiedades
     */
    public JPanel getPanelPropiedades() {
        return jPanel1;
    }

    /**
     * Obtiene el ComboBox de provincias para configuración desde el controlador
     * @return JComboBox de provincias
     */
    public JComboBox<String> getCmbProvincia() {
        return cmbProvincia;
    }

    /**
     * Obtiene el ComboBox de ciudades/sectores para configuración desde el controlador
     * @return JComboBox de ciudades/sectores
     */
    public JComboBox<String> getCmbCiudadSector() {
        return cmbCiudadSector;
    }

    /**
     * Obtiene el ComboBox de habitaciones para configuración desde el controlador
     * @return JComboBox de habitaciones
     */
    public JComboBox<String> getCmbHabitaciones() {
        return cmbHabitaciones;
    }

    /**
     * Obtiene el ComboBox de precios para configuración desde el controlador
     * @return JComboBox de precios
     */
    public JComboBox<String> getCmbPrecio() {
        return cmbPrecio;
    }

    /**
     * Obtiene el botón de búsqueda para configuración de eventos desde el controlador
     * @return JButton del botón de búsqueda
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnLimpiarFiltros;
    public javax.swing.JComboBox<String> cmbCiudadSector;
    public javax.swing.JComboBox<String> cmbHabitaciones;
    public javax.swing.JComboBox<String> cmbPrecio;
    public javax.swing.JComboBox<String> cmbProvincia;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCiudadSector;
    private javax.swing.JLabel lblHabitaciones;
    private javax.swing.JLabel lblHabitaciones1;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblProvincia;
    private javax.swing.JLabel lblTitulo;
    public javax.swing.JLabel lblVolver;
    // End of variables declaration//GEN-END:variables
}

