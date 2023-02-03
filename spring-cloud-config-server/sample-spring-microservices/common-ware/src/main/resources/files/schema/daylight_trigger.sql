DROP TABLE daylight_saving_update;

CREATE TABLE daylight_saving_update 
(	 
	 delta			NUMBER,
     description	VARCHAR2(50),
     modified_date  TIMESTAMP(0)

);

DROP TABLE application_properties;

CREATE TABLE application_properties 
(	 
	 ID				INTEGER PRIMARY KEY,
     KEY			VARCHAR2(100),
     A_TIME			VARCHAR2(100),
     CREATED_DATE   TIMESTAMP(0)  DEFAULT CURRENT_TIMESTAMP,
     MODIFIED_DATE  TIMESTAMP(0)

);

INSERT INTO application_properties (ID, KEY, VALUE) VALUES (100, 'DATLIGHT_SAVING_TIME', 'WINTER');

DROP TABLE daylight_saving_time_update;

CREATE TABLE daylight_saving_time_update 
(	 
	 ID				INTEGER PRIMARY KEY,
     NAME			VARCHAR2(50),
     A_TIME 		VARCHAR2(100),
     CREATED_DATE   TIMESTAMP(0)  DEFAULT CURRENT_TIMESTAMP,
     MODIFIED_DATE  TIMESTAMP(0)
);

INSERT INTO daylight_saving_time_update (ID, NAME, A_TIME) VALUES (100, 'time00', '01:15;02:20;03:25');
INSERT INTO daylight_saving_time_update (ID, NAME, A_TIME) VALUES (101, 'time01', '05:20;06:15;07:20;08:25');
INSERT INTO daylight_saving_time_update (ID, NAME, A_TIME) VALUES (102, 'time02', '10:25;11:15;12:20;13:25');
INSERT INTO daylight_saving_time_update (ID, NAME, A_TIME) VALUES (103, 'time03', '15:30;16:15;17:20;18:25');
INSERT INTO daylight_saving_time_update (ID, NAME, A_TIME) VALUES (104, 'time04', '20:35;21:15;22:20;23:25');
INSERT INTO daylight_saving_time_update (ID, NAME, A_TIME) VALUES (105, 'time05', '23:00');
INSERT INTO daylight_saving_time_update (ID, NAME, A_TIME) VALUES (106, 'time06', '23:40');
INSERT INTO daylight_saving_time_update (ID, NAME, A_TIME) VALUES (107, 'time07', '');
INSERT INTO daylight_saving_time_update (ID, NAME, A_TIME) VALUES (108, 'time08', '   ');
INSERT INTO daylight_saving_time_update (ID, NAME, A_TIME) VALUES (109, 'time09', null);

COMMIT;

DROP TABLE daylight_saving_time_update_2;

CREATE TABLE daylight_saving_time_update_2 
(	 
	 ID				INTEGER PRIMARY KEY,
     NAME			VARCHAR2(50),
     A_TIME_2 		VARCHAR2(100),
     CREATED_DATE   TIMESTAMP(0)  DEFAULT CURRENT_TIMESTAMP,
     MODIFIED_DATE  TIMESTAMP(0)
);

INSERT INTO daylight_saving_time_update_2 (ID, NAME, A_TIME_2) VALUES (100, 'time00', '01:15;02:20;03:25');
INSERT INTO daylight_saving_time_update_2 (ID, NAME, A_TIME_2) VALUES (101, 'time01', '05:20;06:15;07:20;08:25');
INSERT INTO daylight_saving_time_update_2 (ID, NAME, A_TIME_2) VALUES (102, 'time02', '10:25;11:15;12:20;13:25');
INSERT INTO daylight_saving_time_update_2 (ID, NAME, A_TIME_2) VALUES (103, 'time03', '15:30;16:15;17:20;18:25');
INSERT INTO daylight_saving_time_update_2 (ID, NAME, A_TIME_2) VALUES (104, 'time04', '20:35;21:15;22:20;23:25');
INSERT INTO daylight_saving_time_update_2 (ID, NAME, A_TIME_2) VALUES (105, 'time05', '23:00');
INSERT INTO daylight_saving_time_update_2 (ID, NAME, A_TIME_2) VALUES (106, 'time06', '23:40');
INSERT INTO daylight_saving_time_update_2 (ID, NAME, A_TIME_2) VALUES (107, 'time07', '');
INSERT INTO daylight_saving_time_update_2 (ID, NAME, A_TIME_2) VALUES (108, 'time08', '   ');
INSERT INTO daylight_saving_time_update_2 (ID, NAME, A_TIME_2) VALUES (109, 'time09', null);

COMMIT;


CREATE OR REPLACE PROCEDURE p_insert_two_param(delta IN NUMBER, description IN VARCHAR2 )

IS
	CURSOR c_dstu is SELECT ID, A_TIME FROM daylight_saving_time_update;

	CURSOR c_dstu_2 is SELECT ID, A_TIME_2 FROM daylight_saving_time_update_2;

