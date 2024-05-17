package student.course.service;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import student.course.exceptions.BosseNotFoundException;
import student.course.model.Armors;
import student.course.model.Bosses;
import student.course.model.Souls;
import student.course.model.Weapons;
import student.course.repository.ArmorsRepository;
import student.course.repository.BossesRepository;
import student.course.repository.SoulsRepository;
import student.course.repository.WeaponsRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BossesServiceImplTest {

    private final BossesRepository bossesRepository = Mockito.mock(BossesRepository.class);
    private final ArmorsRepository armorsRepository = Mockito.mock(ArmorsRepository.class);
    private final WeaponsRepository weaponsRepository = Mockito.mock(WeaponsRepository.class);
    private final SoulsRepository soulsRepository = Mockito.mock(SoulsRepository.class);

    private final BossesService bossesService = new BossesServiceImpl(
            bossesRepository, armorsRepository, weaponsRepository, soulsRepository);

    @DisplayName("Create boss")
    @Test
    void createBosse() {
        Bosses newBoss = new Bosses();
        Bosses expectedBoss = new Bosses();
        when(bossesRepository
                .save(newBoss))
                .thenReturn(expectedBoss);
        assertEquals(expectedBoss, newBoss);

    }

    @DisplayName("get all bosses")
    @Test
    void getAllBosses() {
        Bosses boss1 = new Bosses();
        Bosses boss2 = new Bosses();
        when(bossesRepository
                .findAll())
                .thenReturn(List.of(boss1, boss2));
        assertEquals(List.of(boss1, boss2), bossesService.getAllBosses());
    }

    @DisplayName("get boss by id")
    @Test
    void getBosseById() throws BosseNotFoundException {
        Bosses existingBoss = new Bosses();
        when(bossesRepository
                .findById(1L))
                .thenReturn(Optional.of(existingBoss));
        Optional<Bosses> optionalBoss = bossesService.getBosseById(1L);
        assertTrue(optionalBoss.isPresent());
        assertEquals(existingBoss, optionalBoss.get());
    }

    @DisplayName("get boss by id but exception")
    @Test
    void getBosseByIdException() {
        when(bossesRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(BosseNotFoundException.class, () -> bossesService.getBosseById(1L));
    }

    @DisplayName("update bosse")
    @Test
    void updateBosse() {
        Bosses existingBosse = new Bosses();
        when(bossesRepository
                .findById(1L))
                .thenReturn(Optional.of(existingBosse));
        Bosses updateBosse = new Bosses();
        assertDoesNotThrow(() -> bossesService.updateBosse(updateBosse, 1L));
        Optional<Bosses> optionalBosses = bossesRepository.findById(1L);
        assertTrue(optionalBosses.isPresent());
        assertEquals(existingBosse, optionalBosses.get());
    }

    @DisplayName("update boss by id but exception")
    @Test
    void updateBossByIdButException() {
        when(bossesRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(BosseNotFoundException.class, () -> bossesService.updateBosse(new Bosses(), 1L));
    }

    @DisplayName("delete boss by id")
    @Test
    void deleteBosseById() {
        Bosses existingBoss = new Bosses();
        when(bossesRepository
                .findById(1L))
                .thenReturn(Optional.of(existingBoss));
        doNothing().when(bossesRepository).delete(existingBoss);
        assertDoesNotThrow(() -> bossesService.deleteBosseById(1L));
        verify(bossesRepository, times(1)).findById(1L);
        verify(bossesRepository, times(1)).delete(existingBoss);
    }

    @DisplayName("add armor to boss")
    @Test
    void addArmorToBoss(){
        Long bossId = 1L;
        Long armorId = 1L;
        Bosses boss = new Bosses();
        boss.setArmor(new HashSet<>());
        Armors armor = new Armors();
        when(bossesRepository.findById(bossId)).thenReturn(Optional.of(boss));
        when(armorsRepository.findById(armorId)).thenReturn(Optional.of(armor));

        bossesService.addArmorToBoss(bossId, armorId);
        assertTrue(boss.getArmor().contains(armor));
    }

    @DisplayName("remove armor from boss")
    @Test
    void removeArmorFromBoss() {
        Long bossId = 1L;
        Long armorId = 1L;
        Bosses boss = new Bosses();
        boss.setArmor(new HashSet<>());
        Armors armor = new Armors();
        when(bossesRepository.findById(bossId)).thenReturn(Optional.of(boss));
        when(armorsRepository.findById(armorId)).thenReturn(Optional.of(armor));

        boss.getArmor().add(armor);
        bossesService.removeArmorFromBoss(bossId, armorId);
        assertFalse(boss.getArmor().contains(armor));
    }

    @DisplayName("add weapon to boss")
    @Test
    void addWeaponToBoss() {
        Long bossId = 1L;
        Long weaponId = 1L;
        Bosses boss = new Bosses();
        boss.setWeapon(new HashSet<>());
        Weapons weapons = new Weapons();
        when(bossesRepository.findById(bossId)).thenReturn(Optional.of(boss));
        when(weaponsRepository.findById(weaponId)).thenReturn(Optional.of(weapons));

        bossesService.addWeaponToBoss(bossId, weaponId);
        assertTrue(boss.getWeapon().contains(weapons));
    }

    @DisplayName("remove weapon from boss")
    @Test
    void removeWeaponFromBoss() {
        Long bossId = 1L;
        Long weaponId = 1L;
        Bosses boss = new Bosses();
        boss.setWeapon(new HashSet<>());
        Weapons weapons = new Weapons();
        when(bossesRepository
                .findById(bossId))
                .thenReturn(Optional.of(boss));
        when(weaponsRepository
                .findById(weaponId))
                .thenReturn(Optional.of(weapons));

        boss.getWeapon().add(weapons);
        bossesService.removeWeaponFromBoss(bossId, weaponId);

        assertFalse(boss.getWeapon().contains(weapons));
    }

    @DisplayName("add soul to boss")
    @Test
    void addSoulToBoss() {
        Long bossId = 1L;
        Long soulId = 1L;
        Bosses boss = new Bosses();
        boss.setSoul(new HashSet<>());
        Souls soul = new Souls();
        when(bossesRepository
                .findById(bossId))
                .thenReturn(Optional.of(boss));
        when(soulsRepository
                .findById(soulId))
                .thenReturn(Optional.of(soul));

        bossesService.addSoulToBoss(bossId, soulId);
        assertTrue(boss.getSoul().contains(soul));
    }

    @DisplayName("remove soul from boss")
    @Test
    void removeSoulFromBoss() {
        Long bossId = 1L;
        Long soulId = 1L;
        Bosses boss = new Bosses();
        boss.setSoul(new HashSet<>());
        Souls soul = new Souls();
        when(bossesRepository
                .findById(bossId))
                .thenReturn(Optional.of(boss));
        when(soulsRepository
                .findById(soulId))
                .thenReturn(Optional.of(soul));

        boss.getSoul().add(soul);
        bossesService.removeSoulFromBoss(bossId, soulId);
        assertFalse(boss.getSoul().contains(soul));
    }
}