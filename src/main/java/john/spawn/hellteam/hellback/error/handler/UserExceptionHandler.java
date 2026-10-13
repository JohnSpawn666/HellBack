package john.spawn.hellteam.hellback.error.handler;

import john.spawn.hellteam.hellback.error.FailLoginException;
import john.spawn.hellteam.hellback.error.PseudoAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class UserExceptionHandler {

    private final static String MESSAGE = "message";

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handlePseudoAlreadyExists(PseudoAlreadyExistException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleFailLogin(FailLoginException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(MESSAGE, ex.getMessage()));
    }


}
