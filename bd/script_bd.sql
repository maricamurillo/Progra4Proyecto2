-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema proyecto_2
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `proyecto_2` ;

-- -----------------------------------------------------
-- Schema proyecto_2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `proyecto_2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `proyecto_2` ;

-- -----------------------------------------------------
-- Table `proyecto_2`.`administradores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto_2`.`administradores` ;

CREATE TABLE IF NOT EXISTS `proyecto_2`.`administradores` (
  `cedula` INT(11) NOT NULL,
  `nombre` VARCHAR(100) NULL DEFAULT NULL,
  `apellido1` VARCHAR(100) NULL DEFAULT NULL,
  `apellido2` VARCHAR(100) NULL DEFAULT NULL,
  `usuario` VARCHAR(45) NULL DEFAULT NULL,
  `clave` VARCHAR(45) NULL DEFAULT NULL,
  `observaciones` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`cedula`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proyecto_2`.`candidatos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto_2`.`candidatos` ;

CREATE TABLE IF NOT EXISTS `proyecto_2`.`candidatos` (
  `cedula` INT(11) NOT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `apellido1` VARCHAR(45) NULL DEFAULT NULL,
  `apellido2` VARCHAR(45) NULL DEFAULT NULL,
  `foto` MEDIUMBLOB NULL DEFAULT NULL,
  `estado` SMALLINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`cedula`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proyecto_2`.`partidos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto_2`.`partidos` ;

CREATE TABLE IF NOT EXISTS `proyecto_2`.`partidos` (
  `id_partido` INT(11) NOT NULL,
  `cedula_candidato` INT(11) NOT NULL,
  `nombre` VARCHAR(150) NULL DEFAULT NULL,
  `siglas` VARCHAR(45) NULL DEFAULT NULL,
  `bandera` MEDIUMBLOB NULL DEFAULT NULL,
  `obersvaciones` VARCHAR(150) NULL DEFAULT NULL,
  `estado` SMALLINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id_partido`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE,
  INDEX `cedula_candidato_idx` (`cedula_candidato` ASC) VISIBLE,
  CONSTRAINT `cedula_candidato`
    FOREIGN KEY (`cedula_candidato`)
    REFERENCES `proyecto_2`.`candidatos` (`cedula`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proyecto_2`.`votantes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto_2`.`votantes` ;

CREATE TABLE IF NOT EXISTS `proyecto_2`.`votantes` (
  `cedula` INT(11) NOT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `apellido1` VARCHAR(45) NULL DEFAULT NULL,
  `apellido2` VARCHAR(45) NULL DEFAULT NULL,
  `clave` VARCHAR(45) NULL DEFAULT NULL,
  `cambio_clave` SMALLINT(1) NULL DEFAULT NULL,
  `estado` SMALLINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`cedula`),
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proyecto_2`.`votaciones`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto_2`.`votaciones` ;

CREATE TABLE IF NOT EXISTS `proyecto_2`.`votaciones` (
  `id_votacion` INT(11) NOT NULL,
  `fecha_inicio` DATETIME NULL DEFAULT NULL,
  `fecha_cierre` DATETIME NULL DEFAULT NULL,
  `estado` SMALLINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id_votacion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proyecto_2`.`registro_votos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto_2`.`registro_votos` ;

CREATE TABLE IF NOT EXISTS `proyecto_2`.`registro_votos` (
  `id_votacion` INT(11) NOT NULL,
  `id_partido` INT(11) NOT NULL,
  `cedula_votante` INT(11) NOT NULL,
  `fecha_voto` DATETIME NULL DEFAULT NULL,
  `estado` SMALLINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id_votacion`, `id_partido`, `cedula_votante`),
  INDEX `cedula_votante_idx` (`cedula_votante` ASC) VISIBLE,
  INDEX `id_partido_idx` (`id_partido` ASC) VISIBLE,
  CONSTRAINT `cedula_votante`
    FOREIGN KEY (`cedula_votante`)
    REFERENCES `proyecto_2`.`votantes` (`cedula`),
  CONSTRAINT `id_partido`
    FOREIGN KEY (`id_partido`)
    REFERENCES `proyecto_2`.`partidos` (`id_partido`),
  CONSTRAINT `id_votacion`
    FOREIGN KEY (`id_votacion`)
    REFERENCES `proyecto_2`.`votaciones` (`id_votacion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `proyecto_2`.`administradores`
(`cedula`,
`nombre`,
`apellido1`,
`apellido2`,
`usuario`,
`clave`,
`observaciones`)
VALUES
(1,
'admin ',
'perez',
'perez',
'admin',
'admin',
'');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
