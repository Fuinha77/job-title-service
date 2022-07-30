package pt.feefo.jobtitleservice.exception;

import pt.feefo.jobtitleservice.exception.repository.NotFoundException;

public class JobStringNullException extends NotFoundException {

    public JobStringNullException() {
        super("Job is null, please rewrite it again.");
    }

}
