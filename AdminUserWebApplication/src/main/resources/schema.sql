create table if not exists user_pool
(
    id serial not null  PRIMARY KEY,
    username varchar(30) not null,
    password varchar(100) not null,
    role varchar(5) not null,
    full_name varchar(30) not null,
    position varchar(30) not null,
    department varchar(30) not null,
    address varchar(30) not null,
    phone_number varchar(13) not null
);