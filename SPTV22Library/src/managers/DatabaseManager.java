/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Author;
import entity.Book;
import entity.History;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author admin
 */
public class DatabaseManager {
    private EntityManager em;

    public DatabaseManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SPTV22LibraryPU");
        this.em = emf.createEntityManager();
    }
    public void saveBook(Book book){
        try {
            em.getTransaction().begin();
            if(book.getId() == null){
                em.persist(book);
            }else{
                em.merge(book);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
    public void saveUser(User user){
        try {
            em.getTransaction().begin();
                if(user.getReader().getId() == null){
                    em.persist(user.getReader());
                }else{
                    em.merge(user.getReader());
                }
            if(user.getId() == null){
                em.persist(user);
            }else{
                em.merge(user);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Пользователь не сохранен: "+e);
        }
    }

    public List<Book> getListBooks() {
        return em.createQuery("SELECT book FROM Book book").getResultList();
    }

    List<User> getListUsers() {
        return em.createQuery("SELECT user FROM User user").getResultList();
    }
    public List<History> getReadingBooks(){
        return  em.createQuery("SELECT history FROM History history WHERE history.returnBook=null")
                .getResultList();
    }

    public User authorization(String login, String password) {
        try {
            return (User) em.createQuery("SELECT user FROM User user WHERE user.login = :login AND user.password = :password")
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Book getBook(Long id) {
        try {
            return em.find(Book.class, id);
        } catch (Exception e) {
            return null;
        }
    }
    public User getUser(Long id) {
        try {
            return em.find(User.class,id);
        } catch (Exception e) {
            return null;
        }
    }

    public void saveHistory(History history) {
        try {
            
            em.getTransaction().begin();
            if(history.getId() == null){
                em.persist(history);
            }else{
                em.merge(history);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    History getHistory(Long id) {
        try {
            return em.find(History.class,id);
        } catch (Exception e) {
            return null;
        }
    }

    List<History> getListHistories() {
        return em.createQuery("SELECT h FROM History h")
                .getResultList();
    }

    void saveAuthor(Author author) {
        try {
            em.getTransaction().begin();
            em.persist(author);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    Author getAuthor(String firstname, String lastname) {
        try {
            return (Author) em.createQuery("SELECT a FROM Author a WHERE a.fistname = :firstname AND a.lastname = :lastname")
                    .setParameter(firstname, firstname)
                    .setParameter("lastname", lastname)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}