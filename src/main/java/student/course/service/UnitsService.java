package student.course.service;

import student.course.model.Units;

import java.util.List;
import java.util.Optional;

public interface UnitsService {

    Units createUnit(Units units);

    List<Units> getAllUnits();

    Optional<Units> getUnitById(Long id);

    void updateUnit(Units units);

    void deleteUnit(Long id);
}
