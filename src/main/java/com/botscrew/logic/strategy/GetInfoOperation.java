package com.botscrew.logic.strategy;

import com.botscrew.database.services.BookService;
import com.botscrew.model.Book;

import java.sql.SQLException;
import java.util.List;

public class GetInfoOperation implements Strategy {

    public String doOperation(String input) {
        StringBuilder answer = new StringBuilder("Our books:\n\r");

        try{
            BookService service = new BookService();
            List<Book> allBooks = service.getAllBooks();
            for(Book book : allBooks){
                answer.append(book.toString()).append("\n\r");
            }

            answer = new StringBuilder("Library is empty");
        } catch (SQLException e) {
            return "Something went wrong, try again :)";
        }

        return answer.toString();
    }
}
