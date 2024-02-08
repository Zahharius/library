/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Reader;
import entity.User;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Scanner;
import tools.PassEncrypt;

/**
 *
 * @author admin
 */
public class ReaderManager {
    private final Scanner scanner;

    public ReaderManager(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public User addReader() throws InvalidKeySpecException {
        
        Reader reader = new Reader();
        System.out.println("----- Add reader -----");
        System.out.print("Firstname: ");
        reader.setFirstname(scanner.nextLine());
        System.out.print("Lastname: ");
        reader.setLastname(scanner.nextLine());
        System.out.print("Phone: ");
        reader.setPhone(scanner.nextLine());
        User user = new User();
        System.out.print("Login: ");
        user.setLogin(scanner.nextLine());
        System.out.print("Password: ");
        PassEncrypt pe = new PassEncrypt();
        user.setPassword(pe.getEncryptPassword(scanner.nextLine(),pe.getSalt()));
        user.setReader(reader);
        System.out.println("New reader added!");
        return user;
    }

    public void printListUserss(DatabaseManager databaseManager) {
        System.out.println("----- List readers -----");
        List<User> users = databaseManager.getListUsers();
        for (int i = 0; i < users.size(); i++) {
            System.out.printf("%d. %s %s. Login: %s (phone: %s)%n",
                    i+1,
                    users.get(i).getReader().getFirstname(),
                    users.get(i).getReader().getLastname(),
                    users.get(i).getLogin(),
                    users.get(i).getReader().getPhone()
            );
        }
    }

    

    
    
}