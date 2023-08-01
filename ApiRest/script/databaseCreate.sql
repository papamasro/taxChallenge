CREATE DATABASE testDB;

CREATE TABLE call_history
(
    history_id  SERIAL PRIMARY KEY,
    Endpoint    varchar(255),
    status_code int8,
    timestamp   varchar(255),
    response    varchar(255)
);

CREATE TABLE users
(
    user_id  SERIAL PRIMARY KEY,
    username varchar(255),
    password varchar(255)
);
