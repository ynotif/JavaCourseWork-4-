package student.course.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class SoulHandlerException {

    @ExceptionHandler(SoulNotFoundException.class)
    public ResponseEntity<ErrorResponse> soulHandlerException(SoulNotFoundException e){
        return ResponseEntity.badRequest().body(
                new ErrorResponse(
                        400L,
                        "Не найдена душа с таким id: " + e.getId()
                )
        );
    }

}
