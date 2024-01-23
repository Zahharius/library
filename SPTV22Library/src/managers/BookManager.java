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
       this.scanner=scanner;
    }
    public Book addBook(){
        System.out.println("--- add book ---");
        Book book = new Book();
        System.out.print("enter book title: ");
        book.setTitle(scanner.nextLine());
        System.out.print("enter published year: ");
        book.setPublishedYear(InputProtection.intInput(868, 2027));
        System.out.println("how many authors in book: ");
        int countAuthors= InputProtection.intInput(1,5);///scanner.nextInt();scanner.nextLine();
        for (int i = 0; i < countAuthors; i++) {
            System.out.printf("enter author (%d) firstname: ",i+1);
            String authorFirstname= scanner.nextLine();
            System.out.printf("enter author (%d) lastname: ",i+1);
            String authorLastname= scanner.nextLine();
            book.getAuthors().add(new Author(authorFirstname, authorLastname));
        }
        System.out.print("Enter quantity of books: ");
        book.setQuantity(InputProtection.intInput(1, 10));
        book.setCount(book.getQuantity());
        
        System.out.println("Added book: "+book.toString());
        return book;
    }

    public void printListBooks(List<Book> books) {
        System.out.println("---List Books--- ");
        for (int i=0; i< books.size(); i++){
            StringBuilder sbAuthorsBooks = new StringBuilder();
                for(int j = 0; j<books.get(i).getAuthors().size();j++){
                    sbAuthorsBooks.append(books.get(i).getAuthors().get(j).getFirstname());
                    sbAuthorsBooks.append(" ");
                    sbAuthorsBooks.append(books.get(i).getAuthors().get(j).getLastname());
                    sbAuthorsBooks.append(". ");
                }
                        
            System.out.printf("%d. %s. %s In store: %d%n",
                    i+1,
                    books.get(i).getTitle(),
                    sbAuthorsBooks.toString(),
                    books.get(i).getCount()
            );
        }
    }
}
