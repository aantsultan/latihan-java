CREATE DATABASE belajar_restapi_flutter;

USE belajar_restapi_flutter;

CREATE TABLE m_user (
    user_id bigint not null auto_increment,
    full_name varchar(100) not null ,
    email varchar(100) not null ,
    phone_number varchar(20) not null ,
    birth_date date,
    gender varchar(10),
    password varchar(255) not null,
    promo_events boolean,
    term_conditions boolean,
    token varchar(255),
    token_expired_at bigint,

    primary key (user_id),
    unique (token, phone_number)
) ENGINE InnoDB;

-- drop table m_user;

select * from m_user;

alter table m_user modify token varchar(500);