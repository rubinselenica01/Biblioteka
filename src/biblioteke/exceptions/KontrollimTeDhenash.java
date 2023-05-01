package biblioteke.exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public interface KontrollimTeDhenash {


    static void kontrolloISBN(String ISBN) throws ISBNException {
        if (ISBN.length() != 17) {
            throw new ISBNException();
        }
    }

    static void kontrolloVitinBotimit(int vitBotimi) throws InputMismatchException {
        if (Integer.toString(vitBotimi).length() != 4 || vitBotimi > 2023) {
            throw new InputMismatchException("Nuk eshte vit botimi!");
        }
    }

    static String kontrolloNumerNeString() throws NumberNotAllowed {
        Scanner scanner = new Scanner(System.in);
        String stringRikthimi = scanner.nextLine().trim().toLowerCase();
        for (Character character : stringRikthimi.toCharArray()) {
            if (Character.isDigit(character)) {
                throw new NumberNotAllowed();
            }
        }
        return stringRikthimi.substring(0, 1).toUpperCase() + stringRikthimi.substring(1);

    }
}
