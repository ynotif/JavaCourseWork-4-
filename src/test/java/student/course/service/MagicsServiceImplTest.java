package student.course.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import student.course.exceptions.MagicNotFoundException;
import student.course.model.Magics;
import student.course.repository.MagicsRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MagicsServiceImplTest {

    private final MagicsRepository magicsRepository = Mockito.mock(MagicsRepository.class);

    private final MagicsService magicsService = new MagicsServiceImpl(magicsRepository);

    @DisplayName("create magic")
    @Test
    void createMagic() {
        Magics magic = new Magics();
        when(magicsRepository
                .save(magic))
                .thenReturn(magic);
        assertEquals(magic, magicsService.createMagic(magic));
    }

    @DisplayName("get all magics")
    @Test
    void getAllMagics() {
        Magics magic1 = new Magics();
        Magics magic2 = new Magics();
        when(magicsRepository
                .findAll())
                .thenReturn(List.of(magic1, magic2));
        assertEquals(List.of(magic1, magic2), magicsService.getAllMagics());
    }

    @DisplayName("get magic by id")
    @Test
    void getMagicById() throws MagicNotFoundException {
        Long magicId = 1L;
        Magics magic = new Magics();
        when(magicsRepository
                .findById(magicId))
                .thenReturn(Optional.of(magic));
        Optional<Magics> magicsOptional = magicsService.getMagicById(magicId);
        assertTrue(magicsOptional.isPresent());
        assertEquals(magic, magicsOptional.get());
    }

    @DisplayName("get magic by id but exception")
    @Test
    void getMagicByIdException() {
        when(magicsRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(MagicNotFoundException.class, () -> magicsService.getMagicById(1L));
    }

    @DisplayName("update magic")
    @Test
    void updateMagic() {
        Magics existingMagic = new Magics();
        when(magicsRepository
                .findById(1L))
                .thenReturn(Optional.of(existingMagic));
        Magics updatedMagic = new Magics();
        assertDoesNotThrow(() -> magicsService.updateMagic(updatedMagic, 1L));
        Optional<Magics> optionalMagics = magicsRepository.findById(1L);
        assertTrue(optionalMagics.isPresent());
        assertEquals(existingMagic, optionalMagics.get());
    }

    @DisplayName("update magic but exception")
    @Test
    void updateMagicException() {
        when(magicsRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(MagicNotFoundException.class, () -> magicsService.updateMagic(new Magics(), 1L));
    }

    @DisplayName("delete magic by id")
    @Test
    void deleteMagicById() {
        Magics existingMagic = new Magics();
        when(magicsRepository
                .findById(1L))
                .thenReturn(Optional.of(existingMagic));
        doNothing().when(magicsRepository).delete(existingMagic);
        assertDoesNotThrow(() -> magicsService.deleteMagicById(1L));
        verify(magicsRepository, times(1)).findById(1L);
        verify(magicsRepository, times(1)).delete(existingMagic);
    }

    @DisplayName("delete magic by id but exception")
    @Test
    void deleteMagicByIdException() {
        when(magicsRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(MagicNotFoundException.class, () -> magicsService.deleteMagicById(1L));
        verify(magicsRepository, times(1)).findById(1L);
        verify(magicsRepository, never()).delete(any());
    }
}