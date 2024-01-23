/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Book;
import entity.History;
import entity.Reader;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import tools.InputProtection;

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
    public History TakeOutBook(List<Book> books, List<Reader> readers){
        History history = new History();
        bookManager.printListBooks(books);
        System.out.print("enter book number from list: ");
        int numberBook =  InputProtection.intInput(1,books.size());///scanner.nextInt(); scanner.nextLine();
        if(books.get(numberBook - 1).getCount()>0){
            books.get(numberBook - 1).setCount(books.get(numberBook - 1).getCount()-1);
            history.setBook(books.get(numberBook - 1));
            readerManager.printListReaders(readers);
            System.out.print("enter reader from list: ");
            int numberReader = InputProtection.intInput(0, readers.size());///scanner.nextInt(); scanner.nextLine();
            history.setReader(readers.get (numberReader-1));
            history.setTakeOutBook(new GregorianCalendar().getTime());
            return history;
        }else{
            System.out.println("All books are taken");
            return null;
        }
    }
    public void printListReadingBooks(List<History> histories){
        System.out.println("---List reading books---");
        for (int i = 0; i < histories.size(); i++) {
            if(histories.get(i).getReturnBook() == null){
            System.out.printf("%d. %s. read %s %s",
                    i+1,
                    histories.get(i).getBook().getTitle(),
                    histories.get(i).getReader().getFirstname(),
                    histories.get(i).getReader().getLastname()
            ); 
        }
    }
}

    public void returnBook(List<History>  histories) {
        System.out.println("----vozvrat knigi----");
        this.printListReadingBooks(histories);
        System.out.println("enter number book: ");
        int numberReturnBook = InputProtection.intInput(1, histories.size());
        if(histories.get(numberReturnBook-1).getBook().getCount() <
                histories.get(numberReturnBook-1).getBook().getQuantity()){
             histories.get(numberReturnBook -1).getBook().setCount(histories.get(numberReturnBook -1).getBook().getCount());
             histories.get(numberReturnBook -1).setReturnBook(new GregorianCalendar().getTime());
        }
        else{
            System.out.println("All books already stock");
        }
    }

    public void bookRating(List<History> histories) {
        Map<Book,Integer> mapRatingBook= new HashMap<>();
        for (int i = 0; i < histories.size(); i++) {
            if (mapRatingBook.containsKey(histories.get(i).getBook())){      
            mapRatingBook.put(histories.get(i).getBook(), mapRatingBook.get(histories.get(i).getBook())+1);
            }else{
                mapRatingBook.put(histories.get(i).getBook(), 1);
            }
        }
        Map<Book, Integer> sortedMapRatingBook = mapRatingBook.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1,e2) ->e1,
                LinkedHashMap::new));
        int n = 1;
        for (Map.Entry<Book, Integer> entry : mapRatingBook.entrySet()) {
            System.out.printf("%d. %s: %d",
                    n,
                    entry.getKey().getTitle(),
                    entry.getValue());
            n++;
        }
    }
}