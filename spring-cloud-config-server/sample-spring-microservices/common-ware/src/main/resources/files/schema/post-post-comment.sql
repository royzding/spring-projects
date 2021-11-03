//https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/

DROP SEQUENCE REVIEW_SEQ;
DROP TABLE REVIEW;
DROP SEQUENCE ARTICLE_SEQ;
DROP TABLE ARTICLE;

CREATE TABLE ARTICLE 
(	id 		INT NOT NULL, 
	title 	VARCHAR2(50 CHAR),
	PRIMARY KEY (id)
);
  

CREATE SEQUENCE  ARTICLE_SEQ MINVALUE 1 MAXVALUE 9999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;


INSERT INTO ARTICLE (id, title) VALUES(1, 'article 1');
INSERT INTO ARTICLE (id, title) VALUES(2, 'article 2');
INSERT INTO ARTICLE (id, title) VALUES(3, 'article 3');
INSERT INTO ARTICLE (id, title) VALUES(4, 'article 4');
INSERT INTO ARTICLE (id, title) VALUES(5, 'article 5');
INSERT INTO ARTICLE (id, title) VALUES(6, 'article 6');

CREATE TABLE REVIEW 
(	id 				INT NOT NULL, 
	review 			VARCHAR2(250 CHAR),
	article_id		INT,
	PRIMARY KEY (id),
	FOREIGN KEY (article_id) REFERENCES article(id)
);
  

CREATE SEQUENCE  REVIEW_SEQ MINVALUE 1 MAXVALUE 9999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;


INSERT INTO REVIEW (id, review, article_id) VALUES(1, '1 review article 1', 1);
INSERT INTO REVIEW (id, review, article_id) VALUES(2, '1 review article 2', 2);
INSERT INTO REVIEW (id, review, article_id) VALUES(3, '2 review article 2', 2);
INSERT INTO REVIEW (id, review, article_id) VALUES(4, '1 review article 3', 3);
INSERT INTO REVIEW (id, review, article_id) VALUES(5, '2 review article 3', 3);
INSERT INTO REVIEW (id, review, article_id) VALUES(6, '3 review article 3', 3);

commit;