# 🏠 RentEasy - Sistema de Gestión de Alquileres

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

## 📋 Descripción del Proyecto

**RentEasy** es una aplicación de escritorio desarrollada en Java que facilita la gestión de alquileres de propiedades inmobiliarias. El sistema permite a propietarios publicar sus propiedades y a inquilinos buscar y solicitar alquileres de manera eficiente, proporcionando una solución integral para el mercado de alquileres.

### ✨ Características Principales

- 🔐 **Sistema de Autenticación Seguro**: Login con diferentes roles de usuario
- 🏘️ **Gestión Completa de Propiedades**: CRUD completo para propiedades inmobiliarias
- 👥 **Gestión de Usuarios**: Manejo de propietarios, inquilinos y administradores
- 📝 **Sistema de Solicitudes**: Proceso completo de solicitudes de alquiler
- 🔍 **Búsqueda Avanzada**: Filtros múltiples para encontrar propiedades específicas
- 💾 **Base de Datos Robusta**: MySQL con estructura normalizada
- 🎨 **Interfaz Moderna**: Diseño intuitivo con Swing y FlatLaf

## 🚀 Tecnologías Utilizadas

- **Lenguaje**: Java 17
- **Interfaz Gráfica**: Java Swing con FlatLaf Look and Feel
- **Base de Datos**: MySQL 8.0+
- **Gestión de Dependencias**: Apache Maven 3.9+
- **Conectividad**: JDBC MySQL Connector
- **Arquitectura**: Patrón MVC (Modelo-Vista-Controlador)
- **Patrón de Acceso a Datos**: DAO (Data Access Object)

## 📁 Estructura del Proyecto

```
RentEasy/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/renteasy/
│       │       ├── App.java                     # Clase principal
│       │       ├── controllers/                 # Controladores MVC
│       │       │   ├── ControladorLogin.java
│       │       │   ├── ControladorInicio.java
│       │       │   ├── ControladorRegister.java
│       │       │   └── ...
│       │       ├── dao/                         # Acceso a datos
│       │       │   ├── UsuarioDAO.java
│       │       │   ├── PropiedadDAO.java
│       │       │   └── ...
│       │       ├── database/                    # Configuración BD
│       │       │   ├── ConexionBD.java
│       │       │   └── renteasydb.sql
│       │       ├── models/                      # Modelos de datos
│       │       │   ├── Usuario.java
│       │       │   ├── Propiedad.java
│       │       │   └── ...
│       │       ├── utils/                       # Utilidades
│       │       │   ├── Utilities.java
│       │       │   ├── Validador.java
│       │       │   └── configuracion.txt
│       │       └── views/                       # Interfaces gráficas
│       │           ├── FrmLogin.java
│       │           ├── FrmInicio.java
│       │           ├── FrmAcercaDe.java
│       │           └── ...
│       └── resources/
│           └── images/                          # Recursos gráficos
├── pom.xml                                     # Configuración Maven
└── README.md                                   # Este archivo
```

## 🗄️ Base de Datos

### Esquema de Base de Datos

El sistema utiliza las siguientes tablas principales:

- **usuarios**: Gestión de usuarios del sistema
- **propiedades**: Información de propiedades inmobiliarias
- **solicitudes_alquiler**: Solicitudes de alquiler
- **contratos**: Contratos de alquiler
- **fotos_propiedad**: Imágenes de propiedades
- **mensajes_soporte**: Sistema de soporte

### Configuración de Base de Datos

1. Crear la base de datos ejecutando el script: `src/main/java/com/renteasy/database/renteasydb.sql`
2. Configurar las credenciales en: `src/main/java/com/renteasy/utils/configuracion.txt`

## 🛠️ Instalación y Configuración

### Prerrequisitos

