# Book Store REST API

A simple RESTful API for managing books in a bookstore, built with Spring Boot and MongoDB. This application allows users to perform CRUD (Create, Read, Update, Delete) operations on books stored in a MongoDB database.

## Features

- **Create**: Add new books with details like ID, name, price, author, and description.
- **Get Book**: Retrieve a single book by ID or list all books.
- **Update**: Modify a book’s name while preserving other details.
- **Delete**: Remove a book by ID.
- **RESTful API** endpoints accessible via HTTP requests.
- **MongoDB** integration for persistent storage.
- **Built with Java** records** for immutable data models.

## Technologies Used

- **Spring Boot**: 3.4.1
- **MongoDB**: NoSQL database for storing book data
- **Java**: 24 (with record types)
- **Maven**: 3.9.10 for dependency management
- **Lombok**: For reducing boilerplate code
- **VS Code**: Recommended IDE with Java and Spring Boot extensions
- **MongoDB Compass**: For database visualization
- **Postman**: For testing API endpoints

## Prerequisites

- **Java Development Kit (JDK)**: Version 24
- **Maven**: Version 3.9.10
- **MongoDB**: Installed and running locally (port 27017)
- **VS Code**: With the following extensions:
  - Extension Pack for Java (Microsoft)
  - Spring Boot Extension Pack (VMware)
  - Maven for Java (Microsoft)
  - Lombok Annotations Support for VS Code
- **MongoDB Compass**: For database management (optional)
- **Postman**: For API testing (optional)

## Project Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/neelameghashyam/book-store.git
   cd book-store
   ```

2. **Install MongoDB**:
   - Download and install MongoDB from [https://www.mongodb.com/try/download/community](https://www.mongodb.com/try/download/community).
   - Start MongoDB server:
     ```bash
     mongod
     ```
   - Verify MongoDB is running on `mongodb://localhost:27017` using MongoDB Compass.

3. **Configure MongoDB**:
   - Create a database named `bookstoredb` in MongoDB Compass or via the MongoDB shell:
     ```javascript
     use bookstoredb
     ```

4. **Verify JDK and Maven**:
   - Check Java version:
     ```bash
     java --version
     ```
     Expected output: `java 24.0.1`
   - Check Maven version:
     ```bash
     mvn -version
     ```
     Expected output: `Apache Maven 3.9.10`

5. **Update Configuration**:
   - Open `src/main/resources/application.properties`:
     ```properties
     spring.application.name=book-store
     spring.data.mongodb.uri=mongodb://localhost:27017/bookstoredb
     server.port=8080
     ```
   - Ensure `spring.docker.compose.enabled=false` is removed or absent (no Docker Compose dependency).

## Running the Application

### Via Maven

1. Navigate to the project directory:
   ```bash
   cd book-store
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Check logs for:
   ```
   Started BookStoreApplication in X seconds
   ```
5. Access the API at `http://localhost:8080/book-store`.

### Via VS Code

1. Open the project in VS Code:
   - File > Open Folder > Select `book-store` directory.
2. Configure launch settings:
   - Create or update `.vscode/launch.json`:
     ```json
     {
         "version": "0.2.0",
         "configurations": [
             {
                 "type": "java",
                 "name": "Launch BookStoreApplication",
                 "request": "launch",
                 "mainClass": "com.thinkconstructive.book_store.BookStoreApplication",
                 "projectName": "book-store"
             }
         ]
     }
     ```
3. Run the application:
   - Open Run and Debug (`Ctrl + Shift + D`).
   - Select `Launch BookStoreApplication` and click the green play button.
4. Verify the application starts at `http://localhost:8080/book-store`.

## API Endpoints

Use Postman or any HTTP client to test the following endpoints:

| Method | Endpoint                  | Description                     | Request Body Example                                      |
|--------|---------------------------|---------------------------------|----------------------------------------------------------|
| POST   | `/book-store`             | Create a new book               | `{"bookId":"1","name":"Sample Book","price":"29.99","author":"John Doe","description":"A sample book"}` |
| GET    | `/book-store/{bookId}`    | Get a book by ID                | None                                                     |
| GET    | `/book-store`             | Get all books                   | None                                                     |
| PUT    | `/book-store`             | Update a book’s name            | `{"bookId":"1","name":"Updated Book","price":"29.99","author":"John Doe","description":"A sample book"}` |
| DELETE | `/book-store/{bookId}`    | Delete a book by ID             | None                                                     |

### Example Requests

- **Create a Book**:
  ```
  POST http://localhost:8080/book-store
  Content-Type: application/json
  {
      "bookId": "1",
      "name": "Sample Book",
      "price": "29.99",
      "author": "John Doe",
      "description": "A sample book"
  }
  ```

- **Get a Book**:
  ```
  GET http://localhost:8080/book-store/1
  ```

- **Delete a Book**:
  ```
  DELETE http://localhost:8080/book-store/1
  ```

## Testing

1. **Unit Tests**:
   - Run tests:
     ```bash
     mvn test
     ```
   - Check `src/test/java/com/thinkconstructive/book_store/BooksStoreApplicationTests.java`.

2. **MongoDB Verification**:
   - Open MongoDB Compass.
   - Connect to `mongodb://localhost:27017/bookstoredb`.
   - Verify the `books` collection for created/updated/deleted documents.

3. **API Testing**:
   - Use Postman to send requests to the endpoints.
   - Confirm responses match expected outputs (e.g., `200 OK` for successful operations).

## Troubleshooting

- **Constructor Errors**:
  - If errors like `The constructor Book(...) is undefined` appear, ensure `Book.java` and `BookDto.java` have matching fields and `BookMapper.java` uses correct record accessors.
  - Run `mvn clean install -X` and share the output.

- **MongoDB Connection Issues**:
  - Verify MongoDB is running (`mongod` process).
  - Check `spring.data.mongodb.uri` in `application.properties`.
  - Test connection in MongoDB Compass.

- **DELETE Operation Fails**:
  - If `DELETE /book-store/{id}` returns `500`, ensure `BookRepository.java` uses `deleteById` or a correct `@DeleteQuery`.
  - Check MongoDB document for `_id` vs. `bookId` mismatch.
  - Share the stack trace from server logs or Postman.

- **VS Code Errors**:
  - Ensure Lombok extension is installed and enabled.
  - Restart VS Code after updating `pom.xml` or Java files.

- **Spring Boot Version**:
  - If issues arise, revert `spring-boot-starter-parent` to `3.4.1` in `pom.xml`.

## Project Structure

```
book-store
├── src
│   ├── main
│   │   ├── java/com/thinkconstructive/book_store
│   │   │   ├── controller
│   │   │   │   └── BookController.java
│   │   │   ├── dto
│   │   │   │   └── BookDto.java
│   │   │   ├── entity
│   │   │   │   └── Book.java
│   │   │   ├── mapper
│   │   │   │   └── BookMapper.java
│   │   │   ├── repository
│   │   │   │   └── BookRepository.java
│   │   │   ├── service
│   │   │   │   ├── impl
│   │   │   │   │   └── BookServiceImpl.java
│   │   │   │   └── BookService.java
│   │   │   └── BookStoreApplication.java
│   │   └── resources
│   │       └── application.properties
│   └── test/java/com/thinkconstructive/book_store
│       └── BooksStoreApplicationTests.java
├── pom.xml
└── README.md
```
