

ALTER TABLE table_name ADD column_name data_type constraint;

ALTER TABLE table_name 
ADD (
    column_name_1 data_type constraint,
    column_name_2 data_type constraint,
    ...
);

alter table table_name rename column old_column_name TO new_column_name;

ALTER TABLE table_name DROP COLUMN column_name;

ALTER TABLE table_name DROP (column_name1, column_name2,…);

---- ALTER TABLE table_name MODIFY column_name action;
ALTER TABLE table_name MODIFY created_datetime DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE table_name MODIFY id PRIMARY KEY;

alter table
   table_name
modify
   column_name  datatype;
   
alter table
   table_name
modify
   (
   column1_name  column1_datatype,
   column2_name  column2_datatype,
   column3_name  column3_datatype,
   column4_name  column4_datatype
   );   
   
ALTER TABLE
   customer
MODIFY
   (
   cust_name varchar2(100) not null,
   cust_hair_color  varchar2(20)
   )
;   


alter table
   cust_table
add constraint
   fk_cust_name FOREIGN KEY (person_name)
references
   person_table (person_name)
initially deferred deferrable;

alter table
   table_name
ENABLE constraint
   constraint_name;

alter table
   table_name
DISABLE constraint
   constraint_name;
   
alter table
   table_name
add constraint
   check_constraint_name
   CHECK
   (check_column_name IN
      (
       'check_constraint1_value',
       'check_constraint2_value',
       'check_constraint3_value',
       'check_constraint4_value'
      )
   ) DISABLE|ENABLE;
   
   
alter table
   cust_table
add constraint
   fk_cust_name FOREIGN KEY (person_name, person_gender)
references
   person_table (person_name, person_gender)
initially deferred deferrable;

ALTER TABLE
   cust_table
drop constraint
   fk_cust_table_ref;
   
alter table
   cust_table
add constraint
   check_cust_types
   CHECK
   (cust_type IN
      (
       'yuppie',
       'dink',
       'guppie'
      )
   );
      
CONSTRAINT check_supplier_id  CHECK (supplier_id BETWEEN 100 and 9999);
CONSTRAINT check_supplier_name  CHECK (supplier_name = upper(supplier_name));  


    