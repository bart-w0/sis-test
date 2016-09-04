SIS - technical test - Bart W

To run:
- start Application.java or deploy to an application server.

REST endpoints:
- get team: GET /team/{id}
- get all teams: GET /teams
- create new team: POST /team (please note this endpoint accepts application/json Content-type)
* please use some REST client to test endpoints (e.g. Firefox Rest Client)

Technologies used:
- Maven
- Spring Boot
- JodaTime
- SLF4J
- Jackson
- Junit, Mockito

Logging:
- to console and to /logs/team.log file

To run tests:
- mvn verify

Live deployment:
- on Heroku
- base URL: https://bart-w-sis-test.herokuapp.com (please note there is no HTML content)

Few notes:
- Spring Boot has been chosen due to its popularity.
- There are no code comments. I believe sometimes code is more clear without them.
- I wasn't trying to cover all edge cases in my unit tests. (this can be improved)
- There is no authentication. It could be implemented be requiring a token with each request, which could be obtained after successful authentication. (this can be implemented)
- No docker as I don't feel strong in this area.

Thank you for reviewing!