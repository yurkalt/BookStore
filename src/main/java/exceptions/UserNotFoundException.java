package exceptions;

/**
 * Created by Iurii on 07.11.2016.
 */
public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String message) {
        super(message);
    }
}
