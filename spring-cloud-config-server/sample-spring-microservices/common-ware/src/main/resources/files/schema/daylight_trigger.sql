

CREATE TABLE daylight_saving_update 
(	 
	 delta			NUMBER,
     description	VARCHAR2(50),
     modified_date  TIMESTAMP(0)

);

create or replace PROCEDURE p_insert_two_param(delta IN NUMBER, description IN VARCHAR2 )

IS

BEGIN

   insert into daylight_saving_update (delta, description, modified_date) values (delta, description, CURRENT_TIMESTAMP);

EXCEPTION

   WHEN OTHERS THEN
      dbms_output.put_line( SQLERRM );

END;
/



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
