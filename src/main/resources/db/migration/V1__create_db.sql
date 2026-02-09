CREATE TABLE client (
                        id IDENTITY PRIMARY KEY,
                        name VARCHAR(200) NOT NULL CHECK (length(name) >= 3)
);

CREATE TABLE planet (
                        id VARCHAR(50) PRIMARY KEY CHECK (id REGEXP '^[A-Z0-9]+$'),
                        name VARCHAR(500) NOT NULL CHECK (length(name) >= 1)
);

CREATE TABLE ticket (
                        id IDENTITY PRIMARY KEY,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        client_id BIGINT NOT NULL,
                        from_planet_id VARCHAR(50) NOT NULL,
                        to_planet_id VARCHAR(50) NOT NULL,
                        FOREIGN KEY (client_id) REFERENCES client(id),
                        FOREIGN KEY (from_planet_id) REFERENCES planet(id),
                        FOREIGN KEY (to_planet_id) REFERENCES planet(id)
);
