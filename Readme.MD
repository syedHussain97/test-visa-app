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
- Keystore present /etc/ssl to be copied over

Other Requirements:
- Keystore having the certificates(RootCert, Product cert, private keys) which is referenced in the docker-compose.yml file

### Potential Improvements:
- Add jwt authentication to secure the endpoint exposed  
- Change the api Client from supplier to interface when increasing the number of endpoints invoked
- Add feign client after above step if no business logic is involved
- Add Shell scripts to generate the keystore having the certs rather than mounting them in docker-compose file