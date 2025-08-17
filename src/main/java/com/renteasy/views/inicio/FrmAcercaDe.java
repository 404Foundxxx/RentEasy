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
        lblVersion.setText("Versión 1.0-SNAPSHOT | Java 17 + MySQL 8.0");

        txtAreaDescripcion.setEditable(false);
        txtAreaDescripcion.setBackground(new java.awt.Color(248, 249, 250));
        txtAreaDescripcion.setColumns(20);
        txtAreaDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAreaDescripcion.setLineWrap(true);
        txtAreaDescripcion.setRows(5);
        txtAreaDescripcion.setText("🏠 RentEasy - Sistema de Gestión de Alquileres v1.0-SNAPSHOT\n\n" +
                "RentEasy es una aplicación de escritorio desarrollada en Java con interfaz gráfica Swing que revoluciona la gestión de alquileres de propiedades inmobiliarias. El sistema integra las necesidades de propietarios, inquilinos y administradores en una plataforma unificada.\n\n" +
                "🎯 CARACTERÍSTICAS PRINCIPALES:\n" +
                "• Sistema de autenticación multi-rol con seguridad BCrypt\n" +
                "• CRUD completo de propiedades con galería de imágenes\n" +
                "• Gestión avanzada de usuarios (propietarios, inquilinos, administradores)\n" +
                "• Sistema integral de solicitudes de alquiler\n" +
                "• Búsqueda inteligente con filtros múltiples y combinables\n" +
                "• Base de datos MySQL normalizada con integridad referencial\n" +
                "• Interfaz moderna con FlatLaf Look and Feel\n" +
                "• Sistema de soporte integrado para comunicación\n\n" +
                "🚀 TECNOLOGÍAS UTILIZADAS:\n" +
                "• Java 17 LTS con arquitectura MVC\n" +
                "• MySQL 8.0+ con patrón DAO\n" +
                "• Apache Maven para gestión de dependencias\n" +
                "• FlatLaf 3.6.1 para experiencia visual moderna\n" +
                "• BCrypt para encriptación segura de contraseñas");
        txtAreaDescripcion.setWrapStyleWord(true);
        txtAreaDescripcion.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jScrollPane1.setViewportView(txtAreaDescripcion);

        lblEquipoTitulo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblEquipoTitulo.setForeground(new java.awt.Color(0, 119, 182));
        lblEquipoTitulo.setText("🏆 Equipo de Desarrollo Colaborativo:");

        lblLider.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblLider.setText("• 🎯 Líder del Equipo: Luis David (@LuisDavidMV12)");

        lblDBA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDBA.setText("• 🗄️ DBA Especialista: Wilson Marte (@404Foundxxx)");

        lblSQA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSQA.setText("• 🧪 SQA y UI/UX: Alfreilin Sánchez (@alfreilin-rgb)");

        txtAreaManual.setEditable(false);
        txtAreaManual.setBackground(new java.awt.Color(248, 249, 250));
        txtAreaManual.setColumns(20);
        txtAreaManual.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtAreaManual.setLineWrap(true);
        txtAreaManual.setRows(5);
        txtAreaManual.setText("📖 MANUAL DE USUARIO COMPLETO - RentEasy v1.0\n\n" +
                "🔐 1. SISTEMA DE AUTENTICACIÓN:\n" +
                "   • Registro: Complete formulario con datos personales y seleccione tipo de usuario\n" +
                "   • Login: Ingrese email y contraseña registrados\n" +
                "   • Tipos: Propietario (publicar propiedades), Inquilino (buscar/solicitar), Admin (gestión completa)\n" +
                "   • Seguridad: Contraseñas encriptadas con BCrypt, sesiones seguras\n\n" +
                "🏠 2. GESTIÓN DE PROPIEDADES (PROPIETARIOS):\n" +
                "   • Crear: Formulario completo con título, descripción, ubicación, precio, características\n" +
                "   • Fotos: Subir hasta 10 imágenes por propiedad (JPG, PNG)\n" +
                "   • Editar: Modificar cualquier información de propiedades existentes\n" +
                "   • Estado: Cambiar entre disponible, alquilada, inactiva\n" +
                "   • Eliminar: Confirmación doble antes de eliminar permanentemente\n\n" +
                "🔍 3. BÚSQUEDA AVANZADA (INQUILINOS):\n" +
                "   • Filtros: Ubicación (ciudad/provincia), precio (rango min-max), características\n" +
                "   • Combinación: Use múltiples filtros simultáneamente\n" +
                "   • Ordenamiento: Por precio, fecha, área, popularidad\n" +
                "   • Detalles: Click en cualquier propiedad para ver información completa\n\n" +
                "📝 4. SISTEMA DE SOLICITUDES:\n" +
                "   • Enviar: Desde detalles de propiedad, incluya mensaje personal\n" +
                "   • Seguimiento: Monitoree estado (pendiente/aceptada/rechazada)\n" +
                "   • Comunicación: Mensajería directa con propietarios\n" +
                "   • Gestión: Propietarios pueden revisar perfil completo del solicitante\n\n" +
                "💬 5. SOPORTE Y AYUDA:\n" +
                "   • Contacto: Use formulario integrado para comunicarse con administradores\n" +
                "   • FAQ: Preguntas frecuentes en sección de ayuda\n" +
                "   • Tickets: Sistema de seguimiento para resolver problemas\n\n" +
                "🚪 6. NAVEGACIÓN Y SEGURIDAD:\n" +
                "   • Menú: Navegación intuitiva específica por tipo de usuario\n" +
                "   • Sesión: Cierre automático por inactividad (30 min)\n" +
                "   • Perfil: Edite información personal desde 'Mi Perfil'\n" +
                "   • Salir: Siempre use 'Cerrar Sesión' para salida segura");
        txtAreaManual.setWrapStyleWord(true);
        txtAreaManual.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jScrollPane2.setViewportView(txtAreaManual);

        lblManualTitulo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblManualTitulo.setForeground(new java.awt.Color(0, 119, 182));
        lblManualTitulo.setText("📚 Manual de Usuario Interactivo:");

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

