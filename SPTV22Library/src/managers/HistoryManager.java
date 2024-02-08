/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import sptv22library.App;
import tools.InputProtection;

/**
 *
 * @author admin
 */
public class HistoryManager {

    private final Scanner scanner;
    private final BookManager bookManager;
    private final ReaderManager readerManager;

    public HistoryManager(
            Scanner scanner, 
            ReaderManager readerManager, 
            BookManager bookManager) {
        this.scanner = scanner;
        this.bookManager = bookManager;
        this.readerManager = readerManager;
    }
    /**
     * Логика работы метода
     * 1. список книг
     * 2. пользователь вводит номер книги (id)
     * 3. получаем из базы книгу по id и записываем ее в историю
     * 4. список читателей
     * 5. пользователь вводит номер читателя (id)
     * 6. получаем из базы пользователя по id и записываем его в историю
     * 7. записываем в историю дату выдачи книги
     * 8. сохраняем полученную историю в базу
     * @param databaseManager
     * 
     */
    public void takeOutBook(DatabaseManager databaseManager) {
        History history = new History();
        bookManager.printListBooks(databaseManager);
        System.out.print("Enter number book from list: ");
        int numberBook = InputProtection.intInput(1, null); 
        Book book = databaseManager.getBook((long)numberBook);//Ставим book под управление em
        if(book.getCount() > 0){
            book.setCount(book.getCount() - 1);
            databaseManager.saveBook(book); //Сохраняем изменненную книгу в базу
            history.setBook(book);
            history.setUser(databaseManager.getUser(App.user.getId()));//Ставим App.user под управление em
            history.setTakeOutBook(new GregorianCalendar().getTime());
            databaseManager.saveHistory(history);
        }else{
            System.out.println("All books are taken");
        }
    }

    public int printListReadingBooks(DatabaseManager databaseManager) {
        System.out.println("----- List reading books -----");
        List<History> histories = databaseManager.getReadingBooks();
        for (int i = 0; i < histories.size(); i++) {
            System.out.printf("%d. %s. read %s %s%n",
                    histories.get(i).getId(),
                    histories.get(i).getBook().getTitle(),
                    histories.get(i).getUser().getReader().getFirstname(),
                    histories.get(i).getUser().getReader().getLastname()
            );
        }
        return histories.size();
    }

    public void returnBook(DatabaseManager databaseManager) {
        System.out.println("Return book:");
        this.printListReadingBooks(databaseManager);
        System.out.println("Enter number book: ");
        int idReturnBookHistory = InputProtection.intInput(1, null);
        History history = databaseManager.getHistory((long)idReturnBookHistory);
        if(history.getBook().getCount() 
                < history.getBook().getQuantity()){
            history.getBook().setCount(history.getBook().getCount() + 1);
            history.setReturnBook(new GregorianCalendar().getTime());
            databaseManager.saveHistory(history);
        }else{
            System.out.println("All copies already in stock");
        }
    }

    public void bookRating(DatabaseManager databaseManager) {
        List<History> histories = databaseManager.getListHistories();
        Map<Book,Integer> mapRatingBook = new HashMap<>();
        for (int i = 0; i < histories.size(); i++) {
            if(mapRatingBook.containsKey(histories.get(i).getBook())){
                mapRatingBook.put(histories.get(i).getBook(), mapRatingBook.get(histories.get(i).getBook())+1);
            }else{
                mapRatingBook.put(histories.get(i).getBook(),1);
            }
        }
        Map<Book, Integer> sortedMapRatingBook = mapRatingBook.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        int n = 1;
        for (Map.Entry<Book, Integer> entry : sortedMapRatingBook.entrySet()) {
            System.out.printf("%d. %s: %d%n",
                    n,
                    entry.getKey().getTitle(),
                    entry.getValue()
            );
            n++;
        }
    }
    public void userRating(DatabaseManager databaseManager) {
        List<History> histories = databaseManager.getListHistories();
        Map<User,Integer> mapRatingUser = new HashMap<>();
        for (int i = 0; i < histories.size(); i++) {
            if(mapRatingUser.containsKey(histories.get(i).getBook())){
                mapRatingUser.put(histories.get(i).getUser(), mapRatingUser.get(histories.get(i).getUser())+1);
            }else{
                mapRatingUser.put(histories.get(i).getUser(),1);
            }
        }
        Map<User, Integer> sortedMapRatingBook = mapRatingUser.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        int n = 1;
        for (Map.Entry<User, Integer> entry : sortedMapRatingBook.entrySet()) {
            System.out.printf("%d. %s: %d%n",
                    n,
                    entry.getKey().getLogin(),
                    entry.getValue()
            );
            n++;
        }
    }
    
}