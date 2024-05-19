--<table name>:				Users
--<description>:			Used to store the list of all users of the system

CREATE TABLE Users
(
	id				    SERIAL NOT NULL,
	idnp		        VARCHAR(13) NOT NULL,
	password		    VARCHAR(255) NOT NULL,
	email			    VARCHAR(255) NULL,
	is_enabled		    BOOLEAN NOT NULL DEFAULT FALSE,
	name			    VARCHAR(100) NOT NULL,
	surname			    VARCHAR(100) NOT NULL,
	patronymic		    VARCHAR(100) NULL,
	CONSTRAINT		    PK_Users PRIMARY KEY
	(
		id
	)
);