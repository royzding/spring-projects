
DROP TABLE audit_logging_info;

CREATE TABLE audit_logging_info (
  	 id 					INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  	 table_name 			VARCHAR2(50),
  	 action 				VARCHAR2(20),
  	 column_names 			VARCHAR2(500),
  	 old_values 			VARCHAR2(500),
  	 new_values 			VARCHAR2(500),
  	 exceptions				VARCHAR2(100),
     created_date   		TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP
);
