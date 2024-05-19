-- INSERT DEFAULT ADMIN !!!
-- DEFAULT Account - user: 'admin' password: 'admin' WITHOUT '
INSERT INTO Users
(idnp,password,email,is_enabled,name,surname,patronymic)
VALUES
('2000000000000','admin','admin@admin.admin',TRUE,'Admin','Admin','Admin');

INSERT INTO UserToRole
VALUES
(
    (SELECT MAX(Id) FROM Users),
    (SELECT Id FROM UserRoles WHERE Name = 'Admin')
);