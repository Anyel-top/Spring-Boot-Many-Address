# **Select Language:** 🌍
- [Español (Spanish)](README-es.md)
- [English](README.md)

# NCAPAS - Multi-layer CRUD Application with Spring Boot

## Description
NCAPAS is a Spring Boot application designed to manage CRUD operations (Create, Read, Update, and Delete) for a list of persons. The application uses a layered architecture to separate concerns and improve code maintainability and scalability. Additionally, it implements services to transform data to uppercase before storing it.

## RESULTS
### Swagger UI Documentation
![Alt text](doc/document.PNG) 
### Create a new person
![Alt text](doc/save.PNG) 
### Read all persons
![Alt text](doc/findAll.PNG)
### Read by identification
![Alt text](doc/findBy.PNG)
### Update by identification
![Alt text](doc/update.PNG) 
### Delete by identification
![Alt text](doc/delete.PNG) 



## Project Structure
The project is organized into the following layers:

- **Controller**: Handles HTTP requests and returns appropriate responses.
- **Service**: Contains the business logic.
- **Repository**: Interacts with the stored data (in this case, an in-memory list).
- **Model**: Defines the data entities.
- **Utils**: Provides auxiliary utilities.

## Dependencies
The main dependencies used in this project are:

- Spring Boot Starter Web
- Spring Boot DevTools
- Lombok
- Spring Boot Starter Test
- SpringDoc OpenAPI

## Swagger Configuration
Swagger is used to document and test the API. The documentation is available at `http://localhost:8080/persons/v1/swagger-ui.html`.

## How to Run the Project
1. Clone the repository.
2. Navigate to the project directory.
3. Run `mvn spring-boot:run`.

## API Endpoints
### GET /
Returns a welcome message and the URL to access Swagger documentation.

### POST /persons/v1/save
Saves a new person.
- **Request Body**: JSON with the person's data.
- **Response**: The saved person with data in uppercase.

### GET /persons/v1/findAll
Returns a list of all saved persons.

### GET /persons/v1/findById/{identification}
Returns a person by their identification.
- **Path Variable**: `identification` (String).

### PUT /persons/v1/updateById/{identification}
Updates an existing person by their identification.
- **Path Variable**: `identification` (String).
- **Request Body**: JSON with the updated person's data.
- **Response**: The updated person with data in uppercase.

### DELETE /persons/v1/deleteById/{identification}
Deletes a person by their identification.
- **Path Variable**: `identification` (String).
- **Response**: Message indicating whether the person was deleted or not found.

## Example Code
### Controller

```java
@RestController
@RequestMapping("/persons/v1")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public String index(){
        return "In /persons/v1/swagger-ui.html you can see the documentation of the API";
    }

    @PostMapping("/save")
    public Person save(@RequestBody Person person) {
        return personService.save(PersonUpperCaseService.toUpperCase(person));
    }

    @GetMapping("/findAll")
    public List<Person> findAll(){
        return personService.findAll();
    }

    @GetMapping("/findById/{identification}")
    public Person findById(@PathVariable String identification){
        return personService.findById(identification);
    }

    @PutMapping("/updateById/{identification}")
    public Person updateById(@PathVariable String identification, @RequestBody Person person){
        return personService.updateById(identification, PersonUpperCaseService.toUpperCase(person));
    }

    @DeleteMapping("/deleteById/{identification}")
    public String deleteById(@PathVariable String identification){
        return personService.deleteById(identification);
    }
}
```

### Service

```java
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person save(Person person){
        return personRepository.save(person);
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person findById(String identification){
        return personRepository.findById(identification);
    }

    public Person updateById(String identification, Person person){
        return personRepository.updateById(identification, person);
    }

    public String deleteById(String identification){
        return personRepository.deleteById(identification);
    }
}
```

### Repository

```java
@Repository
public class PersonRepository {

    List<Person> persons = new ArrayList<>();

    public Person save(Person person){
        persons.add(person);
        return person;
    }

    public List<Person> findAll(){
        return persons;
    }

    public Person findById(String identification){
        for (Person person : persons) {
            if (person.identification().equals(identification)) {
                return person;
            }
        }
        return null;
    }

    public Person updateById(String identification, Person person){
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).identification().equals(identification)) {
                persons.set(i, person);
                return person;
            }
        }
        return null;
    }

    public String deleteById(String identification){
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).identification().equals(identification)) {
                persons.remove(i);
                return "Person with identification " + identification + " was deleted";
            }
        }
        return "Person with identification " + identification + " was not found";
    }
}
```

### Uppercase Utility

```java
public class PersonUpperCaseService {

    private static UpperCaseImpl helper = new UpperCaseImpl();

    public static Person toUpperCase(Person person) {
        String upperIdentification = helper.upperCase(person.identification());
        String upperFirstName = helper.upperCase(person.fisrtName());
        String upperLastName = helper.upperCase(person.lastName());
        String upperEmails = helper.upperCase(person.email());
        return new Person(upperIdentification, upperFirstName, upperLastName, upperEmails);
    }
}
```

### Configuration File application.properties

```properties
spring.application.name=NCAPAS
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
springdoc.swagger-ui.path=/swagger-ui.html
```

### POM.xml

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.5</version>
        <relativePath/>
    </parent>
    <groupId>top.anyel</groupId>
    <artifactId>NCAPAS</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>NCAPAS</name>
    <description>NCAPAS</description>
    <properties>
        <java.version>17</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.5.0</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

## Contribution
If you wish to contribute to this project, please follow these steps:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-new`).
3. Make your changes and commit them (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-new`).
5. Create a new Pull Request.

## License
This project is licensed under the MIT License.