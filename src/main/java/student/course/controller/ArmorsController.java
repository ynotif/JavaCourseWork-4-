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

    @GetMapping
    public ResponseEntity<List<Armors>> getAllArmors() {
        return ResponseEntity.ok(armorsService.getAllArmors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Armors>> getArmorById(@PathVariable Long id) {
        return ResponseEntity.ok(armorsService.getArmorById(id));
    }

}
