package student.course.service;

import student.course.model.Magics;
import java.util.List;
import java.util.Optional;

public interface MagicsService {

    Magics createMagic(Magics magics);

    List<Magics> getAllMagics();

    Optional<Magics> getMagicById(Long id);

}
