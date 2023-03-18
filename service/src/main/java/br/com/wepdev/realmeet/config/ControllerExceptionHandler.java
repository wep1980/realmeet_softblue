package br.com.wepdev.realmeet.config;

import br.com.wepdev.realmeet.api.model.ResponseError;
import br.com.wepdev.realmeet.exception.SalaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(SalaNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception exception) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, exception);
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, Exception exception) {
        return new ResponseEntity<>(
            new ResponseError()
                .status(httpStatus.getReasonPhrase())
                .code(httpStatus.value())
                .message(exception.getMessage()),
            httpStatus
        );
    }
}
