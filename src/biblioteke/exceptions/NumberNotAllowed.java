package biblioteke.exceptions;

public class NumberNotAllowed extends RuntimeException {
    public NumberNotAllowed() {
        super("Kjo fushe nuk mund te permbaje numer!");
    }
}
