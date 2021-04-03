//https://attacomsian.com/blog/spring-data-jpa-many-to-many-mapping
//https://attacomsian.com/blog/spring-data-jpa-one-to-one-mapping

DROP SEQUENCE ADDRESS_SEQ;
DROP SEQUENCE COURSE_SEQ;
DROP SEQUENCE STUDENT_SEQ;

DROP TABLE student_course;
DROP TABLE ADDRESS;
DROP TABLE student;
DROP TABLE course;

CREATE TABLE student (
     id 			INT PRIMARY KEY,
     name  			VARCHAR2(50) NOT NULL,
     age	 		INT
);

CREATE SEQUENCE  STUDENT_SEQ MINVALUE 1 MAXVALUE 9999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

CREATE TABLE address (
     id 			INT PRIMARY KEY,
     addr  			VARCHAR2(250) NOT NULL,
	 student_id     INT,
	 FOREIGN KEY (student_id) REFERENCES student(id)
);

CREATE SEQUENCE  ADDRESS_SEQ MINVALUE 1 MAXVALUE 9999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

CREATE TABLE course (
     id 			INT PRIMARY KEY,
     name  			VARCHAR2(50) NOT NULL
);

CREATE SEQUENCE  COURSE_SEQ MINVALUE 1 MAXVALUE 9999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

CREATE TABLE student_course (
     student_id 	INT,
     course_id  	INT,
	 FOREIGN KEY (student_id) REFERENCES student(id),
	 FOREIGN KEY (course_id) REFERENCES course(id)
);


insert into student (id,name,age) values (1, 'John',  23);
insert into student (id,name,age) values (2, 'Jane',  24);
insert into student (id,name,age) values (3, 'Steve', 25);
insert into student (id,name,age) values (4, 'Bruce', 26);
insert into student (id,name,age) values (5, 'Lina',  27);
insert into student (id,name,age) values (6, 'Wang',  28);

insert into address (id,addr,student_id) values (1, 'John addr',  6);
insert into address (id,addr,student_id) values (2, 'Jane addr',  2);
insert into address (id,addr,student_id) values (3, 'Steve addr', 4);
insert into address (id,addr,student_id) values (4, 'Bruce addr', 3);
insert into address (id,addr,student_id) values (5, 'Lina addr',  1);
insert into address (id,addr,student_id) values (6, 'Wang addr',  5);

insert into course (id,name) values (5, 'Computer');
insert into course (id,name) values (2, 'Chemistry');
insert into course (id,name) values (3, 'Physics');

insert into student_course (student_id,course_id) values (2, 5);
insert into student_course (student_id,course_id) values (3, 3);
insert into student_course (student_id,course_id) values (5, 2);
insert into student_course (student_id,course_id) values (2, 3);
insert into student_course (student_id,course_id) values (3, 2);
insert into student_course (student_id,course_id) values (5, 3);


commit;



//////////////////////////////////////////////////////////////


    {
        "name": "Lina",
        "age": 27,
        "address": {
            "addr": "Wang addr"
        },
        "course": [
            {
                "id": 2,
                "name": "Chemistry"
            },
            {
                "id": 3,
                "name": "Physics"
            }
        ]
    }
