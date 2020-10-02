CREATE USER 'mobilecartuser'@'localhost' IDENTIFIED BY 'mobilecartpwd';

GRANT ALL PRIVILEGES ON mobilecart.* TO 'mobilecartuser'@'localhost' WITH GRANT OPTION;

SHOW GRANTS FOR 'mobilecartuser'@'localhost';