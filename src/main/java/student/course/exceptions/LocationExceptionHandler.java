package student.course.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class LocationExceptionHandler {

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<ErrorResponse> locationExceptionHandler(LocationNotFoundException e){
        return ResponseEntity.badRequest().body(
                new ErrorResponse(
                        400L,
                        "Нет локации с таким id: " + e.getId()
                )
        );
    }

}
