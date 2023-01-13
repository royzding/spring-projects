http://localhost:8080/graphiql 

Let's now run a query to get the data.

query employeeDetails {
  employees {
    id
    name
  }
}

The output will be:

{
  "data": {
    "employees": [
      {
        "id": "1",
        "name": "Andi"
      }
    ]
  }
}

Now, run the following query.

query employeeDetails {
  employees {
    id
    name
  }
}

This will create a row in the Vehicle table. The result should be:

{
  "data": {
    "createVehicle": {
      "id": "1"
    }
  }
}


query employeeDetails {
  employee(id:0) {
    id
    name
  }
}

query bookDetails {
  bookById (id: "book-2") {
    id
    
  }
}

query getGreetings {
  greeting
}

query getGreetings {
  greetingsMono
}

query getGreetings {
  greetingsFlux
}