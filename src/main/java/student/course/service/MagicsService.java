package student.course.service;

import student.course.exceptions.MagicNotFoundException;
import student.course.model.Magics;
import java.util.List;
import java.util.Optional;

public interface MagicsService {

    Magics createMagic(Magics magics);

    List<Magics> getAllMagics();

    Optional<Magics> getMagicById(Long id) throws MagicNotFoundException;

    void updateMagic(Magics magics) throws MagicNotFoundException;

    void deleteMagicById(Long id) throws MagicNotFoundException;
}
