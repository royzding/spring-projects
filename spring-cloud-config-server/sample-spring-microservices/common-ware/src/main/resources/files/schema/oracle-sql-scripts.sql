
---find out the index. when drop unique constraint sometime there is still an index with this unique key.
select * from user_indexes where index_name = 'CONSTRAINT_NMAE';
drop index  CONSTRAINT_NMAE;


---update with case end
update employee set name = CASE
WHEN name is not null THEN 'name'
WHEN name is null     THEN 'name null'
END,
salary = CASE
WHEN salary is not null THEN 10000
WHEN salary is null     THEN 20000
END
;

---case insensitive unique constraint on column NAME
create unique index un_name on employee ( upper(name) );

---offset and fetch 
select * from employee order by salary offset 2 rows fetch next 4 rows only;


---with clause
with tmp1 as 
(select max(asx2) as asx1 from
(
select avg(salary) as asx2 from professor group by dep_id
)
),
tmp2 as
(
select id, name from department
),
tmp3 as
(
select avg(salary) as asx3, dep_id from professor group by dep_id
)
select tmp2.name, 2*tmp3.asx3 from tmp1, tmp2, tmp3
where tmp3.asx3 = tmp1.asx1 and tmp2.id = tmp3.dep_id
;


---SQL: Concatenation Operator  ||
SELECT id, first_name, last_name, first_name || last_name, salary, first_name || salary FROM myTable

---SQL | MINUS Operator
SELECT column1 , column2 , ... columnN
FROM table_name
WHERE condition
MINUS
SELECT column1 , column2 , ... columnN
FROM table_name
WHERE condition;


---SQL general functions | NVL, NVL2, DECODE, COALESCE, NULLIF, LNNVL and NANVL

SELECT  salary, NVL(commission_pct, 0),
    (salary*12) + (salary*12*NVL(commission_pct, 0))
      annual_salary FROM employees;
	  
expr1 is the source value or expression that may contain null
expr2 is the value returned if expr1 is not null
expr3 is the value returned if expr1 is null

SELECT last_name, salary, commission_pct,
 NVL2(commission_pct, ’SAL+COMM’, ’SAL’)
 income FROM employees;

DECODE(col|expression, search1, result1 
 [, search2, result2,...,][, default]);
 
 
 COALESCE (expr_1, expr_2, ... expr_n)
 SELECT last_name, 
    COALESCE(commission_pct, salary, 10) comm
    FROM employees ORDER BY commission_pct;
	
---SQL | Conditional Expressions	

 CASE expr WHEN comparison_expr1 THEN return_expr1
         [WHEN comparison_expr2 THEN return_expr2
          .
          .
          .
          WHEN comparison_exprn THEN return_exprn
          ELSE else_expr]
END

SELECT first_name, dpartment_id, salary,
       CASE dpartment_id WHEN 50 THEN 1.5*salary
                         WHEN 12 THEN 2.0*salary
       ELSE salary
       END "REVISED SALARY"
FROM Employee;


SELECT first_name, dpartment_id, salary,
       DECODE(dpartment_id, 50, 1.5*salary,
                             12, 2.0*salary,
              salary)
       "REVISED SALARY"
FROM Employee;


SELECT COALESCE(last_name, '- NA -')
from Employee;


---character functions
Input1: SELECT LOWER('GEEKSFORGEEKS') FROM DUAL;
Output1: geeksforgeeks

Input1: SELECT UPPER('geeksforgeeks') FROM DUAL;
Output1: GEEKSFORGEEKS

Input1: SELECT INITCAP('geeksforgeeks is a computer science portal for geeks') FROM DUAL;
Output1: Geeksforgeeks Is A Computer Science Portal For Geeks 

Input1: SELECT CONCAT('computer' ,'science') FROM DUAL;
Output1: computerscience

Input1: SELECT LENGTH('Learning Is Fun') FROM DUAL;
Output1: 15 

Input1: SELECT SUBSTR('Database Management System', 9) FROM DUAL;
Output1: Management System

Input: SELECT INSTR('Google apps are great applications','app',1,2) FROM DUAL;
Output: 23 

LPAD Input1: SELECT LPAD('100',5,'*') FROM DUAL;
LPAD Output1: **100

RPAD Input1: SELECT RPAD('earn', 19, 'money') FROM DUAL;
RPAD Output1: earnmoneymoneymoney

Input1: SELECT TRIM('G' FROM 'GEEKS') FROM DUAL;
Output1: EEKS

Input2: SELECT TRIM('        geeksforgeeks   ') FROM DUAL; 
Output2:geeksforgeeks

Input1: SELECT REPLACE('DATA MANAGEMENT', 'DATA','DATABASE') FROM DUAL;
Output1: DATABASE MANAGEMENT


---LISTAGG

select dep_id, listagg(name, ' , ') within group ( order by dep_id)  as subjects from professor group by dep_id;

---Aggregate functions in SQL

1) Count()
2) Sum()
3) Avg()
4) Min()
5) Max()

6) LENGTH()
7) ROUND(column_name, decimals)


