CREATE TABLE users
(
    id       SERIAL  NOT NULL PRIMARY KEY,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    status   VARCHAR NOT NULL,
    role_id  INTEGER NOT NULL
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
)