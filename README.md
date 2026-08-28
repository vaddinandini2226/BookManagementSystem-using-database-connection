# Book Management System (Database Connection)

A Java console-based Library/Book Management System that uses **JDBC** to
persist book records in a **PostgreSQL** database. Built with **Maven** and
a simple **DAO (Data Access Object)** layered architecture.

## Features

1. **Add Book** — enter title, author, category, and price; the record is inserted into the database.
2. **Update Book** — enter a Book ID along with new title, author, category, and price to update an existing record.
3. **Delete Book** — enter a Book ID to remove that record from the database.
4. **Get All Books** — fetches and displays every book currently stored, or shows "No books found." if the table is empty.
5. **Exit** — closes the application.

## Project Structure

```
BookManagementSystem
├── pom.xml
├── src
│   └── main
│       └── java
│           └── com
│               └── bridgelabz
│                   ├── Main.java
│                   ├── model
│                   │   └── Book.java
│                   ├── DAO
│                   │   └── BookDAO.java
│                   ├── DAOImpl
│                   │   └── BookDAOImpl.java
│                   ├── Service
│                   │   └── BookService.java
│                   └── util
│                       └── DBConnection.java
└── README.md
```

## Architecture

- **model.Book** — plain Java object representing a book (`bookId`, `title`, `author`, `category`, `price`).
- **DAO.BookDAO** — interface declaring the data access operations (`addBook`, `updateBook`, `deleteBook`, `getAllBooks`).
- **DAOImpl.BookDAOImpl** — implements `BookDAO`, containing the actual JDBC/SQL logic (`PreparedStatement` queries against the `books` table).
- **Service.BookService** — thin service layer that delegates to the DAO, called from `Main`.
- **util.DBConnection** — provides a single method, `getConnection()`, that opens a JDBC connection to the PostgreSQL database.
- **Main** — console menu and entry point.

## Requirements

- Java 17 or higher (project is configured for a recent JDK via Maven)
- Maven
- PostgreSQL installed and running
- PostgreSQL JDBC driver (already declared as a dependency in `pom.xml`)

## Database Setup

1. Create a database named `library_db` in PostgreSQL:
   ```sql
   CREATE DATABASE library_db;
   ```

2. Connect to `library_db` and create the `books` table:
   ```sql
   CREATE TABLE books (
       book_id  SERIAL PRIMARY KEY,
       title    VARCHAR(255) NOT NULL,
       author   VARCHAR(255) NOT NULL,
       category VARCHAR(100),
       price    DOUBLE PRECISION
   );
   ```

3. Update the connection details in `src/main/java/com/bridgelabz/util/DBConnection.java` to match your local PostgreSQL setup:
   ```java
   private static final String URL = "jdbc:postgresql://localhost:5432/library_db";
   private static final String USER = "your_postgres_username";
   private static final String PASSWORD = "your_postgres_password";
   ```

   > **Note:** Avoid committing real database credentials to a public repository.
   > Consider moving these to environment variables or a local, gitignored
   > config file for anything beyond local practice use.

## How to Run

### In IntelliJ IDEA

1. Open IntelliJ IDEA and choose **Open**, then select the project folder.
2. Let IntelliJ import the Maven project (it will read `pom.xml` automatically).
3. Make sure PostgreSQL is running and the `library_db` database/table exist (see above).
4. Update `DBConnection.java` with your own database credentials.
5. Open `Main.java` and click **Run**.
6. Use the console menu to add, update, delete, or view books.

### From the command line

```
mvn compile
mvn exec:java -Dexec.mainClass="com.bridgelabz.Main"
```

## Example Console Output

```
==============================
   LIBRARY BOOK MANAGEMENT
==============================
1. Add Book
2. Update Book
3. Delete Book
4. Get All Books
5. Exit
Enter your choice: 1

--- Add Book ---
Enter title: Clean Code
Enter author: Robert Martin
Enter category: Programming
Enter price: 499.99
Book added successfully.

Enter your choice: 4

--- All Books ---
-------------------------
ID       : 1
Title    : Clean Code
Author   : Robert Martin
Category : Programming
Price    : 499.99

Enter your choice: 5
Application closed.
```
