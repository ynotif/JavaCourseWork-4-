package student.course.service;

import student.course.model.Souls;

import java.util.List;
import java.util.Optional;

public interface SoulsService {

    Souls addSoul(Souls souls);

    List<Souls> getAllSouls();

    Optional<Souls> getSoulById(Long id);

}
