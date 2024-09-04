# Bookshelf Management System (MS)

A simple backend API to manage items on a bookshelf. The API has simple CRUD (Create, Read, Update, Delete) operations. The data is stored in
a Postgres database.

### Run the App
1. Execute the gradle command: `./gradlew bootRun`
   1. *Since Spring Boot 3.x, [Docker Compose is supported](https://spring.io/blog/2023/06/21/docker-compose-support-in-spring-boot-3-1)
      so running this command will also spin up the necessary Docker container (e.g. Postgres)*
2. Interact with the API endpoints using [Insomnia](https://insomnia.rest/download) (or [Postman](https://www.postman.com/downloads/), [curl](https://blog.hubspot.com/website/curl-command))
3. Stop the app with `Ctrl + C`

*Note: You may need Docker. You can download it [here](https://www.docker.com/products/docker-desktop/).*

### Build the App
`./gradlew build`

### Test the App
`./gradlew test`
