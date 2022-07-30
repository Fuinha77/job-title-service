package pt.feefo.jobtitleservice.exception.repository;

public interface ServiceException {

    int getStatusCode();

    String getMessage();
}

