/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Author;
import entity.Book;
import java.util.List;
import java.util.Scanner;
import tools.InputProtection;

/**
 *
 * @author admin
 */
public class BookManager {

    private final Scanner scanner;

    public BookManager(Scanner scanner) {
        this.scanner = scanner;
    }
    public void addBook(DatabaseManager databaseManager){
        System.out.println("----- Add book -----");
        Book book = new Book();
        System.out.print("Enter title: ");
        book.setTitle(scanner.nextLine());
        System.out.print("Enter published year: ");
        book.setPublishedYear(InputProtection.intInput(1000,2030));
        System.out.println("Hoy many authors in book: ");
        int countAuthors = InputProtection.intInput(1,5);//scanner.nextInt(); scanner.nextLine();
        for (int i = 0; i < countAuthors; i++) {
            System.out.printf("Enter author (%d) firstname: ", i+1);
            String authorFirstname = scanner.nextLine();
            System.out.printf("Enter author (%d) lastname: ",i+1);
            String authorLastname = scanner.nextLine();
            Author author = databaseManager.getAuthor(authorFirstname, authorLastname);
            if(author == null){
                author = new Author(authorFirstname,authorLastname);
                databaseManager.saveAuthor(author);
            }
            book.getAuthors().add(author);
        }
        System.out.print("Enter quantity copy: ");
        book.setQuantity(InputProtection.intInput(1,10));
        book.setCount(book.getQuantity());
        databaseManager.saveBook(book);
        System.out.println("Added book: "+book.toString());
    }

    public int printListBooks(DatabaseManager databaseManager) {
        int n = 0;
        System.out.println("----- List books -----");
        List<Book> books = databaseManager.getListBooks();
        for (int i = 0; i < books.size(); i++) {
            StringBuilder sbAuthorsBook = new StringBuilder();
            for (int j = 0; j < books.get(i).getAuthors().size(); j++) {
                sbAuthorsBook.append(books.get(i).getAuthors().get(j).getFistname());
                sbAuthorsBook.append(" ");
                sbAuthorsBook.append(books.get(i).getAuthors().get(j).getLastname());
                sbAuthorsBook.append(". ");
            }
            System.out.printf("%d. %s. %s In store: %d%n",
                    books.get(i).getId(),
                    books.get(i).getTitle(),
                    sbAuthorsBook.toString(),
                    books.get(i).getCount()
            );
            n++;
        }
        return n;
    }
}