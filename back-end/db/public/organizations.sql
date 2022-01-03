create table organizations
(
    organization_uuid varchar(255) not null
        primary key,
    description       varchar(255),
    name              varchar(255)
);

alter table organizations
    owner to postgres;

