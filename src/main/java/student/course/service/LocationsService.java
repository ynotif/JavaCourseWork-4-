package student.course.service;

import student.course.model.Locations;

import java.util.List;
import java.util.Optional;

public interface LocationsService {

    Locations createLocation(Locations location);

    List<Locations> getAllLocations();

    Optional<Locations> getLocationById(Long id);

    void updateLocation(Locations location);

    void deleteLocationById(Long id);

    Locations addArmorToLocation(Long locationId, Long armorId);

    Locations removeArmorFromLocation(Long locationId, Long armorId);

    Locations addBossToLocation(Long locationId, Long bossId);

    Locations removeBossFromLocation(Long locationId, Long bossId);
}
