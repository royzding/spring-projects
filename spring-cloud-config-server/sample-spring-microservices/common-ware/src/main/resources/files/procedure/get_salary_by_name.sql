

CREATE or replace PROCEDURE GET_SALARY_BY_NAME(in_name IN VARCHAR2, out_salary OUT NUMBER)

IS

BEGIN

    SELECT max(salary) into out_salary from manager WHERE name = in_name;
    
END;



DECLARE

	in_name varchar2(50) := 'roy';
	out_salary NUMBER;

BEGIN

	 GET_SALARY_BY_NAME(in_name, out_salary);
	
	  dbms_output.put_line( 'salary := ' || out_salary );
END;
/
	
	