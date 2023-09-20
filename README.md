# Movie API

### Reference Documentation
This application is a API RESTful to enable it possible to read the list of nominees and winners for the Worst Film category at the Golden Raspberry Awards.

### Summary

1. [ Technical Specifications ](#technical-specifications)
2. [ Endpoints ](#endpoints)
3. [ Run On Terminal ](#run-on-terminal)
4. [ Swagger URL ](#swagger-url)
5. [ H2 Console ](#h2-console)
6. [ About the intregation tests ](#about-the-intregation-tests)
7. [ FAQ ](#faq)

### Technical Specifications
- Java JDK 17
- Used Frameworks:
    - Gradle
    - Spring Boot 3.1.3
    - Spring Web
    - JUnit
    - Lombok
    - H2 Database
    - Bean Validation
    - OpenApi (Swagger)
- Database Diagram:

![database-diagram.png](documents%2Fdatabase-diagram.png)

### Endpoints

- `GET /movie`: this endpoint returns all data in database
- `POST /movie/upload-data`: this endpoint uploads and inserts the CSV file into database
- `DELETE /movie/{idMovie}`: this endpoint removes the movie and the data of movie by id
- `DELETE /movie/delete-all`: this endpoint removes all data of database
- `GET /producer/award-interval`: this endpoint returns the producer with the longest gap between two consecutive awards, and the one who received two awards the fastest

### Run On Terminal
obs.: the `JAVA_HOME` environment variable must be assigned in your operational system.
1. Clone the project
2. Open the main folder
3. Run the following command to clean, compile, test and run the application:

`./gradlew clean build test bootRun`

### Swagger URL
- http://localhost:8080/swagger-ui/index.html

### H2 Console
- http://localhost:8080/h2-console/
- JDBC URL: `jdbc:h2:mem:moviedb`
- User Name: `sa`
- Password: `password`

### About the intregation tests
- The report of integration tests is at:
  `./build/reports/tests/test/classes/com.example.movieapi.MovieApiApplicationTests.html`
#### This integration tests verify the following contexts:
- `shouldGetAllDataOK()`: this test verify if "GET /movie" returns status 200.
- `shouldTestResultOfAwardInterval()`: this test verify the producer with the longest gap between two consecutive awards, and the one who received two awards the fastest. It must returns the min with Joel Silver producere with interval = 1 between 1990 and 1991. Finally, the max with Matthew Vaughn producer with interval = 13 between 2002 and 2015.
- `shouldDeleteDataOK()`: this test verify if "DELETE /movie/{id}" returns 200 code when we delete the movie by id.
- `shouldDeleteAllDataOK()`: this test verify if "DELETE /movie/delete-all" returns 200 code.
- `shouldIfHaveNoData()`: finally, this last test verify if the endpoint "GET /movie" returns no data after delete all data
  
This integration test coverages 71% lines of code and 88% of classes.

### FAQ

#### This application loads the .csv file on start?
Yes, the application loads the file `src/main/resources/movielist.csv` and insert this data on H2 database when starts the application.

#### How to reset and upload a new CSV file?
To do this, you need to call endpoint `DELETE /movie/delete-all` and finally call endpoint `POST /movie/upload-data` passing the CSV file. This operation can be done Swagger.
