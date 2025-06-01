create database ss16;
use ss16;

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE DEFAULT '',
                       password VARCHAR(255) NOT NULL DEFAULT '',
                       email VARCHAR(100) NOT NULL UNIQUE DEFAULT '',
                       role VARCHAR(20) NOT NULL DEFAULT 'USER',
                       status BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE buses (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       license_plate VARCHAR(50) NOT NULL UNIQUE DEFAULT '',
                       bus_type VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
                       row_seat INT NOT NULL DEFAULT 1,
                       col_seat INT NOT NULL DEFAULT 1,
                       total_seat INT NOT NULL DEFAULT 1,
                       image VARCHAR(255) NOT NULL DEFAULT ''
);

CREATE TABLE bus_trips (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           departure_point VARCHAR(100) NOT NULL DEFAULT '',
                           destination VARCHAR(100) NOT NULL DEFAULT '',
                           departure_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           arrival_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           bus_id BIGINT NOT NULL,
                           seats_available INT NOT NULL DEFAULT 0,
                           image VARCHAR(255) NOT NULL DEFAULT '',
                           FOREIGN KEY (bus_id) REFERENCES buses(id)
);

CREATE TABLE seats (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name_seat VARCHAR(10) NOT NULL DEFAULT '',
                       price DOUBLE NOT NULL DEFAULT 0.0,
                       bus_id BIGINT NOT NULL,
                       status BOOLEAN NOT NULL DEFAULT FALSE,
                       FOREIGN KEY (bus_id) REFERENCES buses(id)
);

CREATE TABLE tickets (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         trip_bus_id BIGINT NOT NULL,
                         list_seat VARCHAR(255) NOT NULL DEFAULT '',
                         total_money DOUBLE NOT NULL DEFAULT 0.0,
                         departure_date DATE NOT NULL DEFAULT '2000-01-01',
                         FOREIGN KEY (user_id) REFERENCES users(id),
                         FOREIGN KEY (trip_bus_id) REFERENCES bus_trips(id)
);

INSERT INTO users (username, password, email, role, status)
VALUES ('admin', 'admin1', 'admin@example.com', 'ADMIN', TRUE);

INSERT INTO users (username, password, email, role, status)
VALUES ('user', 'user123', 'user@example.com', 'USER', TRUE);