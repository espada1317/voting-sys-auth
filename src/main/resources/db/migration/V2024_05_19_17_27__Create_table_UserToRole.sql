--<table name>:				UserToRole
--<description>:			Used to store the link between user and user group
--                            1 - Admin
--                            2 - User

CREATE TABLE UserToRole
(
	id_user			INT NOT NULL,
	id_role			INT NOT NULL,
	CONSTRAINT		PK_UserToRole PRIMARY KEY
	(
		id_user,
		id_role
	),
	CONSTRAINT		FK_UserToRole_User FOREIGN KEY (id_user)
	REFERENCES		Users (id)
	ON DELETE		NO ACTION
	ON UPDATE		NO ACTION,
	CONSTRAINT		FK_UserToRole_UserRole FOREIGN KEY (id_role)
	REFERENCES		UserRoles (id)
	ON DELETE		NO ACTION
	ON UPDATE		NO ACTION
);