package com.github.tigeorge.webpagesstat.view;

import java.util.Scanner;

/*
* Console Assistant Class
*/
public class ConsoleView {

    private static final Scanner scanner = new Scanner(System.in);

    /*
    * Message output to the console
    */
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    /*
    * Enters lines from the console
    */
    public static String readString() {
        return scanner.nextLine();
    }


}
