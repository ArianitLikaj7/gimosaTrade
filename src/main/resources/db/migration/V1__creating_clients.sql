CREATE TABLE clients (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         address VARCHAR(255) NOT NULL,
                         oib VARCHAR(20) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         phone VARCHAR(20) NOT NULL,
                         date VARCHAR(255) NOT NULL,
                         pallets INT NOT NULL,
                         packages INT NOT NULL
);
