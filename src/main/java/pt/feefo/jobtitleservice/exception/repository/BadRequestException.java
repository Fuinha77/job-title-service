package pt.feefo.jobtitleservice.exception.repository;


public class BadRequestException extends RuntimeException implements ServiceException {

    private static final long serialVersionUID = -3489319829242452513L;

    public BadRequestException() {
        super("Not found!");
    }

    public BadRequestException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return 404;
    }

}