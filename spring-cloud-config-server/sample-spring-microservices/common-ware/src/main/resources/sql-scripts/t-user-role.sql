//https://dzone.com/articles/introduction-to-spring-data-jpa-part-8-many-to-man

DROP TABLE t_user_roles;
DROP TABLE t_user;
DROP TABLE t_role;

CREATE TABLE t_user (
     id 			INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     first_name  	VARCHAR2(50) NOT NULL,
     last_name  	VARCHAR2(50) NOT NULL,
     mobile  		VARCHAR2(50) NOT NULL,
     email  		VARCHAR2(50) NOT NULL
);

CREATE TABLE t_role (
     id 			INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     name  			VARCHAR2(50) NOT NULL,
     description	VARCHAR2(50)
);

CREATE TABLE t_user_roles (
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
            "firstName": "Hello Good Afternoon",
            "lastName":"world",
            "mobile": "9876435234",
            "email":"hi@mail.com",
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