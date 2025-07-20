CREATE TABLE topicos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('abierto', 'cerrado', 'pendiente') DEFAULT 'abierto',
    autor VARCHAR(100) NOT NULL,
    curso VARCHAR(100) NOT NULL
);
