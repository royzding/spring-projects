//https://dzone.com/articles/introduction-to-spring-data-jpa-part-8-many-to-man

DROP TABLE t_user_role;
DROP TABLE t_user;
DROP TABLE t_role;

CREATE TABLE t_user (
     id 			INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     first_name  	VARCHAR2(50) NOT NULL,
     last_name  	VARCHAR2(50) NOT NULL,
     mobile  		VARCHAR2(50) NOT NULL,
     email  		VARCHAR2(50) NOT NULL,
     CONSTRAINT un_t_user_last_first UNIQUE(first_name, last_name)
);

CREATE TABLE t_role (
     id 			INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     name  			VARCHAR2(50) NOT NULL UNIQUE,
     description	VARCHAR2(50)
);

CREATE TABLE t_user_role (
     id 			INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     user_id 		INTEGER,
     role_id  		INTEGER,
	 user_role_type VARCHAR2(10),
	 FOREIGN KEY (user_id) REFERENCES t_user(id),
	 FOREIGN KEY (role_id) REFERENCES t_role(id)
);

commit;

////////////////////////

    {
            "firstName": "f1",
            "lastName":"l1",
            "mobile": "9876435234",
            "email":"fl1@mail.com",
            "roles": [
                {
                    "name": "MANAGER",
                    "description": "Mid Level Managers"
                },
                {
                    "name" : "ACCOUNTS",
                    "description": "ACCOUNTS USERS"
                }
            ]
    }


    {
            "firstName": "f2",
            "lastName":"l2",
            "mobile": "9876435234",
            "email":"fl2@mail.com",
            "roles": [
                {
                	"id": 1,
                    "name": "MANAGER",
                    "description": "Mid Level Managers"
                },
                {
                	"id": 2,
                    "name" : "ACCOUNTS",
                    "description": "ACCOUNTS USERS"
                }
            ]
    }

    {
            "firstName": "f3",
            "lastName":"l3",
            "mobile": "9876435234",
            "email":"fl3@mail.com",
            "roles": [
                {
                    "name": "MANAGER",
                    "description": "Mid Level Managers"
                },
                {
                    "name" : "ACCOUNTS",
                    "description": "ACCOUNTS USERS"
                }
            ]
    }


	
{
    "name": "ADMIN",
    "description": "Administrator",
    "users": [
        {
            "firstName": "hello",
            "lastName":"world",
            "mobile": "9876435234",
            "email":"hello@mail.com"
        },
        {
            "firstName": "Hello Good Morning",
            "lastName":"world",
            "mobile": "9876435234",
            "email":"world@mail.com"
        }
    ]
}	