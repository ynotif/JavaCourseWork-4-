package student.course.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.exceptions.ArmorNotFoundException;
import student.course.model.Armors;
import student.course.service.ArmorsService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/armors")
@RestController
@Slf4j
public class ArmorsController {

    private final ArmorsService armorsService;

    @PostMapping
    public ResponseEntity<Armors> addArmor(@RequestBody Armors armor) {
        log.info("HTTP: Add armor: {}", armor);
        return ResponseEntity.ok(armorsService.createArmor(armor));
    }

    @PutMapping("/{updateById}")
    public ResponseEntity<Armors> updateArmor(@RequestBody Armors armorUpdate, @PathVariable Long updateById) throws ArmorNotFoundException {
        Optional<Armors> optionalArmors = armorsService.getArmorById(updateById);

        if (optionalArmors.isPresent()) {

            armorsService.updateArmor(armorUpdate, updateById);

            armorUpdate.setArmorId(updateById);

            log.info("HTTP: update armor: {}", armorUpdate);
            return ResponseEntity.ok(armorUpdate);
        } else {
            log.error("HTTP: armor with id {} not found", updateById);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Armors>> getAllArmors() {
        log.info("HTTP: getAllArmors");
        return ResponseEntity.ok(armorsService.getAllArmors());
    }

    @GetMapping("/{getById}")
    public ResponseEntity<Optional<Armors>> getArmorById(@PathVariable Long getById) throws ArmorNotFoundException {
        log.info("HTTP: getArmorById: {}", getById);
        return ResponseEntity.ok(armorsService.getArmorById(getById));
    }

    @DeleteMapping("/{deleteById}")
    public ResponseEntity<String> deleteArmor(@PathVariable Long deleteById) throws ArmorNotFoundException {
        armorsService.deleteArmorById(deleteById);
        log.info("HTTP: delete armor: {}", deleteById);
        return ResponseEntity.ok("Armor deleted successfully!");
    }
}
