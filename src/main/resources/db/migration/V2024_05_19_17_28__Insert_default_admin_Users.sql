-- INSERT DEFAULT ADMIN !!!
-- DEFAULT Account - user: 'admin' password: 'admin' WITHOUT '
INSERT INTO Users
(id_institution,idnp,password,email,is_enabled,name,surname,patronymic)
VALUES
(NULL,'2000000000000','admin','admin@admin.admin',TRUE,'Admin','Admin','Admin');

INSERT INTO UserToRole
VALUES
(
    (SELECT MAX(Id) FROM Users),
    (SELECT Id FROM UserRoles WHERE Name = 'Admin')
);