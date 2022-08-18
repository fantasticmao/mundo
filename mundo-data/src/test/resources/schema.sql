CREATE TABLE IF NOT EXISTS t_user (
    id         INTEGER PRIMARY KEY,
    name       VARCHAR(32),
    createTime DATETIME DEFAULT CURRENT_TIMESTAMP,
    modifyTime DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- test01.db
INSERT INTO t_user(id, name)
VALUES (1, 'Tom');

-- test02.db
INSERT INTO t_user(id, name)
VALUES (2, 'Bob');

-- test03.db
INSERT INTO t_user(id, name)
VALUES (3, 'Annie');

-- test04.db
INSERT INTO t_user(id, name)
VALUES (4, 'Jason');
