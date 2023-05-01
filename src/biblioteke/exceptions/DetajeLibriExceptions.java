package biblioteke.exceptions;

import java.util.InputMismatchException;

public interface DetajeLibriExceptions {
    static void permbanNumer(String variabelArg) throws NumberNotAllowed {
        for (Character character : variabelArg.toCharArray()) {
            if (Character.isDigit(character)) {
                throw new NumberNotAllowed("Kjo fushe nuk mund te permbaje numer!");
            }
        }
    }

    static void kontrolloISBN(String ISBN) {
        if (ISBN.length() != 17) {
            throw new ISBNException("""
                    Gjatesia e ISBN tende nuk eshte e duhur!
                    Gjatesia e ISBN eshte fikse! Ajo eshte 13!""");
        }
    }

    static void kontrolloVitinBotimit(int vitBotimi) throws InputMismatchException {
        if (Integer.toString(vitBotimi).length() != 4 || vitBotimi > 2023) {
            throw new InputMismatchException("Nuk eshte vit botimi!");
        }
    }
}
