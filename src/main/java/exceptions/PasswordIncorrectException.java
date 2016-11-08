package exceptions;

/**
 * Created by Iurii on 08.11.2016.
 */
public class PasswordIncorrectException extends Throwable {
    public PasswordIncorrectException(String message) {
        super(message);
    }
}
