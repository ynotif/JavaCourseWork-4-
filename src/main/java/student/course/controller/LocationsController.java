package student.course.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        log.info("HTTP: Add location: {}", location);
        return ResponseEntity.ok(locationsService.createLocation(location));
    }

    @PostMapping("/{locationId}/units/{unitId}")
    public ResponseEntity<Locations> addUnitToLocation(@PathVariable Long locationId, @PathVariable Long unitId) throws LocationNotFoundException, UnitNotFoundException {
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        Optional<Units> optionalUnits = unitsService.getUnitById(unitId);
        if (optionalLocations.isPresent() && optionalUnits.isPresent()) {
            Locations location = optionalLocations.get();
            if (!location.getUnit().contains(optionalUnits.get())) {
                log.info("HTTP: Add unit to location (location id {} unit id {})", locationId, unitId);
                return ResponseEntity.ok(locationsService.addUnitToLocation(locationId, unitId));
            }
            log.warn("HTTP: location has this (location id {} unit id {})", locationId, unitId);
            return ResponseEntity.ok(location);
        }
        log.error("HTTP: error with add unit to location");
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{locationId}/magics/{magicId}")
    public ResponseEntity<Locations> addMagicToLocation(@PathVariable Long locationId, @PathVariable Long magicId) throws LocationNotFoundException, MagicNotFoundException {
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        Optional<Magics> optionalMagics = magicsService.getMagicById(magicId);
        if (optionalLocations.isPresent() && optionalMagics.isPresent()) {
            Locations location = optionalLocations.get();
            if (!location.getMagic().contains(optionalMagics.get())) {
                log.info("HTTP: Add magic to location (location id {} magic id {})", locationId, magicId);
                return ResponseEntity.ok(locationsService.addMagicToLocation(locationId, magicId));
            }
            log.warn("HTTP: location has this Magic (location id {} magic id {})", locationId, magicId);
            return ResponseEntity.ok(location);
        }
        log.error("HTTP: error with add magic to location");
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{locationId}/weapons/{weaponId}")
    public ResponseEntity<Locations> addWeaponToLocation(@PathVariable Long locationId, @PathVariable Long weaponId) throws LocationNotFoundException, WeaponNotFoundException {
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        if (optionalLocations.isPresent() && optionalWeapons.isPresent()) {
            Locations location = optionalLocations.get();
            if (!location.getWeapon().contains(optionalWeapons.get())) {
                log.info("HTTP: add weapon to location (location id {} weapon id {})", locationId, weaponId);
                return ResponseEntity.ok(locationsService.addWeaponToLocation(locationId, weaponId));
            }
            log.warn("HTTP: location has this weapon (location id {} weapon id {})", locationId, weaponId);
            return ResponseEntity.ok(location);
        }
        log.error("HTTP: error with add weapon to location");
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{locationId}/bosses/{bosseId}")
    public ResponseEntity<Locations> addBossToLocation(@PathVariable Long locationId, @PathVariable Long bosseId) throws BosseNotFoundException, LocationNotFoundException {
        Optional<Locations> optionalLocation = locationsService.getLocationById(locationId);
        Optional<Bosses> optionalBosses = bossesService.getBosseById(bosseId);
        if (optionalLocation.isPresent() && optionalBosses.isPresent()) {
            Locations location = optionalLocation.get();
            if (!location.getBoss().contains(optionalBosses.get())) {
                log.info("HTTP: add boss to location (location id {} boss id {})", locationId, bosseId);
                return ResponseEntity.ok(locationsService.addBossToLocation(locationId, bosseId));
            }
            log.warn("HTTP: location has this Boss (location id {} boss id {})", locationId, bosseId);
            return ResponseEntity.ok(location);
        }
        log.error("HTTP: error with add boss to location");
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{locationId}/armors/{armorId}")
    public ResponseEntity<Locations> addArmorToLocation(@PathVariable Long locationId, @PathVariable Long armorId) throws ArmorNotFoundException, LocationNotFoundException {
        Optional<Armors> optionalArmor = armorsService.getArmorById(armorId);
        Optional<Locations> optionalLocation = locationsService.getLocationById(locationId);
        if (optionalArmor.isPresent() && optionalLocation.isPresent()) {
            Locations location = optionalLocation.get();
            if (!location.getArmor().contains(optionalArmor.get())) {
                log.info("HTTP: add armor to location (location id {} armor id {})", locationId, armorId);
                return ResponseEntity.ok(locationsService.addArmorToLocation(locationId, armorId));
            }
            log.warn("HTTP: location has this Armor (location id {} armor id {})", locationId, armorId);
            return ResponseEntity.ok(location);
        }
        log.error("HTTP: error with add armor to location");
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{locationId}/npc/{npcId}")
    public ResponseEntity<Locations> addNPCToLocation(@PathVariable Long locationId, @PathVariable Long npcId) throws LocationNotFoundException, NPCNotFoundException {
        Optional<Locations> optionalLocation = locationsService.getLocationById(locationId);
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        if (optionalLocation.isPresent() && optionalNPC.isPresent()) {
            Locations location = optionalLocation.get();
            if (!location.getNpc().contains(optionalNPC.get())) {
                log.info("HTTP: add npc to location (location id {} npc id {})", locationId, npcId);
                return ResponseEntity.ok(locationsService.addNPCToLocation(locationId, npcId));
            }
            log.warn("HTTP: location has this NPC (location id {} npc id {})", locationId, npcId);
            return ResponseEntity.ok(location);
        }
        log.error("HTTP: error with add npc to location");
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locations> updateLocation(@PathVariable Long id, @RequestBody Locations updateLocation) throws LocationNotFoundException {
        Optional<Locations> optionalLocation = locationsService.getLocationById(id);
        if (optionalLocation.isPresent()) {
            log.info("HTTP: update location: {}", updateLocation);
            return ResponseEntity.ok(locationsService.updateLocation(updateLocation, id));
        } else {
            log.error("HTTP: error with update location by id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Locations>> getAllLocations() {
        log.info("HTTP: get all locations");
        return ResponseEntity.ok(locationsService.getAllLocations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Locations>> findLocationById(@PathVariable Long id) throws LocationNotFoundException {
        Optional<Locations> optionalLocation = locationsService.getLocationById(id);
        if (optionalLocation.isPresent()) {
            log.info("HTTP: find location by id {}", id);
            return ResponseEntity.ok(optionalLocation);
        } else {
            log.error("HTTP: error with find location by id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long id) throws LocationNotFoundException {
        Optional<Locations> optionalLocation = locationsService.getLocationById(id);
        if (optionalLocation.isPresent()) {
            log.info("HTTP: delete location by id {}", id);
            locationsService.deleteLocationById(id);
            return ResponseEntity.ok("Location deleted successfully!");
        } else {
            log.error("HTTP: error with delete location by id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{locationId}/units/{unitId}")
    public ResponseEntity<Locations> removeUnitFromLocation(@PathVariable Long locationId, @PathVariable Long unitId) throws LocationNotFoundException, UnitNotFoundException {
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        Optional<Units> optionalUnits = unitsService.getUnitById(unitId);
        if (optionalLocations.isPresent() && optionalUnits.isPresent()) {
            Locations locations = optionalLocations.get();
            if (locations.getUnit().contains(optionalUnits.get())) {
                log.info("HTTP: remove unit from location (location id {} unit id {})", locationId, unitId);
                return ResponseEntity.ok(locationsService.removeUnitFromLocation(locationId, unitId));
            }
        }
        log.error("HTTP: error with remove unit from location (location id {} unit id {})", locationId, unitId);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{locationId}/magics/{magicId}")
    public ResponseEntity<Locations> removeMagicFromLocation(@PathVariable Long locationId, @PathVariable Long magicId) throws LocationNotFoundException, MagicNotFoundException {
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        Optional<Magics> optionalMagics = magicsService.getMagicById(magicId);
        if (optionalLocations.isPresent() && optionalMagics.isPresent()) {
            Locations locations = optionalLocations.get();
            if (locations.getMagic().contains(optionalMagics.get())) {
                log.info("HTTP: remove magic from location (location id {} magic id {})", locationId, magicId);
                return ResponseEntity.ok(locationsService.removeMagicFromLocation(locationId, magicId));
            }
        }
        log.error("HTTP: remove magic from location (location id {} magic id {})", locationId, magicId);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{locationId}/weapons/{weaponId}")
    public ResponseEntity<Locations> removeWeaponFromLocation(@PathVariable Long locationId, @PathVariable Long weaponId) throws LocationNotFoundException, WeaponNotFoundException {
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        if (optionalLocations.isPresent() && optionalWeapons.isPresent()) {
            Locations locations = optionalLocations.get();
            if (locations.getWeapon().contains(optionalWeapons.get())) {
                log.info("HTTP: remove weapon from location (location id {} weapon id {})", locationId, weaponId);
                return ResponseEntity.ok(locationsService.removeWeaponFromLocation(locationId, weaponId));
            }
        }
        log.error("HTTP: error remove weapon from location (location id {} weapon id {})", locationId, weaponId);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{locationId}/bosses/{bosseId}")
    public ResponseEntity<Locations> removeBossFromLocation(@PathVariable Long locationId, @PathVariable Long bosseId) throws BosseNotFoundException, LocationNotFoundException {
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        Optional<Bosses> optionalBosses = bossesService.getBosseById(bosseId);
        if (optionalLocations.isPresent() && optionalBosses.isPresent()) {
            Locations locations = optionalLocations.get();
            if (locations.getBoss().contains(optionalBosses.get())) {
                log.info("HTTP: remove boss from location (location id {} bosse id {})", locationId, bosseId);
                return ResponseEntity.ok(locationsService.removeBossFromLocation(locationId, bosseId));
            }
        }
        log.error("HTTP: error remove boss from location (location id {} bosse id {})", locationId, bosseId);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{locationId}/armors/{armorId}")
    public ResponseEntity<Locations> removeArmorFromLocation(@PathVariable Long locationId, @PathVariable Long armorId) throws ArmorNotFoundException, LocationNotFoundException {
        Optional<Armors> optionalArmors = armorsService.getArmorById(armorId);
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        if (optionalArmors.isPresent() && optionalLocations.isPresent()) {
            Locations location = optionalLocations.get();
            if (location.getArmor().equals(optionalArmors.get())) {
                log.info("HTTP: remove armor from location (location id {} armor id {})", locationId, armorId);
                return ResponseEntity.ok(locationsService.removeArmorFromLocation(locationId, armorId));
            }
        }
        log.error("HTTP: error remove armor from location (location id {} armor id {})", locationId, armorId);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{locationId}/npc/{npcId}")
    public ResponseEntity<Locations> removeNPCFromLocation(@PathVariable Long locationId, @PathVariable Long npcId) throws LocationNotFoundException, NPCNotFoundException {
        Optional<Locations> optionalLocation = locationsService.getLocationById(locationId);
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        if (optionalLocation.isPresent() && optionalNPC.isPresent()) {
            Locations location = optionalLocation.get();
            if (location.getNpc().contains(optionalNPC.get())) {
                log.info("HTTP: remove npc from location (location id {} npc id {})", locationId, npcId);
                return ResponseEntity.ok(locationsService.removeNPCFromLocation(locationId, npcId));
            }
        }
        log.error("HTTP: error remove npc from location (location id {} npc id {})", locationId, npcId);
        return ResponseEntity.notFound().build();
    }

}
