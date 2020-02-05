CREATE TABLE overdraft (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    value DECIMAL(10,2)
);

CREATE TABLE credit_card (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    value DECIMAL(10,2)
);

CREATE TABLE credit(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    overdraft_id BIGINT,
    credit_card_id BIGINT,

    FOREIGN KEY(overdraft_id) REFERENCES overdraft(id),
    FOREIGN KEY(credit_card_id) REFERENCES credit_card(id),
    UNIQUE(overdraft_id, credit_card_id)
);

CREATE TABLE score(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    minimal INT,
    maximal INT,
    credit_id BIGINT,

    FOREIGN KEY(credit_id) REFERENCES credit(id),
    UNIQUE(maximal, minimal, credit_id)
);

//Insere os limites de chegue especial.
INSERT INTO overdraft(id, value) VALUES(1, 1000.00);
INSERT INTO overdraft(id, value) VALUES(2, 2000.00);
INSERT INTO overdraft(id, value) VALUES(3, 5000.00);

//Insere os limites de cartão de crédito.
INSERT INTO credit_card(id, value) VALUES(1, 200.00);
INSERT INTO credit_card(id, value) VALUES(2, 2000.00);
INSERT INTO credit_card(id, value) VALUES(3, 15000.00);

//Insere os valores de crédito.
INSERT INTO credit(id, overdraft_id, credit_card_id) VALUES(1, null, null);
INSERT INTO credit(id, overdraft_id, credit_card_id) VALUES(2, 1, 1);
INSERT INTO credit(id, overdraft_id, credit_card_id) VALUES(3, 2, 2);
INSERT INTO credit(id, overdraft_id, credit_card_id) VALUES(4, 3, 3);

// Insere os valores dos scores na base de dados.
INSERT INTO score(id, minimal, maximal, credit_id) VALUES(1, 0, 1, 1);
INSERT INTO score(id, minimal, maximal, credit_id) VALUES(2, 2, 5, 2);
INSERT INTO score(id, minimal, maximal, credit_id) VALUES(3, 6, 8, 3);
INSERT INTO score(id, minimal, maximal, credit_id) VALUES(4, 9, 9, 4);