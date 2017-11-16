package com.botscrew.database.dao;

import com.botscrew.model.Book;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface BookDao {

    void addBook(Book book) throws SQLException;
    void updateBook(Book book) throws SQLException;
    List getBookByName(String name) throws SQLException;
    List<Book> getAllBooks() throws SQLException;
    void removeBook(Book book) throws SQLException;
}
