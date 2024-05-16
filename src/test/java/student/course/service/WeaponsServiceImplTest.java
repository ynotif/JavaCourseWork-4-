package student.course.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import student.course.bdsetters.WeaponSetter;
import student.course.exceptions.BosseNotFoundException;
import student.course.exceptions.WeaponNotFoundException;
import student.course.model.Souls;
import student.course.model.Weapons;
import student.course.repository.SoulsRepository;
import student.course.repository.WeaponsRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeaponsServiceImplTest {

    private final WeaponsRepository weaponsRepository = Mockito.mock(WeaponsRepository.class); // Чисто оболочка для тестов
    private final SoulsRepository soulsRepository = Mockito.mock(SoulsRepository.class);

    private final WeaponsService weaponsService = new WeaponsServiceImpl(weaponsRepository, soulsRepository);

    @DisplayName("create weapon")
    @Test
    void createWeapon() {
        Weapons weapons = new Weapons();
        when(weaponsRepository
                .save(weapons))
                .thenReturn(weapons);
        assertEquals(weapons, weaponsService.createWeapon(weapons));
    }

    @DisplayName("git all weapons")
    @Test
    void getAllWeapons() {
        Weapons weapon1 = new Weapons();
        Weapons weapon2 = new Weapons();
        when(weaponsRepository
                .findAll())
                .thenReturn(List.of(weapon1, weapon2));
        assertEquals(List.of(weapon1, weapon2), weaponsService.getAllWeapons());
    }

    @DisplayName("get weapon by id")
    @Test
    void getWeaponById() throws WeaponNotFoundException {
        Weapons existingWeapon = new Weapons();
        when(weaponsRepository
                .findById(1L))
                .thenReturn(Optional.of(existingWeapon));
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(1L);
        assertTrue(optionalWeapons.isPresent());
        assertEquals(existingWeapon, optionalWeapons.get());
    }

    @DisplayName("get weapon by id but exception")
    @Test
    void getWeaponByIdButException(){
        when(weaponsRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(WeaponNotFoundException.class, () -> weaponsService.getWeaponById(1L));
    }

    @DisplayName("update weapon")
    @Test
    void updateWeapon() throws WeaponNotFoundException {
        Weapons existingWeapon = new Weapons();
        when(weaponsRepository
                .findById(1L))
                .thenReturn(Optional.of(existingWeapon));
        Weapons updateWeapon = new Weapons();
        weaponsService.updateWeapon(updateWeapon, 1L);
        Optional<Weapons> optionalWeapons = weaponsRepository.findById(1L);
        assertTrue(optionalWeapons.isPresent());
        assertEquals(existingWeapon, optionalWeapons.get());
    }

    @DisplayName("update weapon but exception")
    @Test
    void updateWeaponButException(){
        when(weaponsRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(WeaponNotFoundException.class, () -> weaponsService.updateWeapon(new Weapons(), 1L));
    }

    @DisplayName("delete weapon by id")
    @Test
    void deleteWeaponById() throws WeaponNotFoundException {
        Weapons existingWeapon = new Weapons();
        when(weaponsRepository
                .findById(1L))
                .thenReturn(Optional.of(existingWeapon));
        doNothing().when(weaponsRepository).delete(existingWeapon);
        weaponsService.deleteWeaponById(1L);
        verify(weaponsRepository, times(1)).findById(1L);
        verify(weaponsRepository, times(1)).delete(existingWeapon);
    }

    @DisplayName("add soul to weapon")
    @Test
    void addSoulToWeapon() {
        Long weaponId = 1L;
        Long soulId = 1L;
        Weapons weapon = new Weapons();
        weapon.setSoul(new HashSet<>());
        Souls soul = new Souls();
        when(weaponsRepository
                .findById(weaponId))
                .thenReturn(Optional.of(weapon));
        when(soulsRepository
                .findById(soulId))
                .thenReturn(Optional.of(soul));
        weaponsService.addSoulToWeapon(weaponId, soulId);
        assertTrue(weapon.getSoul().contains(soul));
    }

    @DisplayName("remove soul from weapon")
    @Test
    void removeSoulFromWeapon() {
        Long weaponId = 1L;
        Long soulId = 1L;
        Souls soul = new Souls();
        Weapons weapon = new Weapons();
        weapon.setSoul(new HashSet<>());
        when(weaponsRepository
                .findById(weaponId))
                .thenReturn(Optional.of(weapon));
        when(soulsRepository
                .findById(soulId))
                .thenReturn(Optional.of(soul));
        weapon.getSoul().add(soul);
        weaponsService.removeSoulFromWeapon(weaponId, soulId);
        assertFalse(weapon.getSoul().contains(soul));
    }
}