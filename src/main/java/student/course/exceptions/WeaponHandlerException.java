package student.course.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class WeaponHandlerException {

    @ExceptionHandler(WeaponNotFoundException.class)
    public ResponseEntity<ErrorResponse> weaponHandlerException(WeaponNotFoundException e){
        return ResponseEntity.badRequest().body(
                new ErrorResponse(
                        400L,
                        "Не найдено оружие с таким id: " + e.getId()
                )
        );
    }

}
