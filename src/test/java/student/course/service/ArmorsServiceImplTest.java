package student.course.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import student.course.exceptions.ArmorNotFoundException;
import student.course.model.Armors;
import student.course.repository.ArmorsRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArmorsServiceImplTest {

    private final ArmorsRepository armorsRepository = Mockito.mock(ArmorsRepository.class);

    private final ArmorsService armorsService = new ArmorsServiceImpl(armorsRepository);

    @DisplayName("create armor")
    @Test
    void createArmor() {
        Armors newArmor = new Armors(
                null,
                "Iron Armor", 0.8,
                0.6, 0.7, 0.5, 0.3,
                0.2, 0.4, 0.1, "Heavy",
                50, 20, 100, 500, 1000,
                "This is a sturdy iron armor.", null, null, null, null
        );
        Armors expectedArmor = new Armors(
                null,
                "Iron Armor", 0.8, 0.6,
                0.7, 0.5, 0.3,
                0.2, 0.4, 0.1, "Heavy",
                50, 20, 100, 500, 1000,
                "This is a sturdy iron armor.", null, null, null, null
        );
        when(armorsRepository
                .save(newArmor))
                .thenReturn(expectedArmor);
        assertEquals(expectedArmor, armorsService.createArmor(newArmor));
    }

    @DisplayName("get all armors")
    @Test
    void getAllArmors() {
        Armors armor1 = new Armors();
        Armors armor2 = new Armors();
        when(armorsRepository.findAll())
                .thenReturn(List.of(armor1, armor2));
        assertEquals(List.of(armor1, armor2), armorsService.getAllArmors());
    }

    @DisplayName("get armor by id")
    @Test
    void getArmorById() throws ArmorNotFoundException {
        Armors existingArmor = new Armors();
        when(armorsRepository
                .findById(1L))
                .thenReturn(Optional.of(existingArmor));
        Optional<Armors> optionalArmors = armorsService.getArmorById(1L);
        assertTrue(optionalArmors.isPresent());
        assertEquals(existingArmor, optionalArmors.get());
    }

    @DisplayName("get armor by id but exception")
    @Test
    void getArmorByIdException() {
        when(armorsRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(ArmorNotFoundException.class, () -> armorsService.getArmorById(1L));
    }

    @DisplayName("update armor")
    @Test
    void updateArmor() {
        Armors existingArmor = new Armors();
        when(armorsRepository
                .findById(1L))
                .thenReturn(Optional.of(existingArmor));
        Armors updatedArmor = new Armors();
        assertDoesNotThrow(() -> armorsService.updateArmor(updatedArmor, 1L));
        Optional<Armors> optionalArmors = armorsRepository.findById(1L);
        assertTrue(optionalArmors.isPresent());
        assertEquals(existingArmor, optionalArmors.get());
    }

    @DisplayName("update armor but exception")
    @Test
    void updateArmorException() {
        when(armorsRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(ArmorNotFoundException.class, () -> armorsService.updateArmor(new Armors(), 1L));
    }

    @DisplayName("delete armor by id")
    @Test
    void deleteArmorById() {
        Armors existingArmor = new Armors();
        when(armorsRepository
                .findById(1L))
                .thenReturn(Optional.of(existingArmor));
        doNothing().when(armorsRepository).delete(existingArmor);
        assertDoesNotThrow(() -> armorsService.deleteArmorById(1L));
        verify(armorsRepository, times(1)).findById(1L);
        verify(armorsRepository, times(1)).delete(existingArmor);
    }

    @DisplayName("delete armor by id but exception")
    @Test
    void deleteArmorByIdException() {
        when(armorsRepository
                .findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ArmorNotFoundException.class, () -> armorsService.deleteArmorById(1L));
        verify(armorsRepository, times(1)).findById(1L);
        verify(armorsRepository, never()).delete(any());
    }
}