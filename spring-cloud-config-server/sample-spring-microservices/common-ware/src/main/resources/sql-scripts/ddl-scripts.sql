
sqlplus SYS AS SYSDBA




//Log in to SQL *Plus:
sqlplus '/ as sysdba'
//Create a new user with an administrator password:

create user c##ding identified by peardoor807x CONTAINER=all

//user_name is the name of the user you are creating and admin_password is the password that you want to assign to the user.
//Assign the sysdba privilege to the new Oracle user:

 
 grant SYSDBA to c##ding container=all;
 grant ALTER DATABASE to c##ding container=all; 
 grant ALTER SYSTEM to c##ding container=all;
 
 grant CREATE ANY INDEX to c##ding container=all;
 grant ALTER ANY INDEX to c##ding container=all;
 grant DROP ANY INDEX to c##ding container=all;
 grant CREATE ANY PROCEDURE to c##ding container=all;
 grant ALTER ANY PROCEDURE to c##ding container=all;
 grant DROP ANY PROCEDURE to c##ding container=all;
 grant EXECUTE ANY PROCEDURE to c##ding container=all;
 grant CREATE ROLE to c##ding container=all;
 grant ALTER ANY ROLE to c##ding container=all;
 grant DROP ANY ROLE to c##ding container=all;
 grant GRANT ANY ROLE c##ding container=all;
 grant CREATE ANY SEQUENCE to c##ding container=all;
 grant ALTER ANY SEQUENCE to c##ding container=all;
 grant DROP ANY SEQUENCE to c##ding container=all;
 grant SELECT ANY SEQUENCE to c##ding container=all;
 grant CREATE SESSION to c##ding container=all;
 grant ALTER SESSION to c##ding container=all;
 grant CREATE ANY SYNONYM to c##ding container=all;
 grant CREATE PUBLIC SYNONYM to c##ding container=all;
 grant DROP ANY SYNONYM to c##ding container=all;
 grant DROP PUBLIC SYNONYM to c##ding container=all;
 grant CREATE ANY TABLE to c##ding container=all;
 grant ALTER ANY TABLE to c##ding container=all;
 grant DELETE ANY TABLE to c##ding container=all;
 grant DROP ANY TABLE to c##ding container=all;
 grant INSERT ANY TABLE to c##ding container=all;
 grant LOCK ANY TABLE to c##ding container=all;
 grant READ ANY TABLE to c##ding container=all;
 grant SELECT ANY TABLE to c##ding container=all;
 grant FLASHBACK ANY TABLE to c##ding container=all;
 grant UPDATE ANY TABLE to c##ding container=all;
 grant REDEFINE ANY TABLE to c##ding container=all;
 
 grant CREATE ANY TRIGGER to c##ding container=all;
 grant ALTER ANY TRIGGER to c##ding container=all;
 grant DROP ANY TRIGGER to c##ding container=all;
 
 grant CREATE ANY TYPE to c##ding container=all;
 grant ALTER ANY TYPE to c##ding container=all;
 grant EXECUTE ANY TYPE to c##ding container=all;
 grant DROP ANY TYPE to c##ding container=all;


 grant CREATE ANY USER to c##ding container=all;
 grant ALTER ANY USER to c##ding container=all;
 grant DROP ANY USER to c##ding container=all;

 grant CREATE ANY VIEW to c##ding container=all;
 grant ALTER ANY VIEW to c##ding container=all;
 grant DROP ANY VIEW to c##ding container=all;
 
 alter user c##ding DEFAULT TABLESPACE USERS quota unlimited on USERS;


 