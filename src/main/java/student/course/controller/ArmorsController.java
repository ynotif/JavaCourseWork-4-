package student.course.controller;

import lombok.RequiredArgsConstructor;
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
public class ArmorsController {

    private final ArmorsService armorsService;

    @PostMapping
    public ResponseEntity<Armors> addArmor(@RequestBody Armors armor) {
        return ResponseEntity.ok(armorsService.createArmor(armor));
    }

    @PutMapping("/{updateById}")
    public ResponseEntity<Armors> updateArmor(@RequestBody Armors armorUpdate, @PathVariable Long updateById) throws ArmorNotFoundException {
        Optional<Armors> optionalArmors = armorsService.getArmorById(updateById);

        if (optionalArmors.isPresent()) {

            armorsService.updateArmor(armorUpdate, updateById);

            armorUpdate.setArmorId(updateById);

            return ResponseEntity.ok(armorUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Armors>> getAllArmors() {
        return ResponseEntity.ok(armorsService.getAllArmors());
    }

    @GetMapping("/{getById}")
    public ResponseEntity<Optional<Armors>> getArmorById(@PathVariable Long getById) throws ArmorNotFoundException {
        return ResponseEntity.ok(armorsService.getArmorById(getById));
    }

    @DeleteMapping("/{DeleteById}")
    public ResponseEntity<String> deleteArmor(@PathVariable Long DeleteById) throws ArmorNotFoundException {
        armorsService.deleteArmorById(DeleteById);
        return ResponseEntity.ok("Armor deleted successfully!");
    }
}
