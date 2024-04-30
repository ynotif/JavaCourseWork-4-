package student.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.model.Locations;
import student.course.service.LocationsService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/locations")
public class LocationsController {

    private final LocationsService locationsService;

    @PostMapping
    public ResponseEntity<Locations> addLocation(@RequestBody Locations location) {
        return ResponseEntity.ok(locationsService.createLocation(location));
    }

    @GetMapping
    public ResponseEntity<List<Locations>> getAllLocations(){
        return ResponseEntity.ok(locationsService.getAllLocations());
    }

    @GetMapping("{/id}")
    public ResponseEntity<Optional<Locations>> findLocationById(@PathVariable Long id){
        return ResponseEntity.ok(locationsService.getLocationById(id));
    }

}
