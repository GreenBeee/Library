package com.botscrew.logic.strategy;

import com.botscrew.database.services.BookService;
import com.botscrew.model.Book;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddOperation implements Strategy {

    public String doOperation(String input) {

        Book toAdd = null;
        StringBuilder answer = new StringBuilder();

        Pattern authorPattern = Pattern.compile("add(.*?)\""),
                namePattern = Pattern.compile("\"(.*?)\"");

        Matcher authorMatcher = authorPattern.matcher(input);
        Matcher nameMatcher = namePattern.matcher(input);

        if (authorMatcher.find() && nameMatcher.find()) {
            toAdd = new Book();

            String author = authorMatcher.group(1).trim();
            String name = nameMatcher.group(1).trim();

            toAdd.setName(name);
            toAdd.setAuthor(author);
            answer.append("Book ").append(toAdd.getAuthor()).append(" \"").append(toAdd.getName()).append("\"").append(" was added");
        }

        try{
            BookService service = new BookService();
            service.addBook(toAdd);
        } catch (SQLException e) {
            return "Something went wrong, try again :)";
        }

        return answer.toString();

    }
}
