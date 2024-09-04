# Bookshelf Management System (MS)

A simple backend API to manage items on a bookshelf. The API has simple CRUD (Create, Read, Update, Delete) operations. The data is stored in
a Postgres database.

### Run the App
1. Execute the gradle command: `./gradlew bootRun`
   1. *Since Spring Boot 3.x, [Docker Compose is supported](https://spring.io/blog/2023/06/21/docker-compose-support-in-spring-boot-3-1)
      so running this command will also spin up the necessary Docker container (e.g. Postgres)*
2. Interact with the API endpoints using Insomnia (or Postman, curl)
3. Stop the app with `Ctrl + C`

*Note: This assumes that you already have the necessary services installed on your local machine like Gradle, Docker, etc.*

### Build the App
`./gradlew build`

### Test the App
`./gradlew test`
