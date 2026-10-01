package framework.util;

import java.util.Scanner;
import java.util.OptionalInt;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static class EndOfInputException extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    public static String input(String info) {
        System.out.print(info);
        if (!scanner.hasNextLine()) {
            throw new EndOfInputException();
        }
        return scanner.nextLine().trim();
    }

    public static OptionalInt parseInteger(String value) {
        try {
            return OptionalInt.of(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            return OptionalInt.empty();
        }
    }
}