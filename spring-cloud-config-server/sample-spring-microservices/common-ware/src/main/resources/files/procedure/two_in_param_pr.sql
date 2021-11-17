

CREATE OR REPLACE PROCEDURE two_in_param_pr (in_manager_id IN NUMBER, in_salary_inc IN NUMBER)

IS

  r_manager manager%ROWTYPE;
  f_salary NUMBER;
  
BEGIN

   dbms_output.put_line( 'this is a test' );

  -- get manager based on id  
  SELECT * INTO r_manager  FROM manager  WHERE id = in_manager_id;
  
  f_salary := r_manager.salary + in_salary_inc;
  
  insert into manager_bk (id, name, salary) 
  	values (r_manager.id,r_manager.name, f_salary);

  -- print out contact's information
  dbms_output.put_line( r_manager.id || ' ' ||  r_manager.name || ' ' || r_manager.salary ||' ' );

EXCEPTION

   WHEN OTHERS THEN
      dbms_output.put_line( SQLERRM );
      
END;

