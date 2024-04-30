package student.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.model.Weapons;
import student.course.service.WeaponsService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/weapons")
public class WeaponsController {

    private final WeaponsService weaponsService;

    @PostMapping
    public ResponseEntity<Weapons> createWeapons(@RequestBody Weapons weapon) {
        return ResponseEntity.ok(weaponsService.createWeapon(weapon));
    }

    @GetMapping
    public ResponseEntity<List<Weapons>> getAllWeapons() {
       return ResponseEntity.ok(weaponsService.getAllWeapons());
    }

    @GetMapping("{/id}")
    public ResponseEntity<Optional<Weapons>> getWeaponById(@PathVariable Long id) {
        return ResponseEntity.ok(weaponsService.getWeaponById(id));
    }

}
