package student.course.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class NPCHandlerException {

    @ExceptionHandler(NPCNotFoundException.class)
    public ResponseEntity<ErrorResponse> npcHandlerException(NPCNotFoundException e){
        return ResponseEntity.badRequest().body(
                new ErrorResponse(
                        400L,
                        "Не найден npc с таким id: " + e.getId()
                )
        );
    }

}
