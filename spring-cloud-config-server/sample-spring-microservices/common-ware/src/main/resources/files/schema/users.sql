DROP SEQUENCE users_SEQ;
DROP TABLE users;

CREATE TABLE users 
(	id 		NUMBER(19) NOT NULL ENABLE, 
	username 	VARCHAR2(50 CHAR), 
	password 	VARCHAR2(250 CHAR),
	PRIMARY KEY (id)
);
  

CREATE SEQUENCE  users_SEQ MINVALUE 1 MAXVALUE 9999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;


INSERT INTO users (id, username, password) VALUES(1, 'javainuse','$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6');
INSERT INTO users (id, username, password) VALUES(2, 'royding2','cs2');
INSERT INTO users (id, username, password) VALUES(3, 'royding3','cs3');
INSERT INTO users (id, username, password) VALUES(4, 'royding4','cs4');
INSERT INTO users (id, username, password) VALUES(5, 'royding5','cs5');
INSERT INTO users (id, username, password) VALUES(6, 'royding6','cs6');

commit;

