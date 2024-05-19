package student.course.bdsetters;

import lombok.Data;
import student.course.model.Locations;

@Data
public class LocationSetter {
    public static void update(Locations location, Locations locationUpdate, Long id) {
        location.setLocationId(id);
        location.setLocationName(locationUpdate.getLocationName());
        location.setLocationEstusQuantity(locationUpdate.getLocationEstusQuantity());
        location.setLocationUniqueUnitsQuantity(locationUpdate.getLocationUniqueUnitsQuantity());
        location.setLocationNewMagic(locationUpdate.getLocationNewMagic());
        location.setLocationUniqueWeaponsQuantity(locationUpdate.getLocationUniqueWeaponsQuantity());
        location.setLocationsUniqueBossesQuantity(locationUpdate.getLocationsUniqueBossesQuantity());
        location.setLocationUniqueArmorsQuantity(locationUpdate.getLocationUniqueArmorsQuantity());
        location.setLocationBosseQuantity(locationUpdate.getLocationBosseQuantity());
        location.setLocationNPCQuantity(locationUpdate.getLocationNPCQuantity());
        location.setLocationLizardsQuantity(locationUpdate.getLocationLizardsQuantity());
        location.setLocationSomeInformation(locationUpdate.getLocationSomeInformation());
    }
}
