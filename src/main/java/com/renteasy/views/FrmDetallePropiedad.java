package com.renteasy.views;

public class FrmDetallePropiedad extends javax.swing.JFrame {

    public FrmDetallePropiedad() {
        initComponents();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblInicio = new javax.swing.JLabel();
        lblPublicarPropiedad = new javax.swing.JLabel();
        lblContacto = new javax.swing.JLabel();
        lblCerrarSesion = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RentEasy - Detalles de Propiedad");
        setResizable(false);

        panelPrincipal.setBackground(new java.awt.Color(245, 245, 245));
        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerPanel.setBackground(new java.awt.Color(0, 119, 182));
        headerPanel.setPreferredSize(new java.awt.Dimension(1200, 80));

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("RentEasy");

        lblInicio.setForeground(new java.awt.Color(255, 255, 255));
        lblInicio.setText("Inicio");
        lblInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblPublicarPropiedad.setForeground(new java.awt.Color(255, 255, 255));
        lblPublicarPropiedad.setText("Publicar Propiedad");
        lblPublicarPropiedad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblContacto.setForeground(new java.awt.Color(255, 255, 255));
        lblContacto.setText("Contacto");
        lblContacto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        lblCerrarSesion.setText("Cerrar Sesión");
        lblCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 737, Short.MAX_VALUE)
                .addComponent(lblInicio)
                .addGap(18, 18, 18)
                .addComponent(lblPublicarPropiedad)
                .addGap(18, 18, 18)
                .addComponent(lblContacto)
                .addGap(18, 18, 18)
                .addComponent(lblCerrarSesion)
                .addGap(27, 27, 27))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPublicarPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogo))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        panelPrincipal.add(headerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 60));

        contenidoPanel.setBackground(new java.awt.Color(245, 245, 245));

        panelImagen.setBackground(new java.awt.Color(255, 255, 255));
        panelImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        panelImagen.setPreferredSize(new java.awt.Dimension(500, 400));

        lblImagen.setBackground(new java.awt.Color(240, 240, 240));
        lblImagen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblImagen.setForeground(new java.awt.Color(108, 117, 125));
        lblImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagen.setText("Cargando imagen...");
        lblImagen.setOpaque(true);

        javax.swing.GroupLayout panelImagenLayout = new javax.swing.GroupLayout(panelImagen);
        panelImagen.setLayout(panelImagenLayout);
        panelImagenLayout.setHorizontalGroup(
            panelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
        );
        panelImagenLayout.setVerticalGroup(
            panelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImagenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelInformacion.setBackground(new java.awt.Color(255, 255, 255));
        panelInformacion.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panelInformacion.setPreferredSize(new java.awt.Dimension(400, 400));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 119, 182));
        lblTitulo.setText("Villa con Piscina en Cocotal");

        lblUbicacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblUbicacion.setForeground(new java.awt.Color(102, 102, 102));
        lblUbicacion.setText("Cocotal, La Altagracia");

        caracteristicasPanel.setBackground(new java.awt.Color(255, 255, 255));

        lblHabitaciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHabitaciones.setText("4 habs");

        lblBanos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBanos.setText("5 baños");

        lblAreaM2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAreaM2.setText("350 m²");

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
        txtDescripcion.setText("Espectacular villa para disfrutar del paraíso caribeño. Esta hermosa propiedad cuenta con todas las comodidades que necesitas para unas vacaciones perfectas. Ubicada en una zona privilegiada con acceso directo a la playa y servicios de primer nivel.");
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBorder(null);
        txtDescripcion.setFocusable(false);
        jScrollPane1.setViewportView(txtDescripcion);

        lblPrecio.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(0, 119, 182));
        lblPrecio.setText("$2500/mes");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addComponent(btnSolicitarAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout contenidoPanelLayout = new javax.swing.GroupLayout(contenidoPanel);
        contenidoPanel.setLayout(contenidoPanelLayout);
        contenidoPanelLayout.setHorizontalGroup(
            contenidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenidoPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(panelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(panelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        contenidoPanelLayout.setVerticalGroup(
            contenidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenidoPanelLayout.createSequentialGroup()
                .addGroup(contenidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contenidoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contenidoPanelLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(panelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        panelPrincipal.add(contenidoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 86, 1200, 690));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    public javax.swing.JPanel headerPanel;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblAreaM2;
    public javax.swing.JLabel lblBanos;
    public javax.swing.JLabel lblCerrarSesion;
    public javax.swing.JLabel lblContacto;
    public javax.swing.JLabel lblHabitaciones;
    public javax.swing.JLabel lblImagen;
    public javax.swing.JLabel lblInicio;
    private javax.swing.JLabel lblLogo;
    public javax.swing.JLabel lblPrecio;
    public javax.swing.JLabel lblPublicarPropiedad;
    public javax.swing.JLabel lblTitulo;
    public javax.swing.JLabel lblUbicacion;
    public javax.swing.JPanel panelImagen;
    private javax.swing.JPanel panelInformacion;
    private javax.swing.JPanel panelPrincipal;
    public javax.swing.JTextArea txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
