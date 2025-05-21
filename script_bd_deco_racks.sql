CREATE DATABASE bd_decoracks;

USE bd_decoracks;

-- Tabla de Sedes
CREATE TABLE sedes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    direccion VARCHAR(255),
    distrito VARCHAR(100),
    latitud DECIMAL(10,8),
    longitud DECIMAL(11,8)
);

-- Tabla de Usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    rol ENUM('administrador', 'jefe_sede', 'vendedor', 'tecnico'),
    sede_id INT,
    estado BOOLEAN DEFAULT 1,
    FOREIGN KEY (sede_id) REFERENCES sedes(id)
);

-- Tabla de Clientes
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    dni VARCHAR(20) UNIQUE,
    telefono VARCHAR(20),
    direccion VARCHAR(255),
    distrito VARCHAR(100),
    correo VARCHAR(100)
);

-- Tabla de Categorías
CREATE TABLE categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion TEXT
);

-- Tabla de Productos
CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion TEXT,
    precio DECIMAL(10,2), -- incluye IGV
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

-- Tabla de Stock por Sede
CREATE TABLE stock_productos_sede (
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT,
    sede_id INT,
    stock INT DEFAULT 0,
    FOREIGN KEY (producto_id) REFERENCES productos(id),
    FOREIGN KEY (sede_id) REFERENCES sedes(id)
);

-- Tabla de Ventas
CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    usuario_id INT, -- vendedor
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    tecnico_id INT, -- repartidor asignado
    sede_id INT, -- sede que realiza la venta
    estado_actual ENUM('pendiente', 'asignado', 'en_reparto', 'entregado', 'cancelado') DEFAULT 'pendiente',
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (tecnico_id) REFERENCES usuarios(id),
    FOREIGN KEY (sede_id) REFERENCES sedes(id)
);

-- Tabla de Detalle de Ventas
CREATE TABLE detalle_ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venta_id INT,
    producto_id INT,
    cantidad INT,
    precio_unitario DECIMAL(10,2),
    FOREIGN KEY (venta_id) REFERENCES ventas(id),
    FOREIGN KEY (producto_id) REFERENCES productos(id)
);

-- Tabla de Pagos
CREATE TABLE pagos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venta_id INT,
    codigo_operacion VARCHAR(100),
    fecha_pago DATETIME,
    FOREIGN KEY (venta_id) REFERENCES ventas(id)
);

-- Tabla de Historial de Estados
-- Incluye foto_entrega como evidencia solo cuando el estado sea 'entregado'
CREATE TABLE historial_estados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venta_id INT,
    estado ENUM('pendiente', 'asignado', 'en_reparto', 'entregado', 'cancelado'),
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    usuario_id INT,
    FOREIGN KEY (venta_id) REFERENCES ventas(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);


-- Agregar campo foto_entrega a historial_estados
ALTER TABLE historial_estados ADD foto_entrega VARCHAR(255) NULL;

-- Tabla de Sesiones (opcional)
CREATE TABLE sesiones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    fecha_inicio DATETIME,
    fecha_fin DATETIME,
    ip VARCHAR(45),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
