CREATE DATABASE belajar_spring_security_form;
USE belajar_spring_security_form;

CREATE TABLE m_user (
    user_id int not null auto_increment,
    username varchar(100) not null,
    password varchar(500) not null,

    primary key (user_id),
    unique (username)
) engine InnoDB;

select * from m_user;

SHOW TABLES ;