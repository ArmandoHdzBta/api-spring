# Generate RSA Keys

> Using `Git bash here` console from Git

Got to path `src/main/resources/jwtKey`

Run command to generate `private_key.pem`:
````bash
    openssl genpkey -algorithm RSA -out private_key.pem
````

Run command to generate `prublic_key.pem`:
````bash
    openssl rsa -in private_key.pem -pubout -out public_key.pem
````