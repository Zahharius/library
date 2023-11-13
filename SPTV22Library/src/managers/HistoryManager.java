/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Book;
import entity.History;
import entity.Reader;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author admin
 */

public class HistoryManager {

    private final Scanner scanner;
    private final BookManager bookManager;
    private final ReaderManager readerManager;
    
    
    public HistoryManager(Scanner scanner, ReaderManager readerManager, BookManager bookManager){
        this.scanner = scanner;
        this.bookManager = bookManager;
        this.readerManager = readerManager;
    }
    /**
     * логика работы 
     * 1 список книг
     * 2 пользователь вводит номер книги
     * 3 записать в хистори выбранную книгу из массива букс
     * 4 список читателей
     * 5 пользователь вводит номер читателя
     * 6 записываем в хистори выбранного читателя из массива читателей
     * 7 записываем в хистори дату выдачи книги 
     * @return History history
     */
    public History TakeOutBook(Book[] books, Reader[] readers){
        History history = new History();
        bookManager.printListBooks(books);
        System.out.print("enter book number from list: ");
        int numberBook = scanner.nextInt(); scanner.nextLine();
        history.setBook(books[numberBook - 1]);
        readerManager.printListReaders(readers);
        System.out.print("enter reader from list: ");
        int numberReader = scanner.nextInt(); scanner.nextLine();
        history.setReader(readers [numberReader-1]);
        history.setTakeOutBook(new GregorianCalendar().getTime());
        
        return history;
    }
    public void printListReadingBooks(History[] histories){
        System.out.println("---List reading books---");
        for (int i = 0; i < histories.length; i++) {
            if(histories[i].getReturnBook() == null){
            System.out.printf("%d. %s. read %s %s",
                    i+1,
                    histories[i].getBook().getTitle(),
                    histories[i].getReader().getFirstname(),
                    histories[i].getReader().getLastname()
            ); 
        }
    }
}
}