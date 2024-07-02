# User API Spec

## Register User

Endpoint : POST /api/users

Request Body :
```json
{
  "username": "aant",
  "password": "rahasia",
  "name" : "A-Ant Sultan"
}
```

Response Body (Success) :

```json
{
  "data" : "OK"
}
```
Response Body (Failed) :

```json
{
  "errors" : "Username must not blank, ???"
}
```

## Login User

Endpoint : POST /api/auth/login

Request Body :
```json
{
  "username": "aant",
  "password": "rahasia"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "token" : "TOKEN",
    "expiredAt" : 31312331312312313 // milliseconds
  }
}
```
Response Body (Failed, 401) :

```json
{
  "errors" : "Username or password are wrong"
}
```

## Get User

Endpoint : GET /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "username" : "aant",
    "name" : "A-Ant Sultan"
  }
}
```
Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

## Update User

Endpoint : PATCH /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "name" : "A-Ant Rahmanya", // put if only want to update name
  "password" : "baru" // put if only want to update password
}
```

Response Body (Success) :

```json
{
  "data" : {
    "username" : "aant",
    "name" : "A-Ant Sultan"
  }
}
```
Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

## Logout User

Endpoint : DELETE /api/auth/logout

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : "OK"
}
```