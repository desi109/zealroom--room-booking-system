Project setup: clone the project import project in intellij/eclipse install https://www.enterprisedb.com/downloads/postgres-postgresql-downloads *im using 13.1 go in pgAdmin4 create a database zealroom then open the db folder and right click on it - then choose import/restore . Select zealroom.sql file.

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users(
user_uuid VARCHAR(255) NOT NULL PRIMARY KEY UNIQUE,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
is_admin BOOLEAN NOT NULL,
session_token VARCHAR(255),
email VARCHAR(255) NOT NULL,
pwd VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS organizations CASCADE;
CREATE TABLE organizations(
organization_uuid VARCHAR(255) NOT NULL PRIMARY KEY UNIQUE
);

DROP TABLE IF EXISTS userorganizationconnections CASCADE;
CREATE TABLE userorganizationconnections(
connection_id SERIAL NOT NULL UNIQUE,
is_manager boolean NOT NULL DEFAULT false,
user_uuid VARCHAR(255) NOT NULL REFERENCES users(user_uuid),	organization_uuid VARCHAR(255) NOT NULL REFERENCES organizations(organization_uuid),
CONSTRAINT ouc_pk PRIMARY KEY(user_uuid,organization_uuid)
);

INSERT INTO users(user_uuid,first_name,last_name,is_admin,email,pwd) VALUES('1admin1','Admin','Admin',True,'ad@abv.bg','$2a$10$XIx2TyD8YC.2zHsbclQrFurkN663Pvo4/FGlDh9WHfTMgIOAk7Dcu');
INSERT INTO organizations(organization_uuid) VALUES ('orgid123');
INSERT INTO userorganizationconnections(connection_id,is_manager,user_uuid,organization_uuid) VALUES(1,True,'1admin1','orgid123')
DROP TABLE IF EXISTS users CASCADE; CREATE TABLE users( user_uuid VARCHAR(255) NOT NULL PRIMARY KEY UNIQUE, first_name VARCHAR(255) NOT NULL, last_name VARCHAR(255) NOT NULL, is_admin BOOLEAN NOT NULL, session_token VARCHAR(255), email VARCHAR(255) NOT NULL, pwd VARCHAR(255) NOT NULL );
