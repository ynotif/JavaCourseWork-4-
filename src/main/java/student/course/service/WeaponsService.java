package student.course.service;

import student.course.exceptions.WeaponNotFoundException;
import student.course.model.Weapons;

import java.util.List;
import java.util.Optional;

public interface WeaponsService {

    Weapons createWeapon(Weapons weapons);

    List<Weapons> getAllWeapons();

    Optional<Weapons> getWeaponById(Long id) throws WeaponNotFoundException;

    void updateWeapon(Weapons weapons) throws WeaponNotFoundException;

    void deleteWeaponById(Long id) throws WeaponNotFoundException;

    Weapons addSoulToWeapon(Long weaponId, Long soulId);

    Weapons removeSoulFromWeapon(Long weaponId, Long soulId);
}
