package student.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.exceptions.SoulNotFoundException;
import student.course.model.Souls;
import student.course.service.SoulsService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/souls")
@RestController
public class SoulsController {

    private final SoulsService soulsService;


    @PostMapping
    ResponseEntity<Souls> addSouls(@RequestBody Souls soul) {
        return ResponseEntity.ok(soulsService.createSoul(soul));
    }

    @PutMapping("/{id}")
    ResponseEntity<Souls> updateSouls(@PathVariable Long id, @RequestBody Souls updatedSoul) throws SoulNotFoundException {
        Optional<Souls> souls = soulsService.getSoulById(id);
        if (souls.isPresent()) {
            soulsService.updateSoul(updatedSoul, id);
            return ResponseEntity.ok(updatedSoul);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    ResponseEntity<List<Souls>> getAllSouls() {
        return ResponseEntity.ok(soulsService.getAllSouls());
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<Souls>> getSoulById(@PathVariable Long id) throws SoulNotFoundException {
        return ResponseEntity.ok(soulsService.getSoulById(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteSoulById(@PathVariable Long id) throws SoulNotFoundException {
        Optional<Souls> souls = soulsService.getSoulById(id);
        if (souls.isPresent()) {
            soulsService.deleteSoulById(id);
            return ResponseEntity.ok("Soul deleted successfully!");
        }
        return ResponseEntity.notFound().build();
    }

}
