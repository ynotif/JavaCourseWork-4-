package student.course.service;

import student.course.model.Units;

import java.util.List;
import java.util.Optional;

public interface UnitsService {

    Units createUnit(Units units);

    List<Units> getAllUnits();

    Optional<Units> getUnitById(Long id);

    void updateUnit(Units units);

    void deleteUnitById(Long id);

    Units addArmorToUnit(Long unitId, Long armorId);

    Units removeArmorFromUnit(Long unitId, Long armorId);

    Units addWeaponToUnit(Long unitId, Long weaponId);

    Units removeWeaponFromUnit(Long unitId, Long weaponId);

    Units addSoulToUnit(Long unitId, Long soulId);

    Units removeSoulFromUnit(Long unitId, Long soulId);
}
