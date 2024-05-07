package student.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.exceptions.*;
import student.course.model.*;
import student.course.service.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/locations")
public class LocationsController {

    private final LocationsService locationsService;
    private final ArmorsService armorsService;
    private final BossesService bossesService;
    private final UnitsService unitsService;
    private final MagicsService magicsService;
    private final WeaponsService weaponsService;
    private final NPCService npcService;

    @PostMapping
    public ResponseEntity<Locations> addLocation(@RequestBody Locations location) {
        return ResponseEntity.ok(locationsService.createLocation(location));
    }

    @PostMapping("/{locationId}/units/{unitId}")
    public ResponseEntity<Locations> addUnitToLocation(@PathVariable Long locationId, @PathVariable Long unitId) throws LocationNotFoundException, UnitNotFoundException {
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        Optional<Units> optionalUnits = unitsService.getUnitById(unitId);
        if(optionalLocations.isPresent() && optionalUnits.isPresent()) {
            Locations location = optionalLocations.get();
            if(!location.getUnit().contains(optionalUnits.get())) {
                return ResponseEntity.ok(locationsService.addUnitToLocation(locationId, unitId));
            }
            return ResponseEntity.ok(location);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{locationId}/magics/{magicId}")
    public ResponseEntity<Locations> addMagicToLocation(@PathVariable Long locationId, @PathVariable Long magicId) throws LocationNotFoundException, MagicNotFoundException {
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        Optional<Magics> optionalMagics = magicsService.getMagicById(magicId);
        if(optionalLocations.isPresent() && optionalMagics.isPresent()) {
            Locations location = optionalLocations.get();
            if(!location.getMagic().contains(optionalMagics.get())) {
                return ResponseEntity.ok(locationsService.addMagicToLocation(locationId, magicId));
            }
            return ResponseEntity.ok(location);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{locationId}/weapons/{weaponId}")
    public ResponseEntity<Locations> addWeaponToLocation(@PathVariable Long locationId, @PathVariable Long weaponId) throws LocationNotFoundException, WeaponNotFoundException {
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        if(optionalLocations.isPresent() && optionalWeapons.isPresent()) {
            Locations location = optionalLocations.get();
            if(!location.getWeapon().contains(optionalWeapons.get())) {
                return ResponseEntity.ok(locationsService.addWeaponToLocation(locationId, weaponId));
            }
            return ResponseEntity.ok(location);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{locationId}/bosses/{bosseId}")
    public ResponseEntity<Locations> addBossToLocation(@PathVariable Long locationId, @PathVariable Long bosseId) throws BosseNotFoundException, LocationNotFoundException {
        Optional<Locations> optionalLocation = locationsService.getLocationById(locationId);
        Optional<Bosses> optionalBosses = bossesService.getBosseById(bosseId);
        if (optionalLocation.isPresent() && optionalBosses.isPresent()) {
            Locations location = optionalLocation.get();
            if(!location.getBoss().contains(optionalBosses.get())) {
                return ResponseEntity.ok(locationsService.addBossToLocation(locationId, bosseId));
            }
            return ResponseEntity.ok(location);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{locationId}/armors/{armorId}")
    public ResponseEntity<Locations> addArmorToLocation(@PathVariable Long locationId, @PathVariable Long armorId) throws ArmorNotFoundException, LocationNotFoundException {
        Optional<Armors> optionalArmor = armorsService.getArmorById(armorId);
        Optional<Locations> optionalLocation = locationsService.getLocationById(locationId);
        if (optionalArmor.isPresent() && optionalLocation.isPresent()) {
            Locations location = optionalLocation.get();
            if(!location.getArmor().contains(optionalArmor.get())) {
                return ResponseEntity.ok(locationsService.addArmorToLocation(locationId, armorId));
            }
            return ResponseEntity.ok(location);
        }
        return ResponseEntity.notFound().build();
    }

   @PostMapping("/{locationId}/npc/{npcId}")
   public ResponseEntity<Locations> addNPCToLocation(@PathVariable Long locationId, @PathVariable Long npcId) throws LocationNotFoundException, NPCNotFoundException {
        Optional<Locations> optionalLocation = locationsService.getLocationById(locationId);
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        if (optionalLocation.isPresent() && optionalNPC.isPresent()) {
            Locations location = optionalLocation.get();
            if(!location.getNpc().contains(optionalNPC.get())) {
                return ResponseEntity.ok(locationsService.addNPCToLocation(locationId, npcId));
            }
            return ResponseEntity.ok(location);
        }
        return ResponseEntity.notFound().build();
   }

   @PutMapping("/{id}")
   public ResponseEntity<Locations> updateLocation(@PathVariable Long id, @RequestBody Locations updateLocation) throws LocationNotFoundException {
        Optional<Locations> optionalLocation = locationsService.getLocationById(id);
        if (optionalLocation.isPresent()) {

            locationsService.updateLocation(updateLocation);

            return ResponseEntity.ok(updateLocation);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Locations>> getAllLocations(){
        return ResponseEntity.ok(locationsService.getAllLocations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Locations>> findLocationById(@PathVariable Long id) throws LocationNotFoundException{
        return ResponseEntity.ok(locationsService.getLocationById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long id) throws LocationNotFoundException {
        Optional<Locations> optionalLocation = locationsService.getLocationById(id);
        if (optionalLocation.isPresent()) {
            locationsService.deleteLocationById(id);
            return ResponseEntity.ok("Location deleted successfully!");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{locationId}/units/{unitId}")
    public ResponseEntity<Locations> removeUnitFromLocation(@PathVariable Long locationId, @PathVariable Long unitId) throws LocationNotFoundException, UnitNotFoundException {
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        Optional<Units> optionalUnits = unitsService.getUnitById(unitId);
        if(optionalLocations.isPresent() && optionalUnits.isPresent()) {
            Locations locations = optionalLocations.get();
            if(locations.getUnit().contains(optionalUnits.get())) {
                return ResponseEntity.ok(locationsService.removeUnitFromLocation(locationId, unitId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{locationId}/magics/{magicId}")
    public ResponseEntity<Locations> removeMagicFromLocation(@PathVariable Long locationId, @PathVariable Long magicId) throws LocationNotFoundException, MagicNotFoundException {
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        Optional<Magics> optionalMagics = magicsService.getMagicById(magicId);
        if(optionalLocations.isPresent() && optionalMagics.isPresent()) {
            Locations locations = optionalLocations.get();
            if(locations.getMagic().contains(optionalMagics.get())) {
                return ResponseEntity.ok(locationsService.removeMagicFromLocation(locationId, magicId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{locationId}/weapons/{weaponId}")
    public ResponseEntity<Locations> removeWeaponFromLocation(@PathVariable Long locationId, @PathVariable Long weaponId) throws LocationNotFoundException, WeaponNotFoundException {
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        if(optionalLocations.isPresent() && optionalWeapons.isPresent()) {
            Locations locations = optionalLocations.get();
            if(locations.getWeapon().contains(optionalWeapons.get())) {
                return ResponseEntity.ok(locationsService.removeWeaponFromLocation(locationId, weaponId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{locationId}/bosses/{bosseId}")
    public ResponseEntity<Locations> removeBossFromLocation(@PathVariable Long locationId, @PathVariable Long bosseId) throws BosseNotFoundException, LocationNotFoundException {
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        Optional<Bosses> optionalBosses = bossesService.getBosseById(bosseId);
        if(optionalLocations.isPresent() && optionalBosses.isPresent()) {
            Locations locations = optionalLocations.get();
            if(locations.getBoss().contains(optionalBosses.get())){
                return ResponseEntity.ok(locationsService.removeBossFromLocation(locationId, bosseId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{locationId}/armors/{armorId}")
    public ResponseEntity<Locations> removeArmorFromLocation(@PathVariable Long locationId, @PathVariable Long armorId) throws ArmorNotFoundException, LocationNotFoundException {
        Optional<Armors> optionalArmors = armorsService.getArmorById(armorId);
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        if (optionalArmors.isPresent() && optionalLocations.isPresent()) {
            Locations location = optionalLocations.get();
            if(location.getArmor().equals(optionalArmors.get())){
                return ResponseEntity.ok(locationsService.removeArmorFromLocation(locationId, armorId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{locationId}/npc/{npcId}")
    public ResponseEntity<Locations> removeNPCFromLocation(@PathVariable Long locationId, @PathVariable Long npcId) throws LocationNotFoundException, NPCNotFoundException {
        Optional<Locations> optionalLocation = locationsService.getLocationById(locationId);
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        if (optionalLocation.isPresent() && optionalNPC.isPresent()) {
            Locations location = optionalLocation.get();
            if(location.getNpc().contains(optionalNPC.get())) {
                return ResponseEntity.ok(locationsService.removeNPCFromLocation(locationId, npcId));
            }
        }
        return ResponseEntity.notFound().build();
    }

}
