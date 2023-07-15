# email-service
Send Emails With Spring Boot

##### 1. New Add User
###### POST
``http://localhost:9000/v1/api/users ``
###### REQUEST BODY
````
{
"name": "Test User",
"email": "testuser@gmail.com",
"password": "123456",
"enabled": true
}
````
###### RESPONSE
````
{
    "timeStamp": "2023-07-15T09:38:33.471773900",
    "statusCode": 201,
    "status": "CREATED",
    "message": "User created.",
    "data": {
        "user": {
            "id": 1,
            "name": "Test User",
            "email": "testuser@gmail.com",
            "password": "123456",
            "enabled": false
        }
    }
}
````
