CREATE TABLE IF NOT EXISTS t_employee (
    id          INTEGER PRIMARY KEY,
    name        VARCHAR(32),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    modify_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- employee_sale.db
INSERT INTO t_employee(id, name)
VALUES (1, 'Bob');

-- employee_tech.db
INSERT INTO t_employee(id, name)
VALUES (1, 'Tom');

CREATE TABLE IF NOT EXISTS t_user (
    id          INTEGER PRIMARY KEY,
    name        VARCHAR(32),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    modify_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- test00.db
INSERT INTO t_user(id, name)
VALUES (4, 'Jason');

-- test01.db
INSERT INTO t_user(id, name)
VALUES (1, 'Tom');

-- test02.db
INSERT INTO t_user(id, name)
VALUES (2, 'Bob');

-- test03.db
INSERT INTO t_user(id, name)
VALUES (3, 'Annie');
