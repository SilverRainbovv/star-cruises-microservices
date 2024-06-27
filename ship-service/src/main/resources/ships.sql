DROP TABLE IF EXISTS seat;
DROP TABLE IF EXISTS ship;

CREATE TABLE IF NOT EXISTS ship
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(64) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS seat
(
    id         BIGSERIAL PRIMARY KEY,
    ship_id    BIGINT REFERENCES ship (id) ON DELETE CASCADE NOT NULL,
    seat_class VARCHAR(32)                                   NOT NULL,
    seat_group INT                                           NOT NULL,
    number     INT                                           NOT NULL,
    price      NUMERIC(9, 2)                                 NOT NULL,
    vacancy    VARCHAR(16)                                   NOT NULL,
    number_of_persons INT NOT NULL
);