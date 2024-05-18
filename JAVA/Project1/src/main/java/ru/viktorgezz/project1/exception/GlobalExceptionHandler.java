package ru.viktorgezz.project1.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AccountErrorResponse> handleException(AccountNotFoundException e) {
        AccountErrorResponse response = new AccountErrorResponse(
                "Account with this id wasn't found",
                System.currentTimeMillis() / (1000 * 60)
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
