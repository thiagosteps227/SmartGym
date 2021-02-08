create table timetable(
classID int not null auto_increment,
classCode int not null,
className varchar(25) ,
instructor varchar(25),
weekDay varchar(100),
classTime varchar(10),
primary key(classCode),
foreign key (classID) references classtable(classID));

insert into timetable (classID, classCode, className, instructor, weekDay, classTime) 
values (1, 9901, 'Fitness for Kids', 'Paul Byrne', 'Monday', '10:00');
insert into timetable 
values (1, 9902, 'Fitness for Kids', 'Paul Byrne', 'Wednesday', '16:00');
insert into timetable
values (1, 9903,'Fitness for Kids', 'Paul Byrne', 'Friday', '10:00');
insert into timetable 
values (2, 9801, 'Zumba', 'Francine Kurtz', 'Tuesday', '15:00');
insert into timetable 
values (2, 9802, 'Zumba', 'Francine Kurtz', 'Thursday', '19:00');
insert into timetable 
values (3, 9701, 'Spinning', 'John OHana', 'Monday' , '11:00');
insert into timetable 
values (3, 9702, 'Spinning', 'John OHana', 'Tuesday' , '18:00');
insert into timetable 
values (3, 9703, 'Spinning', 'John OHana', 'Saturday', '11:00');
insert into timetable 
values (4, 9601, 'Aerobics', 'Audrey Clark', 'Tuesday' , '07:00');
insert into timetable 
values (4, 9602, 'Aerobics', 'Audrey Clark', 'Wednesday' ,'15:00' );
insert into timetable 
values (4, 9603, 'Aerobics', 'Audrey Clark','Thursday', '07:00');
insert into timetable 
values (5, 9501, 'Fitness for over 60s', 'David Black', 'Monday', '08:00');
insert into timetable 
values (5, 9502, 'Fitness for over 60s', 'David Black', 'Thursday', '08:00');
insert into timetable 
values (5, 9503, 'Fitness for over 60s', 'David Black', 'Saturday'  , '08:00');

select * from timetable;
