CREATE TABLE client(
    id BIGINT AUTO_INCREMENT,
    type VARCHAR(32),
    document VARCHAR(14),
    score INT,

    UNIQUE(document)
);