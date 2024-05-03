package student.course.bdsetters;

import lombok.Data;
import student.course.model.Locations;

@Data
public class LocationSetter {
    public void update(Locations location, Locations locationUpdate) {
        location.setLocationName(locationUpdate.getLocationName());
        location.setLocationEstusQuantity(locationUpdate.getLocationEstusQuantity());
        location.setLocationUniqueUnitsQuantity(locationUpdate.getLocationUniqueUnitsQuantity());
        location.setUnit(locationUpdate.getUnit());
        location.setLocationNewMagic(locationUpdate.getLocationNewMagic());
        location.setMagic(locationUpdate.getMagic());
        location.setLocationUniqueWeaponsQuantity(locationUpdate.getLocationUniqueWeaponsQuantity());
        location.setWeapon(locationUpdate.getWeapon());
        location.setLocationsUniqueBossesQuantity(locationUpdate.getLocationsUniqueBossesQuantity());
        location.setBoss(locationUpdate.getBoss());
        location.setLocationUniqueArmorsQuantity(locationUpdate.getLocationUniqueArmorsQuantity());
        location.setArmor(locationUpdate.getArmor());
        location.setLocationBosseQuantity(locationUpdate.getLocationBosseQuantity());
        location.setLocationNPCQuantity(locationUpdate.getLocationNPCQuantity());
        location.setNpc(locationUpdate.getNpc());
        location.setLocationLizardsQuantity(locationUpdate.getLocationLizardsQuantity());
        location.setLocationSomeInformation(locationUpdate.getLocationSomeInformation());
    }
}
