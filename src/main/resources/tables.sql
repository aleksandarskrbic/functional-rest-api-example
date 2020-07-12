CREATE TABLE trips (
  id BIGSERIAL PRIMARY KEY,
  destination VARCHAR NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  description VARCHAR
);