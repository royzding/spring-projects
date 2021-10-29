
DROP TABLE kafka_consumer_message;

CREATE TABLE kafka_consumer_message (
  	 id 				INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  	 name	 			VARCHAR2(25),
     topic_num			VARCHAR2(25),
     group_num			VARCHAR2(25),
     message			VARCHAR2(25),
     partition_num		Integer,
     offset_num			Integer,
     modified_by		VARCHAR2(50),
     created_date   	TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP
);



