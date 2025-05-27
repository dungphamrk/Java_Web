CREATE DATABASE ss12;
USE ss12;

CREATE TABLE student (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         email VARCHAR(100) NOT NULL,
                         dob DATE NOT NULL
);

DELIMITER $$
CREATE PROCEDURE insert_student(
    IN p_name VARCHAR(100),
    IN p_email VARCHAR(100),
    IN p_dob DATE
)
BEGIN
    INSERT INTO student (name, email, dob)
    VALUES (p_name, p_email, p_dob);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE update_student(
    IN p_id INT,
    IN p_name VARCHAR(100),
    IN p_email VARCHAR(100),
    IN p_dob DATE
)
BEGIN
    UPDATE student
    SET name = p_name,
        email = p_email,
        dob = p_dob
    WHERE id = p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE delete_student(IN p_id INT)
BEGIN
    DELETE FROM student WHERE id = p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE get_all_students()
BEGIN
    SELECT * FROM student;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE find_student_by_id(IN p_id INT)
BEGIN
    SELECT * FROM student WHERE id = p_id;
END$$
DELIMITER ;






CREATE TABLE product (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         price DECIMAL(10,2) NOT NULL,
                         quantity INT NOT NULL,
                         image VARCHAR(255)
);

DELIMITER $$
CREATE PROCEDURE insert_product(
    IN p_name VARCHAR(100),
    IN p_price DECIMAL(10,2),
    IN p_quantity INT,
    IN p_image VARCHAR(255)
)
BEGIN
    INSERT INTO product (name, price, quantity, image)
    VALUES (p_name, p_price, p_quantity, p_image);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE update_product(
    IN p_id INT,
    IN p_name VARCHAR(100),
    IN p_price DECIMAL(10,2),
    IN p_quantity INT,
    IN p_image VARCHAR(255)
)
BEGIN
    UPDATE product
    SET name = p_name,
        price = p_price,
        quantity = p_quantity,
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



CREATE TABLE bus (
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     license_plate VARCHAR(20) NOT NULL,
                     bus_type VARCHAR(20) NOT NULL,
                     row_seat INT NOT NULL,
                     col_seat INT NOT NULL,
                     total_seat INT NOT NULL,
                     image VARCHAR(255)
);

CREATE TABLE seat (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name_seat VARCHAR(10) NOT NULL,
                      price DECIMAL(10,2) NOT NULL,
                      bus_id INT NOT NULL,
                      status VARCHAR(20) DEFAULT 'còn trống',
                      FOREIGN KEY (bus_id) REFERENCES bus(id) ON DELETE CASCADE
);

DELIMITER $$
CREATE PROCEDURE insert_bus(
    IN p_license_plate VARCHAR(20),
    IN p_bus_type VARCHAR(20),
    IN p_row_seat INT,
    IN p_col_seat INT,
    IN p_total_seat INT,
    IN p_image VARCHAR(255)
)
BEGIN
    INSERT INTO bus (license_plate, bus_type, row_seat, col_seat, total_seat, image)
    VALUES (p_license_plate, p_bus_type, p_row_seat, p_col_seat, p_total_seat, p_image);
    SELECT LAST_INSERT_ID() AS new_bus_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE insert_seat_for_bus(
    IN p_bus_id INT,
    IN p_bus_type VARCHAR(20),
    IN p_row_seat INT,
    IN p_col_seat INT
)
BEGIN
    DECLARE r INT DEFAULT 1;
    DECLARE c INT DEFAULT 1;
    DECLARE seat_name VARCHAR(10);
    DECLARE seat_price DECIMAL(10,2);

    IF p_bus_type = 'NORMAL' THEN SET seat_price = 100000;
    ELSEIF p_bus_type = 'VIP' THEN SET seat_price = 150000;
    ELSE SET seat_price = 200000;
    END IF;

    SET r = 1;
    WHILE r <= p_row_seat DO
            SET c = 1;
            WHILE c <= p_col_seat DO
                    SET seat_name = CONCAT(CHAR(64 + r), c); -- A1, A2, B1, ...
                    INSERT INTO seat (name_seat, price, bus_id, status)
                    VALUES (seat_name, seat_price, p_bus_id, 'còn trống');
                    SET c = c + 1;
                END WHILE;
            SET r = r + 1;
        END WHILE;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE update_bus(
    IN p_id INT,
    IN p_license_plate VARCHAR(20),
    IN p_bus_type VARCHAR(20),
    IN p_row_seat INT,
    IN p_col_seat INT,
    IN p_total_seat INT,
    IN p_image VARCHAR(255)
)
BEGIN
    UPDATE bus
    SET license_plate = p_license_plate,
        bus_type = p_bus_type,
        row_seat = p_row_seat,
        col_seat = p_col_seat,
        total_seat = p_total_seat,
        image = p_image
    WHERE id = p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE delete_bus(IN p_id INT)
BEGIN
    DELETE FROM bus WHERE id = p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE get_all_bus()
BEGIN
    SELECT * FROM bus;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE find_bus_by_id(IN p_id INT)
BEGIN
    SELECT * FROM bus WHERE id = p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE get_seats_by_bus_id(IN p_bus_id INT)
BEGIN
    SELECT * FROM seat WHERE bus_id = p_bus_id;
END$$
DELIMITER ;

