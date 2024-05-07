package student.course.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class MagicHandlerException {

    @ExceptionHandler(MagicNotFoundException.class)
    public ResponseEntity<ErrorResponse> magicHandlerException(MagicNotFoundException e){
        return ResponseEntity.badRequest().body(
                new ErrorResponse(
                        400L,
                        "Не найдена магия с таким id: " + e.getId()
                )
        );
    }

}
