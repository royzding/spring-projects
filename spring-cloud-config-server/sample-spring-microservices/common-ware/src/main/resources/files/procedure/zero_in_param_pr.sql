

CREATE OR REPLACE PROCEDURE zero_in_param_pr

IS

BEGIN

  insert into manager_bk (id, name, salary) values (1000,'r_manager.name', 1000.0);

EXCEPTION

   WHEN OTHERS THEN
      dbms_output.put_line( SQLERRM );
      
END;

