package sepe.handlers;

@SuppressWarnings("serial")
public class EmailExistsException extends Throwable {

    public EmailExistsException(String message) {
        super(message);
    }
}
