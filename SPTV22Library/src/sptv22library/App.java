/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sptv22library;
import managers.SaveManager;
import managers.HistoryManager;
import entity.Reader;
import entity.Author;
import entity.Book;
import entity.History;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import managers.BookManager;
import managers.HistoryManager;
import managers.ReaderManager;
import tools.InputProtection;

/**
 *
 * @author admin
 */
public class App {
    private final Scanner scanner;
    private List<Book> books;
    private List<Reader> readers;
    private List<History> histories;
    private final BookManager bookManager;
    private final HistoryManager historyManager;
    private final ReaderManager readerManager;
    private final SaveManager saveManager;
    
    public App() {
        this.scanner = new Scanner(System.in);
        this.saveManager = new SaveManager();
        this.books = saveManager.loadBooks();
        this.histories = saveManager.loadHistories();
        this.bookManager= new BookManager(scanner);
        this.readerManager= new ReaderManager(scanner);
        this.readers = saveManager.loadReaders();
        this.historyManager = new HistoryManager(scanner, readerManager, bookManager);
    }
    
    
    
    public void run(){
        boolean repeat = true;
        System.out.println("--- Library ---");
        do{
            System.out.println("List of tasks:");
            System.out.println("0-exit");
            System.out.println("1-add new book");
            System.out.println("2-print list of books");
            System.out.println("3-add new reader");
            System.out.println("4-print list of readers");
            System.out.println("5-take out book");
            System.out.println("6-print taken books");
            System.out.println("7-return book");
            System.out.println("8-book rating");
            System.out.print("enter task number: ");
            int task = InputProtection.intInput(0,8);
            System.out.printf("You select task %d, for exit press \"0\", to continue press \"1\": ",task);
            int toCont = InputProtection.intInput(0,1);
            if(toCont ==0 )continue;
            switch (task) {
                case 0:
                    repeat=false;
                    break;
                case 1:
                    this.books.add(bookManager.addBook());
                    saveManager.saveBooks(this.books);
                    break;
                case 2:
                    bookManager.printListBooks(books);
                    break;
                case 3:
                    this.readers.add(readerManager.addReader());
                    saveManager.saveReaders(this.readers);
                    break;
                case 4:
                    readerManager.printListReaders(readers);
                    break;
                case 5:
                    History history =historyManager.TakeOutBook(books, readers);
                    if (history != null){
                    this.histories.add(history);
                    saveManager.saveHistories(histories);
                    }
                    break;
                case 6:
                    historyManager.printListReadingBooks(histories);
                    saveManager.saveHistories(histories);
                    break;
                 case 7:
                    historyManager.returnBook(histories);
                    saveManager.saveHistories(histories);
                    break;
                 case 8:
                    historyManager.bookRating(this.histories);
                    break; 
                default:
                    System.out.println("choose task FROM THE LIST ");
            }
            System.out.println("------------------------------");
    }while(repeat);
        System.out.println("Bye-bye!");
}
}