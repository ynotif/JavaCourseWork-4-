package student.course.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SoulsController {

    private final SoulsService soulsService;


    @PostMapping
    ResponseEntity<Souls> addSouls(@RequestBody Souls soul) {
        log.info("HTTP: add soul");
        return ResponseEntity.ok(soulsService.createSoul(soul));
    }

    @PutMapping("/{id}")
    ResponseEntity<Souls> updateSouls(@PathVariable Long id, @RequestBody Souls updatedSoul) throws SoulNotFoundException {
        Optional<Souls> souls = soulsService.getSoulById(id);
        if (souls.isPresent()) {

            log.info("HTTP: update soul by id: {}", id);
            return ResponseEntity.ok(soulsService.updateSoul(updatedSoul, id));
        }
        log.error("HTTP: soul not found for update by id: {}", id);
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    ResponseEntity<List<Souls>> getAllSouls() {
        log.info("HTTP: get all souls");
        return ResponseEntity.ok(soulsService.getAllSouls());
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<Souls>> getSoulById(@PathVariable Long id) throws SoulNotFoundException {
        Optional<Souls> souls = soulsService.getSoulById(id);
        if (souls.isPresent()) {
            log.info("HTTP: get soul by id: {}", id);
            return ResponseEntity.ok(souls);
        }
        log.error("HTTP: soul not found for get by id: {}", id);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteSoulById(@PathVariable Long id) throws SoulNotFoundException {
        Optional<Souls> souls = soulsService.getSoulById(id);
        if (souls.isPresent()) {
            soulsService.deleteSoulById(id);
            log.info("HTTP: delete soul by id: {}", id);
            return ResponseEntity.ok("Soul deleted successfully!");
        }
        log.error("HTTP: soul not found for delete by id: {}", id);
        return ResponseEntity.notFound().build();
    }

}
