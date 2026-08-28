package com.bridgelabz.DAOImpl;

import com.bridgelabz.DAO.BookDAO;
import com.bridgelabz.model.Book;
import com.bridgelabz.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookDAOImpl implements BookDAO {

    private Scanner scanner = new Scanner(System.in);


    // ADD BOOK
    @Override
    public void addBook() {

        System.out.println("\n--- Add Book ---");

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        String sql = """
                INSERT INTO books
                (title, author, category, price)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, category);
            statement.setDouble(4, price);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Book added successfully.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // UPDATE BOOK
    @Override
    public void updateBook() {

        System.out.println("\n--- Update Book ---");

        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new title: ");
        String title = scanner.nextLine();

        System.out.print("Enter new author: ");
        String author = scanner.nextLine();

        System.out.print("Enter new category: ");
        String category = scanner.nextLine();

        System.out.print("Enter new price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        String sql = """
                UPDATE books
                SET title = ?,
                    author = ?,
                    category = ?,
                    price = ?
                WHERE book_id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, category);
            statement.setDouble(4, price);
            statement.setInt(5, bookId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Book updated successfully.");
            } else {
                System.out.println("Book not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // DELETE BOOK
    @Override
    public void deleteBook() {

        System.out.println("\n--- Delete Book ---");

        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        String sql = """
                DELETE FROM books
                WHERE book_id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, bookId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Book not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // GET ALL BOOKS
    @Override
    public void getAllBooks() {

        System.out.println("\n--- All Books ---");

        String sql = """
                SELECT *
                FROM books
                ORDER BY book_id
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            boolean found = false;

            while (resultSet.next()) {

                found = true;

                System.out.println("-------------------------");

                System.out.println(
                        "ID       : "
                                + resultSet.getInt("book_id"));

                System.out.println(
                        "Title    : "
                                + resultSet.getString("title"));

                System.out.println(
                        "Author   : "
                                + resultSet.getString("author"));

                System.out.println(
                        "Category : "
                                + resultSet.getString("category"));

                System.out.println(
                        "Price    : "
                                + resultSet.getDouble("price"));
            }

            if (!found) {
                System.out.println("No books found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}