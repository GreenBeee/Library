package com.botscrew.database.services;

import com.botscrew.database.dao.implementation.BookDaoImpl;
import com.botscrew.model.Book;

import java.sql.SQLException;
import java.util.List;

public class BookService {

    public void addBook(Book book) throws SQLException{
        new BookDaoImpl().addBook(book);
    }

    public void updateBook(Book book) throws SQLException{
        new BookDaoImpl().updateBook(book);
    }

    public void removeBook(Book book) throws SQLException{
        new BookDaoImpl().removeBook(book);
    }

    public List<Book> getBookByName(String name) throws SQLException{
        return new BookDaoImpl().getBookByName(name);
    }

    public List<Book> getAllBooks() throws SQLException{
        return new BookDaoImpl().getAllBooks();
    }
}
