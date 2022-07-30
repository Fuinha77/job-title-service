package pt.feefo.jobtitleservice.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import pt.feefo.jobtitleservice.config.commons.BasicResponseDTO;
import pt.feefo.jobtitleservice.exception.repository.BadRequestException;
import pt.feefo.jobtitleservice.exception.repository.NotFoundException;

@Slf4j
@ControllerAdvice
public class ControllerAdviceDeclaration {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<BasicResponseDTO> notFoundException(final NotFoundException ex, final ServletWebRequest request) {
        return this.basicResponseDTONotFoundException(ex, request);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<BasicResponseDTO> notFoundException(final BadRequestException ex, final ServletWebRequest request) {
        return this.basicResponseDTONotFoundException(ex, request);
    }


    private ResponseEntity<BasicResponseDTO> basicResponseDTONotFoundException(final Exception ex, final ServletWebRequest request) {
        return new ResponseEntity<>(BasicResponseDTO.fail(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequest().getRequestURI()
        ), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<BasicResponseDTO> basicResponseDTOBadRequestException(final Exception ex, final ServletWebRequest request) {
        return new ResponseEntity<>(BasicResponseDTO.fail(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getRequest().getRequestURI()
        ), HttpStatus.BAD_REQUEST);
    }

}
