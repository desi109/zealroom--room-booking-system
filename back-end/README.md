Project setup: clone the project import project in intellij/eclipse install https://www.enterprisedb.com/downloads/postgres-postgresql-downloads *im using 13.1 go in pgAdmin4 create a database zealroom right click the database and open QueryTool write and execute this:

DROP TABLE IF EXISTS users CASCADE; CREATE TABLE users( user_uuid VARCHAR(255) NOT NULL PRIMARY KEY UNIQUE, first_name VARCHAR(255) NOT NULL, last_name VARCHAR(255) NOT NULL, is_admin BOOLEAN NOT NULL, session_token VARCHAR(255), email VARCHAR(255) NOT NULL, pwd VARCHAR(255) NOT NULL );