BEGIN

   insert into daylight_saving_update (delta, description, modified_date) values (delta, description, CURRENT_TIMESTAMP);

   FOR dls_time in c_dstu
   LOOP

	  IF (dls_time.A_TIME is not null) AND (TRIM(dls_time.A_TIME) is not null) THEN
	  
	      UPDATE daylight_saving_time_update 
	      	SET A_TIME = f_convert_daylight_time(delta, dls_time.A_TIME),
	      	MODIFIED_DATE =  CURRENT_TIMESTAMP
	      WHERE ID = dls_time.ID;  
	      
	  END IF;   

   END LOOP;

   FOR dls_time_2 in c_dstu_2
   LOOP

	  IF (dls_time_2.A_TIME_2 is not null) AND (TRIM(dls_time_2.A_TIME_2) is not null) THEN
	  
	      UPDATE daylight_saving_time_update_2 
	      	SET A_TIME_2 = f_convert_daylight_time(delta, dls_time_2.A_TIME_2),
	      	MODIFIED_DATE =  CURRENT_TIMESTAMP
	      WHERE ID = dls_time_2.ID;  
	      
	  END IF;   

   END LOOP;

EXCEPTION

WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);

END;
/


CREATE OR REPLACE Function f_convert_daylight_time  ( delta IN NUMBER, a_time IN varchar2 ) RETURN VARCHAR2

IS
	A_TIME_HOUR_STR	VARCHAR2(50);
	A_TIME_MIN_STR	VARCHAR2(50);
	A_TIME_HOUR  	INTEGER;
	
    C_TIME          VARCHAR2(500) := '';

BEGIN

   FOR S_TIME IN (    
      SELECT REGEXP_SUBSTR (a_time, '[^;]+', 1, LEVEL) AS TIME_STR  
      FROM DUAL
      CONNECT BY REGEXP_SUBSTR (a_time, '[^;]+', 1, LEVEL) IS NOT NULL)
   LOOP
   
      A_TIME_HOUR_STR := SUBSTR( TRIM(S_TIME.TIME_STR), 1, 2);
      A_TIME_MIN_STR  := SUBSTR( TRIM(S_TIME.TIME_STR), 4, 2);

      A_TIME_HOUR := TO_NUMBER( A_TIME_HOUR_STR, '99') + delta;
      
      IF A_TIME_HOUR = 24 THEN
      
      	A_TIME_HOUR := 0;
      	
      ELSIF A_TIME_HOUR = -1 THEN
      
       	A_TIME_HOUR := 23;       
      	
      END IF;

	  C_TIME := C_TIME || ';' || TO_CHAR(A_TIME_HOUR, 'fm00') || ':' || A_TIME_MIN_STR;
   
   END LOOP;

   RETURN SUBSTR( C_TIME, 2, LENGTH(C_TIME)-1);

EXCEPTION

WHEN OTHERS THEN

   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
   
END;
/



---- call P_INSERT_TWO_PARAM (1, 'test');


INSERT INTO daylight_saving_update VALUES (23,SYSDATE,2);

CREATE OR REPLACE PROCEDURE P_DAYLIGHT_SAVING_TRIG_JOB
IS
	ld_date date;
BEGIN
	SELECT B INTO ld_date FROM daylight_saving_update;
	
	IF to_char(ld_date,'DD-MM-YYYY') = to_char(sysdate,'DD-MM-YYYY') THEN
	
	UPDATE daylight_saving_update SET C = A + 1;
	INSERT INTO daylight_saving_update VALUES (23,SYSDATE,2);
	
	END IF;
END;


DECLARE
	ln_job_no number;
BEGIN

	dbms_job.submit(ln_job_no,'P_DAYLIGHT_SAVING_TRIG_JOB;',SYSDATE,'SYSDATE+1',NULL);
	
	dbms_job.run(ln_job_no);
	
END;



-- job package using

-- create a table
CREATE TABLE TEMP(ID NUMBER, NAME VARCHAR2(25));

-- write a pl/sql procedure to insert a new row in the temp tble each time when the job is called.

CREATE OR REPLACE PROCEDURE TEMPPROCEDURE AS
BEGIN
	insert into temp values(1,'khaleel');
COMMIT;
END;
/
-- prepare and start the job.

CREATE OR REPLACE PROCEDURE jobpro1
AS
	v_jobno NUMBER ;
	v_what VARCHAR2(2000) := 'TEMPPROCEDURE;';
	v_interval VARCHAR2(200) := 'SYSDATE+1/(24*60)'; -- to execute after every one munite once
BEGIN

	DBMS_JOB.SUBMIT(job => v_jobno,
					what => v_what,
					next_date => SYSDATE,
					interval =>v_interval);
	commit;
	
	dbms_output.put_line('job Number : '||v_jobno);

END;



-- droping a job is
exec dbms_job.remove(42); -- 42 is job number
-- stopping a job
exec dbms_job.broken(42,true) -- to stop the running
-- to restart the job
exec dbms_job.broken(42,false) --
-- to run the job without considering its next trip
exec dbms_job.run(42)

-- end of the job submitting procedure
