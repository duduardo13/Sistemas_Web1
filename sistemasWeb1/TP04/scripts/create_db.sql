-- =======================================================
-- Script para Cria��o do Banco - Projeto Servlet Cookies
-- Nomes da Dupla:
-- Eduardo Barbosa e Vinicius Pontes

-- =======================================================

CREATE DATABASE IF NOT EXISTS `cookies`;
USE `cookies`;

CREATE TABLE IF NOT EXISTS `preferences` (
  `user_id`       VARCHAR(255) NOT NULL,
  `currency_pair` VARCHAR(10)  NOT NULL,
  PRIMARY KEY (`user_id`)
);