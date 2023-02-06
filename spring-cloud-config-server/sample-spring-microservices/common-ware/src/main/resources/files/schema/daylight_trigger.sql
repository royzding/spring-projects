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

INSERT INTO application_properties (ID, KEY, A_TIME) VALUES (100, 'DATLIGHT_SAVING_TIME', 'WINTER');

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

---- ALL, DSTU1, or DSTU1
---- call p_insert_two_param_plus_one('ALL');
---- call p_insert_two_param_minus_one('DSTU1');
