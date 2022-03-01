
DROP TRIGGER USER_TABLE_AUDIT_TRG;
DROP TRIGGER person_AUDIT_TRG;
DROP TRIGGER manager_AUDIT_TRG;

CREATE OR REPLACE TRIGGER USER_TABLE_AUDIT_TRG
AFTER INSERT OR UPDATE OR DELETE ON USER_TABLE
FOR EACH ROW

DECLARE

	TABLE_NAME		VARCHAR(50)		:= 'USER_TABLE';
	ACTION			VARCHAR(20)		:= '';
	column_names	VARCHAR(500)	:= '';
	old_values		VARCHAR(500)	:= '';
	new_values		VARCHAR(500)	:= '';
	
BEGIN
	IF INSERTING THEN
	
		ACTION := 'INSERTING';
		
	END IF;
	
	IF UPDATING THEN
	
		ACTION := 'UPDATING';

	END IF;
	
	IF DELETING THEN
	
		ACTION := 'DELETING';

	END IF;
	
	IF ( (:old.id <> :new.id) OR (:old.id is null and :new.id is not NULL) or (:old.id is not null and :new.id is NULL) ) THEN	
		column_names := column_names || 'ID' || ':';		
		old_values	 := old_values || :old.id  || ':';
		new_values	 := new_values || :new.id || ':';	
	END IF;
	
	IF ( (:old.name <> :new.name) OR (:old.name is null and :new.name is not NULL) or (:old.name is not null and :new.name is NULL) ) THEN
		column_names := column_names || 'NAME' || ':';		
		old_values	 := old_values || :old.name  || ':';
		new_values	 := new_values || :new.name || ':';	
	END IF;
	
	column_names := SUBSTR(column_names, 0, LENGTH(column_names)-1);
	old_values := SUBSTR(old_values, 0, LENGTH(old_values)-1);
	new_values := SUBSTR(new_values, 0, LENGTH(new_values)-1);
	
	INSERT INTO AUDIT_LOGGING_INFO(table_name, action, column_names, old_values, new_values)
									VALUES(table_name, action, column_names, old_values, new_values);
EXCEPTION 

   WHEN no_data_found THEN 
   
	INSERT INTO AUDIT_LOGGING_INFO(table_name, action, column_names, old_values, new_values, exceptions)
						VALUES(table_name, action, column_names, old_values, new_values, 'NO_DATA_FOUND');
      
   WHEN VALUE_ERROR then
   
	INSERT INTO AUDIT_LOGGING_INFO(table_name, action, column_names, old_values, new_values, exceptions)
						VALUES(table_name, action, column_names, old_values, new_values, 'VALUE_ERROR');
   WHEN others THEN 
   
	INSERT INTO AUDIT_LOGGING_INFO(table_name, action, column_names, old_values, new_values, exceptions)
						VALUES(table_name, action, column_names, old_values, new_values, 'OTHERS_ERROR');

END;
/

CREATE OR REPLACE TRIGGER person_AUDIT_TRG
AFTER INSERT OR UPDATE OR DELETE ON person
FOR EACH ROW

DECLARE

	TABLE_NAME		VARCHAR(50)		:= 'PERSON';
	ACTION			VARCHAR(20)		:= '';
	column_names	VARCHAR(500)	:= '';
	old_values		VARCHAR(500)	:= '';
	new_values		VARCHAR(500)	:= '';
	
BEGIN
	IF INSERTING THEN
	
		ACTION := 'INSERTING';
		
	END IF;
	
	IF UPDATING THEN
	
		ACTION := 'UPDATING';

	END IF;
	
	IF DELETING THEN
	
		ACTION := 'DELETING';

	END IF;
	
	IF ( (:old.id <> :new.id) OR (:old.id is null and :new.id is not NULL) or (:old.id is not null and :new.id is NULL) ) THEN	
		column_names := column_names || 'ID' || ':';		
		old_values	 := old_values || :old.id  || ':';
		new_values	 := new_values || :new.id || ':';	
	END IF;
	
	IF ( (:old.name <> :new.name) OR (:old.name is null and :new.name is not NULL) or (:old.name is not null and :new.name is NULL) ) THEN
		column_names := column_names || 'NAME' || ':';		
		old_values	 := old_values || :old.name  || ':';
		new_values	 := new_values || :new.name || ':';	
	END IF;
	
	IF ( (:old.country <> :new.country) OR (:old.country is null and :new.country is not NULL) or (:old.country is not null and :new.country is NULL) ) THEN
		column_names := column_names || 'COUNTRY' || ':';		
		old_values	 := old_values || :old.country  || ':';
		new_values	 := new_values || :new.country || ':';	
	END IF;
	
	column_names := SUBSTR(column_names, 0, LENGTH(column_names)-1);
	old_values := SUBSTR(old_values, 0, LENGTH(old_values)-1);
	new_values := SUBSTR(new_values, 0, LENGTH(new_values)-1);
	
	INSERT INTO AUDIT_LOGGING_INFO(table_name, action, column_names, old_values, new_values)
									VALUES(table_name, action, column_names, old_values, new_values);
