-- Criar banco de dados
CREATE DATABASE sistema_malaria;

USE sistema_malaria;

-- Tabela para armazenar informações sobre as cidades
CREATE TABLE cidades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    populacao INT NOT NULL
);

-- Tabela para armazenar os dados das coletas de Malária
CREATE TABLE coletas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cidade_id INT,
    data_coleta DATE,
    casos_confirmados INT,
    casos_com_sintomas INT,
    mortes INT,
    FOREIGN KEY (cidade_id) REFERENCES cidades(id)
);
