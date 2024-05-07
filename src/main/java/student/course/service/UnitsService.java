package student.course.service;

import student.course.exceptions.UnitNotFoundException;
import student.course.model.Units;

import java.util.List;
import java.util.Optional;

public interface UnitsService {

    Units createUnit(Units units);

    List<Units> getAllUnits();

    Optional<Units> getUnitById(Long id) throws UnitNotFoundException;

    void updateUnit(Units units) throws UnitNotFoundException;

    void deleteUnitById(Long id) throws UnitNotFoundException;

    Units addArmorToUnit(Long unitId, Long armorId);

    Units removeArmorFromUnit(Long unitId, Long armorId);

    Units addWeaponToUnit(Long unitId, Long weaponId);

    Units removeWeaponFromUnit(Long unitId, Long weaponId);

    Units addSoulToUnit(Long unitId, Long soulId);

    Units removeSoulFromUnit(Long unitId, Long soulId);
}
