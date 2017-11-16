package com.botscrew.logic;

import com.botscrew.logic.strategy.*;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Handler {

    private String getInput() throws Exception {

        Scanner scanner = new Scanner(System.in);
        String command;
        showUserHelp();
        command = scanner.nextLine();
        while (!isCorrectInput(command)) {

            if (command.equals("")) {
                throw new Exception("Exit program");
            }

            System.out.println("Wrong command! Try again :)");
            showUserHelp();
            command = scanner.nextLine();
        }
        return command;
    }

    private void showUserHelp() {
        System.out.println("Commands: ");
        System.out.println("\tadd {author} \"{name}\"\t- add book to library");
        System.out.println("\tedit {name}\t- set new name to book");
        System.out.println("\tremove {name}\t- remove book from library");
        System.out.println("\tall books\t- show all books in library");
        System.out.println("For exit click ENTER");
    }

    private boolean isCorrectInput(String command) {
        final Pattern
                addPattern = Pattern.compile("^(\\s*add\\s+)(.*?\".*?\")$"),
                removeEditPattern = Pattern.compile("^((\\s*remove\\s+)|(\\s*edit\\s+))+.*$"),
                getAllPattern = Pattern.compile("^\\s*all\\s+books\\s*$");

        return command != null && (addPattern.matcher(command).matches() || removeEditPattern.matcher(command).matches()
                || getAllPattern.matcher(command).matches());
    }

    private Strategy getStrategy(String command) {
        Strategy strategy = null;
        switch (command.trim().split(" ")[0]) {
            case "add":
                strategy = new AddOperation();
                break;
            case "edit":
                strategy = new EditOperation();
                break;
            case "remove":
                strategy = new RemoveOperation();
                break;
            case "all":
                strategy = new GetInfoOperation();
                break;
        }
        return strategy;
    }

    public void handle() {

        boolean isExit = false;
        while (!isExit) {
            try {
                String userLine = getInput();

                Strategy strategy = getStrategy(userLine);
                System.out.println(strategy.doOperation(userLine));
            } catch (Exception e) {
                isExit = true;
            }
        }

    }
}
