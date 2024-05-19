package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import student.course.bdsetters.SoulSetter;
import student.course.bdsetters.UnitSetter;
import student.course.exceptions.UnitNotFoundException;
import student.course.model.Armors;
import student.course.model.Souls;
import student.course.model.Units;
import student.course.model.Weapons;
import student.course.repository.ArmorsRepository;
import student.course.repository.SoulsRepository;
import student.course.repository.UnitsRepository;
import student.course.repository.WeaponsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitsServiceImpl implements UnitsService {

    private final UnitsRepository unitsRepository;
    private final ArmorsRepository armorsRepository;
    private final WeaponsRepository weaponsRepository;
    private final SoulsRepository soulsRepository;

    @CacheEvict(cacheNames = "Units", allEntries = true)
    @Override
    public Units createUnit(Units units) {
        return unitsRepository.save(units);
    }

    @Cacheable(cacheNames = "Units")
    @Override
    public List<Units> getAllUnits() {
        return unitsRepository.findAll();
    }

    @Override
    public Optional<Units> getUnitById(Long id) throws UnitNotFoundException {
        Optional<Units> unitsOptional = unitsRepository.findById(id);
        if(unitsOptional.isPresent()) {
            return unitsOptional;
        }
        else{
            throw new UnitNotFoundException(id);
        }
    }

    @CacheEvict(cacheNames = "Units", allEntries = true)
    @Override
    public void updateUnit(Units updateUnit, Long id) throws UnitNotFoundException {
        Units unit = unitsRepository.findById(id)
                .orElseThrow(() -> new UnitNotFoundException(id));

        UnitSetter.update(unit, updateUnit, id);

        unitsRepository.save(unit);
    }

    @CacheEvict(cacheNames = "Units", allEntries = true)
    @Override
    public void deleteUnitById(Long id) throws UnitNotFoundException {
        Optional<Units> optionalUnits = getUnitById(id);
        optionalUnits.ifPresent(unitsRepository::delete);
    }

    @CacheEvict(cacheNames = "Units", allEntries = true)
    @Override
    public Units addArmorToUnit(Long unitId, Long armorId) {
        Units units = unitsRepository.findById(unitId)
                .orElseThrow(() -> new IllegalArgumentException("Unit not found"));

        Armors armors = armorsRepository.findById(armorId)
                .orElseThrow(() -> new IllegalArgumentException("Armor not found"));

        units.getArmor().add(armors);

        return unitsRepository.save(units);
    }

    @CacheEvict(cacheNames = "Units", allEntries = true)
    @Override
    public Units removeArmorFromUnit(Long unitId, Long armorId) {
        Units units = unitsRepository.findById(unitId)
                .orElseThrow(() -> new IllegalArgumentException("Unit not found"));

        Armors armors = armorsRepository.findById(armorId)
                .orElseThrow(() -> new IllegalArgumentException("Armor not found"));

        units.getArmor().remove(armors);
        return unitsRepository.save(units);
    }

    @CacheEvict(cacheNames = "Units", allEntries = true)
    @Override
    public Units addWeaponToUnit(Long unitId, Long weaponId) {
        Units units = unitsRepository.findById(unitId)
                .orElseThrow(() -> new IllegalArgumentException("Unit not found"));

        Weapons weapons = weaponsRepository.findById(weaponId)
                .orElseThrow(() -> new RuntimeException("Weapon not found"));

        units.getWeapon().add(weapons);
        return unitsRepository.save(units);
    }

    @CacheEvict(cacheNames = "Units", allEntries = true)
    @Override
    public Units removeWeaponFromUnit(Long unitId, Long weaponId) {
        Units units = unitsRepository.findById(unitId)
                .orElseThrow(() -> new IllegalArgumentException("Unit not found"));

        Weapons weapons = weaponsRepository.findById(weaponId)
                .orElseThrow(() -> new RuntimeException("Weapon not found"));

        units.getWeapon().remove(weapons);
        return unitsRepository.save(units);
    }

    @CacheEvict(cacheNames = "Units", allEntries = true)
    @Override
    public Units addSoulToUnit(Long unitId, Long soulId) {
        Units units = unitsRepository.findById(unitId)
                .orElseThrow(() -> new IllegalArgumentException("Unit not found"));

        Souls souls = soulsRepository.findById(soulId)
                .orElseThrow(() -> new RuntimeException("Soul not found"));

        units.getSoul().add(souls);
        return unitsRepository.save(units);
    }

    @CacheEvict(cacheNames = "Units", allEntries = true)
    @Override
    public Units removeSoulFromUnit(Long unitId, Long soulId) {
        Units units = unitsRepository.findById(unitId)
                .orElseThrow(()-> new RuntimeException("Unit not found"));

        Souls souls = soulsRepository.findById(soulId)
                .orElseThrow(() -> new RuntimeException("Soul not found"));

        units.getSoul().remove(souls);
        return unitsRepository.save(units);
    }


}
