package com.bridgelabz;

import com.bridgelabz.Service.BookService;



import java.util.Scanner;

public class Main {

    public static  void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BookService  bookService=new BookService();

        while (true) {

            System.out.println("\n==============================");
            System.out.println("   LIBRARY BOOK MANAGEMENT");
            System.out.println("==============================");

            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");

            System.out.println("4. Get All Books");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    bookService.addBook();
                    break;

                case 2:
                    bookService.updateBook();
                    break;

                case 3:
                    bookService.deleteBook();
                    break;

                case 4:
                    bookService.getBookDetails();
                    break;

                case 5:
                    System.out.println("Application closed.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}