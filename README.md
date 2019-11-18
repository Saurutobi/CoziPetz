# CoziPetz

### To run & build, use Maven:
`mvn clean spring-boot:run`

### API Documentation(endpoints and verbs):
- Run application
- Access the Swagger UI Docs: http://localhost:8080/swagger-ui.html

### Design Decisions:
- Limited myself to 8 hours(give or take some setup)
- While H2 repo is included in pom, I didn't want to experiment with stuff that I've never done, so I used a simple List<> to get the Api running
- CreatePets can(but shouldn't) accept an ID, but must conform to UUID. Ran into a roadblock doing inherritance with json mapping so skipped it for now.
- No validation on sex/type yet.
- Focused on the CRUD ops functioning, showing of the streams and use of Option
- Tests need a lot more buildout to be prod ready. I didn't test every edge case on purpose. Optimized for time and getting the base stuff running. Dataproviders should be used to consolidate a bit.


### Sample data used for testing create
```
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
```