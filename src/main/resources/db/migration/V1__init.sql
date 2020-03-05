drop table if exists account;
drop sequence if exists hibernate_sequence;
create table account (id bigint not null, email varchar(255), password varchar(255), user_name varchar(255), primary key (id));
create sequence hibernate_sequence start with 1 increment by 1;
