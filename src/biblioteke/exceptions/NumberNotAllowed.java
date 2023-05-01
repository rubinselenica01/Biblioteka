package biblioteke.exceptions;

public class NumberNotAllowed extends RuntimeException {
    public NumberNotAllowed(String pershkruesPermbajtje) {
        super(pershkruesPermbajtje);
    }
}
