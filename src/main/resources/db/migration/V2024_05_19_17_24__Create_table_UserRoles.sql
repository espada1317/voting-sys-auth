--<table name>:				UserRoles
--<description>:			Used to store the list of all users roles of the system (admin, user).

CREATE TABLE UserRoles
(
	id				INT NOT NULL,
	name			VARCHAR(32) NOT NULL,
	CONSTRAINT		PK_UserRoles PRIMARY KEY
	(
		id
	)
);