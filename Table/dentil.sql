-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 26-11-2020 a las 04:39:45
-- Versión del servidor: 8.0.21
-- Versión de PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dentil`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cita`
--

DROP TABLE IF EXISTS `cita`;
CREATE TABLE IF NOT EXISTS `cita` (
  `citaid` int NOT NULL AUTO_INCREMENT,
  `citatipocita` varchar(30) DEFAULT NULL,
  `citafecha` date DEFAULT NULL,
  `citahora` varchar(30) NOT NULL,
  `paciid` int DEFAULT NULL,
  `citanombrepaciente` varchar(40) DEFAULT NULL,
  `citaiddoctor` int DEFAULT NULL,
  `citanombredoctor` varchar(30) DEFAULT NULL,
  `citaconsultorio` varchar(30) DEFAULT NULL,
  `citaconfirmacion` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`citaid`),
  KEY `paciid` (`paciid`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cita`
--

INSERT INTO `cita` (`citaid`, `citatipocita`, `citafecha`, `citahora`, `paciid`, `citanombrepaciente`, `citaiddoctor`, `citanombredoctor`, `citaconsultorio`, `citaconfirmacion`) VALUES
(20, 'kjdnfk', '2020-11-06', '11:11:11', 8866, 'uyuyu', 8787, 'KJSDNKS', 'kjndkj', 'NO'),
(21, 'ncjk', '2020-11-05', '11:11:11', 8866, 'kjndksj null null', 15, 'Carlos P P', 'kjdsnkj', 'NO'),
(22, 'ncjk', '2020-11-05', '11:11:11', 67565, 'kjndksj null null', 89, 'Carlos P P', 'kjdsnkj', 'NO'),
(23, 'ijiji', '2020-01-25', '11:!1:11', 8866, 'kjndksj null null', 15, 'Carlos P P', 'kdsjn', 'NO'),
(24, 'kjdnfk', '2020-11-06', '11:11:11', 8866, 'kjndksj', 8787, 'bhjb', 'kjndkj', 'NO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consulta`
--

DROP TABLE IF EXISTS `consulta`;
CREATE TABLE IF NOT EXISTS `consulta` (
  `consid` int NOT NULL AUTO_INCREMENT,
  `consconsultorio` varchar(30) DEFAULT NULL,
  `emplid` int DEFAULT NULL,
  `consnombreempleado` varchar(40) DEFAULT NULL,
  `paciid` int DEFAULT NULL,
  `consnombrepaciente` varchar(40) DEFAULT NULL,
  `conspadecimiento` varchar(40) DEFAULT NULL,
  `consmedicamento` varchar(40) DEFAULT NULL,
  `consfecha` varchar(30) DEFAULT NULL,
  `conshora` varchar(30) NOT NULL,
  PRIMARY KEY (`consid`),
  KEY `paciid` (`paciid`),
  KEY `emplid` (`emplid`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `consulta`
--

INSERT INTO `consulta` (`consid`, `consconsultorio`, `emplid`, `consnombreempleado`, `paciid`, `consnombrepaciente`, `conspadecimiento`, `consmedicamento`, `consfecha`, `conshora`) VALUES
(20, 'bjkbkj', 7878, 'kjncj', 7878, 'kjndksj', 'knkj', '11:11:!1', '2020-11-19', '11:11:!1'),
(21, 'Daguessssss', 7878, 'kjncj', 7878, 'kjndksj', 'knkj', '11:11:!1', '2020-11-19', '11:11:!1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

DROP TABLE IF EXISTS `empleado`;
CREATE TABLE IF NOT EXISTS `empleado` (
  `emplid` int NOT NULL AUTO_INCREMENT,
  `emplnombre` varchar(20) NOT NULL,
  `emplpaterno` varchar(20) NOT NULL,
  `emplmaterno` varchar(20) NOT NULL,
  `emplfechanacimiento` varchar(30) NOT NULL,
  `empltelefono` int DEFAULT NULL,
  `emplcelular` int DEFAULT NULL,
  `emplemail` varchar(30) DEFAULT NULL,
  `empldireccion` varchar(30) DEFAULT NULL,
  `emplpuesto` varchar(30) NOT NULL,
  `empldepartamento` varchar(30) NOT NULL,
  PRIMARY KEY (`emplid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`emplid`, `emplnombre`, `emplpaterno`, `emplmaterno`, `emplfechanacimiento`, `empltelefono`, `emplcelular`, `emplemail`, `empldireccion`, `emplpuesto`, `empldepartamento`) VALUES
(14, 'juan', 'Angeles', 'politron', '2020-11-13', 123, 333, 'poltron', 'jnjn', 'Administrador Global', 'nununkjn'),
(15, 'Carlos', 'Angeles', 'P', '2020-11-18', 1234, 122, 'carlos_ss', 'alksmakl', 'Doctor', 'nscjknkj'),
(17, 'Carlos', 'A', 'P', '2020-11-15', 987, 554987366, 'juancarlos', 'ksjadnkjsadkjasddnsj', 'Administrador Global', 'ksjankds'),
(18, 'Cofal', 'p', 'kj', '2020-11-18', 78, 98, 'cofla', 'kjnj', 'Administrador Global', 'kjnj'),
(20, 'kjs', 'cj', 'jnc', '2020-11-12', 33, 33, 'sarai', 'kedn', 'Administrador Global', 'dsj');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

DROP TABLE IF EXISTS `inventario`;
CREATE TABLE IF NOT EXISTS `inventario` (
  `inveid` int NOT NULL AUTO_INCREMENT,
  `invedescripcion` varchar(30) NOT NULL,
  `invefechallegada` date NOT NULL,
  `invecantidad` int NOT NULL,
  `invekg` int NOT NULL,
  `invelts` int NOT NULL,
  `invecosto` int NOT NULL,
  PRIMARY KEY (`inveid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`inveid`, `invedescripcion`, `invefechallegada`, `invecantidad`, `invekg`, `invelts`, `invecosto`) VALUES
(3, 'kjnkjkjnkj', '2020-11-05', 898, 9, 9, 90),
(6, 'xxxxxxxx', '2020-11-05', 898, 9, 9, 90);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `laboratorio`
--

DROP TABLE IF EXISTS `laboratorio`;
CREATE TABLE IF NOT EXISTS `laboratorio` (
  `laboid` int NOT NULL AUTO_INCREMENT,
  `paciid` int DEFAULT NULL,
  `labonombrepaciente` varchar(30) DEFAULT NULL,
  `emplid` int DEFAULT NULL,
  `labonombreempleado` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `labofecha` varchar(30) DEFAULT NULL,
  `labocosto` float DEFAULT NULL,
  `labodescripcion` varchar(40) DEFAULT NULL,
  `laboresultados` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`laboid`),
  KEY `paciid` (`paciid`),
  KEY `emplid` (`emplid`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `laboratorio`
--

INSERT INTO `laboratorio` (`laboid`, `paciid`, `labonombrepaciente`, `emplid`, `labonombreempleado`, `labofecha`, `labocosto`, `labodescripcion`, `laboresultados`) VALUES
(4, 950204, 'kjadnfj dask akfdnfk', 15, 'Carlos A', '2020-11-6 7:24:55', 899, 'lkxzcnkl', 'kcnzkxcnj'),
(5, 950204, 'kjadnfj dask akfdnfk', 15, 'CxxxxA', '2020-11-6 7:24:55', 899, 'lkxzcnkl', 'kcnzkxcnj');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicamento`
--

DROP TABLE IF EXISTS `medicamento`;
CREATE TABLE IF NOT EXISTS `medicamento` (
  `mediid` int NOT NULL AUTO_INCREMENT,
  `medidescripcion` varchar(30) DEFAULT NULL,
  `medicantidad` int DEFAULT NULL,
  `medicosto` float DEFAULT NULL,
  `mediventa` float DEFAULT NULL,
  `paciid` int NOT NULL,
  `medinombrepaciente` varchar(30) NOT NULL,
  PRIMARY KEY (`mediid`),
  KEY `paciid` (`paciid`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `medicamento`
--

INSERT INTO `medicamento` (`mediid`, `medidescripcion`, `medicantidad`, `medicosto`, `mediventa`, `paciid`, `medinombrepaciente`) VALUES
(4, 'parecatamol', 1, 23, 2, 989, 'Coflassjn');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

DROP TABLE IF EXISTS `paciente`;
CREATE TABLE IF NOT EXISTS `paciente` (
  `paciid` int NOT NULL,
  `pacinombre` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pacipaterno` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pacimaterno` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pacifechanacimiento` date DEFAULT NULL,
  `paciedad` int DEFAULT NULL,
  `pacitelefono` int DEFAULT NULL,
  `pacicelular` int DEFAULT NULL,
  `paciemail` varchar(40) DEFAULT NULL,
  `pacidireccion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`paciid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`paciid`, `pacinombre`, `pacipaterno`, `pacimaterno`, `pacifechanacimiento`, `paciedad`, `pacitelefono`, `pacicelular`, `paciemail`, `pacidireccion`) VALUES
(45, 'kdsnjfk', 'dnkjs', 'knkj', '2020-01-23', 22, 89, 65, 'mn m', 'jknjk'),
(674, 'kjadnfj', 'akfdnfk', 'dask', '2020-11-26', 67, 9898, 9898, 'dksnfj', 'dksnfj'),
(950204, 'kjadnfj', 'akfdnfk', 'dask', '2020-11-26', 22, 9898, 9898, 'dksnfj', 'dksnfj');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
