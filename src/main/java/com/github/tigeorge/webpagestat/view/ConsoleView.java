package com.github.tigeorge.webpagestat.view;

import java.util.Scanner;

public class ConsoleView {

    private static final Scanner scanner = new Scanner(System.in);

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        return scanner.nextLine();
    }
}
