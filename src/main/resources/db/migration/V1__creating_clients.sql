CREATE TABLE clients (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         address VARCHAR(255) NOT NULL,
                         oib VARCHAR(20) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         phone VARCHAR(20) NOT NULL
);
CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        pallets INT,
                        packages INT,
                        order_date DATE NOT NULL,
                        client_id BIGINT NOT NULL,
                        CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE CASCADE
);
