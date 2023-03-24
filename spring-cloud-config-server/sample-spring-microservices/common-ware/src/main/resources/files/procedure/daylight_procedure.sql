CREATE OR REPLACE Function f_convert_daylight_time  ( delta IN NUMBER, a_time IN varchar2 ) RETURN VARCHAR2

IS
	T_TIME_STR		VARCHAR2(100);
	A_TIME_HOUR_STR	VARCHAR2(50);
	A_TIME_MIN_STR	VARCHAR2(50);
	A_TIME_HOUR  	INTEGER;
	
    C_TIME          VARCHAR2(500) := '';

BEGIN


	IF (a_time is not null) AND (TRIM(a_time) is not null) THEN


	   FOR S_TIME IN (    
	      SELECT REGEXP_SUBSTR (a_time, '[^;]+', 1, LEVEL) AS TIME_STR  
	      FROM DUAL
	      CONNECT BY REGEXP_SUBSTR (a_time, '[^;]+', 1, LEVEL) IS NOT NULL)
	   LOOP
	   
	   	  T_TIME_STR := TRIM(S_TIME.TIME_STR);
	   
	      A_TIME_HOUR_STR := SUBSTR( T_TIME_STR, 1, INSTR(T_TIME_STR, ':', 1, 1)-1 );
	      A_TIME_MIN_STR  := SUBSTR( T_TIME_STR, INSTR(T_TIME_STR, ':', 1, 1) );
	
	      A_TIME_HOUR := TO_NUMBER( A_TIME_HOUR_STR, '99') + delta;
	      
	      IF A_TIME_HOUR = 24 THEN
	      
	      	A_TIME_HOUR := 0;
	      	
	      ELSIF A_TIME_HOUR = -1 THEN
	      
	       	A_TIME_HOUR := 23;       
	      	
	      END IF;
	
		  C_TIME := C_TIME || ';' || TO_CHAR(A_TIME_HOUR, 'fm00') || A_TIME_MIN_STR;
	   
	   END LOOP;
	   
	   	  C_TIME := SUBSTR( C_TIME, 2, LENGTH(C_TIME)-1);
	  
	  ELSE
	  
	   	  C_TIME := a_time;
	  	  
	  END IF;   

   RETURN C_TIME;

EXCEPTION

WHEN OTHERS THEN

   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
   
END;
/

CREATE OR REPLACE PROCEDURE p_insert_two_param(delta IN NUMBER, description IN VARCHAR2 )

IS
    SWITCH_STR		VARCHAR2(30);
    
	CURSOR c_dstu is SELECT ID, A_TIME FROM daylight_saving_time_update;
	CURSOR c_dstu_2 is SELECT ID, A_TIME_2 FROM daylight_saving_time_update_2;

BEGIN

   SAVEPOINT TR_BEGIN;
   
   SWITCH_STR := UPPER(TRIM(description));
   
   IF SWITCH_STR='ALL' OR SWITCH_STR='DSTU1' THEN

	   FOR dls_time in c_dstu
	   LOOP
	
			UPDATE daylight_saving_time_update 
		      	SET A_TIME = f_convert_daylight_time(delta, dls_time.A_TIME),
		      	MODIFIED_DATE =  CURRENT_TIMESTAMP
		    WHERE ID = dls_time.ID;  
	
	   END LOOP;
	
   END IF;
	
   IF SWITCH_STR='ALL' OR SWITCH_STR='DSTU2' THEN
	
	   FOR dls_time_2 in c_dstu_2
	   LOOP
	
			UPDATE daylight_saving_time_update_2 
		      	SET A_TIME_2 = f_convert_daylight_time(delta, dls_time_2.A_TIME_2),
		      	MODIFIED_DATE =  CURRENT_TIMESTAMP
		    WHERE ID = dls_time_2.ID;  
	
	   END LOOP;
	   
   END IF;
   
   COMMIT;

EXCEPTION

WHEN OTHERS THEN

   ROLLBACK TO TR_BEGIN;
   
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);

END;
/


CREATE OR REPLACE PROCEDURE p_insert_two_param_plus_one(description IN VARCHAR2)
IS
BEGIN

	p_insert_two_param(1, description);

END;
/

CREATE OR REPLACE PROCEDURE p_insert_two_param_minus_one(description IN VARCHAR2)
IS
BEGIN

	p_insert_two_param(-1, description);

END;
/




---- ALL, DSTU1, or DSTU1
---- call p_insert_two_param_plus_one('ALL');
---- call p_insert_two_param_minus_one('DSTU1');



---- anonymous PL/SQL block

DECLARE
  l_message VARCHAR2( 255 ) := 'Hello World!';
BEGIN
  DBMS_OUTPUT.PUT_LINE( l_message );
END;
/

--------------------------------

DECLARE
      v_result NUMBER;
BEGIN
   v_result := 1 / 0;
   EXCEPTION
      WHEN ZERO_DIVIDE THEN
         DBMS_OUTPUT.PUT_LINE( SQLERRM );
END;
/

---------------------------------------------

DECLARE

	description 	VARCHAR2(30) := 'ALL';
	delta			NUMBER		 := 1;
    SWITCH_STR		VARCHAR2(30);
    
	CURSOR c_dstu is SELECT ID, A_TIME FROM daylight_saving_time_update;
	CURSOR c_dstu_2 is SELECT ID, A_TIME_2 FROM daylight_saving_time_update_2;

BEGIN

   SAVEPOINT TR_BEGIN;
   --EXECUTE IMMEDIATE 'ALTER TRIGGER TRIGGER_NAME DISABLE';
   
   SWITCH_STR := UPPER(TRIM(description));
   
   IF SWITCH_STR='ALL' OR SWITCH_STR='DSTU1' THEN

	   FOR dls_time in c_dstu
	   LOOP
	
			UPDATE daylight_saving_time_update 
		      	SET A_TIME = f_convert_daylight_time(delta, dls_time.A_TIME),
		      	MODIFIED_DATE =  CURRENT_TIMESTAMP
		    WHERE ID = dls_time.ID;  
	
	   END LOOP;
	
   END IF;
	
   IF SWITCH_STR='ALL' OR SWITCH_STR='DSTU2' THEN
	
	   FOR dls_time_2 in c_dstu_2
	   LOOP
	
			UPDATE daylight_saving_time_update_2 
		      	SET A_TIME_2 = f_convert_daylight_time(delta, dls_time_2.A_TIME_2),
		      	MODIFIED_DATE =  CURRENT_TIMESTAMP
		    WHERE ID = dls_time_2.ID;  
	
	   END LOOP;
	   
   END IF;
   
   COMMIT;
   
   --EXECUTE IMMEDIATE 'ALTER TRIGGER TRIGGER_NAME ENABLE';

EXCEPTION

WHEN OTHERS THEN

   --EXECUTE IMMEDIATE 'ALTER TRIGGER TRIGGER_NAME ENABLE';

   ROLLBACK TO TR_BEGIN;
   
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);

END;
/


