package com.renteasy.views.inicio;

/**
 * Ventana "Acerca de" del sistema RentEasy
 * Contiene información del software, versión y manual de usuario
 */
public class FrmAcercaDe extends javax.swing.JFrame {

    public FrmAcercaDe() {
        initComponents();
        setLocationRelativeTo(null);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblVersion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDescripcion = new javax.swing.JTextArea();
        lblEquipoTitulo = new javax.swing.JLabel();
        lblLider = new javax.swing.JLabel();
        lblDBA = new javax.swing.JLabel();
        lblSQA = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaManual = new javax.swing.JTextArea();
        lblManualTitulo = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Acerca de RentEasy");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 119, 182));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("RentEasy");

        lblVersion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblVersion.setForeground(new java.awt.Color(102, 102, 102));
        lblVersion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVersion.setText("Versión 1.0-SNAPSHOT");

        txtAreaDescripcion.setEditable(false);
        txtAreaDescripcion.setBackground(new java.awt.Color(248, 249, 250));
        txtAreaDescripcion.setColumns(20);
        txtAreaDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAreaDescripcion.setLineWrap(true);
        txtAreaDescripcion.setRows(5);
        txtAreaDescripcion.setText("RentEasy es una aplicación de gestión de alquileres de propiedades desarrollada en Java con interfaz gráfica Swing. El sistema permite a propietarios publicar sus propiedades y a inquilinos buscar y solicitar alquileres de manera eficiente.\n\nCaracterísticas principales:\n• Sistema de autenticación con diferentes roles de usuario\n• Gestión completa de propiedades (CRUD)\n• Gestión de usuarios (propietarios, inquilinos, administradores)\n• Sistema de solicitudes de alquiler\n• Búsqueda avanzada con filtros\n• Interfaz intuitiva y moderna");
        txtAreaDescripcion.setWrapStyleWord(true);
        txtAreaDescripcion.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jScrollPane1.setViewportView(txtAreaDescripcion);

        lblEquipoTitulo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblEquipoTitulo.setForeground(new java.awt.Color(0, 119, 182));
        lblEquipoTitulo.setText("Equipo de Desarrollo:");

        lblLider.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblLider.setText("• Líder del Equipo: Luis David");

        lblDBA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDBA.setText("• DBA (Base de Datos): Wilson Marte");

        lblSQA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSQA.setText("• SQA y Diseño: Alfreilin Sánchez");

        txtAreaManual.setEditable(false);
        txtAreaManual.setBackground(new java.awt.Color(248, 249, 250));
        txtAreaManual.setColumns(20);
        txtAreaManual.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtAreaManual.setLineWrap(true);
        txtAreaManual.setRows(5);
        txtAreaManual.setText("MANUAL DE USUARIO:\n\n1. INICIO DE SESIÓN:\n   • Ingrese su email y contraseña\n   • Seleccione \"Iniciar Sesión\" o regístrese si es nuevo usuario\n\n2. NAVEGACIÓN PRINCIPAL:\n   • Pantalla de inicio: Visualiza propiedades disponibles\n   • Buscar: Use los filtros para encontrar propiedades específicas\n   • Publicar: Solo para propietarios, permite agregar nuevas propiedades\n\n3. FUNCIONES POR TIPO DE USUARIO:\n   • INQUILINO: Buscar propiedades, ver detalles, enviar solicitudes\n   • PROPIETARIO: Publicar propiedades, gestionar solicitudes, ver contratos\n   • ADMIN: Acceso completo al sistema\n\n4. GESTIÓN DE PROPIEDADES:\n   • Crear: Complete el formulario con todos los datos\n   • Editar: Modifique la información desde el panel de gestión\n   • Eliminar: Confirme la eliminación cuando sea necesario\n\n5. SOPORTE:\n   • Use la opción \"Contacto\" para comunicarse con el equipo\n   • Cerrar Sesión: Siempre cierre su sesión al terminar");
        txtAreaManual.setWrapStyleWord(true);
        txtAreaManual.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jScrollPane2.setViewportView(txtAreaManual);

        lblManualTitulo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblManualTitulo.setForeground(new java.awt.Color(0, 119, 182));
        lblManualTitulo.setText("Manual de Usuario:");

        btnCerrar.setBackground(new java.awt.Color(220, 53, 69));
        btnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setText("Cerrar");
        btnCerrar.setBorder(null);
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCerrar.setFocusPainted(false);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(0, 119, 182));
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setText("🏠");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEquipoTitulo)
                            .addComponent(lblLider)
                            .addComponent(lblDBA)
                            .addComponent(lblSQA)
                            .addComponent(lblManualTitulo))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblVersion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(350, 350, 350)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(350, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVersion)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblEquipoTitulo)
                .addGap(10, 10, 10)
                .addComponent(lblLider)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDBA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSQA)
                .addGap(20, 20, 20)
                .addComponent(lblManualTitulo)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDBA;
    private javax.swing.JLabel lblEquipoTitulo;
    private javax.swing.JLabel lblLider;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblManualTitulo;
    private javax.swing.JLabel lblSQA;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JTextArea txtAreaDescripcion;
    private javax.swing.JTextArea txtAreaManual;
    // End of variables declaration//GEN-END:variables
}

