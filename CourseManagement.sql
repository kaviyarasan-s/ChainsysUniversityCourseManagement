create table users_registration (
id int primary key,
name varchar2(100),
email varchar2(100) unique,
phone varchar2(10) unique,
password varchar2(100) default 'pass123',
message varchar2(4000)
);

alter table users_registration add password varchar2(100) default 'pass123';

create sequence users_registration_id_seq start with 1 increment by 1;

create table chainsys_course (
id number primary key,
name varchar2(200),
course_code varchar(10)
);

create sequence chainsys_course_id_seq start with 1 increment by 1;

alter table chainsys_course  add course_code varchar(10);


create table course_enrollment(
id int primary key,
user_id int,
course_id int ,
start_date timestamp,
end_date timestamp,
status varchar2(50),
constraints userregistration_id_fk foreign key(user_id) references users_registration(id),
constraints chainsyscourse_id_fk foreign key(course_id) references chainsys_course(id)
);

alter table course_enrollment add start_date timestamp;
alter table course_enrollment add end_date timestamp;
alter table course_enrollment add status varchar2(50);


create sequence course_enrollment_id_seq  start with 1 increment by 1;