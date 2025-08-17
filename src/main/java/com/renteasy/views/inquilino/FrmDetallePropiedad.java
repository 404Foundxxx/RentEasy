package com.renteasy.views.inquilino;

public class FrmDetallePropiedad extends javax.swing.JFrame {

    public FrmDetallePropiedad() {
        initComponents();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenidoPanel = new javax.swing.JPanel();
        panelImagen = new javax.swing.JPanel();
        lblImagen = new javax.swing.JLabel();
        panelInformacion = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblUbicacion = new javax.swing.JLabel();
        caracteristicasPanel = new javax.swing.JPanel();
        lblHabitaciones = new javax.swing.JLabel();
        lblBanos = new javax.swing.JLabel();
        lblAreaM2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        lblPrecio = new javax.swing.JLabel();
        btnSolicitarAlquiler = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        lblMarca = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Detalles de Propiedad - RentEasy");
        setResizable(false);

        contenidoPanel.setBackground(new java.awt.Color(245, 245, 245));

        panelImagen.setBackground(new java.awt.Color(255, 255, 255));
        panelImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        panelImagen.setPreferredSize(new java.awt.Dimension(500, 400));

        lblImagen.setBackground(new java.awt.Color(240, 240, 240));
        lblImagen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblImagen.setForeground(new java.awt.Color(108, 117, 125));
        lblImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagen.setText("Sin imagen disponible");
        lblImagen.setOpaque(true);

        javax.swing.GroupLayout panelImagenLayout = new javax.swing.GroupLayout(panelImagen);
        panelImagen.setLayout(panelImagenLayout);
        panelImagenLayout.setHorizontalGroup(
            panelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
        );
        panelImagenLayout.setVerticalGroup(
            panelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
        );

        panelInformacion.setBackground(new java.awt.Color(255, 255, 255));
        panelInformacion.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panelInformacion.setPreferredSize(new java.awt.Dimension(400, 400));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 119, 182));
        lblTitulo.setText("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        lblUbicacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblUbicacion.setForeground(new java.awt.Color(102, 102, 102));
        lblUbicacion.setText("xxxxxxxxxxxxxxxxxxxxxxxxx");

        caracteristicasPanel.setBackground(new java.awt.Color(255, 255, 255));

        lblHabitaciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHabitaciones.setText("XXXXX");

        lblBanos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBanos.setText("XXXXXX");

        lblAreaM2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAreaM2.setText("XXXXXX");

        javax.swing.GroupLayout caracteristicasPanelLayout = new javax.swing.GroupLayout(caracteristicasPanel);
        caracteristicasPanel.setLayout(caracteristicasPanelLayout);
        caracteristicasPanelLayout.setHorizontalGroup(
            caracteristicasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(caracteristicasPanelLayout.createSequentialGroup()
                .addComponent(lblHabitaciones)
                .addGap(15, 15, 15)
                .addComponent(lblBanos)
                .addGap(15, 15, 15)
                .addComponent(lblAreaM2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        caracteristicasPanelLayout.setVerticalGroup(
            caracteristicasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(lblHabitaciones)
            .addComponent(lblBanos)
            .addComponent(lblAreaM2)
        );

        txtDescripcion.setEditable(false);
        txtDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.setText("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBorder(null);
        txtDescripcion.setFocusable(false);
        jScrollPane1.setViewportView(txtDescripcion);

        lblPrecio.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(0, 119, 182));
        lblPrecio.setText("Xxxxxx.xx");

        btnSolicitarAlquiler.setBackground(new java.awt.Color(76, 175, 80));
        btnSolicitarAlquiler.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSolicitarAlquiler.setForeground(new java.awt.Color(255, 255, 255));
        btnSolicitarAlquiler.setText("Solicitar Alquiler");
        btnSolicitarAlquiler.setBorderPainted(false);
        btnSolicitarAlquiler.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSolicitarAlquiler.setFocusPainted(false);

        btnVolver.setBackground(new java.awt.Color(108, 117, 125));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.setBorderPainted(false);
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.setFocusPainted(false);

        javax.swing.GroupLayout panelInformacionLayout = new javax.swing.GroupLayout(panelInformacion);
        panelInformacion.setLayout(panelInformacionLayout);
        panelInformacionLayout.setHorizontalGroup(
            panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
            .addComponent(lblUbicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(caracteristicasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformacionLayout.createSequentialGroup()
                .addGroup(panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInformacionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSolicitarAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblPrecio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelInformacionLayout.setVerticalGroup(
            panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionLayout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addGap(10, 10, 10)
                .addComponent(lblUbicacion)
                .addGap(15, 15, 15)
                .addComponent(caracteristicasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrecio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(btnSolicitarAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblMarca.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblMarca.setForeground(new java.awt.Color(0, 119, 182));
        lblMarca.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMarca.setText("Detalles Propiedad");

        javax.swing.GroupLayout contenidoPanelLayout = new javax.swing.GroupLayout(contenidoPanel);
        contenidoPanel.setLayout(contenidoPanelLayout);
        contenidoPanelLayout.setHorizontalGroup(
            contenidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenidoPanelLayout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addGroup(contenidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90)
                .addComponent(panelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        contenidoPanelLayout.setVerticalGroup(
            contenidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenidoPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(contenidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contenidoPanelLayout.createSequentialGroup()
                        .addComponent(lblMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(panelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenidoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(contenidoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnSolicitarAlquiler;
    public javax.swing.JButton btnVolver;
    private javax.swing.JPanel caracteristicasPanel;
    private javax.swing.JPanel contenidoPanel;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblAreaM2;
    public javax.swing.JLabel lblBanos;
    public javax.swing.JLabel lblHabitaciones;
    public javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblMarca;
    public javax.swing.JLabel lblPrecio;
    public javax.swing.JLabel lblTitulo;
    public javax.swing.JLabel lblUbicacion;
    public javax.swing.JPanel panelImagen;
    private javax.swing.JPanel panelInformacion;
    public javax.swing.JTextArea txtDescripcion;
    // End of variables declaration//GEN-END:variables
}

