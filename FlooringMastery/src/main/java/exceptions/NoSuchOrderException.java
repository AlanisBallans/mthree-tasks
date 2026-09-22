package exceptions;

public class NoSuchOrderException extends Exception {

    public NoSuchOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchOrderException(String message) {
        super(message);
    }
}
