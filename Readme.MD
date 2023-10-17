## Visa App

This is just a test api to call ssl secured visa apis .
Build the docker file as :

    docker build -f Dockerfile  -t test-visa-app:latest .


### Requirements
Properties:
- keystore path
- keystore password
- visa application id 
- visa application password

Other Requirements:
- Keystore having the certificates(RootCert, Product cert, private keys) which is referenced in the docker-compose.yml file

### Potential Improvements:
- Add jwt authentication to secure the endpoint exposed  
- Change the api Client from supplier to interface when increasing the number of endpoints invoked
- Add feign client after above step if no business logic is involved