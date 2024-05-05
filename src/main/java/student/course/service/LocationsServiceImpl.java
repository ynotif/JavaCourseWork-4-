package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    @Override
    public Locations createLocation(Locations location) {
        return locationsRepository.save(location);
    }

    @Override
    public List<Locations> getAllLocations() {
        return locationsRepository.findAll();
    }

    @Override
    public Optional<Locations> getLocationById(Long id) {
        return locationsRepository.findById(id);
    }

    public void updateLocation(Locations locations){
        Optional<Locations> optionalLocations = getLocationById(locations.getLocationId());
        if(optionalLocations.isPresent()){
            locationsRepository.save(locations);
        }
    }

    public void deleteLocationById(Long locationId){
        Optional<Locations> optionalLocations = getLocationById(locationId);
        optionalLocations.ifPresent(locationsRepository::delete);
    }

    public Locations addArmorToLocation(Long locationId, Long armorId) {
        Locations location = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Armors armor = armorsRepository.findById(armorId)
                .orElseThrow(() -> new RuntimeException("Armor not found"));
        location.getArmor().add(armor);
        return locationsRepository.save(location);
    }

    public Locations removeArmorFromLocation(Long locationId, Long armorId) {
        Locations location = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Armors armor = armorsRepository.findById(armorId)
                .orElseThrow(() -> new RuntimeException("Armor not found"));
        location.getArmor().remove(armor);
        return locationsRepository.save(location);
    }

    public Locations addBossToLocation(Long locationId, Long bossId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        Bosses boss = bossesRepository.findById(bossId)
                .orElseThrow(() -> new RuntimeException("Boss not found"));

        locations.getBoss().add(boss);
        return locationsRepository.save(locations);
    }

    public Locations removeBossFromLocation(Long locationId, Long bossId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        Bosses bosses = bossesRepository.findById(bossId)
                .orElseThrow(() -> new RuntimeException("Boss not found"));

        locations.getBoss().remove(bosses);
        return locationsRepository.save(locations);
    }

    @Override
    public Locations addUnitToLocation(Long locationId, Long unitId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Units unit = unitsRepository.findById(unitId)
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        locations.getUnit().add(unit);
        return locationsRepository.save(locations);
    }

    @Override
    public Locations removeUnitFromLocation(Long locationId, Long unitId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Units unit = unitsRepository.findById(unitId)
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        locations.getUnit().remove(unit);
        return locationsRepository.save(locations);
    }

    @Override
    public Locations addMagicToLocation(Long locationId, Long magicId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Magics magic = magicsRepository.findById(magicId)
                .orElseThrow(() -> new RuntimeException("Magic not found"));

        locations.getMagic().add(magic);
        return locationsRepository.save(locations);
    }

    @Override
    public Locations removeMagicFromLocation(Long locationId, Long magicId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Magics magic = magicsRepository.findById(magicId)
                .orElseThrow(() -> new RuntimeException("Magic not found"));

        locations.getMagic().remove(magic);
        return locationsRepository.save(locations);
    }

    @Override
    public Locations addWeaponToLocation(Long locationId, Long weaponId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Weapons weapons = weaponsRepository.findById(weaponId)
                .orElseThrow(() -> new RuntimeException("Weapon not found"));

        locations.getWeapon().add(weapons);
        return locationsRepository.save(locations);
    }

    @Override
    public Locations removeWeaponFromLocation(Long locationId, Long weaponId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Weapons weapons = weaponsRepository.findById(weaponId)
                .orElseThrow(() -> new RuntimeException("Weapon not found"));

        locations.getWeapon().remove(weapons);
        return locationsRepository.save(locations);
    }

    @Override
    public Locations addNPCToLocation(Long locationId, Long npcId) {
        Locations locations = locationsRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        NPC npc = npcRepository.findById(npcId)
                .orElseThrow(() -> new RuntimeException("NPC not found"));

        locations.getNpc().add(npc);
        return locationsRepository.save(locations);
    }

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
