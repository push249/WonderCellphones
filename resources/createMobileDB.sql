CREATE DATABASE `mobilecart`;

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `UserName` varchar(45) NOT NULL,
  `eMail` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `address` varchar(150) DEFAULT NULL,
  `contactNo` varchar(45) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `UserName_UNIQUE` (`UserName`),
  UNIQUE KEY `eMail_UNIQUE` (`eMail`),
  UNIQUE KEY `contactNo_UNIQUE` (`contactNo`)
) ;


CREATE TABLE `product` (
  `ProductId` int(11) NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(45) NOT NULL,
  `Price` double DEFAULT NULL,
  `Specs` varchar(1000) DEFAULT NULL,
  `Img` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ProductId`),
  UNIQUE KEY `ProductName_UNIQUE` (`ProductName`)
) ;

CREATE TABLE `orderproduct` (
  `idUser` int(11) NOT NULL,
  `ProductId` int(11) NOT NULL
) ;