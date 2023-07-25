CREATE DATABASE testDB;

CREATE TABLE Taxes (
    id SERIAL PRIMARY KEY,
	endpoint varchar(255),
    taxPercent varchar(255),
    statusCode bigint ,
    timestamp varchar(255),
	response varchar(255)
);
