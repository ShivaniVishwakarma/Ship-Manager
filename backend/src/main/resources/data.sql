create table if not exists users(user_id int, username varchar2(40) , password varchar2(80), is_active boolean);

--Insert Into users (user_id , username, password , is_active) values ( 1, 'shivani','$2a$10$sHCWxNowmG6kOOkfaDQPp.KC6mErRsSVHoz1L1Vr0DDAXV41AVRRa',true );
--$2a$10$2SKDbWdrk3TLV0LiS5KJ2uHvCadmjvChu8FN2EVtloK3yob9mXfxq

--update users set password ='$2a$10$FRkpobaAoprZJcDAuHhN5.a0i.bqMynK6QitsKfZY6IhRgX3qUSOO' where username ='shivani';

create table if not exists ship(id int, ship_name varchar2(40) , ship_code varchar2(80), ship_Length_In_Meters number, ship_width_In_Meters number);

--delete from ship;

--insert into ship (id, ship_name, ship_code , ship_Length_In_Meters , ship_width_In_Meters) values('13','TitanicNew','SHIP-1000-B4',80,65);


create table  if not exists roles(role_id int, role_name varchar2(40));

--Insert Into roles values ( 1, 'ADMIN' );

create table if not exists users_roles(user_id int, role_id int);

--Insert Into users_roles values ( 1, 1 );
