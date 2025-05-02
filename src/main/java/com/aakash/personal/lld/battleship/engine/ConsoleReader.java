package com.aakash.personal.lld.battleship.engine;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleReader {

    private final Scanner scanner;

    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next(); // discard invalid input
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume the newline
        return value;
    }
}
