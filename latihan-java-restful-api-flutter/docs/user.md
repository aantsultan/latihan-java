### Register
POST /api/users

REQUEST
```json
{
    "fullName":"aant sultan rahmanya",
    "email":"aant@mail.com",
    "phoneNumber":"+62111111111",
    "birthDate":"2027-01-01",
    "gender":"MALE",
    "password":"rahasia",
    "confirmPassword":"rahasia",
    "promoEvents":true,
    "termConditions":true
}
```

RESPONSE
```json
{
  "data": "OK"
}
```

### ~~Login~~ (Deprecated)

POST /api/auth/login

REQUEST
```json
{
  "phoneNumber":"+62111111111",
  "password":"rahasia"
}
```

RESPONSE

```json
{
    "data": {
                "token":"TOKEN",
                "expiredAt": 121212121212 // milliseconds
            }
}
```

### Get User

GET /api/users/current

Header : ~~X-API-TOKEN~~, Authorization Bearer {Token UUID}

RESPONSE

```json
{
    "data":{
                "fullName":"aant sultan rahmanya",
                "email":"aant@mail.com",
                "phoneNumber":"+62111111111",
                "birthDate":"2027-01-01",
                "gender":"male",
                "promoEvents":true,
                "termConditions":true
            }
}
```

### Get Token

GET /api/users/token

Header : Authorization Basic {Encode64}

RESPONSE :

```json
{
    "data": {
                "token":"TOKEN",
                "expiredAt": 121212121212 // milliseconds
            }
}
```