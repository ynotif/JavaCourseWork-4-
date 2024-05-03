package student.course.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.bdsetters.LocationSetter;
import student.course.model.Armors;
import student.course.model.Bosses;
import student.course.model.Locations;
import student.course.repository.LocationsRepository;
import student.course.service.ArmorsService;
import student.course.service.BossesService;
import student.course.service.LocationsService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/locations")
public class LocationsController {

    private final LocationsService locationsService;
    private final LocationSetter locationSetter = new LocationSetter();
    private final ArmorsService armorsService;
    private final BossesService bossesService;

    @PostMapping
    public ResponseEntity<Locations> addLocation(@RequestBody Locations location) {
        return ResponseEntity.ok(locationsService.createLocation(location));
    }

    @PostMapping("/{locationId}/armors/{armorId}")
    public ResponseEntity<Locations> addArmorToLocation(@PathVariable Long locationId, @PathVariable Long armorId) {
        Optional<Armors> optionalArmor = armorsService.getArmorById(armorId);
        Optional<Locations> optionalLocation = locationsService.getLocationById(locationId);
        if (optionalArmor.isPresent() && optionalLocation.isPresent()) {
            return ResponseEntity.ok(locationsService.addArmorToLocation(locationId, armorId));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{locationId}/bosses/{bosseId}")
    public ResponseEntity<Locations> addBossToLocation(@PathVariable Long locationId, @PathVariable Long bosseId) {
        Optional<Locations> optionalLocation = locationsService.getLocationById(locationId);
        Optional<Bosses> optionalBosses = bossesService.getBosseById(bosseId);
        if (optionalLocation.isPresent() && optionalBosses.isPresent()) {
            return ResponseEntity.ok(locationsService.addBossToLocation(locationId, bosseId));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locations> updateLocation(@PathVariable Long id, @RequestBody Locations updateLocation) {
        Optional<Locations> optionalLocation = locationsService.getLocationById(id);
        if (optionalLocation.isPresent()) {
            Locations location = optionalLocation.get();

            locationSetter.update(location, updateLocation);

            locationsService.updateLocation(location);

            return ResponseEntity.ok(location);
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
    public ResponseEntity<Optional<Locations>> findLocationById(@PathVariable Long id){
        return ResponseEntity.ok(locationsService.getLocationById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long id){
        Optional<Locations> optionalLocation = locationsService.getLocationById(id);
        if (optionalLocation.isPresent()) {
            locationsService.deleteLocationById(id);
            return ResponseEntity.ok("Location deleted successfully!");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{locationId}/armors/{armorId}")
    public ResponseEntity<Locations> removeArmorFromLocation(@PathVariable Long locationId, @PathVariable Long armorId) {
        Optional<Armors> optionalArmors = armorsService.getArmorById(armorId);
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        if (optionalArmors.isPresent() && optionalLocations.isPresent()) {
            return ResponseEntity.ok(locationsService.removeArmorFromLocation(locationId, armorId));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{locationId}/bosses/{bosseId}")
    public ResponseEntity<Locations> removeBossFromLocation(@PathVariable Long locationId, @PathVariable Long bosseId) {
        Optional<Locations> optionalLocations = locationsService.getLocationById(locationId);
        Optional<Bosses> optionalBosses = bossesService.getBosseById(bosseId);
        if(optionalLocations.isPresent() && optionalBosses.isPresent()) {
            return ResponseEntity.ok(locationsService.removeBossFromLocation(locationId, bosseId));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
