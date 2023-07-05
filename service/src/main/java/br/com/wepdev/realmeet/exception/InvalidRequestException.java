package br.com.wepdev.realmeet.exception;

import br.com.wepdev.realmeet.validator.ValidationErros;

public class InvalidRequestException extends RuntimeException{

    private final ValidationErros validationErrors;


    public InvalidRequestException(ValidationErros validationErrors) {
        super(validationErrors.toString());
        this.validationErrors = validationErrors;
    }

    public ValidationErros getValidationErrors() {
        return validationErrors;
    }
}
