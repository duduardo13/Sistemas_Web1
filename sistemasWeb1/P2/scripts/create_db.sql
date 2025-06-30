CREATE DATABASE prova2;
USE prova2;

CREATE TABLE `salesman` (
  `salesman_id` INT(5) NOT NULL AUTO_INCREMENT,  
  `name` VARCHAR(30) NOT NULL,
  `city` VARCHAR(15) NOT NULL,
  `commission` DECIMAL(5, 2) NOT NULL,
  PRIMARY KEY (`salesman_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `customer` (
  `customer_id` INT(5) NOT NULL AUTO_INCREMENT,  
  `cust_name` VARCHAR(30) NOT NULL,
  `city` VARCHAR(15) NOT NULL,
  `grade` INT(3) NOT NULL,
  `salesman_id` INT(5) NOT NULL,
  PRIMARY KEY (`customer_id`),
  FOREIGN KEY (`salesman_id`) REFERENCES `salesman`(`salesman_id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `orders` (
  `ord_no` INT(5) NOT NULL AUTO_INCREMENT,       
  `purch_amt` DECIMAL(8, 2) NOT NULL,
  `ord_date` DATE NOT NULL,
  `customer_id` INT(5) NOT NULL,
  `salesman_id` INT(5) NOT NULL,
  PRIMARY KEY (`ord_no`),
  FOREIGN KEY (`customer_id`) REFERENCES `customer`(`customer_id`),
  FOREIGN KEY (`salesman_id`) REFERENCES `salesman`(`salesman_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
