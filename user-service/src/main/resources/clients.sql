DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS users;


CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    UUID     VARCHAR            NOT NULL UNIQUE,
    role     VARCHAR(32)        NOT NULL,
    email    VARCHAR(64) UNIQUE NOT NULL,
    password VARCHAR(128)       NOT NULL
);

CREATE TABLE client
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT REFERENCES users (id) NOT NULL UNIQUE,
    firstname  VARCHAR(64)                  NOT NULL,
    lastname   VARCHAR(64)                  NOT NULL,
    birthdate DATE                         NOT NULL
);