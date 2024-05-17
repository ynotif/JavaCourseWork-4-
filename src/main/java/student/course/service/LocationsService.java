package student.course.service;

import student.course.exceptions.LocationNotFoundException;
import student.course.model.Locations;

import java.util.List;
import java.util.Optional;

public interface LocationsService {

    Locations createLocation(Locations location);

    List<Locations> getAllLocations();

    Optional<Locations> getLocationById(Long id) throws LocationNotFoundException;

    void updateLocation(Locations location, Long id) throws LocationNotFoundException;

    void deleteLocationById(Long id) throws LocationNotFoundException;

    Locations addArmorToLocation(Long locationId, Long armorId);

    Locations removeArmorFromLocation(Long locationId, Long armorId);

    Locations addBossToLocation(Long locationId, Long bossId);

    Locations removeBossFromLocation(Long locationId, Long bossId);

    Locations addUnitToLocation(Long locationId, Long unitId);

    Locations removeUnitFromLocation(Long locationId, Long unitId);

    Locations addMagicToLocation(Long locationId, Long magicId);

    Locations removeMagicFromLocation(Long locationId, Long magicId);

    Locations addWeaponToLocation(Long locationId, Long weaponId);

    Locations removeWeaponFromLocation(Long locationId, Long weaponId);

    Locations addNPCToLocation(Long locationId, Long npcId);

    Locations removeNPCFromLocation(Long locationId, Long npcId);
}
