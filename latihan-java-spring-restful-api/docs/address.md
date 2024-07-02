# Address API Specs

## Create Address

Endpoint : POST /api/contacts/{idContact}/addresses

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "street": "jl sungai bambu",
  "city" : "tanjung priok",
  "province" : "Jakarta",
  "country" : "indonesia",
  "postalCode" : "1234"
}
```

Response Body (success):

```json
{
  "data": {
    "id" : "1",
    "street": "jl sungai bambu",
    "city" : "tanjung priok",
    "province" : "Jakarta",
    "country" : "indonesia",
    "postalCode" : "1234"
  }
}
```

Response Body (failed):

```json
{
  "errors" : "Contact is not found"
}
```

## Update Address

Endpoint : PUT /api/contacts/{idContact}/addresses/{idAddress}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "street": "jl sungai bambu",
  "city" : "tanjung priok",
  "province" : "Jakarta",
  "country" : "indonesia",
  "postalCode" : "1234"
}
```

Response Body (success):

```json
{
  "data": {
    "id" : "1",
    "street": "jl sungai bambu",
    "city" : "tanjung priok",
    "province" : "Jakarta",
    "country" : "indonesia",
    "postalCode" : "1234"
  }
}
```

Response Body (failed):

```json
{
  "errors" : "Address is not found"
}
```

## Get Address

Endpoint : GET /api/contacts/{idContact}/addresses/{idAdd}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (success):

```json
{
  "data": {
    "id" : "1",
    "street": "jl sungai bambu",
    "city" : "tanjung priok",
    "province" : "Jakarta",
    "country" : "indonesia",
    "postalCode" : "1234"
  }
}
```

Response Body (failed):

```json
{
  "errors" : "Address is not found"
}
```

## Remove Address

Endpoint : DELETE /api.contacts/{idContact}/addresses/{idAddress}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (success):

```json
{
  "data": "OK"
}
```

Response Body (failed):

```json
{
  "errors" : "Address is not found"
}
```

## List Address

Endpoint : GET /api/contacts/{idContact}/addresses

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (success):

```json
{
  "data": [
    {
      "id" : "1",
      "street": "jl sungai bambu",
      "city" : "tanjung priok",
      "province" : "Jakarta",
      "country" : "indonesia",
      "postalCode" : "1234"
    }
  ]
}
```

Response Body (failed):

```json
{
  "errors" : "Contact is not found"
}
```

