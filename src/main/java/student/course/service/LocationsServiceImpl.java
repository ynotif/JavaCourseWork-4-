package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import student.course.model.Armors;
import student.course.model.Bosses;
import student.course.model.Locations;
import student.course.repository.ArmorsRepository;
import student.course.repository.BossesRepository;
import student.course.repository.LocationsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationsServiceImpl implements LocationsService {

    private final LocationsRepository locationsRepository;
    private final ArmorsRepository armorsRepository;
    private final BossesRepository bossesRepository;

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

}
