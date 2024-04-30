package student.course.service;


import student.course.model.Bosses;

import java.util.List;
import java.util.Optional;

public interface BossesService {

    Bosses createBosse(Bosses bosses);

    List<Bosses> getAllBosses();

    Optional<Bosses> getBosseById(Long id);

}