- Java Development Kit (JDK) 17 o superior
- Apache Maven 3.9 o superior
- MySQL Server 8.0 o superior
- IDE de Java (NetBeans, IntelliJ IDEA, Eclipse, VS Code)

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/404Foundxxx/RentEasy.git
   cd RentEasy
   ```

2. **Configurar la base de datos**
   ```sql
   -- Crear la base de datos
   CREATE DATABASE renteasydb;
   
   -- Importar el schema
   SOURCE src/main/java/com/renteasy/database/renteasydb.sql
   ```

3. **Configurar credenciales de BD**
   
   Editar el archivo `src/main/java/com/renteasy/utils/configuracion.txt`:
   ```
   jdbc:mysql://localhost:3306/renteasydb
   tu_usuario_mysql
   tu_contraseña_mysql
   ```

4. **Compilar el proyecto**
   ```bash
   mvn clean compile
   ```

5. **Ejecutar la aplicación**
   ```bash
   mvn exec:java -Dexec.mainClass="com.renteasy.App"
   ```

## 👥 Equipo de Desarrollo

Este proyecto fue desarrollado siguiendo una metodología de trabajo en equipo con roles específicos:

| Rol | Responsabilidad | Miembro | GitHub |
|-----|----------------|---------|---------|
| **Líder del Equipo** | Coordinación del proyecto y lineamientos generales | Luis David | [@LuisDavidMV12](https://github.com/LuisDavidMV12) |
| **DBA (Database Administrator)** | Diseño y estructura de la base de datos | Wilson Marte | [@404Foundxxx](https://github.com/404Foundxxx) |
| **SQA y Diseño** | Pruebas de funcionalidad e interfaz gráfica | Alfreilin Sánchez | [@alfreilin-rgb](https://github.com/alfreilin-rgb) |

> **Nota**: Todos los miembros participaron activamente en el desarrollo y programación del sistema.

## 📖 Manual de Usuario

### Inicio de Sesión

1. **Ejecutar la aplicación**: La pantalla de login será la primera en aparecer
2. **Ingresar credenciales**: Email y contraseña registrados
3. **Registro de nuevos usuarios**: Click en "Regístrate aquí" para crear una cuenta
4. **Información del sistema**: Click en "Acerca de" para ver detalles del software

### Tipos de Usuario

#### 🏠 Propietario
- Publicar nuevas propiedades
- Gestionar propiedades existentes
- Revisar solicitudes de alquiler
- Gestionar contratos

#### 🏘️ Inquilino
- Buscar propiedades disponibles
- Aplicar filtros de búsqueda
- Enviar solicitudes de alquiler
- Ver detalles de propiedades

#### ⚙️ Administrador
- Acceso completo al sistema
- Gestión de todos los usuarios
- Supervisión de transacciones
- Mantenimiento del sistema

### Funcionalidades Principales

#### Gestión de Propiedades (CRUD)

1. **Crear**: Formulario completo para registrar nuevas propiedades
2. **Leer**: Visualización de propiedades con detalles completos
3. **Actualizar**: Modificación de información de propiedades existentes
4. **Eliminar**: Eliminación segura de propiedades

#### Búsqueda y Filtros

- **Por ubicación**: Ciudad y provincia
- **Por precio**: Rango de precios mensuales
- **Por características**: Número de habitaciones, baños, área
- **Por estado**: Disponible, alquilada, inactiva

#### Sistema de Navegación

- **Pantalla Principal**: Vista general de propiedades
- **Búsqueda Avanzada**: Filtros múltiples
- **Gestión de Cuenta**: Configuración del usuario
- **Contacto**: Sistema de soporte
- **Cerrar Sesión**: Salida segura del sistema

## 🔧 Funcionalidades Técnicas

### Patrones de Diseño Implementados

- **MVC (Model-View-Controller)**: Separación clara de responsabilidades
- **DAO (Data Access Object)**: Abstracción del acceso a datos
- **Singleton**: Gestión de conexión a base de datos
- **Observer**: Manejo de eventos en la interfaz

### Validaciones y Seguridad

- Validación de datos de entrada
- Sanitización de consultas SQL (PreparedStatement)
- Gestión de sesiones de usuario
- Manejo seguro de contraseñas

### Manejo de Errores

- Logging completo con java.util.logging
- Manejo de excepciones SQL
- Validación de conexiones de base de datos
- Mensajes informativos para el usuario

## 🚦 Requisitos Cumplidos

### ✅ Requisitos Funcionales
- [x] **CRUD completo** para dos entidades principales (Usuarios y Propiedades)
- [x] **Base de datos MySQL** con estructura normalizada
- [x] **Sistema de Login** con autenticación segura
- [x] **Menú principal** con opciones de navegación
- [x] **Funciones de búsqueda** con filtros múltiples
- [x] **Pantalla "Acerca de"** con información del software y manual
- [x] **Opción de salir** en todas las pantallas principales

### ✅ Requisitos Técnicos
- [x] **Arquitectura MVC** bien estructurada
- [x] **Patrón DAO** para acceso a datos
- [x] **Interfaz gráfica** moderna e intuitiva
- [x] **Gestión de dependencias** con Maven
- [x] **Documentación completa** del proyecto

## 🤝 Contribución

Para contribuir al proyecto:

1. Fork el repositorio
2. Crear una rama para la nueva funcionalidad (`git checkout -b feature/nueva-funcionalidad`)
3. Commit los cambios (`git commit -am 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crear un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.

## 📞 Contacto y Soporte

- **Repositorio**: [https://github.com/404Foundxxx/RentEasy](https://github.com/404Foundxxx/RentEasy)
- **Equipo de Desarrollo**:
  - **Líder**: Luis David ([@LuisDavidMV12](https://github.com/LuisDavidMV12))
  - **DBA**: Wilson Marte ([@404Foundxxx](https://github.com/404Foundxxx))
  - **SQA**: Alfreilin Sánchez ([@alfreilin-rgb](https://github.com/alfreilin-rgb))
- **Soporte**: [Usar formulario de contacto en la aplicación]

---

## 🎯 Evaluación Académica

Este proyecto fue desarrollado como parte de un ejercicio académico cumpliendo con los siguientes criterios:

- ✅ Implementación de CRUD completo
- ✅ Base de datos relacional con MySQL
- ✅ Sistema de autenticación
- ✅ Interfaz gráfica funcional
- ✅ Documentación completa
- ✅ Arquitectura bien estructurada
- ✅ Creatividad en el diseño
- ✅ Funcionalidad completa
- ✅ Originalidad del código

---

*Desarrollado con ❤️ en Java - RentEasy v1.0-SNAPSHOT*
