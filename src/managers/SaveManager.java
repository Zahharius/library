/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Book;
import entity.History;
import entity.Reader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class SaveManager {
    private final String BOOK_FILENAME = "books";
    private final String READER_FILENAME = "readers";
    private final String HISTORY_FILENAME = "histories";
    
    
    public Book[] loadBooks(){
        Book[] books = new Book[0];
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream(BOOK_FILENAME);
            ois = new ObjectInputStream(fis);
            books = (Book[]) ois.readObject();
        } catch (FileNotFoundException ex) {
            System.out.printf("file \"%s\" not found!%n",BOOK_FILENAME);
        } catch (IOException ex) {
            System.out.println("Error I/O!");
        } catch (ClassNotFoundException ex) {
            System.out.printf("class \"%s\" not found!%n",BOOK_FILENAME);
        }
        return books;
    }

    public void saveBooks(Book[] books) {
        ObjectOutputStream oos;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(BOOK_FILENAME);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(books);
            oos.flush();
        } catch (FileNotFoundException ex) {
            System.out.printf("file \"%s\" not found!%n",BOOK_FILENAME);
        } catch (IOException ex) {
            System.out.println("Error I/O!");
        }
    }
    
    
    
    public Reader[] loadReaders(){
        Reader[] readers = new Reader[0];
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream(READER_FILENAME);
            ois = new ObjectInputStream(fis);
            readers = (Reader[]) ois.readObject();
        } catch (FileNotFoundException ex) {
            System.out.printf("file \"%s\" not found!%n",READER_FILENAME);
        } catch (IOException ex) {
            System.out.println("Error I/O!");
        } catch (ClassNotFoundException ex) {
            System.out.printf("class \"%s\" not found!%n",READER_FILENAME);
        }
        return readers;
    }

    public void saveReaders(Reader[] readers) {
        ObjectOutputStream oos;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(READER_FILENAME);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(readers);
            oos.flush();
        } catch (FileNotFoundException ex) {
            System.out.printf("file \"%s\" not found!%n",READER_FILENAME);
        } catch (IOException ex) {
            System.out.println("Error I/O!");
        }
    }

   
}
