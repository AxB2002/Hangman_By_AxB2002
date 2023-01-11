package hangedMan;

import java.util.Scanner;

public class TerminalReader {
    private static final Scanner sc = new Scanner(System.in);

    public static int readInt(String s) {
        int number = 0;
        boolean converted = false;
        do {
            TerminalPrinter.print(s);
            String inputTxt = sc.next();
            try {
                number = Integer.parseInt(inputTxt);
                converted = true;
            } catch (NumberFormatException e) {
                TerminalPrinter.println("That's not a number");
            }
        } while (!converted);
        return number;
    }

    public static char readChar(String s) {
        TerminalPrinter.print(s);
        String inputTxt = sc.next();
        return inputTxt.charAt(0);
    }
}