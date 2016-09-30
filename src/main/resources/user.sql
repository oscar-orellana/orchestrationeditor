CREATE TABLE orchestrationeditor.user
(
	username character varying(50) NOT NULL,
	password character varying(50) NOT NULL,
	role character varying(50) NOT NULL,
	firstname character varying(50),
	lastname character varying(50),
	birthdate date,
	email character varying(50),
	phonenumber character varying(50),
	CONSTRAINT user_pkey PRIMARY KEY (username)
)
WITH (
	OIDS=FALSE
);
ALTER TABLE orchestrationeditor.user
	OWNER TO orchestrationeditor;