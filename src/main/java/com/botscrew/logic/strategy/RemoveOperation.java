package com.botscrew.logic.strategy;

import com.botscrew.database.services.BookService;
import com.botscrew.model.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RemoveOperation implements Strategy {

    public String doOperation(String input) {
        StringBuilder answer = new StringBuilder();

        try {
            BookService service = new BookService();
            List<Book> allBooks = service.getBookByName(input.substring(6).trim());

            if (allBooks.size() == 1) {

                service.removeBook(allBooks.get(0));
                answer.append(allBooks.get(0)).append(" was removed");

            } else if (allBooks.size() > 1) {

                Book toRemove = chooseBookToDelete(allBooks);
                service.removeBook(toRemove);
                answer.append(toRemove).append(" was removed");

            } else {
                answer.append("There are no books with this name.");
            }
        } catch (SQLException e) {
            return "Something went wrong, try again :)";
        }

        return answer.toString();
    }

    private Book chooseBookToDelete(List<Book> books) {

        System.out.println("We have few books with such name please choose one by typing a number of book:");
        for (Book book : books) {
            System.out.println(book.getId() + ". " + book.toString());
        }

        List<Integer> bookId = books.stream().map(Book::getId).collect(Collectors.toList());

        int idToRemove = readNumber();
        while (!bookId.contains(idToRemove)) {
            System.out.println("This number of book does not exist.");
            idToRemove = readNumber();
        }

        int finalIdToRemove = idToRemove;
        Optional<Book> bookToRemove = books.stream().filter(x -> x.getId() == finalIdToRemove).findAny();

        return bookToRemove.orElse(null);
    }

    private int readNumber() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of book: ");
        while (true) {
            try {
                int number = scanner.nextInt();
                return number;
            } catch (Exception e) {
                System.out.print("Must be integer value!\nTry Again: ");
                scanner.next();
            }
        }
    }
}
