package Task9.Task4;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Введите интервал в секундах: ");

        TimePrinter timePrinter = new TimePrinter(scanner.nextLong());
        timePrinter.start();
    }
}
