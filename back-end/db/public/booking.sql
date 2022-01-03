create table booking
(
    booking_uuid varchar(255) not null
        constraint booking_pk
            primary key,
    check_in     timestamp    not null,
    check_out    timestamp    not null,
    is_booked    boolean      not null,
    room_id      varchar(255) not null
        constraint fk_rooms
            references rooms,
    user_uuid    varchar(255) not null
        constraint fk_users
            references users
);

alter table booking
    owner to postgres;

