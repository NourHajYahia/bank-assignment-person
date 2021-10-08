package microservice.core.person.advice;

import microservice.core.person.exceptions.PersonSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * AOP that catch each thrown exception from,
 * PersonSystemException and MethodArgumentNotValidException classes and handles them to sustain cleaner code
 */
@ControllerAdvice
@RestController
public class PersonControllerAdvise {

    /**
     * PersonSystemException handler
     *
     * @param exception thrown exception from controller that is instance of PersonSystemException
     * @return ErrorDetails
     */
    @ExceptionHandler(value = {PersonSystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails exceptionHandler(Exception exception) {
        Map<String, String> error = new HashMap<>();
        error.put(((PersonSystemException) exception).getParam(), exception.getMessage());
        return new ErrorDetails("System Service Exception", error);
    }

    /**
     * MethodArgumentNotValidException handler
     *
     * @param exception thrown exception from controller that is instance of MethodArgumentNotValidException
     * @return ErrorDetails
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleValidationExceptions(
            MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ErrorDetails("System Validation Exception", errors);
    }

}
