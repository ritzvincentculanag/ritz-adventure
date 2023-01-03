package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntegerInput(String message) {
        System.out.print(message);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }

        return 0;
    }

    public static String getStringInputLine(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static String getStringInput(String message) {
        System.out.print(message);
        return scanner.next();
    }

    public static void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }
}
