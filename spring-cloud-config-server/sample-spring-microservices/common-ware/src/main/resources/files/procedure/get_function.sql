
CREATE OR REPLACE FUNCTION get_salary_by_id(in_id IN NUMBER) 

RETURN NUMBER

IS

    out_salary NUMBER := 0;
    
BEGIN

   SELECT salary into out_salary from manager WHERE id = in_id;
    
   RETURN out_salary;
   
END;



DECLARE

	in_id NUMBER := 22;
	out_salary NUMBER;

BEGIN

	  out_salary := get_salary_by_id(in_name);
	
	  dbms_output.put_line( 'salary := ' || out_salary );
END;
/
	