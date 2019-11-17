# CoziPetz

To run & build, use Maven:
`mvn clean spring-boot:run`

API Documentation(endpoints and verbs):
-Run application
-Access the Swagger UI Docs: http://localhost:8080/swagger-ui.html



TODO: make it so createPets doesn't take an ID
Updating doesn't work.
No search functionality
unit tests(more)
return messages are meh



Sample data used for testing
[
  {
    "description": "My Pet 1",
    "image_url": "https://bla.com/pic",
    "name": "Fido",
    "owner_email": "my@email.com",
    "sex": "m",
    "type": "dog,"
  },
  {
    "description": "My Pet 2",
    "image_url": "https://bla.com/pic2",
    "name": "Rex",
    "owner_email": "my@email.com",
    "sex": "f",
    "type": "bird"
  }
]
