
//need to execute the following to see put_line in oracle

set serveroutput on;


CREATE OR REPLACE PROCEDURE insert_manager_bk(in_manager_id NUMBER)

IS

  r_manager manager%ROWTYPE;
  
BEGIN

   dbms_output.put_line( 'this is a test' );

  -- get manager based on id  
  SELECT * INTO r_manager  FROM manager  WHERE id = in_manager_id;
  
  insert into manager_bk (id, name, salary) values (r_manager.id,r_manager.name, r_manager.salary);

  -- print out contact's information
  dbms_output.put_line( r_manager.id || ' ' ||  r_manager.name || ' ' || r_manager.salary ||' ' );

EXCEPTION

   WHEN OTHERS THEN
      dbms_output.put_line( SQLERRM );
      
END;


EXECUTE insert_manager_bk(22);