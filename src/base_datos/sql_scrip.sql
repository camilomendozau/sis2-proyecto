/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  USER
 * Created: 2 oct. 2024
 */
CREATE DATABASE IF NOT EXISTS proyecto_sisinf2;

USE proyecto_sisinf2;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    contra VARCHAR(50) NOT NULL, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
