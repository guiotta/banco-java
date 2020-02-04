CREATE TABLE client(
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(64),
    type VARCHAR(32),
    document VARCHAR(14),
    score INT,

    UNIQUE(document)
);