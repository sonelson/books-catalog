# Books Catalog - API

The Book Catalog is developed as a REST API service to demonstrate a minimal viable product functionality for Books represented as a web resource. The REST API will support creation, retrieval, update and deletion of book resources. 

# API Service Implementation

## Technology/Framework:
The implementation depends/uses the following technology stack:

- Java8
- JAX-RX with Jersey
- JPA with Hibernate ORM
- Dropwizard.io REST framework
- Maven
- JUnit5

## Application Modules
The sample dropwizard implementation is comprised of the following modules:

- auth :: provides basic HTTP authentication and role-based authorization to protect resources
- dao :: [BookDAO](https://github.com/sonelson/books-catalog/blob/master/src/main/java/dev/sol/catalog/dao/BookDAO.java) and [AuthorDAO](https://github.com/sonelson/books-catalog/blob/master/src/main/java/dev/sol/catalog/dao/AuthorDAO.java) use the Data Access Object pattern with Hibernate ORM 
- entities :: map Java classes [Book](https://github.com/sonelson/books-catalog/blob/master/src/main/java/dev/sol/catalog/entities/Book.java) and [Author](https://github.com/sonelson/books-catalog/blob/master/src/main/java/dev/sol/catalog/entities/Author.java) to database tables with JPA annotations. JPQL statements for use in the DAO classes are  defined here.
- jaxresources :: [BookResource](https://github.com/sonelson/books-catalog/blob/master/src/main/java/dev/sol/catalog/jaxresources/BookResource.java) and [AuthorResource](https://github.com/sonelson/books-catalog/blob/master/src/main/java/dev/sol/catalog/jaxresources/AuthorResource.java) are the JAX-RS resources, which use their respective DAOs to persist data

# Running the API Service

To package application into a fat JAR, run the following maven command from the dropwizard project root directory:

    $ mvn clean install

A successful build output will produce the fat JAR (located under project-root/books-catalog/target/books-api-1.0-SNAPSHOT.jar)

To run the server:

    $ java -jar target/books-api-1.0-SNAPSHOT.jar server books-catalog.yml

To hit the API endpoint:

    http://localhost:8080/api/book


