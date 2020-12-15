INSERT INTO roles(id, role)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

INSERT INTO permissions(id, permission)
VALUES (1, 'READ:HOTELS'),
       (2, 'WRITE:HOTELS');

INSERT INTO role_permissions (role_id, permission_id)
VALUES (2, 1), -- USER - READ:HOTELS
       (1, 1), -- ADMIN - READ:HOTELS
       (1, 2); -- ADMIN - WRITE:HOTELS

INSERT INTO users(username,
                  password,
                  firstname,
                  lastname,
                  status,
                  role_id)
VALUES ('admin',
        '$2a$10$ayMtvsK0znTmA.PkYelW6OnXb2ftk.SU4LvdANx74/UGmkwSBC.Sy',
        'Admin',
        'Adminovich',
        'ACTIVE',
        1),
       ('user',
        '$2a$10$h.DI7twP43oj71JLfooZfO2JgnWoEEb4DafnMQQOI.z0jEXE/Bh1W',
        'User',
        'Userovich',
        'ACTIVE',
        2);
