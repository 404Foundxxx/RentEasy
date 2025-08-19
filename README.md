# 🏠 RentEasy - Sistema de Gestión de Alquileres

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

## 📋 Descripción

**RentEasy** es una aplicación de escritorio desarrollada en Java que facilita la gestión de alquileres de propiedades inmobiliarias. Conecta propietarios, inquilinos y administradores en una plataforma unificada.

## ✨ Características Principales

- 🔐 **Sistema de autenticación** con roles (Propietario, Inquilino, Administrador)
- 🏘️ **Gestión completa de propiedades** con galería de fotos
- 📝 **Sistema de solicitudes** de alquiler
- 🔍 **Búsqueda avanzada** con filtros múltiples
- 🎨 **Interfaz moderna** con FlatLaf Look and Feel
- � **Seguridad** con encriptación BCrypt

## 🚀 Tecnologías

- **Java 17** + **Swing** para la interfaz gráfica
- **MySQL 8.0+** para la base de datos
- **Maven** para gestión de dependencias
- **FlatLaf** para Look and Feel moderno

## 🛠️ Instalación

### Prerrequisitos
- Java JDK 17+
- Maven 3.9+
- MySQL 8.0+

### Pasos de Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/404Foundxxx/RentEasy.git
cd RentEasy
```

2. **Configurar la base de datos**
```sql
CREATE DATABASE renteasydb;
USE renteasydb;
SOURCE src/main/resources/sql/renteasydb.sql;
```

3. **Configurar credenciales**

Editar `src/main/java/com/renteasy/utils/configuracion.txt`:
```
jdbc:mysql://localhost:3306/renteasydb
tu_usuario_mysql
tu_contraseña_mysql
```

4. **Ejecutar la aplicación**
```bash
mvn clean compile exec:java -Dexec.mainClass="com.renteasy.App"
```

## 👥 Equipo de Desarrollo

| Rol | Desarrollador | GitHub |
|-----|---------------|---------|
| **Líder del Equipo** | Luis David | [@LuisDavidMV12](https://github.com/LuisDavidMV12) |
| **Database Administrator** | Wilson Marte | [@404Foundxxx](https://github.com/404Foundxxx) |
| **QA & UI/UX** | Alfreilin Sánchez | [@alfreilin-rgb](https://github.com/alfreilin-rgb) |

## 📂 Estructura del Proyecto

```
src/main/java/com/renteasy/
├── App.java                    # Punto de entrada
├── controllers/                # Controladores MVC
├── dao/                        # Acceso a datos
├── models/                     # Entidades del dominio
├── views/                      # Interfaces gráficas
└── utils/                      # Utilidades
```

## 📖 Manual de Usuario

### Roles del Sistema

#### 🏠 Propietario
- Publicar y gestionar propiedades
- Revisar solicitudes de alquiler
- Subir fotos de propiedades

#### 🏘️ Inquilino
- Buscar propiedades disponibles
- Enviar solicitudes de alquiler
- Ver historial de solicitudes

#### ⚙️ Administrador
- Gestionar usuarios del sistema
- Moderar propiedades publicadas
- Atender soporte técnico

## 🤝 Contribuir

1. Fork el proyecto
2. Crear una rama para tu funcionalidad (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -am 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crear un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver [LICENSE](LICENSE) para más detalles.

## 📞 Contacto

- **Repositorio**: [github.com/404Foundxxx/RentEasy](https://github.com/404Foundxxx/RentEasy)

---

<div align="center">

**🏠 RentEasy - Facilitando el alquiler de propiedades**

*Desarrollado con ❤️ por el equipo RentEasy*

</div>
