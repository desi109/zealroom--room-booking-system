create table userorganizationconnections
(
    connection_id     bigint  not null
        primary key,
    is_manager        boolean not null,
    organization_uuid varchar(255),
    user_uuid         varchar(255)
);

alter table userorganizationconnections
    owner to postgres;

