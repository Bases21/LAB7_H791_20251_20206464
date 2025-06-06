CREATE DATABASE IF NOT EXISTS lab7;
USE lab7;

DROP TABLE IF EXISTS estudiante;

CREATE TABLE estudiante (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombres VARCHAR(100) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  dni CHAR(8) NOT NULL UNIQUE,
  codigo_pucp CHAR(8) NOT NULL UNIQUE,
  fecha_nacimiento DATE NOT NULL,
  sexo CHAR(1) NOT NULL CHECK (sexo IN ('M', 'F')),
  correo_institucional VARCHAR(100) NOT NULL,
  correo_personal VARCHAR(100) NOT NULL,
  telefono CHAR(9) NOT NULL,
  direccion VARCHAR(100),
  departamento VARCHAR(50),
  provincia VARCHAR(50),
  carrera VARCHAR(100),
  fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
  ultima_actualizacion DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  estado BOOLEAN DEFAULT TRUE,

  CONSTRAINT chk_telefono CHECK (telefono REGEXP '^9[0-9]{8}$'),
  CONSTRAINT chk_correo_inst CHECK (correo_institucional LIKE '%@pucp.edu.pe'),
  CONSTRAINT chk_correo_distinto CHECK (correo_institucional <> correo_personal)
);
