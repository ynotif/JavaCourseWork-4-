package student.course.service;

import student.course.model.Weapons;

import java.util.List;
import java.util.Optional;

public interface WeaponsService {

    Weapons createWeapon(Weapons weapons);

    List<Weapons> getAllWeapons();

    Optional<Weapons> getWeaponById(Long id);

    void updateWeapon(Weapons weapons);

    void deleteWeaponById(Long id);
}
