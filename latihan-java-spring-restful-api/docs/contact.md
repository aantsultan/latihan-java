# Contact API Spec

## Create Contact

Endpoint : POST /api/contacts

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "firstName" : "Elaina",
  "lastName" : "R",
  "email" : "elaina@mail.com",
  "phone" : "0123456"
}
```

Response Body (success) :

```json
{
  "data": {
    "id" : "random string",
    "firstName" : "Elaina",
    "lastName" : "R",
    "email" : "elaina@mail.com",
    "phone" : "0123456"
  }
}
```

Response Body (failed) :

```json
{
  "errors" : "Emial format invalid, phone format invalid, ..."
}
```

## Update Contact

Endpoint : PUT /api/contacts/{idContact}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
    "id" : "random string",
    "firstName" : "Elaina",
    "lastName" : "R",
    "email" : "elaina@mail.com",
    "phone" : "0123456"
}
```

Response Body (success) :

```json
{
  "data": {
    "id" : "random string",
    "firstName" : "Elaina",
    "lastName" : "R",
    "email" : "elaina@mail.com",
    "phone" : "0123456"
  }
}
```

Response Body (failed) :

```json
{
  "errors" : "Emial format invalid, phone format invalid, ..."
}
```

## Get Contact

Endpoint : GET /api/contacts/{idContact}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (success) :

```json
{
  "data": {
    "id" : "random string",
    "firstName" : "Elaina",
    "lastName" : "R",
    "email" : "elaina@mail.com",
    "phone" : "0123456"
  }
}
```

Response Body (failed, 404) :

```json
{
  "errors" : "Contact is not found"
}
```

## Search Contact

Endpoint : GET /api/contacts

Query Param :

- name : String, contact first name or last name , using like query, optional
- phone : String, contact phone, using like query, optional
- email : String, contact email, using like query, optional
- page : integer, start from 0, default 0
- size : integer, default 10

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (success) :

```json
{
  "data": [
    {
      "id" : "random string",
      "firstName" : "Elaina",
      "lastName" : "R",
      "email" : "elaina@mail.com",
      "phone" : "0123456"
    }
  ],
  "paging" : {
    "currentPage" : 0,
    "totalPage" : 10,
    "size" :10
  }
}
```

Response Body (failed) :

```json
{
  "errors" : "Unauthorized"
}
```

## Remove Contact

Endpoint : DELETE /api/contacts/{idContact}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (success) :

```json
{
  "data" : "OK"
}
```

Response Body (failed) :
```json
{
  "errors" : "Contact is not found"
}
```