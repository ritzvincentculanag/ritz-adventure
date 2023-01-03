package utils;

import java.io.*;

public class Dialog implements Printer {
    private static BufferedReader fileReader;

    @Override
    public void printFromFile(String fileName) {
        try {
            fileReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        try {
            String line = fileReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = fileReader.readLine();
            }

            fileReader.close();
        } catch (IOException e) {
            System.out.println("File not found!");
        }
    }

    @Override
    public void printCountdown(int duration, String message) {
        int timer = duration;

        System.out.println(message);
        while (timer > 0) {
            try {
                System.out.println(timer--);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void printSeparator() {
        System.out.println("==================================");
    }
}
