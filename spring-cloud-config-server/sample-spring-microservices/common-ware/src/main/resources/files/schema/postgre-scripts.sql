
DROP TABLE IF EXISTS cities;
CREATE TABLE cities(id serial PRIMARY KEY, name VARCHAR(255), population integer);

INSERT INTO cities(name, population) VALUES('Bratislava', 432000);
INSERT INTO cities(name, population) VALUES('Budapest', 1759000);
INSERT INTO cities(name, population) VALUES('Prague', 1280000);
INSERT INTO cities(name, population) VALUES('Warsaw', 1748000);
INSERT INTO cities(name, population) VALUES('Los Angeles', 3971000);
INSERT INTO cities(name, population) VALUES('New York', 8550000);
INSERT INTO cities(name, population) VALUES('Edinburgh', 464000);
INSERT INTO cities(name, population) VALUES('Berlin', 3671000);

commit;


DROP TABLE IF EXISTS employee;
CREATE TABLE employee(id serial PRIMARY KEY, name VARCHAR(255), dep_id integer, manager_id integer, salary float);

insert into employee (id,name,dep_id,manager_id,salary) values (1, 'John',  11, 2, 1200);
insert into employee (id,name,dep_id,manager_id,salary) values (2, 'Jane',  12, 2, 2200);
insert into employee (id,name,dep_id,manager_id,salary) values (3, 'Steve', 13, 2, 1800);
insert into employee (id,name,dep_id,manager_id,salary) values (4, 'Bruce', 14, 2, 3200);
insert into employee (id,name,dep_id,manager_id,salary) values (5, 'Lina',  15, 2, 5200);
insert into employee (id,name,dep_id,manager_id,salary) values (6, 'Wang',  16,	3, 6900);
insert into employee (id,name,dep_id,manager_id,salary) values (7, 'Zhan',  17, 2, 1500);
insert into employee (id,name,dep_id,manager_id,salary) values (8, 'Liu',   18,	5, 6900);
insert into employee (id,name,dep_id,manager_id,salary) values (9, 'Chen',  19, 2, 3400);
insert into employee (id,name,dep_id,manager_id,salary) values (10, 'Ding', 110, 2, 4200);


commit;


