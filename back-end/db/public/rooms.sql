create table rooms
(
    room_uuid        varchar(255) not null
        primary key,
    capacity         integer      not null,
    room_description varchar(255) not null,
    room_number      varchar(255) not null
);

alter table rooms
    owner to postgres;

