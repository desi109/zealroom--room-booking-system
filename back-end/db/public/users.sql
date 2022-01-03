create table users
(
    user_uuid     varchar(255) not null
        primary key,
    first_name    varchar(255) not null,
    last_name     varchar(255) not null,
    is_admin      boolean      not null,
    session_token varchar(255),
    email         varchar(255) not null,
    pwd           varchar(255) not null
);

alter table users
    owner to postgres;

