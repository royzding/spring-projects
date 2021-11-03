DROP SEQUENCE STUDENT_SEQ;
DROP TABLE STUDENT;

CREATE TABLE student 
(	id 		NUMBER(19) NOT NULL ENABLE, 
	name 	VARCHAR2(50 CHAR), 
	email 	VARCHAR2(50 CHAR), 
	branch 	VARCHAR2(50 CHAR), 
	PRIMARY KEY (id)
);
  

CREATE SEQUENCE  STUDENT_SEQ MINVALUE 1 MAXVALUE 9999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;


INSERT INTO STUDENT (id, name, email, branch) VALUES(1, 'royding1','royding1@yahoo.com', 'cs1');
INSERT INTO STUDENT (id, name, email, branch) VALUES(2, 'royding2','royding2@yahoo.com', 'cs2');
INSERT INTO STUDENT (id, name, email, branch) VALUES(3, 'royding3','royding3@yahoo.com', 'cs3');
INSERT INTO STUDENT (id, name, email, branch) VALUES(4, 'royding4','royding4@yahoo.com', 'cs4');
INSERT INTO STUDENT (id, name, email, branch) VALUES(5, 'royding5','royding5@yahoo.com', 'cs5');
INSERT INTO STUDENT (id, name, email, branch) VALUES(6, 'royding6','royding6@yahoo.com', 'cs6');

commit;