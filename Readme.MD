## Visa App

This is just a test api to call ssl secured visa apis .

### Steps:
To create the keystore: go to https://developer.visa.com/pages/working-with-visa-apis/two-way-ssl

First build the project with the following mvn command: 

    mvn clean install

Build the docker file as :

    docker build -f Dockerfile  -t test-visa-app:latest .

Run docker compose to start the server 

    docker-compose -f docker-compose.yml up 

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