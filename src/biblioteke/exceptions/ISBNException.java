package biblioteke.exceptions;

public class ISBNException extends RuntimeException {
    public ISBNException() {
        super("""
                Gjatesia e ISBN tende nuk eshte e duhur!
                Gjatesia e ISBN eshte fikse! Ajo eshte 13!""");
    }
}
