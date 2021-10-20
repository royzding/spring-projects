




/*create professor, department */

drop table professor;
drop table department;

CREATE TABLE professor (
     id 			NUMBER PRIMARY KEY,
     name  			VARCHAR2(50) NOT NULL,
     dep_id  		NUMBER,
     salary  		NUMBER(10,2)
);

insert into professor (id,name,dep_id,salary) values (1, 'John', 	2, 1200);
insert into professor (id,name,dep_id,salary) values (2, 'Jane', 	2, 2200);
insert into professor (id,name,dep_id,salary) values (3, 'Steve', 	2, 1800);
insert into professor (id,name,dep_id,salary) values (4, 'Bruce', 	2, 3200);
insert into professor (id,name,dep_id,salary) values (5, 'Lina', 	2, 5200);
insert into professor (id,name,dep_id,salary) values (6, 'Wang', 	3, 6900);
insert into professor (id,name,dep_id,salary) values (7, 'Zhan', 	2, 1500);
insert into professor (id,name,dep_id,salary) values (8, 'Liu', 	5, 6900);
insert into professor (id,name,dep_id,salary) values (9, 'Chen', 	2, 3400);
insert into professor (id,name,dep_id,salary) values (10, 'Ding', 	2, 4200);

CREATE TABLE department (
     id 			NUMBER PRIMARY KEY,
     name  			VARCHAR2(50) NOT NULL
);

insert into department (id,name) values (2, 'Computer Science');
insert into department (id,name) values (3, 'Chemistry');
insert into department (id,name) values (5, 'Physics');


with tmp1 as 
(select max(asx2) as asx1 from
(
select avg(salary) as asx2 from professor group by dep_id
)
),
tmp2 as
(
select id, name from department
),
tmp3 as
(
select avg(salary) as asx3, dep_id from professor group by dep_id
)
select tmp2.name, tmp3.asx3 from tmp1, tmp2, tmp3
where tmp3.asx3 = tmp1.asx1 and tmp2.id = tmp3.dep_id
;


select d.name, avg_salary
from
(
	select avg_salary, dep_id from
	(
		select round(avg(p.salary),4) as avg_salary, p.dep_id from professor p
		group by p.dep_id
	) 
	where avg_salary =
	(
		select max(avg_salary) from
		(
			select round(avg(salary),4) as avg_salary from professor
			group by dep_id
			order by avg_salary desc
		) 
	)
)
	
inner join department d on dep_id = d.id;


///above is oracle


select d.name, x.avg_salary
from
(
	select avg_salary, dep_id from
	(select round(avg(p.salary),4) as avg_salary, p.dep_id from professor p
	group by p.dep_id
	) as sal_id
	where avg_salary =
	(
		select round(avg(salary),4) as avg_salary from professor
		group by dep_id
		order by avg_salary desc
		limit 1	
	)
	) as x
	
inner join department d on x.dep_id = d.id;


select d.name, x.avg_salary
from
(
	select avg_salary, dep_id from
	(
		select round(avg(p.salary),4) as avg_salary, p.dep_id from professor p
		group by p.dep_id
	) as sal_id
	where avg_salary =
	(
		select max(avg_salary) from
		(
			select round(avg(salary),4) as avg_salary from professor
			group by dep_id
			order by avg_salary desc
		) as max_sal
	)
) as x
	
inner join department d on x.dep_id = d.id;
