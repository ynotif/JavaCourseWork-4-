package student.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Souls> addSouls(@RequestBody Souls soul) {
        return ResponseEntity.ok(soulsService.createSoul(soul));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Souls> updateSouls(@PathVariable Long id, @RequestBody Souls updatedSoul) {
        Optional<Souls> souls = soulsService.getSoulById(id);
        if (souls.isPresent()) {
            soulsService.updateSoul(updatedSoul);
            return ResponseEntity.ok(updatedSoul);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Souls>> getAllSouls() {
        return ResponseEntity.ok(soulsService.getAllSouls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Souls>> getSoulById(@PathVariable Long id) {
        return ResponseEntity.ok(soulsService.getSoulById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSoulById(@PathVariable Long id) {
        Optional<Souls> souls = soulsService.getSoulById(id);
        if (souls.isPresent()) {
            soulsService.deleteSoulById(id);
            return ResponseEntity.ok("Soul deleted successfully!");
        }
        return ResponseEntity.notFound().build();
    }

}
