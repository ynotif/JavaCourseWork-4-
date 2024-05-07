package student.course.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class ArmorExceptionHandler {

    @ExceptionHandler(ArmorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleArmorNotFoundException(ArmorNotFoundException e) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse(
                        400L,
                        "Нет брони с таким id: " + e.getId()
                )
        );
    }

}
