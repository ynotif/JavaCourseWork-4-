package student.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.model.Armors;
import student.course.service.ArmorsService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/armors")
@RestController
public class ArmorsController {

    private final ArmorsService armorsService;

    @PostMapping
    public ResponseEntity<Armors> addArmor(@RequestBody Armors armor) {
        return ResponseEntity.ok(armorsService.createArmor(armor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Armors> updateArmor(@RequestBody Armors armorUpdate, @PathVariable Long id) {
        Optional<Armors> optionalArmors = armorsService.getArmorById(id);

        if (optionalArmors.isPresent()) {

            armorsService.updateArmor(armorUpdate);

            return ResponseEntity.ok(armorUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Armors>> getAllArmors() {
        return ResponseEntity.ok(armorsService.getAllArmors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Armors>> getArmorById(@PathVariable Long id) {
        return ResponseEntity.ok(armorsService.getArmorById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArmor(@PathVariable Long id) {
        armorsService.deleteArmorById(id);
        return ResponseEntity.ok("Armor deleted successfully!");
    }
}
