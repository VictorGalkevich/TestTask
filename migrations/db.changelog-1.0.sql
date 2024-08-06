--liquibase formatted sql

--changeset VictorGalkevich:1
CREATE TABLE IF NOT EXISTS translation
(
    id         BIGSERIAL PRIMARY KEY,
    req VARCHAR(256),
    resp VARCHAR(256),
    ip VARCHAR(32)
);
