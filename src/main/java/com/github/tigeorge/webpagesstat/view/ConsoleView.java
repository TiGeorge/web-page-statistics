package com.github.tigeorge.webpagesstat.view;

import java.util.Scanner;

public class ConsoleView {

    static final Scanner scanner = new Scanner(System.in);

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        return scanner.nextLine();
    }


}