EXCEPTION 

   WHEN no_data_found THEN 
   
	INSERT INTO AUDIT_LOGGING_INFO(table_name, action, column_names, old_values, new_values, exceptions)
						VALUES(table_name, action, column_names, old_values, new_values, 'NO_DATA_FOUND');
      
   WHEN VALUE_ERROR then
   
	INSERT INTO AUDIT_LOGGING_INFO(table_name, action, column_names, old_values, new_values, exceptions)
						VALUES(table_name, action, column_names, old_values, new_values, 'VALUE_ERROR');
   WHEN others THEN 
   
	INSERT INTO AUDIT_LOGGING_INFO(table_name, action, column_names, old_values, new_values, exceptions)
						VALUES(table_name, action, column_names, old_values, new_values, 'OTHERS_ERROR');

END;
/

CREATE OR REPLACE TRIGGER manager_AUDIT_TRG
AFTER INSERT OR UPDATE OR DELETE ON manager
FOR EACH ROW

DECLARE

	TABLE_NAME		VARCHAR(50)		:= 'MANAGER';
	ACTION			VARCHAR(20)		:= '';
	column_names	VARCHAR(500)	:= '';
	old_values		VARCHAR(500)	:= '';
	new_values		VARCHAR(500)	:= '';
	
BEGIN
	IF INSERTING THEN
	
		ACTION := 'INSERTING';
		
	END IF;
	
	IF UPDATING THEN
	
		ACTION := 'UPDATING';

	END IF;
	
	IF DELETING THEN
	
		ACTION := 'DELETING';

	END IF;
	
	IF ( (:old.id <> :new.id) OR (:old.id is null and :new.id is not NULL) or (:old.id is not null and :new.id is NULL) ) THEN	
		column_names := column_names || 'ID' || ':';		
		old_values	 := old_values || :old.id  || ':';
		new_values	 := new_values || :new.id || ':';	
	END IF;
	
	IF ( (:old.name <> :new.name) OR (:old.name is null and :new.name is not NULL) or (:old.name is not null and :new.name is NULL) ) THEN
		column_names := column_names || 'NAME' || ':';		
		old_values	 := old_values || :old.name  || ':';
		new_values	 := new_values || :new.name || ':';	
	END IF;
	
	IF ( (:old.salary <> :new.salary) OR (:old.salary is null and :new.salary is not NULL) or (:old.salary is not null and :new.salary is NULL) ) THEN
		column_names := column_names || 'SALARY' || ':';		
		old_values	 := old_values || :old.salary  || ':';
		new_values	 := new_values || :new.salary || ':';	
	END IF;
	
	column_names := SUBSTR(column_names, 0, LENGTH(column_names)-1);
	old_values := SUBSTR(old_values, 0, LENGTH(old_values)-1);
	new_values := SUBSTR(new_values, 0, LENGTH(new_values)-1);
	
	INSERT INTO AUDIT_LOGGING_INFO(table_name, action, column_names, old_values, new_values)
									VALUES(table_name, action, column_names, old_values, new_values);
EXCEPTION 

   WHEN no_data_found THEN 
   
	INSERT INTO AUDIT_LOGGING_INFO(table_name, action, column_names, old_values, new_values, exceptions)
						VALUES(table_name, action, column_names, old_values, new_values, 'NO_DATA_FOUND');
      
   WHEN VALUE_ERROR then
   
	INSERT INTO AUDIT_LOGGING_INFO(table_name, action, column_names, old_values, new_values, exceptions)
						VALUES(table_name, action, column_names, old_values, new_values, 'VALUE_ERROR');
   WHEN others THEN 
   
	INSERT INTO AUDIT_LOGGING_INFO(table_name, action, column_names, old_values, new_values, exceptions)
						VALUES(table_name, action, column_names, old_values, new_values, 'OTHERS_ERROR');
      
END;
/
