https://bezkoder.com/spring-boot-pagination-sorting-example/

DROP TABLE tutorials;

CREATE TABLE tutorials (
     id 			INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     title  		VARCHAR2(50) NOT NULL,
	 description    VARCHAR2(100),
	 published      SMALLINT
);

insert into tutorials (title,description, published) values ('t01', 'desc 01',  1);
insert into tutorials (title,description, published) values ('t02', 'desc 02',  0);
insert into tutorials (title,description, published) values ('t03', 'desc 03',  1);
insert into tutorials (title,description, published) values ('t04', 'desc 04',  0);
insert into tutorials (title,description, published) values ('t05', 'desc 05',  1);
insert into tutorials (title,description, published) values ('t06', 'desc 06',  0);
insert into tutorials (title,description, published) values ('t07', 'desc 07',  1);
insert into tutorials (title,description, published) values ('t08', 'desc 08',  0);
insert into tutorials (title,description, published) values ('t09', 'desc 09',  1);
insert into tutorials (title,description, published) values ('t10', 'desc 10',  0);
insert into tutorials (title,description, published) values ('t11', 'desc 11',  1);
insert into tutorials (title,description, published) values ('t12', 'desc 12',  1);

commit;