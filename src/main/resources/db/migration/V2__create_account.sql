CREATE TABLE account(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(6),
    agency VARCHAR(32),
    type VARCHAR(32),
    client_id BIGINT,

    FOREIGN KEY(client_id) REFERENCES client(id),
    UNIQUE(number)
);