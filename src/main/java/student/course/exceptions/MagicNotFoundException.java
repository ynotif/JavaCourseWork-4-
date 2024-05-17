package student.course.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MagicNotFoundException extends Exception{

    private final Long id;

}
