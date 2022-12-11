CREATE TABLE IF NOT EXISTS t_employee (
    id          INTEGER PRIMARY KEY,
    name        TEXT,
    create_time TEXT DEFAULT (STRFTIME('%Y-%m-%dT%H:%M:%S', 'now')),
    update_time TEXT DEFAULT (STRFTIME('%Y-%m-%dT%H:%M:%S', 'now'))
);

-- employee_sale.db
INSERT INTO t_employee(id, name)
VALUES (1, 'Bob');

-- employee_tech.db
INSERT INTO t_employee(id, name)
VALUES (1, 'Tom');

CREATE TABLE IF NOT EXISTS t_user (
    id          INTEGER PRIMARY KEY,
    name        TEXT,
    create_time TEXT DEFAULT (STRFTIME('%Y-%m-%dT%H:%M:%S', 'now')),
    update_time TEXT DEFAULT (STRFTIME('%Y-%m-%dT%H:%M:%S', 'now'))
);

-- user_00.db
INSERT INTO t_user(id, name)
VALUES (4, 'Jason');

-- user_01.db
INSERT INTO t_user(id, name)
VALUES (1, 'Tom');

-- user_02.db
INSERT INTO t_user(id, name)
VALUES (2, 'Bob');

-- user_03.db
INSERT INTO t_user(id, name)
VALUES (3, 'Annie');
