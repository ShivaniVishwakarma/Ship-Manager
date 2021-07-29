drop table if exists user_roles;
drop table if exists role;
drop table if exists user;

create table role (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id));
create table user_roles (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id));
create table user(id int, username varchar2(40) , password varchar2(80), is_active boolean, primary key (id));

INSERT INTO role (id, description, name) VALUES (1, 'Admin role', 'ADMIN');
INSERT INTO role (id, description, name) VALUES (2, 'User role', 'USER');

-- ADMIN User -- password is admin
Insert Into user (id , username, password , is_active) values ( 1, 'shivani','$2a$10$sHCWxNowmG6kOOkfaDQPp.KC6mErRsSVHoz1L1Vr0DDAXV41AVRRa',true );
-- USER -- password is demo
Insert Into user (id , username, password , is_active) values ( 2, 'demo','$2a$10$1JNeb3eDyDr5IzNjWdk0v..w49X2olk96LPEQPxfVAYToJMAMsxcG', true);

Insert Into user_roles (user_id , role_id) values (1, 1); -- mapping shivani user with ADMIN role
Insert Into user_roles (user_id , role_id) values (2, 2); -- mapping demo user with USER role
