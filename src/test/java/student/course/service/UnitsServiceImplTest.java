package student.course.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import student.course.exceptions.UnitNotFoundException;
import student.course.model.Armors;
import student.course.model.Souls;
import student.course.model.Units;
import student.course.model.Weapons;
import student.course.repository.ArmorsRepository;
import student.course.repository.SoulsRepository;
import student.course.repository.UnitsRepository;
import student.course.repository.WeaponsRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class UnitsServiceImplTest {

    private final UnitsRepository unitsRepository = Mockito.mock(UnitsRepository.class);
    private final ArmorsRepository armorsRepository = Mockito.mock(ArmorsRepository.class);
    private final WeaponsRepository weaponsRepository = Mockito.mock(WeaponsRepository.class);
    private final SoulsRepository soulsRepository = Mockito.mock(SoulsRepository.class);

    private final UnitsService unitsService = new UnitsServiceImpl(
            unitsRepository, armorsRepository, weaponsRepository, soulsRepository
    );

    @DisplayName("create unit")
    @Test
    void createUnit() {
        Units unit = new Units();
        when(unitsRepository
                .save(unit))
                .thenReturn(unit);
        assertEquals(unit, unitsService.createUnit(unit));
    }

    @DisplayName("get all units")
    @Test
    void getAllUnits() {
        Units unit1 = new Units();
        Units unit2 = new Units();
        when(unitsRepository
                .findAll())
                .thenReturn(List.of(unit1, unit2));
        assertEquals(List.of(unit1, unit2), unitsService.getAllUnits());
    }

    @DisplayName("get unit by id")
    @Test
    void getUnitById() throws UnitNotFoundException {
        Units existingUnit = new Units();
        when(unitsRepository
                .findById(1L))
                .thenReturn(Optional.of(existingUnit));
        Optional<Units> optionalUnits = unitsService.getUnitById(1L);
        assertTrue(optionalUnits.isPresent());
        assertEquals(existingUnit, optionalUnits.get());
    }

    @DisplayName("get unit by id but exception")
    @Test
    void getUnitByIdException(){
        when(unitsRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(UnitNotFoundException.class, () -> unitsService.getUnitById(1L));
    }

    @DisplayName("update unit")
    @Test
    void updateUnit() throws UnitNotFoundException {
        Units existingUnit = new Units();
        when(unitsRepository
                .findById(1L))
                .thenReturn(Optional.of(existingUnit));
        Units updatedUnit = new Units();
        unitsService.updateUnit(updatedUnit, 1L);
        Optional<Units> optionalUnits = unitsRepository.findById(1L);
        assertTrue(optionalUnits.isPresent());
        assertEquals(existingUnit, optionalUnits.get());
    }

    @DisplayName("update unit but exception")
    @Test
    void updateUnitException(){
        when(unitsRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(UnitNotFoundException.class, () -> unitsService.updateUnit(new Units(), 1L));
    }

    @DisplayName("delete unit by id")
    @Test
    void deleteUnitById() throws UnitNotFoundException {
        Units existingUnit = new Units();
        when(unitsRepository
                .findById(1L))
                .thenReturn(Optional.of(existingUnit));
        doNothing().when(unitsRepository).delete(existingUnit);
        unitsService.deleteUnitById(1L);
        verify(unitsRepository, times(1)).findById(1L);
        verify(unitsRepository, times(1)).delete(existingUnit);
    }

    @DisplayName("delete unit by id but exception")
    @Test
    void deleteUnitByIdException(){
        when(unitsRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(UnitNotFoundException.class, () -> unitsService.deleteUnitById(1L));
    }

    @DisplayName("add armor to unit")
    @Test
    void addArmorToUnit() {
        Long unitId = 1L;
        Long armorId = 1L;
        Units unit = new Units();
        unit.setArmor(new HashSet<>());
        Armors armor = new Armors();
        when(unitsRepository
                .findById(unitId))
                .thenReturn(Optional.of(unit));
        when(armorsRepository
                .findById(armorId))
                .thenReturn(Optional.of(armor));
        unitsService.addArmorToUnit(unitId, armorId);
        assertTrue(unit.getArmor().contains(armor));
    }

    @DisplayName("remove armor from unit")
    @Test
    void removeArmorFromUnit() {
        Long unitId = 1L;
        Long armorId = 1L;
        Units unit = new Units();
        unit.setArmor(new HashSet<>());
        Armors armor = new Armors();
        when(unitsRepository
                .findById(unitId))
                .thenReturn(Optional.of(unit));
        when(armorsRepository
                .findById(armorId))
                .thenReturn(Optional.of(armor));
        unit.getArmor().add(armor);
        unitsService.removeArmorFromUnit(unitId, armorId);
        assertFalse(unit.getArmor().contains(armor));
    }

    @DisplayName("add weapon to unit")
    @Test
    void addWeaponToUnit() {
        Long unitId = 1L;
        Long weaponId = 1L;
        Units unit = new Units();
        unit.setWeapon(new HashSet<>());
        Weapons weapon = new Weapons();
        when(unitsRepository
                .findById(unitId))
                .thenReturn(Optional.of(unit));
        when(weaponsRepository
                .findById(weaponId))
                .thenReturn(Optional.of(weapon));
        unitsService.addWeaponToUnit(unitId, weaponId);
        assertTrue(unit.getWeapon().contains(weapon));
    }

    @DisplayName("remove weapon from unit")
    @Test
    void removeWeaponFromUnit() {
        Long unitId = 1L;
        Long weaponId = 1L;
        Units unit = new Units();
        unit.setWeapon(new HashSet<>());
        Weapons weapon = new Weapons();
        when(unitsRepository
                .findById(unitId))
                .thenReturn(Optional.of(unit));
        when(weaponsRepository
                .findById(weaponId))
                .thenReturn(Optional.of(weapon));
        unit.getWeapon().add(weapon);
        unitsService.removeWeaponFromUnit(unitId, weaponId);
        assertFalse(unit.getWeapon().contains(weapon));
    }

    @DisplayName("add soul to unit")
    @Test
    void addSoulToUnit() {
        Long unitId = 1L;
        Long soulId = 1L;
        Units unit = new Units();
        unit.setSoul(new HashSet<>());
        Souls soul = new Souls();
        when(unitsRepository
                .findById(unitId))
                .thenReturn(Optional.of(unit));
        when(soulsRepository
                .findById(soulId))
                .thenReturn(Optional.of(soul));
        unitsService.addSoulToUnit(unitId, soulId);
        assertTrue(unit.getSoul().contains(soul));
    }

    @DisplayName("remove soul from unit")
    @Test
    void removeSoulFromUnit() {
        Long unitId = 1L;
        Long soulId = 1L;
        Units unit = new Units();
        unit.setSoul(new HashSet<>());
        Souls soul = new Souls();
        when(unitsRepository
                .findById(unitId))
                .thenReturn(Optional.of(unit));
        when(soulsRepository
                .findById(soulId))
                .thenReturn(Optional.of(soul));
        unit.getSoul().add(soul);
        unitsService.removeSoulFromUnit(unitId, soulId);
        assertFalse(unit.getSoul().contains(soul));
    }
}