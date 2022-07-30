package pt.feefo.jobtitleservice.exception.repository;


public class NotFoundException extends RuntimeException implements ServiceException {

    private static final long serialVersionUID = -3489319829242452513L;

    public NotFoundException() {
        super("Not found!");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return 404;
    }

}