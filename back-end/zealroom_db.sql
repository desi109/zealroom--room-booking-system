DROP TABLE booking;
DROP TABLE userorganizationconnections;
DROP TABLE rooms;
DROP TABLE users;
DROP TABLE organizations;

CREATE TABLE booking (
    room_id integer NOT NULL,
    booking_uuid integer NOT NULL,
    user_uuid character varying NOT NULL,
    is_booked boolean NOT NULL,
    check_in date NOT NULL,
    check_out date NOT NULL
);
CREATE TABLE rooms (
    capacity integer NOT NULL,
    room_uuid integer NOT NULL,
    room_description character varying(255) NOT NULL,
    room_number character varying(255) NOT NULL
);
CREATE TABLE users (
    user_uuid character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    is_admin boolean NOT NULL,
    session_token character varying(255),
    email character varying(255) NOT NULL,
    pwd character varying(255) NOT NULL
);
CREATE TABLE organizations (
    organization_uuid character varying(255) NOT NULL
);
CREATE TABLE userorganizationconnections (
    connection_id integer NOT NULL,
    user_uuid character varying(255) NOT NULL,
    organization_uuid character varying(255) NOT NULL,
    is_manager boolean NOT NULL
);
ALTER TABLE ONLY booking
ADD CONSTRAINT booking_pkey PRIMARY KEY (booking_uuid);
ALTER TABLE ONLY rooms ADD CONSTRAINT rooms_pkey PRIMARY KEY (room_uuid);
ALTER TABLE ONLY users ADD CONSTRAINT users_pkey PRIMARY KEY (user_uuid);
ALTER TABLE ONLY booking ADD CONSTRAINT fk_rooms FOREIGN KEY (room_id) REFERENCES rooms(room_uuid);
ALTER TABLE ONLY booking ADD CONSTRAINT fk_users FOREIGN KEY (user_uuid) REFERENCES users(user_uuid);
ALTER TABLE ONLY userorganizationconnections ADD CONSTRAIT fk_users FOREIGN KEY (user_uuid) REFERENCES users(user_uuid);
ALTER TABLE ONLY userorganizationconnections ADD CONSTRAIT fk_organizations FOREIGN KEY (organization_uuid) REFERENCES organizations(organization_uuid);
