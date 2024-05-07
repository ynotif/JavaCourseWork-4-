package student.course.service;

import student.course.exceptions.SoulNotFoundException;
import student.course.model.Souls;

import java.util.List;
import java.util.Optional;

public interface SoulsService {

    Souls createSoul(Souls souls);

    List<Souls> getAllSouls();

    Optional<Souls> getSoulById(Long id) throws SoulNotFoundException;

    void updateSoul(Souls souls) throws SoulNotFoundException;

    void deleteSoulById(Long id) throws SoulNotFoundException;
}
