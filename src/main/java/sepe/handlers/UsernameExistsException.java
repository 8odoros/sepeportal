package sepe.handlers;

@SuppressWarnings("serial")
public class UsernameExistsException extends Throwable {

    public UsernameExistsException(String message) {
        super(message);
    }
}

