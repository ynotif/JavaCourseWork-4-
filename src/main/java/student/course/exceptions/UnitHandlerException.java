package student.course.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class UnitHandlerException {

    @ExceptionHandler(UnitNotFoundException.class)
    public ResponseEntity<ErrorResponse> unitHandlerException(UnitNotFoundException e){
        return ResponseEntity.badRequest().body(
                new ErrorResponse(
                        400L,
                        "Не найден юнит с данным id: "+e.getId()
                )
        );
    }

}
