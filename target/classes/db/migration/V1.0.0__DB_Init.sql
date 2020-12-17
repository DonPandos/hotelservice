CREATE TABLE users
(
    id        SERIAL  NOT NULL PRIMARY KEY,
    username  VARCHAR NOT NULL,
    password  VARCHAR NOT NULL,
    firstname VARCHAR NOT NULL,
    lastname  VARCHAR NOT NULL,
    status    VARCHAR NOT NULL,
    role_id   INTEGER NOT NULL
);

CREATE TABLE roles
(
    id   SERIAL  NOT NULL PRIMARY KEY,
    role VARCHAR NOT NULL
);

CREATE TABLE permissions
(
    id         SERIAL  NOT NULL PRIMARY KEY,
    permission VARCHAR NOT NULL
);

CREATE TABLE role_permissions
(
    id            SERIAL  NOT NULL PRIMARY KEY,
    role_id       INTEGER NOT NULL,
    permission_id INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id),
    FOREIGN KEY (permission_id) REFERENCES permissions (id)
);

CREATE TABLE countries
(
    id      SERIAL  NOT NULL PRIMARY KEY,
    country VARCHAR NOT NULL
);

CREATE TABLE cities
(
    id         SERIAL  NOT NULL PRIMARY KEY,
    city       VARCHAR NOT NULL,
    country_id INTEGER NOT NULL,
    FOREIGN KEY (country_id) REFERENCES countries (id)
);

CREATE TABLE facilities
(
    id       SERIAL  NOT NULL PRIMARY KEY,
    facility VARCHAR NOT NULL
);

CREATE TABLE hotels
(
    id            SERIAL  NOT NULL PRIMARY KEY,
    name          VARCHAR NOT NULL,
    adress        VARCHAR,
    description   TEXT,
    image_name    VARCHAR,
    rating        INTEGER,
    city_id       INTEGER NOT NULL,
    FOREIGN KEY (city_id) REFERENCES cities (id)
);

CREATE TABLE hotels_facility
(
    id SERIAL NOT NULL PRIMARY KEY,
    hotel_id INTEGER NOT NULL,
    facility_id INTEGER NOT NULL,
    FOREIGN KEY (hotel_id) REFERENCES hotels (id),
    FOREIGN KEY (facility_id) REFERENCES facilities (id),
    UNIQUE (hotel_id, facility_id)
);
