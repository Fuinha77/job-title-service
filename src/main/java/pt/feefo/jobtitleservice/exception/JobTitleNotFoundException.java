package pt.feefo.jobtitleservice.exception;

import pt.feefo.jobtitleservice.exception.repository.NotFoundException;

public class JobTitleNotFoundException extends NotFoundException {

    public JobTitleNotFoundException() {
        super("No matches found, please try a different job.");
    }

    public JobTitleNotFoundException(final String msg) {
        super(String.format("Job %s didn't have any match, please try a different one.", msg));
    }

}
