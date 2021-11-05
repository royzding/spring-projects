
drop table Batch_JOB_EXECUTION_CONTEXT;
drop table Batch_JOB_EXECUTION_PARAMS;
drop table Batch_JOB_EXECUTION_SEQ;
drop table Batch_JOB_SEQ;
drop table Batch_STEP_EXECUTION_CONTEXT;
drop table Batch_STEP_EXECUTION_SEQ;
drop table Batch_STEP_EXECUTION;
drop table Batch_JOB_EXECUTION;
drop table Batch_JOB_INSTANCE;
DROP TABLE account;
DROP TABLE person;

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

DROP TABLE coffee;

CREATE TABLE coffee  (
    coffee_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    brand VARCHAR2(20),
    origin VARCHAR2(20),
    characteristics VARCHAR2(30)
);


INSERT INTO account (name) VALUES ('name1');

commit;

