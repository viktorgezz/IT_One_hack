package ru.viktorgezz.project1.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.viktorgezz.project1.exception.ErrorResponse;
import ru.viktorgezz.project1.exception.NotCreatedException;

import java.util.List;

@Component
public class ValidCashFlow {
    public void checkError(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new NotCreatedException(errorMsg.toString());
        }
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleExpense(NotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
