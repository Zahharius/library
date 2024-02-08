/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sptv22library;

import managers.HistoryManager;
import managers.ReaderManager;
import entity.History;
import entity.User;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Scanner;
import managers.BookManager;
import managers.DatabaseManager;
import tools.InputProtection;
import tools.PassEncrypt;

/**
 *
 * @author admin
 */
public class App {
    public static User user;
    private final Scanner scanner; 
    //private List<Book> books;
    //private List<User> users;
    //private List<History> histories;
    
    private final BookManager bookManager;
    private final ReaderManager readerManager;
    private final HistoryManager historyManager;
    //private final SaveManager saveManager;
    private final DatabaseManager databaseManager;

    public App() {
        this.scanner = new Scanner(System.in);
//        this.saveManager = new SaveManager();
        this.databaseManager = new DatabaseManager();
       // this.books = saveManager.loadBooks();
        //this.users = saveManager.loadUsers();
        //this.histories = saveManager.loadHistories();
        this.bookManager = new BookManager(scanner);
        this.readerManager = new ReaderManager(scanner);
        this.historyManager = new HistoryManager(scanner,readerManager,bookManager);
    }
    
    
    
    public void run() throws InvalidKeySpecException {
        System.out.println("If you have a login and password press y, otherwise press n");
        String word = scanner.nextLine();
        if("n".equals(word)){
            databaseManager.saveUser(readerManager.addReader());
        }
        for(int attempt=0;attempt<3;attempt++){
            System.out.print("Please enter your login: ");
            String login = scanner.nextLine();
            System.out.print("Please enter your password: ");
            String password = scanner.nextLine();
            PassEncrypt pe =new PassEncrypt();
            String encryptPassword = pe.getEncryptPassword(password, pe.getSalt());
            App.user = databaseManager.authorization(login, encryptPassword);
            if(App.user != null){
                break;
            }
            System.out.println("Invalid login or password");
        }
        if(App.user == null) return;
        System.out.printf("Hello %s %s, welcome to the library%n",App.user.getReader().getFirstname(),App.user.getReader().getLastname());
        boolean repeat = true;
        System.out.println("------- Library -------");
        do{
            System.out.println("List tasks:");
            System.out.println("0. Exit");
            System.out.println("1. Add new book");
            System.out.println("2. Print list books");
            System.out.println("3. Add new reader");
            System.out.println("4. Print list readers");
            System.out.println("5. Take out book");
            System.out.println("6. Print list reading books");
            System.out.println("7. Return book");
            System.out.println("8. Book rating");
            System.out.println("9. User rating");
            System.out.print("Enter task number: ");
            int task = InputProtection.intInput(0,9); 
            System.out.printf("You select task %d, for exit press \"0\", to continue press \"1\": ",task);
            int toContinue = InputProtection.intInput(0,1);
            if(toContinue == 0) continue;
            switch (task) {
                case 0:
                    repeat = false;
                    break;
                case 1:
                    bookManager.addBook(databaseManager);
                    break;
                case 2:
                    bookManager.printListBooks(databaseManager);
                    break;
                case 3:
                    databaseManager.saveUser(readerManager.addReader());
                    break;
                case 4:
                    readerManager.printListUserss(databaseManager);
                    break;
                case 5:
                    historyManager.takeOutBook(databaseManager);
                    break;
                case 6:
                    historyManager.printListReadingBooks(databaseManager);
                    break;
                case 7:
                    historyManager.returnBook(databaseManager);
                    break;
                case 8:
                    historyManager.bookRating(databaseManager);
                    break;
                case 9:
                    historyManager.userRating(databaseManager);
                    break;
                default:
                    System.out.println("Select from list tasks!");
            }
            System.out.println("-----------------------");
        }while(repeat);
        System.out.println("Bye-bye!");
    }
    
}
