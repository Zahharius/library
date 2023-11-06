/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Author;
import entity.Book;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class BookManager {

    private final Scanner scanner;

    public BookManager(Scanner scanner) {
       this.scanner=scanner;
    }
    public Book addBook(){
        System.out.println("--- add book ---");
        Book book = new Book();
        System.out.print("enter book title: ");
        book.setTitle(scanner.nextLine());
        System.out.print("enter published year: ");
        book.setPublishedYear(scanner.nextInt());scanner.nextLine();
        System.out.println("how many authors in book: ");
        int countAuthors=scanner.nextInt();scanner.nextLine();
        for (int i = 0; i < countAuthors; i++) {
            System.out.printf("enter author (%d) firstname: ",i+1);
            String authorFirstname= scanner.nextLine();
            System.out.printf("enter author (%d) lastname: ",i+1);
            String authorLastname= scanner.nextLine();
            book.addAuthor(new Author(authorFirstname, authorLastname));
        }
        System.out.println("Added book: "+book.toString());
        return book;
    }
}
