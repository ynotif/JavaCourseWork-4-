package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import student.course.bdsetters.LocationSetter;
import student.course.exceptions.LocationNotFoundException;
import student.course.model.*;
import student.course.repository.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationsServiceImpl implements LocationsService {

    private final LocationsRepository locationsRepository;
    private final ArmorsRepository armorsRepository;
    private final BossesRepository bossesRepository;
    private final UnitsRepository unitsRepository;
    private final MagicsRepository magicsRepository;
    private final NPCRepository npcRepository;
    private final WeaponsRepository weaponsRepository;

    @CacheEvict(cacheNames = "Locations", allEntries = true)
    @Override
    public Locations createLocation(Locations location) {
        return locationsRepository.save(location);
    }

    @CacheEvict(cacheNames = "Locations", allEntries = true)
    @Override
    public List<Locations> getAllLocations() {
        return locationsRepository.findAll();
    }

    @Override
    public Optional<Locations> getLocationById(Long id) throws LocationNotFoundException {
        Optional<Locations> optionalLocation = locationsRepository.findById(id);
        if (optionalLocation.isPresent()) {
            return optionalLocation;
        } else {
            throw new LocationNotFoundException(id);
        }
    }

    @CacheEvict(cacheNames = "Locations", allEntries = true)
    @Override
    public Locations updateLocation(Locations updateLocation, Long id) throws LocationNotFoundException {
        Locations locations = locationsRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));

        LocationSetter.update(locations, updateLocation, id);

        locationsRepository.save(locations);
        return locations;
    }

    @CacheEvict(cacheNames = "Locations", allEntries = true)
    @Override
    public void deleteLocationById(Long locationId) throws LocationNotFoundException {
        Optional<Locations> optionalLocations = getLocationById(locationId);
        optionalLocations.ifPresent(locationsRepository::delete);
    }

    @CacheEvict(cacheNames = "Locations", allEntries = true)
    @Override
    public Locations addArmorToLocation(Long locationId, Long armorId) {
        Locations location = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Armors armor = armorsRepository.findById(armorId)
                .orElseThrow(() -> new RuntimeException("Armor not found"));
        location.getArmor().add(armor);
        return locationsRepository.save(location);
    }

    @CacheEvict(cacheNames = "Locations", allEntries = true)
    @Override
    public Locations removeArmorFromLocation(Long locationId, Long armorId) {
        Locations location = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Armors armor = armorsRepository.findById(armorId)
                .orElseThrow(() -> new RuntimeException("Armor not found"));
        location.getArmor().remove(armor);
        return locationsRepository.save(location);
    }

    @CacheEvict(cacheNames = "Locations", allEntries = true)
    @Override
    public Locations addBossToLocation(Long locationId, Long bossId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        Bosses boss = bossesRepository.findById(bossId)
                .orElseThrow(() -> new RuntimeException("Boss not found"));

        locations.getBoss().add(boss);
        return locationsRepository.save(locations);
    }

    @CacheEvict(cacheNames = "Locations", allEntries = true)
    @Override
    public Locations removeBossFromLocation(Long locationId, Long bossId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        Bosses bosses = bossesRepository.findById(bossId)
                .orElseThrow(() -> new RuntimeException("Boss not found"));

        locations.getBoss().remove(bosses);
        return locationsRepository.save(locations);
    }

    @CacheEvict(cacheNames = "Locations", allEntries = true)
    @Override
    public Locations addUnitToLocation(Long locationId, Long unitId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Units unit = unitsRepository.findById(unitId)
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        locations.getUnit().add(unit);
        return locationsRepository.save(locations);
    }

    @CacheEvict(cacheNames = "Locations", allEntries = true)
    @Override
    public Locations removeUnitFromLocation(Long locationId, Long unitId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Units unit = unitsRepository.findById(unitId)
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        locations.getUnit().remove(unit);
        return locationsRepository.save(locations);
    }

    @CacheEvict(cacheNames = "Locations", allEntries = true)
    @Override
    public Locations addMagicToLocation(Long locationId, Long magicId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Magics magic = magicsRepository.findById(magicId)
                .orElseThrow(() -> new RuntimeException("Magic not found"));

        locations.getMagic().add(magic);
        return locationsRepository.save(locations);
    }

    @CacheEvict(cacheNames = "Locations", allEntries = true)
    @Override
    public Locations removeMagicFromLocation(Long locationId, Long magicId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Magics magic = magicsRepository.findById(magicId)
                .orElseThrow(() -> new RuntimeException("Magic not found"));

        locations.getMagic().remove(magic);
        return locationsRepository.save(locations);
    }

    @CacheEvict(cacheNames = "Locations", allEntries = true)
    @Override
    public Locations addWeaponToLocation(Long locationId, Long weaponId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Weapons weapons = weaponsRepository.findById(weaponId)
                .orElseThrow(() -> new RuntimeException("Weapon not found"));

        locations.getWeapon().add(weapons);
        return locationsRepository.save(locations);
    }

    @CacheEvict(cacheNames = "Locations", allEntries = true)
    @Override
    public Locations removeWeaponFromLocation(Long locationId, Long weaponId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Weapons weapons = weaponsRepository.findById(weaponId)
                .orElseThrow(() -> new RuntimeException("Weapon not found"));

        locations.getWeapon().remove(weapons);
        return locationsRepository.save(locations);
    }

    @CacheEvict(cacheNames = "Locations", allEntries = true)
    @Override
    public Locations addNPCToLocation(Long locationId, Long npcId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        NPC npc = npcRepository.findById(npcId)
                .orElseThrow(() -> new RuntimeException("NPC not found"));

        locations.getNpc().add(npc);
        return locationsRepository.save(locations);
    }

    @CacheEvict(cacheNames = "Locations", allEntries = true)
    @Override
    public Locations removeNPCFromLocation(Long locationId, Long npcId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        NPC npc = npcRepository.findById(npcId)
                .orElseThrow(() -> new RuntimeException("NPC not found"));

        locations.getNpc().remove(npc);
        return locationsRepository.save(locations);
    }

}
