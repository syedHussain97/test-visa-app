## Visa App

This is just a test api to call ssl secured visa apis 

### Requirements
- keystore path
- keystore password
- visa application id 
- visa application password 

### Potential Improvements:
- Add jwt authentication to secure the endpoint exposed  
- Containerize the application 
- Change the api Client from supplier to interface when increasing the number of endpoints invoked
- Add feign client after above step if no business logic is involved