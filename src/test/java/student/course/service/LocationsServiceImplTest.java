package student.course.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import student.course.exceptions.LocationNotFoundException;
import student.course.model.*;
import student.course.repository.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationsServiceImplTest {

    private final LocationsRepository locationsRepository = Mockito.mock(LocationsRepository.class);
    private final ArmorsRepository armorsRepository = Mockito.mock(ArmorsRepository.class);
    private final BossesRepository bossesRepository = Mockito.mock(BossesRepository.class);
    private final UnitsRepository unitsRepository = Mockito.mock(UnitsRepository.class);
    private final MagicsRepository magicsRepository = Mockito.mock(MagicsRepository.class);
    private final NPCRepository npcRepository = Mockito.mock(NPCRepository.class);
    private final WeaponsRepository weaponsRepository = Mockito.mock(WeaponsRepository.class);

    private final LocationsService locationsService = new LocationsServiceImpl(locationsRepository, armorsRepository,
            bossesRepository, unitsRepository, magicsRepository, npcRepository, weaponsRepository);

    @DisplayName("create location")
    @Test
    void createLocation() {
        Locations locations = new Locations();
        when(locationsRepository
                .save(locations))
                .thenReturn(locations);

        assertEquals(locations, locationsService.createLocation(locations));
    }

    @DisplayName("get all locations")
    @Test
    void getAllLocations() {
        Locations location1 = new Locations();
        Locations location2 = new Locations();
        when(locationsRepository
                .findAll())
                .thenReturn(List.of(location1, location2));
        assertEquals(List.of(location1, location2), locationsService.getAllLocations());
    }

    @DisplayName("get location by id")
    @Test
    void getLocationById() throws LocationNotFoundException {
        Long locationId = 1L;
        Locations existingLocation = new Locations();
        when(locationsRepository
                .findById(locationId))
                .thenReturn(Optional.of(existingLocation));
        Optional<Locations> locations = locationsService.getLocationById(locationId);
        assertTrue(locations.isPresent());
        assertEquals(existingLocation, locations.get());
    }

    @DisplayName("get location by is but exception")
    @Test
    void getLocationByIdException() {
        when(locationsRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(LocationNotFoundException.class, () -> locationsService.getLocationById(1L));
    }

    @DisplayName("update location")
    @Test
    void updateLocation() {
        Long locationId = 1L;
        Locations existingLocation = new Locations();
        when(locationsRepository
                .findById(locationId))
                .thenReturn(Optional.of(existingLocation));
        Locations updatedLocation = new Locations();
        assertDoesNotThrow(() -> locationsService.updateLocation(updatedLocation, locationId));
        Optional<Locations> optionalLocations = locationsRepository.findById(locationId);
        assertTrue(optionalLocations.isPresent());
        assertEquals(existingLocation, optionalLocations.get());
    }

    @DisplayName("update location but exception")
    @Test
    void updateLocationException(){
        when(locationsRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(LocationNotFoundException.class, () -> locationsService.updateLocation(new Locations(), 1L));
    }

    @DisplayName("delete location by id")
    @Test
    void deleteLocationById() {
        Long locationId = 1L;
        Locations existingLocation = new Locations();
        when(locationsRepository
                .findById(locationId))
                .thenReturn(Optional.of(existingLocation));
        doNothing().when(locationsRepository).delete(existingLocation);
        assertDoesNotThrow(() -> locationsService.deleteLocationById(locationId));
        verify(locationsRepository, times(1)).findById(locationId);
        verify(locationsRepository, times(1)).delete(existingLocation);
    }

    @DisplayName("delete location by id but exception")
    @Test
    void deleteLocationByIdException() {
        when(locationsRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(LocationNotFoundException.class, () -> locationsService.deleteLocationById(1L));
        verify(locationsRepository, times(1)).findById(1L);
        verify(locationsRepository, never()).delete(any());
    }

    @DisplayName("add armor to location")
    @Test
    void addArmorToLocation() {
        Long locationId = 1L;
        Long armorId = 1L;
        Locations location = new Locations();
        location.setArmor(new HashSet<>());
        Armors armor = new Armors();
        when(locationsRepository
                .findById(locationId))
                .thenReturn(Optional.of(location));
        when(armorsRepository
                .findById(armorId))
                .thenReturn(Optional.of(armor));

        locationsService.addArmorToLocation(locationId, armorId);
        assertTrue(location.getArmor().contains(armor));
    }

    @DisplayName("remove armor from location")
    @Test
    void removeArmorFromLocation() {
        Long locationId = 1L;
        Long armorId = 1L;
        Locations location = new Locations();
        location.setArmor(new HashSet<>());
        Armors armor = new Armors();
        when(locationsRepository
                .findById(locationId))
                .thenReturn(Optional.of(location));
        when(armorsRepository
                .findById(armorId))
                .thenReturn(Optional.of(armor));

        location.getArmor().add(armor);
        locationsService.removeArmorFromLocation(locationId, armorId);
        assertFalse(location.getArmor().contains(armor));
    }

    @DisplayName("add boss to location")
    @Test
    void addBossToLocation() {
        Long locationId = 1L;
        Long bossId = 1L;
        Locations location = new Locations();
        location.setBoss(new HashSet<>());
        Bosses boss = new Bosses();
        when(locationsRepository
                .findById(locationId))
                .thenReturn(Optional.of(location));
        when(bossesRepository
                .findById(bossId))
                .thenReturn(Optional.of(boss));

        locationsService.addBossToLocation(locationId, bossId);
        assertTrue(location.getBoss().contains(boss));
    }

    @DisplayName("remove boss from location")
    @Test
    void removeBossFromLocation() {
        Long locationId = 1L;
        Long bossId = 1L;
        Locations location = new Locations();
        location.setBoss(new HashSet<>());
        Bosses boss = new Bosses();
        when(locationsRepository
                .findById(locationId))
                .thenReturn(Optional.of(location));
        when(bossesRepository
                .findById(bossId))
                .thenReturn(Optional.of(boss));

        location.getBoss().add(boss);
        locationsService.removeBossFromLocation(locationId, bossId);
        assertFalse(location.getBoss().contains(boss));
    }

    @DisplayName("add unit to location")
    @Test
    void addUnitToLocation() {
        Long locationId = 1L;
        Long unitId = 1L;
        Locations location = new Locations();
        location.setUnit(new HashSet<>());
        Units unit = new Units();
        when(locationsRepository
                .findById(locationId))
                .thenReturn(Optional.of(location));
        when(unitsRepository
                .findById(unitId))
                .thenReturn(Optional.of(unit));

        locationsService.addUnitToLocation(locationId, unitId);
        assertTrue(location.getUnit().contains(unit));
    }

    @DisplayName("remove unit from location")
    @Test
    void removeUnitFromLocation() {
        Long locationId = 1L;
        Long unitId = 1L;
        Locations location = new Locations();
        location.setUnit(new HashSet<>());
        Units unit = new Units();
        when(locationsRepository
                .findById(locationId))
                .thenReturn(Optional.of(location));
        when(unitsRepository
                .findById(unitId))
                .thenReturn(Optional.of(unit));

        location.getUnit().add(unit);
        locationsService.removeUnitFromLocation(locationId, unitId);
        assertFalse(location.getUnit().contains(unit));
    }

    @DisplayName("add magic to location")
    @Test
    void addMagicToLocation() {
        Long locationId = 1L;
        Long magicId = 1L;
        Locations location = new Locations();
        location.setMagic(new HashSet<>());
        Magics magic = new Magics();
        when(locationsRepository
                .findById(locationId))
                .thenReturn(Optional.of(location));
        when(magicsRepository
                .findById(magicId))
                .thenReturn(Optional.of(magic));

        locationsService.addMagicToLocation(locationId, magicId);
        assertTrue(location.getMagic().contains(magic));
    }

    @DisplayName("remove magic from location")
    @Test
    void removeMagicFromLocation() {
        Long locationId = 1L;
        Long magicId = 1L;
        Locations location = new Locations();
        location.setMagic(new HashSet<>());
        Magics magic = new Magics();
        when(locationsRepository
                .findById(locationId))
                .thenReturn(Optional.of(location));
        when(magicsRepository
                .findById(magicId))
                .thenReturn(Optional.of(magic));
        location.getMagic().add(magic);
        locationsService.removeMagicFromLocation(locationId, magicId);
        assertFalse(location.getMagic().contains(magic));
    }

    @DisplayName("add weapon to location")
    @Test
    void addWeaponToLocation() {
        Long locationId = 1L;
        Long weaponId = 1L;
        Locations location = new Locations();
        location.setWeapon(new HashSet<>());
        Weapons weapons = new Weapons();
        when(locationsRepository
                .findById(locationId))
                .thenReturn(Optional.of(location));
        when(weaponsRepository
                .findById(weaponId))
                .thenReturn(Optional.of(weapons));
        locationsService.addWeaponToLocation(locationId, weaponId);
        assertTrue(location.getWeapon().contains(weapons));
    }

    @DisplayName("remove weapon from location")
    @Test
    void removeWeaponFromLocation() {
        Long locationId = 1L;
        Long weaponId = 1L;
        Locations location = new Locations();
        location.setWeapon(new HashSet<>());
        Weapons weapons = new Weapons();
        when(locationsRepository
                .findById(locationId))
                .thenReturn(Optional.of(location));
        when(weaponsRepository
                .findById(weaponId))
                .thenReturn(Optional.of(weapons));
        location.getWeapon().add(weapons);
        locationsService.removeWeaponFromLocation(locationId, weaponId);
        assertFalse(location.getWeapon().contains(weapons));
    }

    @DisplayName("add npc to location")
    @Test
    void addNPCToLocation() {
        Long locationId = 1L;
        Long npcId = 1L;
        Locations location = new Locations();
        location.setNpc(new HashSet<>());
        NPC npc = new NPC();
        when(locationsRepository
                .findById(locationId))
                .thenReturn(Optional.of(location));
        when(npcRepository
                .findById(npcId))
                .thenReturn(Optional.of(npc));
        locationsService.addNPCToLocation(locationId, npcId);
        assertTrue(location.getNpc().contains(npc));
    }

    @DisplayName("remove npc from location")
    @Test
    void removeNPCFromLocation() {
        Long locationId = 1L;
        Long npcId = 1L;
        Locations location = new Locations();
        location.setNpc(new HashSet<>());
        NPC npc = new NPC();
        when(locationsRepository
                .findById(locationId))
                .thenReturn(Optional.of(location));
        when(npcRepository
                .findById(npcId))
                .thenReturn(Optional.of(npc));
        location.getNpc().add(npc);
        locationsService.removeNPCFromLocation(locationId, npcId);
        assertFalse(location.getNpc().contains(npc));
    }
}