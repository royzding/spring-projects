
DROP TABLE account;
DROP TABLE person;
DROP TABLE employee;
DROP TABLE manager;
DROP TABLE department;
DROP TABLE user_table;
DROP TABLE department_holiday_date_mapping;
DROP TABLE holiday_date;

CREATE TABLE holiday_date 
(	 id 			INTEGER GENERATED ALWAYS as Identity primary key, 
	 name 			VARCHAR2(50 CHAR), 
	 day 			SMALLINT NOT NULL, 
	 month 			SMALLINT NOT NULL, 
	 year 			SMALLINT NOT NULL, 
     modified_by	VARCHAR2(50),
     created_date   TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
     modified_date  TIMESTAMP(0)
);

CREATE TABLE department_holiday_date_mapping 
(	 id 				INTEGER GENERATED ALWAYS as Identity primary key, 
	 department_id		INTEGER NOT NULL, 
	 holiday_date_id	INTEGER NOT NULL, 
     modified_by		VARCHAR2(50),
     created_date   	TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
     modified_date  	TIMESTAMP(0),
     constraint FK_dhdm_department_id   FOREIGN KEY (department_id) REFERENCES department(id),
     constraint FK_dhdm_holiday_date_id FOREIGN KEY (holiday_date_id) REFERENCES holiday_date(id)     
);

CREATE TABLE user_table (
  	 id 			INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  	 name 			VARCHAR2(50) NOT NULL,
     modified_by	VARCHAR2(50),
     created_date   TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
     modified_date  TIMESTAMP(0)
);

CREATE TABLE account (
     id 			INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     name  			VARCHAR2(50) NOT NULL,
     country    	VARCHAR2(50),     
     modified_by	VARCHAR2(50),
     created_date   TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
     modified_date  TIMESTAMP(0)
     
);

CREATE TABLE person (
     id 			INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     name  			VARCHAR2(50) NOT NULL,
     country    	VARCHAR2(50),     
     modified_by	VARCHAR2(50),
     created_date   TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
     modified_date  TIMESTAMP(0)
     
);

CREATE TABLE manager (
     id 			INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     name  			VARCHAR2(50) NOT NULL,
     salary 		NUMBER(10,2),
     modified_by	VARCHAR2(50),
     created_date   TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
     modified_date  TIMESTAMP(0)
);

DROP TABLE manager_bk; 
CREATE TABLE manager_bk (
     id 			INTEGER,
     name  			VARCHAR2(50) NOT NULL,
     salary 		NUMBER(10,2),
     modified_by	VARCHAR2(50),
     created_date   TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
     modified_date  TIMESTAMP(0)
);

CREATE TABLE department (
     id 			INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     name  			VARCHAR2(50) NOT NULL,
     modified_by	VARCHAR2(50),
     created_date   TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
     modified_date  TIMESTAMP(0)
);

CREATE TABLE employee (
     id 					INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     name  					VARCHAR2(50) NOT NULL,
     dep_id 				NUMBER,
     manager_id 			NUMBER,
     salary 				NUMBER(10,2),
     designation    		VARCHAR2(50),
     modified_by			VARCHAR2(50),
     created_date   		TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
     modified_date  		TIMESTAMP(0),
	 CONSTRAINT fk_dep_id FOREIGN KEY (dep_id) REFERENCES department(id),
	 CONSTRAINT fk_manager_id FOREIGN KEY (manager_id) REFERENCES manager(id)
);


http://localhost:8083/employee/v3/swagger-ui.html
http://localhost:8084/department/v3/swagger-ui.html


//get all departments:
//http://localhost:8082/department/department/all

////create department:
//http://localhost:8082/department/department/list
[
  {
    "name": "dep-1"
  },
  {
    "name": "dep-2"
  },
  {
    "name": "dep-3"
  }
]

////create manager:
[
  {
    "name": "manager-1",
	"salary": 10
  },
  {
    "name": "manager-2",
	"salary": 20
  },
  {
    "name": "manager-3",
	"salary": 30
  }
]

///create employee:
[
	{
	  "id": 0,
	  "name": "name1",
	  "salary": 10,
	  "depId": 1,
	  "managerId": 4
	},
	{
	  "id": 0,
	  "name": "name2",
	  "salary": 20,
	  "depId": 2,
	  "managerId": 4
	},
	{
	  "id": 0,
	  "name": "name3",
	  "salary": 30,
	  "depId": 2,
	  "managerId": 5
	},
	{
	  "id": 0,
	  "name": "name4",
	  "salary": 40,
	  "depId": 3,
	  "managerId": 6
	},
	{
	  "id": 0,
	  "name": "name5",
	  "salary": 50,
	  "depId": 3,
	  "managerId": 6
	},
	{
	  "id": 0,
	  "name": "name6",
	  "salary": 60,
	  "depId": 3,
	  "managerId": 6
	}
]


insert into person (id,name,country) values (1, 'John', 	'USA');
insert into person (id,name,country) values (2, 'Jane', 	'CHINA');
insert into person (id,name,country) values (3, 'Steve', 	'UK');
insert into person (id,name,country) values (4, 'Bruce', 	'GERMAN');
insert into person (id,name,country) values (5, 'Lina', 	'JAPAN');
insert into person (id,name,country) values (6, 'Wang', 	'CHINA');


insert into employee (id,name,lastname,dep_id,salary) values (1, 'John', 'l1', 2, 1200);
insert into employee (id,name,lastname,dep_id,salary) values (2, 'Jane', 'l2', 2, 2200);
insert into employee (id,name,lastname,dep_id,salary) values (3, 'Steve', 'l3', 2, 1800);
insert into employee (id,name,lastname,dep_id,salary) values (4, 'Bruce', 'l4', 2, 3200);
insert into employee (id,name,lastname,dep_id,salary) values (5, 'Lina', 'l5', 2, 5200);
insert into employee (id,name,lastname,dep_id,salary) values (6, 'Wang', 'l6',	3, 6900);
insert into employee (id,name,lastname,dep_id,salary) values (7, 'Zhan', 'l7', 2, 1500);
insert into employee (id,name,lastname,dep_id,salary) values (8, 'Liu',   'l8',	5, 6900);
insert into employee (id,name,lastname,dep_id,salary) values (9, 'Chen', 'l9', 2, 3400);
insert into employee (id,name,lastname,dep_id,salary) values (10, 'Ding', 'l10', 2, 4200);

insert into manager (id,name,salary) values (1, 'Marc', 1200);
insert into manager (id,name,salary) values (2, 'Luke', 2200);
insert into manager (id,name,salary) values (3, 'Emily', 1800);

insert into department (id,name) values (2, 'Computer Science');
insert into department (id,name) values (3, 'Chemistry');
insert into department (id,name) values (5, 'Physics');

commit;


