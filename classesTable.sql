create database smartgym;

use smartgym;

create table classtable(
classID int not null auto_increment,
className varchar(25) ,
personLimit int,
pricePerClass double,
priceTwelveWeeks double,
primary key (classID));

insert into classtable (className, personLimit, pricePerClass, priceTwelveWeeks) 
values ('Fitness for Kids', 40, 5, 60);
insert into classtable 
values (2, 'Zumba', 30, 10, 120);
insert into classtable 
values (3, 'Spinning', 30, 15, 165);
insert into classtable 
values (4, 'Aerobics', 30, 20, 240);
insert into classtable 
values (5, 'Fitness for over 60s', 20, 5, 60);

select * from classtable;
