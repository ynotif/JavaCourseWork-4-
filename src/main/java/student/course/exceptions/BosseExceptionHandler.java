package student.course.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class BosseExceptionHandler {

    @ExceptionHandler(BosseNotFoundException.class)
    public ResponseEntity<ErrorResponse> bosseHandleException(BosseNotFoundException e){
        return ResponseEntity.badRequest().body(
                new ErrorResponse(
                        400L,
                        "Нет босса с таким id: " + e.getId()
                )
        );

    }

}
