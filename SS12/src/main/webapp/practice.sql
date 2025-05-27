CREATE DATABASE IF NOT EXISTS product_management;
USE product_management;

CREATE TABLE product (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         status BIT NOT NULL,
                         image VARCHAR(255)
);

DELIMITER $$
CREATE PROCEDURE insert_product(
    IN p_name VARCHAR(100),
    IN p_status BIT,
    IN p_image VARCHAR(255)
)
BEGIN
    INSERT INTO product (name, status, image)
    VALUES (p_name, p_status, p_image);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE update_product(
    IN p_id INT,
    IN p_name VARCHAR(100),
    IN p_status BIT,
    IN p_image VARCHAR(255)
)
BEGIN
    UPDATE product
    SET name = p_name,
        status = p_status,
        image = p_image
    WHERE id = p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE delete_product(IN p_id INT)
BEGIN
    DELETE FROM product WHERE id = p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE get_all_products()
BEGIN
    SELECT * FROM product;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE find_product_by_id(IN p_id INT)
BEGIN
    SELECT * FROM product WHERE id = p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE search_products_by_name(IN p_name VARCHAR(100))
BEGIN
    SELECT * FROM product WHERE name LIKE CONCAT('%', p_name, '%');
END$$
DELIMITER ;
