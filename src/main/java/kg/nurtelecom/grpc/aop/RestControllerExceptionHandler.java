package kg.nurtelecom.grpc.aop;

import kg.nurtelecom.grpc.models.ErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestControllerExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorModel> handleException(Exception e) {
        e.printStackTrace();
        log.error(String.format("%s: %s; stack: %s", e.getClass().getName(), e.getMessage(), e.getStackTrace()[0]));
        return new ResponseEntity<>(new ErrorModel(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}