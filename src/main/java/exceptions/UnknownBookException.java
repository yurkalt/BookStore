package exceptions;

/**
 * Created by Iurii on 28.10.2016.
 */
public class UnknownBookException extends Exception {
    public UnknownBookException(String message) {
        super(message);
    }
}
