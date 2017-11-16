package com.botscrew.database.dao.implementation;

import com.botscrew.database.dao.BookDao;
import com.botscrew.model.Book;
import com.botscrew.database.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookDaoImpl implements BookDao {

    public void addBook(Book book) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.save(book);
        } catch (Exception e) {
            System.out.println("Cant do this action");
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateBook(Book book) throws SQLException {
        Session session = null;
        Transaction tx;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            tx = session.beginTransaction();
            session.update(book);
            tx.commit();

        } catch (Exception e) {
            System.out.println("Cant do this action");
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<Book> getBookByName(String name) throws SQLException {
        Session session = null;
        Query query;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            query = session.createQuery("from Book b where b.name = '" + name + "'");
            return new ArrayList<Book>(query.list());
        } catch (Exception e) {
            System.out.println("Cant do this action");
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }

    public List<Book> getAllBooks() throws SQLException {
        Session session = null;
        List<Book> books = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            books = session.createQuery("SELECT b FROM Book b order by b.name", Book.class).list();
        } catch (Exception e) {
            System.out.println("Cant do this action");
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return books;
    }

    public void removeBook(Book book) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.delete(book);

            Transaction t = session.beginTransaction();
            session.flush();
            t.commit();
        } catch (Exception e) {
            System.out.println("Cant do this action");
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
