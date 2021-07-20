create table if not exists users(user_id number, username varchar2(40) , password varchar2(80), is_active boolean);

create table  if not exists roles(role_id int, role_name varchar2(40));

create table if not exists users_roles(user_id int, role_id int);