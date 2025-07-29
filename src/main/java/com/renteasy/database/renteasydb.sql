-- Crear base de datos
CREATE DATABASE IF NOT EXISTS renteasydb;
USE renteasydb;

-- Tabla usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    tipo_usuario ENUM('propietario', 'inquilino', 'admin') NOT NULL,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla propiedades
CREATE TABLE propiedades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    propietario_id INT NOT NULL,
    titulo VARCHAR(150) NOT NULL,
    descripcion TEXT,
    direccion TEXT NOT NULL,
    ciudad VARCHAR(100),
    provincia VARCHAR(100),
    precio_mensual DECIMAL(10,2) NOT NULL,
    habitaciones INT,
    baños INT,
    area_m2 INT,
    estado ENUM('disponible', 'alquilada', 'inactiva') DEFAULT 'disponible',
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (propietario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Tabla fotos de propiedades
CREATE TABLE fotos_propiedad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    propiedad_id INT NOT NULL,
    url_foto VARCHAR(255) NOT NULL,
    FOREIGN KEY (propiedad_id) REFERENCES propiedades(id) ON DELETE CASCADE
);

-- Tabla solicitudes de alquiler
CREATE TABLE solicitudes_alquiler (
    id INT AUTO_INCREMENT PRIMARY KEY,
    inquilino_id INT NOT NULL,
    propiedad_id INT NOT NULL,
    mensaje TEXT,
    estado ENUM('pendiente', 'aceptada', 'rechazada') DEFAULT 'pendiente',
    fecha_solicitud TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (inquilino_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (propiedad_id) REFERENCES propiedades(id) ON DELETE CASCADE
);

-- Tabla contratos
CREATE TABLE contratos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    propiedad_id INT NOT NULL,
    propietario_id INT NOT NULL,
    inquilino_id INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    monto_mensual DECIMAL(10,2) NOT NULL,
    firmado_propietario BOOLEAN DEFAULT FALSE,
    firmado_inquilino BOOLEAN DEFAULT FALSE,
    estado ENUM('activo', 'finalizado', 'cancelado') DEFAULT 'activo',
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (propiedad_id) REFERENCES propiedades(id),
    FOREIGN KEY (propietario_id) REFERENCES usuarios(id),
    FOREIGN KEY (inquilino_id) REFERENCES usuarios(id)
);

-- Tabla pagos
CREATE TABLE pagos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    contrato_id INT NOT NULL,
    fecha_pago DATE NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    metodo_pago VARCHAR(50),
    estado ENUM('pendiente', 'pagado', 'vencido') DEFAULT 'pendiente',
    FOREIGN KEY (contrato_id) REFERENCES contratos(id) ON DELETE CASCADE
);

-- Tabla mensajes de soporte
CREATE TABLE mensajes_soporte (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    asunto VARCHAR(150) NOT NULL,
    mensaje TEXT NOT NULL,
    estado ENUM('abierto', 'en_proceso', 'cerrado') DEFAULT 'abierto',
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);
