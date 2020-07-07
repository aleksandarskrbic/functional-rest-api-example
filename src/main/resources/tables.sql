CREATE TABLE trips (
  id BIGSERIAL PRIMARY KEY,
  destination VARCHAR NOT NULL,
  starDate DATE NOT NULL,
  endDate DATE NOT NULL,
  description VARCHAR
);