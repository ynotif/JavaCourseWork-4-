package student.course.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import student.course.exceptions.SoulNotFoundException;
import student.course.model.Souls;
import student.course.repository.SoulsRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SoulsServiceImplTest {

    private final SoulsRepository soulsRepository = Mockito.mock(SoulsRepository.class);

    private final SoulsServiceImpl soulsService = new SoulsServiceImpl(soulsRepository);

    @DisplayName("Test for createSoul")
    @Test
    void createSoul() {
        Souls inputSoul = new Souls(null, "Name", 100, "Info", null, null, null, null);
        Souls expectedSouls = new Souls(1L, "Name", 100, "Info", null, null, null, null);
        when(soulsRepository
                        .save(inputSoul)) // Если делаем полностью правильно Unit-test, то надо еще прописать в моделе @EqualsAndHashCode
                .thenReturn(expectedSouls);

        assertEquals(expectedSouls, soulsService.createSoul(inputSoul)); // 1 Что ожидаем 2 что получаем
    }

    @Test
    void getAllSouls() {
        Souls existingSoul = new Souls(null, "Name", 100, "Info", null, null, null, null);
        Souls existingSoul2 = new Souls(null, "Name2", 200, "Info2", null, null, null, null);
        when(soulsRepository
                .findAll())
                .thenReturn(List.of(existingSoul, existingSoul2));
        List<Souls> allSouls = soulsRepository.findAll();
        assertEquals(allSouls, soulsService.getAllSouls());
    }

    @DisplayName("get soul by id")
    @Test
    void getSoulById() throws SoulNotFoundException {
        Souls existingSoul = new Souls(null, "Name", 100, "Info", null, null, null, null);
        when(soulsRepository.
                findById(1L)).
                thenReturn(Optional.of(existingSoul));

        Optional<Souls> optionalSouls = soulsService.getSoulById(1L);
        assertTrue(optionalSouls.isPresent());
        assertEquals(existingSoul, optionalSouls.get());
    }

    @DisplayName("get soul by id but exception")
    @Test
    void getSoulByIdButException() {
        when(soulsRepository.
                findById(1L)).
                thenReturn(Optional.empty());

        assertThrows(SoulNotFoundException.class, () -> soulsService.getSoulById(1L));

    }

    @DisplayName("update soul")
    @Test
    void updateSoul() {
        Souls existingSoul = new Souls(null, "Name", 100, "Info", null, null, null, null);
        when(soulsRepository
                .findById(1L))
                .thenReturn(Optional.of(existingSoul));
        Souls updateSoul = new Souls(1L, "updateName", 200, "updateInfo", null, null, null, null);
        assertDoesNotThrow(() -> soulsService.updateSoul(updateSoul, 1L));
        Optional<Souls> optionalSouls = soulsRepository.findById(1L);
        assertTrue(optionalSouls.isPresent());
        assertEquals(existingSoul, optionalSouls.get());
    }

    @DisplayName("update soul but exception")
    @Test
    void updateSoulButException() {
        when(soulsRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(SoulNotFoundException.class, () -> soulsService.updateSoul(new Souls(), 1L));
    }

    @DisplayName("delete soul")
    @Test
    void deleteSoulById() {
        Souls existingSoul = new Souls(null, "Name", 100, "Info", null, null, null, null);
        when(soulsRepository
                .findById(1L))
                .thenReturn(Optional.of(existingSoul));
        doNothing().when(soulsRepository).delete(existingSoul);
        assertDoesNotThrow(() -> soulsService.deleteSoulById(1L));
        verify(soulsRepository, times(1)).findById(1L);
        verify(soulsRepository, times(1)).delete(existingSoul);
    }

    @DisplayName("delete soul but exception")
    @Test
    void deleteSoulByIdButException(){
        when(soulsRepository
                .findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(SoulNotFoundException.class, () -> soulsService.deleteSoulById(1L));
        verify(soulsRepository, times(1)).findById(1L);
        verify(soulsRepository, never()).delete(any());
    }

}