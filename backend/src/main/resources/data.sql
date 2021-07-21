create table if not exists users(user_id int, username varchar2(40) , password varchar2(80), is_active boolean);

--Insert Into users (user_id , username, password , is_active) values ( 1, 'shivani','$2a$10$sHCWxNowmG6kOOkfaDQPp.KC6mErRsSVHoz1L1Vr0DDAXV41AVRRa',true );

create table  if not exists roles(role_id int, role_name varchar2(40));

--Insert Into roles values ( 1, 'ADMIN' );

create table if not exists users_roles(user_id int, role_id int);

--Insert Into users_roles values ( 1, 1 );
