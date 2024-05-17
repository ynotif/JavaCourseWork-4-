package student.course.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NPCNotFoundException extends Exception {

    private final Long id;

}
