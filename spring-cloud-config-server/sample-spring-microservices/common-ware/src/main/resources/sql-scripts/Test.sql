
DROP SEQUENCE TEST_SEQ;
DROP TABLE TEST;

CREATE TABLE test (
     id 			NUMBER PRIMARY KEY,
     name  			VARCHAR2(50) NOT NULL,
     age	 		NUMBER
);

CREATE SEQUENCE  TEST_SEQ MINVALUE 1 MAXVALUE 9999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

insert into test (id,name,age) values (1, 'John',  23);
insert into test (id,name,age) values (2, 'Jane',  24);
insert into test (id,name,age) values (3, 'Steve', 25);
insert into test (id,name,age) values (4, 'Bruce', 26);
insert into test (id,name,age) values (5, 'Lina',  27);
insert into test (id,name,age) values (6, 'Wang',  28);

commit;