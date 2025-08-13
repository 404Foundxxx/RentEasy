package com.renteasy.views;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;

/**
 *
 * @author gmart
 */



public class FrmInicio extends javax.swing.JFrame {

    public FrmInicio() {
        initComponents();
    }

    // @SuppressWarnings("unchecked") innecesario
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblMarca = new javax.swing.JLabel();
        lblContacto = new javax.swing.JLabel();
        lblCerrarSesion = new javax.swing.JLabel();
        lblPublicarPropiedad = new javax.swing.JLabel();
        panelRound1 = new com.renteasy.utils.PanelRound();
        cmbProvincia = new javax.swing.JComboBox<>();
        cmbCiudadSector = new javax.swing.JComboBox<>();
        cmbHabitaciones = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        lblProvincia = new javax.swing.JLabel();
        lblCiudadSector = new javax.swing.JLabel();
        lblHabitaciones = new javax.swing.JLabel();
        cmbPrecio = new javax.swing.JComboBox<>();
        lblHabitaciones1 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Propiedades - RentEasy");
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHeader.setBackground(new java.awt.Color(0, 119, 182));

        lblMarca.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblMarca.setForeground(new java.awt.Color(255, 255, 255));
        lblMarca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMarca.setText("RentEasy");

        lblContacto.setForeground(new java.awt.Color(255, 255, 255));
        lblContacto.setText("Contacto");
        lblContacto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        lblCerrarSesion.setText("Cerrar Sesión");
        lblCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblPublicarPropiedad.setForeground(new java.awt.Color(255, 255, 255));
        lblPublicarPropiedad.setText("Publicar Propiedad");
        lblPublicarPropiedad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 781, Short.MAX_VALUE)
                .addComponent(lblPublicarPropiedad)
                .addGap(18, 18, 18)
                .addComponent(lblContacto)
                .addGap(18, 18, 18)
                .addComponent(lblCerrarSesion)
                .addGap(27, 27, 27))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPublicarPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.add(pnlHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, -1));

        panelRound1.setBackground(new java.awt.Color(204, 204, 204));
        panelRound1.setRoundBottomLeft(30);
        panelRound1.setRoundBottomRight(30);
        panelRound1.setRoundTopLeft(30);
        panelRound1.setRoundTopRight(30);

        btnBuscar.setBackground(new java.awt.Color(0, 119, 182));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");

        lblProvincia.setForeground(new java.awt.Color(0, 0, 0));
        lblProvincia.setText("Provincia ");

        lblCiudadSector.setForeground(new java.awt.Color(0, 0, 0));
        lblCiudadSector.setText("Ciudad / Sector");

        lblHabitaciones.setForeground(new java.awt.Color(0, 0, 0));
        lblHabitaciones.setText("Habitaciones");

        lblHabitaciones1.setForeground(new java.awt.Color(0, 0, 0));
        lblHabitaciones1.setText("Precio (USD)");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProvincia)
                    .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCiudadSector, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCiudadSector))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHabitaciones))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(cmbPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblHabitaciones1))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProvincia)
                    .addComponent(lblCiudadSector)
                    .addComponent(lblHabitaciones)
                    .addComponent(lblHabitaciones1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCiudadSector, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanel3.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 740, -1));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 119, 182));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Encuentra tu próximo hogar");
        jPanel3.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 740, 60));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jScrollPane1.setViewportView(jPanel1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 1200, 450));

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
    public javax.swing.JComboBox<String> cmbCiudadSector;
    public javax.swing.JComboBox<String> cmbHabitaciones;
    public javax.swing.JComboBox<String> cmbPrecio;
    public javax.swing.JComboBox<String> cmbProvincia;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblCerrarSesion;
    private javax.swing.JLabel lblCiudadSector;
    public javax.swing.JLabel lblContacto;
    private javax.swing.JLabel lblHabitaciones;
    private javax.swing.JLabel lblHabitaciones1;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblProvincia;
    public javax.swing.JLabel lblPublicarPropiedad;
    private javax.swing.JLabel lblTitulo;
    private com.renteasy.utils.PanelRound panelRound1;
    public javax.swing.JPanel pnlHeader;
    // End of variables declaration//GEN-END:variables
}
